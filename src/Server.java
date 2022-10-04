import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Server extends JFrame {
    // Text area for displaying contents
    private JTextArea jta = new JTextArea();

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
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

            }catch (IOException e) {
                System.err.println("Exception in class");
                e.printStackTrace();
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

