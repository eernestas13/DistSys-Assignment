package Server;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

public class Server extends JFrame {
    // Text area for displaying contents
    private JTextArea jta = new JTextArea();
    public static ResultSet rs;
    public static boolean loggedIn = true;
    //^ was public static
    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        // Place text area on the frame
        setLayout(new BorderLayout());
        add(new JScrollPane(jta), BorderLayout.CENTER);

        setTitle("Server");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // It is necessary to show the frame here!


        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            jta.append("Server.Server started at " + new Date() + '\n');
            System.out.println("Server.Server Started");
            while (true) {
                // Listen for a connection request
                Socket socket = serverSocket.accept();
                // Connect to a client Thread

                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());


                ThreadClass thread = new ThreadClass(socket , inputFromClient  , outputToClient);
                thread.start();
            }

        }
        catch(IOException ex) {
            System.err.println(ex);
        }
    }

    private class ThreadClass extends Thread {
        private Socket socket;
        private InetAddress address;
        private DataInputStream inputFromClient;
        private DataOutputStream outputToClient;





        public ThreadClass(Socket socket , DataInputStream dataInputStream , DataOutputStream dataOutputStream) throws IOException {
            this.socket = socket;
            this.outputToClient = dataOutputStream;
            this.inputFromClient = dataInputStream;
            address = socket.getInetAddress();

            try {
                while (loggedIn == false) {

                    System.out.println("TRYING TO LOG IN");
                    //start client server
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assign1", "root", "");
                    Statement st = con.createStatement();
                    int id = inputFromClient.readInt();
                    System.out.println("User Input Read");
                    rs = st.executeQuery("select * from students WHERE STUD_ID = '" + id + "'");
                    if (rs.next()) {
                        id = rs.getInt("STUD_ID");
                        outputToClient.writeUTF(rs.getString("FNAME"));
                        outputToClient.flush();
                        st.executeUpdate("update students set TOT_REQ=TOT_REQ+1 where STUD_ID='" + id + "'");
                      //  jta.append("Server.Server Processing...\n " + " just connected " + " hostname: ");
                        loggedIn = true;
                        jta.append("User Logged In" + '\n');
                        System.out.println("Logged In");

                    } else {
                        loggedIn = false;
                       // outputToClient.writeUTF("false");
                        outputToClient.flush();
                        jta.append("User Login Failed"+ '\n');
                    }
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }


        }



        public void run() {
            try {
                    while (true) {
                        System.out.println("Calculating..");
                        // Receive radius from the client
                        double radius = inputFromClient.readDouble();
                        // Compute area
                        double area = radius * radius * Math.PI;

                        // Send area back to the client
                        outputToClient.writeDouble(area);

                        jta.append("Radius received from client: " + radius + '\n');
                        jta.append("Area found: " + area + '\n');
                    }
            } catch(Exception e) {
                System.err.println(e + "on " + socket);
                e.printStackTrace();
            }
        }
    }



}

