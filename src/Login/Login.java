package Login;

import java.net.*;
import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {
    public JTextField jtf = new JTextField();

    // Text area to display contents
    public  JTextArea jta = new JTextArea();
    public static ResultSet rs;
    public static String stud_id = "";

    public JButton login_but = new JButton("Login");
    private Socket socket;
    public static boolean loggedIn = false;
    public static int id;


    public static void main(String[] args) {
        new Login();

//        try {
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assign1", "root", "");
//            System.out.println("HELLO2");
//            Socket socket = new Socket("localhost", 8000);
//            Statement st = con.createStatement();
//            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
//            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
//            System.out.println("HELLO1");
//            int id = inputFromClient.readInt();
//            System.out.println(id);
//            System.out.println("HELLO0");
//
//            // System.out.println(radius);
//            rs = st.executeQuery("select * from students WHERE STUD_ID = '" + id + "'");
//            if (rs.next()) {
//                id = rs.getInt("STUD_ID");
//                outputToClient.writeUTF(rs.getString("FNAME"));
//                outputToClient.flush();
//                st.executeUpdate("update students set TOT_REQ=TOT_REQ+1 where STUD_ID='" + id + "'");
//                jta.append("Server.Server Processing...\n " + " just connected " + " hostname: ");
//                loggedIn = true;
//                System.out.println("HELLO3");
//
//            } else {
//                loggedIn = false;
//                outputToClient.writeUTF("false");
//                outputToClient.flush();
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public JPanel getLoginView()  {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(new JLabel("Enter ID"), BorderLayout.WEST);
        p.add(jtf, BorderLayout.CENTER);
        p.add(login_but, BorderLayout.WEST);
        jtf.setHorizontalAlignment(JTextField.RIGHT);

        setLayout(new BorderLayout());
        add(p, BorderLayout.NORTH);
        add(new JScrollPane(jta), BorderLayout.CENTER);

        //  jtf.addActionListener(new Login.Login.Listener()); // Register listener

//        setTitle("Login??");
//        setSize(300, 200);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true); // It is necessary to show the frame here!

        return  p ;
    }


       //   login_but.addActionListener(new Listener());

//        try {
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assign1", "root", "");
//            System.out.println("HELLO2");
//            Socket socket = new Socket("localhost", 8000);
//            Statement st = con.createStatement();
//            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
//            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
//            System.out.println("HELLO1");
//            while (true) {
//                int id = inputFromClient.readInt();
//                System.out.println("HELLO0");
//                System.out.println(id);
//
//
//                rs = st.executeQuery("select * from students WHERE STUD_ID = '" + id + "'");
//                if (rs.next()) {
//                    id = rs.getInt("STUD_ID");
//                    outputToClient.writeUTF(rs.getString("FNAME"));
//                    outputToClient.flush();
//                    st.executeUpdate("update students set TOT_REQ=TOT_REQ+1 where STUD_ID='" + id + "'");
//                    jta.append("Server.Server Processing...\n " + " just connected " + " hostname: ");
//                    loggedIn = true;
//                    System.out.println("HELLO3");
//
//                } else {
//                    loggedIn = false;
//                    outputToClient.writeUTF("false");
//                    outputToClient.flush();
//                }
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }


//    private class Listener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            try {
//                if (loggedIn = true) {
//                    //start client server
//                } else {
//
//                }
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//    }
}
