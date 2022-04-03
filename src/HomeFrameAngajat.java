import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class HomeFrameAngajat extends JFrame {

    JLabel message;
    Container container = getContentPane();

    private JTextField welcomeText;
    private JButton jcomp6;
    private JButton departament;
    private JButton Logout;
    private String numeUser;


    public HomeFrameAngajat( String username, Connection con ) {
        message = new JLabel("You are successfully logged in, " + username + " !");

        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment(JLabel.CENTER);

        container.setLayout(new GridLayout(4,2,0,0));

        jcomp6 = new JButton ("Iesire");
        departament = new JButton("Departament");
        Logout = new JButton("Log Out");

        this.setTitle("");
        this.setVisible(true);
        this.setBounds(500, 500, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setBounds();
        addComponents();

        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });

        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new GUI();

            }
        });


        departament.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Statement statement = null;
                try {
                    statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("select nume_departament from angajat where nume = "+"'"+ username+"'");
                    while(rs.next())
                    {
                        String x = rs.getString("nume_departament");
                        if(x.equals("IT")){
                            dispose();
                            new IT(username,con);
                        }
                        else if (x.equals("Asistenta Clienti")){
                            dispose();
                            new AsistentaClienti(username,con);
                        }
                        else if(x.equals("Contabilitate")){
                            dispose();
                            new Contabilitate(username,con);
                        }

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });

    }

    public void setBounds() {
        message.setBounds(10, 10, 400, 30);

    }

    public void addComponents() {
        container.add(message);
        //container.add(jcomp2);
        //container.add(jcomp3);
        //container.add(jcomp4);
        //container.add(jcomp5);

        container.add(departament);
        container.add(Logout);
        container.add(jcomp6);

    }



}