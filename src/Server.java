import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import Login.Login;

public class Server extends JFrame {
    // Text area for displaying contents
    private JTextArea jta = new JTextArea();
    public static ResultSet rs;

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
            jta.append("Server started at " + new Date() + '\n');
            System.out.println("HELLO3");


            while (true) {
                // Listen for a connection request
                Socket socket = serverSocket.accept();
                // Connect to a client Thread
                ThreadClass thread = new ThreadClass(socket);
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

        public ThreadClass(Socket socket) throws IOException {
            this.socket = socket;
            this.outputToClient = new DataOutputStream(socket.getOutputStream());
            this.inputFromClient = new DataInputStream(socket.getInputStream());
            address = socket.getInetAddress();

            try {
                if (loggedIn == false) {

                    System.out.println("TRYING TO LOG IN");
                    //start client server
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assign1", "root", "");
                    Statement st = con.createStatement();

                    System.out.println("HELLO011111111");

                    int id = inputFromClient.readInt();
                    System.out.println("HELLO0111");
                    rs = st.executeQuery("select * from students WHERE STUD_ID = '" + id + "'");
                    if (rs.next()) {
                        id = rs.getInt("STUD_ID");
                        outputToClient.writeUTF(rs.getString("FNAME"));
                        outputToClient.flush();
                        st.executeUpdate("update students set TOT_REQ=TOT_REQ+1 where STUD_ID='" + id + "'");
                        jta.append("Server Processing...\n " + " just connected " + " hostname: ");
                        loggedIn = true;
                        System.out.println("HELLO333333");

                    } else {
                        loggedIn = false;
                        outputToClient.writeUTF("false");
                        outputToClient.flush();
                    }
                } else {
                    System.out.println("ELSE");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }


        }



        public void run() {
            try {
                while (true) {

                    System.out.println("HELLO");
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

