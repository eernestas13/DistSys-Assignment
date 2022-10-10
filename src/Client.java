import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static Server.Server.loggedIn;


public class Client extends JFrame {
    // Text field for receiving radius
    private final JTextField jtf = new JTextField();
    // Text area to display contents
    private final JTextArea jta = new JTextArea();
    Login login = new Login();

    // IO streams
    private DataOutputStream toServer;
    private DataInputStream fromServer;

    public JFrame loginWindow;
    public JPanel clientWindow;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        boolean validation = loggedIn;
        try {
            System.out.println(loggedIn);

        JFrame loginWindow = new JFrame();

        login.getLoginView();

        loginWindow.add(login.getLoginView());
        loginWindow.setTitle("Login");
        loginWindow.setSize(300, 200);
        loginWindow.setVisible(true);
        loginWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        login.login_but.addActionListener(new Listener());
try {
    if (validation == true) {
        JPanel clientWindow = new JPanel();
        clientWindow.setLayout(new BorderLayout());
        clientWindow.add(new JLabel("Enter radius"), BorderLayout.WEST);
        clientWindow.add(jtf, BorderLayout.CENTER);
        jtf.setHorizontalAlignment(JTextField.RIGHT);
        setLayout(new BorderLayout());
        add(clientWindow, BorderLayout.NORTH);
        add(new JScrollPane(jta), BorderLayout.CENTER);
        setTitle("Client");
        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jtf.addActionListener(new Listener2());

        System.out.println("CLEINTTTTTTTT");
    }        //LISTENS TO BOTH WINDOWS

} catch (Exception e) {
    throw new RuntimeException(e);
}
        } catch (Exception e) {
    throw new RuntimeException(e);
}
        System.out.println("opening soon");
        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);
            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());
            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            jta.append(ex.toString() + '\n');
        }
        jta.append("Connected to Server");

    }

    public class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                toServer.writeInt(Integer.parseInt(login.jtf.getText()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }


    private class Listener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                try {
                    toServer.flush();
                    // Get the radius from the text field
                    double radius = Double.parseDouble(jtf.getText().trim());

                    // Send the radius to the server
                    toServer.writeDouble(radius);
                    toServer.flush();

                    // Get area from the server
                    double area = fromServer.readDouble();

                    // Display to the text area
                    jta.append("Radius is " + radius + "\n");
                    jta.append("Area received from the server is: "
                            + area + '\n');
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
