import com.mysql.cj.jdbc.CallableStatement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Contabilitate extends JFrame{
    private JButton exitButton;
    private JButton logOutButton;
    private JButton backButton;
    private JPanel Contabilitate;
    private JButton aprobaButton;
    private JButton aprobaButton1;
    private JComboBox comboBox1;

    public Contabilitate(String username, Connection con){

        setContentPane(Contabilitate);
        setTitle("Contabilitate");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,600);

        comboBox1.removeAllItems();

        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select id from tranzactie_cont where Status_ = 'CREATED'" );
            while(rs.next()){
                comboBox1.addItem(rs.getString("id"));
            }
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new GUI();

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new HomeFrameAngajat(username,con);

            }
        });

        aprobaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try(
                        Statement stmt = con.createStatement();
                        ){

                    String sql = "UPDATE eliberare_card" + " SET aprobare_angajat = 'aprobat'";
                    stmt.executeUpdate(sql);
                }
                catch(SQLException exception) {
                    exception.printStackTrace();}

            }
        });

        aprobaButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s1 = new String();
                String s2 = new String();
                float f ;
                String query = "{CALL Transfer1(?,?,?)}";
                ResultSet rs2;

                String id = comboBox1.getSelectedItem().toString();
                Statement statement = null;
                try {
                    statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("select * from tranzactie_cont where id = "  + "'" + id + "'" );
                    if(rs.next()){
                        s1 = rs.getString("cont_sursa_iban");
                        s2 = rs.getString("cont_destinatie_iban");
                        f = rs.getFloat("valoare");

                        CallableStatement stmt = (CallableStatement) con.prepareCall(query);

                        stmt.setString(1,s2);
                        stmt.setString(2,s1);
                        stmt.setFloat(3,f);

                        rs2 = stmt.executeQuery();
                        if(rs.next()){
                           System.out.println("SUCCES");
                        }

                    }
                    rs.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

    }




}
