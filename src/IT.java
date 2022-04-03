import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IT extends JFrame{
    private JPanel IT;
    private JButton exitButton;
    private JButton logOutButton;
    private JButton backButton;
    private JComboBox NumeClienti;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JButton afisareInformatiiClientiButton;
    private JComboBox NumeAngajati;
    private JTextField textField12;
    private JButton afisareInformatiiAngajatiButton;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JTextField textField18;
    private JTextField textField19;
    private JTextField textField20;
    private JTextField textField21;
    private JTextField textField22;
    private JButton updateButton;

    public IT(String username, Connection con) {

        setContentPane(IT);
        setTitle("IT");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,600);

        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select nume from client_");
            while(rs.next()){
                String x = rs.getString("nume");
                NumeClienti.addItem(x);
            }
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Statement statement1 = null;
        try {
            statement1 = con.createStatement();
            ResultSet rs = statement.executeQuery("select nume from angajat");
            while(rs.next()){
                String x = rs.getString("nume");
                NumeAngajati.addItem(x);
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

        final String[] x2 = new String[1];
        afisareInformatiiAngajatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nume =  NumeAngajati.getSelectedItem().toString();
                Statement statement = null;
                try {
                    statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("select * from angajat where nume = " + "'" + nume + "'");

                    while(rs.next()){
                        String x1 = rs.getString("nume");
                        x2[0] = rs.getString("CNP");
                        String x3 = rs.getString("adresa");
                        String x4 = rs.getString("nr_telefon");
                        String x5 = rs.getString("email");

                        String x7 = rs.getString("nr_contract");
                        String x8 = rs.getString("norma");
                        String x9 = rs.getString("salariu");
                        String x10 = rs.getString("sucursala");
                        String x11 = rs.getString("nume_departament");
                        textField12.setText(x1);
                        textField13.setText(x2[0]);
                        textField14.setText(x3);
                        textField15.setText(x4);
                        textField16.setText(x5);

                        textField18.setText(x7);
                        textField19.setText(x8);
                        textField20.setText(x9);
                        textField21.setText(x10);
                        textField22.setText(x11);

                    }
                    rs.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        //final String[] x2 = new String[1];
        afisareInformatiiClientiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String nume =  NumeClienti.getSelectedItem().toString();
                Statement statement = null;
                try {
                    statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("select * from client_ where nume = " + "'" + nume + "'");

                    while(rs.next()){
                        String x1 = rs.getString("nume");
                        x2[0] = rs.getString("CNP");
                        String x3 = rs.getString("adresa");
                        String x4 = rs.getString("nr_telefon");
                        String x5 = rs.getString("email");
                        String x6 = rs.getString("cont_iban");
                        String x7 = rs.getString("nr_contract");
                        String x8 = rs.getString("data_nasterii");
                        String x9 = rs.getString("sursa_venit");
                        String x10 = rs.getString("tranz_online");
                        String x11 = rs.getString("nr_conturi");
                        textField1.setText(x1);
                        textField2.setText(x2[0]);
                        textField3.setText(x8);
                        textField4.setText(x3);
                        textField5.setText(x4);
                        textField6.setText(x5);
                        textField7.setText(x11);
                        textField8.setText(x6);
                        textField9.setText(x7);
                        textField10.setText(x9);
                        textField11.setText(x10);

                    }
                    rs.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try(
                        Statement stmt = con.createStatement();
                ) {
                    String s1=textField1.getText();
                    String s3=textField3.getText();
                    String s4=textField4.getText();
                    String s5=textField5.getText();
                    String s6=textField6.getText();
                    String s7=textField7.getText();
                    String s9=textField9.getText();
                    String s10=textField10.getText();
                    String s11=textField11.getText();

                    String sql = "UPDATE client_ " +
                            "SET nume = '"+s1+
                            "', data_nasterii = '"+ s3 +
                            "', adresa = '"+ s4 +
                            "', nr_telefon = '"+ s5 +
                            "', email = '"+ s6 +
                            "', nr_conturi = '"+ s7 +
                            "', nr_contract = '"+ s9 +
                            "', sursa_venit = '"+ s10 +
                            "', tranz_online = '"+ s11 +
                            "' WHERE CNP ="  + "'" + x2[0] + "'";
                    stmt.executeUpdate(sql);
                }
                catch (SQLException exception) {
                    exception.printStackTrace();
                }

                try(
                        Statement stmt = con.createStatement();
                ) {
                    String s1=textField12.getText();
                    String s3=textField14.getText();
                    String s4=textField15.getText();
                    String s5=textField16.getText();
                    String s7=textField18.getText();
                    String s8=textField19.getText();
                    String s9=textField20.getText();
                    String s10=textField21.getText();
                    String s11=textField22.getText();

                    String sql = "UPDATE angajat " +
                            "SET nume = '"+s1+
                            "', adresa = '"+ s3 +
                            "', nr_telefon = '"+ s4 +
                            "', email = '"+ s5 +
                            "', nr_contract = '"+ s7 +
                            "', norma = '"+ s8 +
                            "', salariu = '"+ s9 +
                            "', sucursala = '"+ s10 +
                            "', nume_departament = '"+ s11 +
                            "' WHERE CNP ="  + "'" + x2[0] + "'";
                    stmt.executeUpdate(sql);
                }
                catch (SQLException exception) {
                    exception.printStackTrace();
                }

            }
        });



    }
}
