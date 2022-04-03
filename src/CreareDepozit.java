
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;

public class CreareDepozit {

    private static Connection con;
    private static String username;
    private static String iban_sursa;
    private static String CNP;


    public CreareDepozit(Connection a, String name) {

        con = a;
        username = name;
        setIban_sursa();
        setCNP();

        JFrame frameTranzactie = new JFrame();
        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        frameTranzactie.setTitle("Creare Depozit");
        constraints.fill = GridBagConstraints.NORTHWEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        grid.add(getEmptyLabel(new Dimension(250,50)), constraints);
        JLabel titlu = new JLabel("Introduceti perioada si suma: ");
        grid.add(titlu,constraints);
        ++constraints.gridy;

        JLabel Perioada = new JLabel("Perioada");
        grid.add(Perioada,constraints);
        ++constraints.gridx;

        JTextField perioadaText = new JTextField();
        constraints.ipadx = 300;
        grid.add(perioadaText,constraints);
        ++constraints.gridy;

        constraints.gridx=0;
        constraints.ipadx = 0;
        JLabel suma = new JLabel("Suma");
        grid.add(suma,constraints);
        ++constraints.gridx;

        JTextField sumaText = new JTextField();
        constraints.ipadx =300;
        grid.add(sumaText,constraints);

        JButton buton = new JButton("Creare");
        buton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                Creare(sumaText.getText(), perioadaText.getText());
            }
        });
        constraints.gridy++;
        constraints.gridx=1;
        constraints.ipadx=150;
        grid.add(buton,constraints);


        frameTranzactie.setContentPane(grid);
        frameTranzactie.pack();
        frameTranzactie.setLocationRelativeTo(null);
        frameTranzactie.setVisible(true);

    }

    private void Creare(String suma, String perioada){
        try {

            PreparedStatement p = null;
            ResultSet rs = null;

            String query = "{CALL CreareDepozit(?,?,?,?)}";

            CallableStatement stm = con.prepareCall(query);

            Float valoare = Float.parseFloat(suma);

            int per = Integer.parseInt(perioada);

            stm.setString("iban",iban_sursa);

            stm.setFloat("valoare",valoare);

            stm.setInt("perioada",per);

            stm.setString("CNP_detinator", CNP);

            rs = stm.executeQuery();

            if(rs.next()){
                FrameMesaj("Creare finalizata cu succes");
            }
            else{
                FrameMesaj("Eroare, nu s-a putut crea depozitul");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void FrameMesaj(String mesajText){
        JFrame frameMesaj = new JFrame();
        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        grid.add(getEmptyLabel(new Dimension(300,80)), constraints);
        constraints.gridy = 0;
        constraints.gridx = 0;

        JLabel mesaj = new JLabel(mesajText);
        grid.add(mesaj,constraints);
        ++constraints.gridy;
        JButton butonClose = new JButton("OK");
        butonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMesaj.dispatchEvent(new WindowEvent(frameMesaj, WindowEvent.WINDOW_CLOSING));
            }
        });
        grid.add(butonClose,constraints);

        frameMesaj.setContentPane(grid);
        frameMesaj.pack();
        frameMesaj.setLocationRelativeTo(null);
        frameMesaj.setVisible(true);
    }

    private JLabel getEmptyLabel(Dimension d) {
        JLabel label = new JLabel();
        label.setPreferredSize(d);
        return label;
    }

    public void setIban_sursa()
    {

        String Iban = "";

        try {
            Statement stm = con.createStatement();
            String sql = "select iban_card from conturi_existente c join client_ k on (c.iban_card = k.cont_iban)  where k.nume =" + '"' + username + '"';
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {

                Iban = rs.getString("iban_card");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.iban_sursa = Iban;

    }

    public void setCNP()
    {
        String Cnp = "";

        try {
            Statement stm = con.createStatement();
            String sql = "select CNP from client_ where nume = " + '"' + username + '"';
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {

                Cnp = rs.getString("CNP");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.CNP = Cnp;
    }

}
