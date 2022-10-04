import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.util.Date;

public class Retrieve {
    public static ResultSet rs;
    public static String name = "";
    public static String email = "";

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JLabel labell = new JLabel("Name: ");
        JLabel labe12 = new JLabel("Email: ");

        JButton prev_but = new JButton("Prev");
        JButton next_but = new JButton("Next ");

        JTextField text1 = new JTextField(20);
        JTextField text2 = new JTextField(20);


        try {
            System.out.println("hello");
            //  Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assign1", "root", "");
            System.out.println("hello1");


            Statement st = con.createStatement();
            rs = st.executeQuery("select * from students");

            if (rs.next()) {
                name = rs.getString("fname");
                email = rs.getString("sname");
                text1.setText(name);
                text2.setText(email);
            }
            System.out.println("hello2");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

            JPanel p = new JPanel(new GridLayout(3, 2));
            System.out.println("hello3");
            p.add(labell);
            p.add(text1);
            p.add(labe12);
            p.add(text2);
            p.add(prev_but);
            p.add(next_but);
            f.add(p);
            f.setVisible(true);
            f.pack();
            //       Add NEXT Button Anonymous Class
            p.add(next_but);
            next_but.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        if (rs.next()) {
                            name = rs.getString("FNAME");
                            email = rs.getString("SNAME");
                            text1.setText(name);
                            text2.setText(email);
                        }
                    } catch (SQLException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            p.add(prev_but);
            next_but.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        if (rs.previous()) {
                            name = rs.getString("FNAME");
                            email = rs.getString("SNAME");
                            text1.setText(name);
                            text2.setText(email);
                        }
                    } catch (SQLException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }
















//
//import java.awt.*;
//import java.sql.*;
//import javax.swing.*;
//
//public class Retrieve
//{
//    public static ResultSet rs;
//    public static String name="", email ="";
//    public static void main(String[] args){
//        JFrame f=new JFrame();
//        JLabel labell = new JLabel("Name: ");
//        JLabel labe12 = new JLabel("Email: ");
//        JTextField textl = new JTextField(20);
//        JTextField text2=new JTextField(20);
//        JButton nextB = new JButton("Next");
//        JButton prevB = new JButton("Prev");
//        try{
//            System.out.println("hello");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_create_db", "root", "");
//            System.out.println("hello1");
//            Statement st=con.createStatement();
//            rs=st.executeQuery("select from data");
//            if(rs.next()){
//                name=rs.getString("name");
//                email=rs.getString("email");
//                textl.setText(name);
//                text2.setText(email);
//            }
//        }catch(Exception e){}
//        JPanel p=new JPanel(new GridLayout(3,2));
//        p.add(labell);
//        p.add(textl);
//        p.add(labe12);
//        p.add(text2);
//        p.add(nextB);
//        p.add(prevB);
//
//        f.add(p);
//        f.setVisible(true);
//        f.pack();
//
//
//
//
//    }
//
//
//
//
//}