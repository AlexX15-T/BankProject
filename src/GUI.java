
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.*;


public class GUI extends JFrame implements ActionListener {

    private static JLabel userLabel = new JLabel("Username");
    private static JTextField userText = new JTextField();
    private static JLabel passwordLabel = new JLabel("Password");
    private static JPasswordField passwordText = new JPasswordField();
    private static JButton button = new JButton("Login");
    private static JLabel loginText = new JLabel("");

    private final static String USERNAME = "root";
    private final static String PASSWORD = "qwer1234";
    private final static String DB_NAME = "banca";
    private final static String CONNECTION_LINK = "jdbc:mysql://localhost:3306/";


    JPanel panel = new JPanel();
    JFrame frame = new JFrame();

    public GUI () {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(550,550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);

        frame.setTitle("Meniu");

        JLabel titlu = new JLabel("BAZA DE DATE BANCA");
        titlu.setBounds(200,10,130,125);
        panel.add(titlu);

        userLabel.setBounds(30,100,80,25);
        panel.add(userLabel);


        userText.setBounds(100, 100, 165, 25);
        panel.add(userText);


        passwordLabel.setBounds(30,130,80,25);
        panel.add(passwordLabel);


        passwordText.setBounds(100, 130 , 165, 25);
        panel.add(passwordText);


        button.setBounds(100,165,80,25);
        button.addActionListener(this);
        panel.add(button);


        loginText.setBounds(10,185,300,25);
        panel.add(loginText);

        frame.setBackground(Color.BLUE);
        panel.setBackground(Color.CYAN);

        frame.setVisible(true);

    }

    public Connection returnCon() throws Exception {

        Connection con1 =  DriverManager.getConnection(CONNECTION_LINK + DB_NAME, USERNAME, PASSWORD); ;

        try {

            Connection con = DriverManager.getConnection(CONNECTION_LINK + DB_NAME, USERNAME, PASSWORD);
            return con;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return con1;
    }


    public void actionPerformed(java.awt.event.ActionEvent evt){
        try{

            String tip ="";

            Connection con = DriverManager.getConnection(CONNECTION_LINK + DB_NAME, USERNAME, PASSWORD);
            String username = userText.getText();
            String password = passwordText.getText();
            Statement stm = con.createStatement();
            String sql = "select * from user_ where username='"+username+"'and password1 ='"+password+"'";
            ResultSet rs = stm.executeQuery(sql);

            if(rs.next()){

                tip = rs.getString("tip_utilizator");
                dispose();
            }

            if (username.equals("admin"))
                new AdminFrame();

            if(tip.equals("angajat"))
            {
                dispose();
                new HomeFrameAngajat(username, con);
                panel.setVisible(false);
            }

            if(tip.equals("client"))
            {
                new ClientFrame(username,con).setVisible(true);
                panel.setVisible(false);
                dispose();
            }

            //continuare celalalte doua interfete
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main ( String [] args ){
        new GUI();
    }

}