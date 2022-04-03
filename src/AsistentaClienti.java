import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AsistentaClienti extends JFrame{
    private JPanel Asistenta;
    private JButton backButton;
    private JButton logOutButton;
    private JButton exitButton;
    private JComboBox NumeClienti;
    private JButton afisareInformatiiButton;
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

    public AsistentaClienti(String username, Connection con) {

        setContentPane(Asistenta);
        setTitle("Asistenta Clienti");
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

        afisareInformatiiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nume =  NumeClienti.getSelectedItem().toString();
                Statement statement = null;
                try {
                    statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("select * from client_ where nume = " + "'" + nume + "'");
                    while(rs.next()){
                        String x1 = rs.getString("nume");
                        String x2 = rs.getString("CNP");
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
                        textField2.setText(x2);
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

    }
}
