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

    boolean validation = false;
    public JFrame loginWindow;
    public JPanel clientWindow;

    public static void main(String[] args) throws IOException {
        new Client();
    }

    public Client() throws IOException {
        // Login Window parameters
        // Connect to a client Thread

        Socket socket = new Socket("localhost", 8000);

        fromServer = new DataInputStream(socket.getInputStream());
        toServer = new DataOutputStream(socket.getOutputStream());

        JFrame loginWindow = new JFrame();

        login.getLoginView();

        loginWindow.add(login.getLoginView());
        loginWindow.setTitle("Login");
        loginWindow.setSize(300, 200);
        loginWindow.setVisible(true);
        loginWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        login.login_but.addActionListener(new LoginListener());

        //loggedIn coming from Server Class, if user is logged in = true , vise versa


        try {

            while (true) {

                if (!validation) {
                    validation = fromServer.readBoolean();
                    System.out.println(validation);

                }
                if (validation == true) {
                    loginWindow.dispose();
                    break;
                }
            }

            // if logged in = true, calculator is opened
            if (validation == true) {

                // Calculator window parameters
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
                jtf.addActionListener(new CalculatorListener());
            }

            if (validation) {
                while (true) {
                    // Get area from the server
                    double area123;
                    area123 = fromServer.readDouble();
                    // Display to the text area
                    jta.append("Area received from the server is: "
                            + area123 + '\n');
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public class LoginListener implements ActionListener {
        //This Listener gets the users input for the login stage
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                toServer.writeInt(Integer.parseInt(login.jtf.getText()));

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    private class CalculatorListener implements ActionListener {
        //This Listener gets the users input for the login stage
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Get the radius from the text field
                double radius = Double.parseDouble(jtf.getText().trim());
                jta.append("Radius is " + radius + "\n");

                // Send the radius to the server
                toServer.writeDouble(radius);
                toServer.flush();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}