package Views;

import java.net.*;
import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {
    public JTextField jtf = new JTextField();
    public  JTextArea jta = new JTextArea();
    public JButton login_but = new JButton("Login");


    public static void main(String[] args) {
        new Login();

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
        return  p ;
    }


}
