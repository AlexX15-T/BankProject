import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ContacteFavorite extends JFrame{
    private JPanel panel1;
    private JLabel Contacte;
    private JButton exitButton;
    private JButton logOutButton;
    private JButton backButton;
    private JTable table1;

    public ContacteFavorite(String username, Connection con) {
        setContentPane(panel1);
        setTitle("Contacte Favorite");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,600);

        String coloane = "Nume";
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn(coloane);

        try{

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select nume_pers1 from contacte_favorite where utilizator = "+"'"+ username+"'");
            while(rs.next())
            {
                String x = rs.getString("nume_pers1");
                table.addRow(new String[]{x});
            }
            table1.setModel(table);

        }
        catch (SQLException e){
            e.printStackTrace();
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

    }
}
