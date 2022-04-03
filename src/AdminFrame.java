
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.*;
import java.sql.*;

public class AdminFrame extends JFrame {
    JLabel message;
    Container container = getContentPane();

    private JTextField welcomeText;
    private JButton jcomp2;
    private JButton jcomp3;
    private JButton jcomp4;
    private JButton jcomp5;
    private JButton jcomp6;
    private JButton jcomp7;
    private JButton jcomp8;

    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String DB_NAME = "banca";
    private final static String CONNECTION_LINK = "jdbc:mysql://localhost:3306/";

    public AdminFrame() {
        message = new JLabel("You are successfully logged in, admin !");

        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment(JLabel.CENTER);

        container.setLayout(new GridLayout(4,2,0,0));

        jcomp2 = new JButton ("Plata salariu");
        jcomp3 = new JButton ("Cautare clienti");
        jcomp4 = new JButton ("Vizualizare tranzactii");
        jcomp5 = new JButton ("Update Dobanda");
        jcomp6 = new JButton ("Aprobare card");
        jcomp7 = new JButton("Lichidare depozit");
        jcomp8 = new JButton("Aprobare depozit");

        jcomp2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plataSalariu(e);
            }
        });
        jcomp3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cautareUtilizatori(e);
            }
        });
        jcomp4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vizTranzactii( e);
            }
        });
        jcomp5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateDobanda( e);
            }
        });
        jcomp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aprobareCard(e);
            }
        });
        jcomp7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lichidareDepozit(e);
            }
        });
        jcomp8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aprobareDepozit(e);
            }
        });

        this.setTitle("");
        this.setVisible(true);
        this.setBounds(500, 500, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setBounds();
        addComponents();

    }

    public void setBounds() {
        message.setBounds(10, 10, 400, 30);

    }

    public void addComponents() {
        container.add(message);
        container.add(jcomp2);
        container.add(jcomp3);
        container.add(jcomp4);
        container.add(jcomp5);
        container.add(jcomp6);
        container.add(jcomp7);
        container.add(jcomp8);

    }

    private void plataSalariu(ActionEvent e){
        JFrame frameSalariu = new JFrame();
        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NORTHWEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        grid.add(getEmptyLabel(new Dimension(250,50)), constraints);
        JLabel titlu = new JLabel("Introduceti iban si suma");
        grid.add(titlu,constraints);
        ++constraints.gridy;

        JLabel iban = new JLabel("IBAN");
        grid.add(iban,constraints);
        ++constraints.gridx;

        JTextField ibanText = new JTextField();
        constraints.ipadx =300;
        grid.add(ibanText,constraints);
        ++constraints.gridy;

        constraints.gridx=0;
        constraints.ipadx =0;
        JLabel suma = new JLabel("SUMA");
        grid.add(suma,constraints);
        ++constraints.gridx;

        JTextField sumaText = new JTextField();
        constraints.ipadx =300;
        grid.add(sumaText,constraints);

        JButton buton = new JButton("Plata");
        buton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                Plata(ibanText.getText(),sumaText.getText());
            }
        });
        constraints.gridy++;
        constraints.gridx=1;
        constraints.ipadx=150;
        grid.add(buton,constraints);


        frameSalariu.setContentPane(grid);
        frameSalariu.pack();
        frameSalariu.setLocationRelativeTo(null);
        frameSalariu.setVisible(true);
    }

    private void Plata(String iban, String suma){
        try {
            p1.MySQLCon connection = new p1.MySQLCon();
            Connection con = null;
            PreparedStatement p = null;
            ResultSet rs = null;
            con=connection.connectDB();
            String query = "{CALL PlataSalariu(?,?)}";
            CallableStatement stm = con.prepareCall(query);
            stm.setString("cont_destinatie",iban);
            Float valoare = Float.parseFloat(suma);
            stm.setFloat("valoare_transfer",valoare);
            rs=stm.executeQuery();
            if(rs.next()){
                FrameMesaj("Plata Finalizata cu succes");
            }
            else{
                FrameMesaj("Iban incorect. Introduceti un nou iban");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void cautareUtilizatori(ActionEvent e){
        JFrame frameCautare = new JFrame();
        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NORTHWEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        grid.add(getEmptyLabel(new Dimension(250,50)), constraints);
        JLabel titlu = new JLabel("Introduceti numele");
        grid.add(titlu,constraints);
        ++constraints.gridy;

        JTextField numeText = new JTextField();
        constraints.ipadx =300;
        grid.add(numeText,constraints);
        ++constraints.gridy;

        JButton buton = new JButton("Cautare");
        buton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                Cautare(numeText.getText());
            }
        });
        constraints.gridy++;
        constraints.gridx=0;
        constraints.ipadx=150;
        grid.add(buton,constraints);

        frameCautare.setContentPane(grid);
        frameCautare.pack();
        frameCautare.setLocationRelativeTo(null);
        frameCautare.setVisible(true);

    }

    private void Cautare(String nume){
        try {
            p1.MySQLCon connection = new p1.MySQLCon();
            Connection con = null;
            PreparedStatement p = null;
            ResultSet rs = null;
            con = connection.connectDB();
            String sql = "select * from client_ where nume like \"%" + nume +"%\"";
            p = con.prepareStatement(sql);
            rs=p.executeQuery();
            if(rs.next()){
                JFrame f = new JFrame();
                String[][] data ={
                        {rs.getString("CNP"), String.valueOf(rs.getInt("nr_conturi")),rs.getString("nume"),
                                rs.getString("adresa"),rs.getString("nr_telefon"),rs.getString("email"),
                                rs.getString("cont_iban"), String.valueOf(rs.getInt("nr_contract")), String.valueOf(rs.getDate("data_nasterii")),
                                rs.getString("sursa_venit"), String.valueOf(rs.getBoolean("tranz_online"))}
                };
                String[] columnNames = {"CNP","Numar Conturi","Nume","Adresa","Numar Telefon","email","IBAN","Numar Contract","Data nasterii","Sursa venit"};
                JTable t = new JTable(data,columnNames);
                t.setBounds(30,40,200,300);
                JScrollPane sp = new JScrollPane(t);
                f.add(sp);
                f.setSize(1000,200);
                f.setVisible(true);

            }
            else{
                FrameMesaj("Nu s-a gasit niciun client cu numele introdus. Introduceti alt nume");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void vizTranzactii(ActionEvent e){
        JFrame frameTranz = new JFrame();
        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NORTHWEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        grid.add(getEmptyLabel(new Dimension(250,50)), constraints);
        JLabel titlu = new JLabel("Introduceti tipul operatiunii");
        grid.add(titlu,constraints);
        ++constraints.gridy;

        JTextField tipText = new JTextField();
        constraints.ipadx =300;
        grid.add(tipText,constraints);
        ++constraints.gridy;


        JButton buton = new JButton("Cautare");
        buton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                Vizualizare(tipText.getText());
            }
        });
        constraints.gridy++;
        constraints.gridx=0;
        constraints.ipadx=150;
        grid.add(buton,constraints);


        frameTranz.setContentPane(grid);
        frameTranz.pack();
        frameTranz.setLocationRelativeTo(null);
        frameTranz.setVisible(true);
    }

    public void Vizualizare(String tip) {
        try {
            p1.MySQLCon connection = new p1.MySQLCon();
            Connection con = null;
            PreparedStatement p = null;
            ResultSet rs = null;
            con = connection.connectDB();
            String sql = "select * from tranzactie_cont where detaliu = \"" + tip +"\"";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();
            JFrame f = new JFrame();
            String[][] data =new String[10][10];
            int k=0;
            String[] columnNames = {"ID","Cont sursa","Cont destinatie","Angajat responsabil","Data","Ora","Valoare","Tip"};
            while(rs.next()){
                data[k]= new String[]{String.valueOf(rs.getInt("id")), rs.getString("cont_sursa_iban"),rs.getString("cont_destinatie_iban"),
                        rs.getString("angajat_responsabil"), String.valueOf(rs.getDate("data")), String.valueOf(rs.getTime("timp")),
                        String.valueOf(rs.getFloat("valoare")),rs.getString("detaliu")};
                k++;
                System.out.println(data[0]);
            }
            JTable t = new JTable(data,columnNames);
            t.setBounds(30,40,300,400);
            JScrollPane sp = new JScrollPane(t);
            f.add(sp);
            f.setSize(1000,200);
            f.setVisible(true);

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void UpdateDobanda(ActionEvent e){
        JFrame frameDobanda = new JFrame();
        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NORTHWEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        grid.add(getEmptyLabel(new Dimension(250,50)), constraints);
        JLabel titlu = new JLabel("Introduceti iban si noua dobanda");
        grid.add(titlu,constraints);
        ++constraints.gridy;

        JLabel iban = new JLabel("IBAN");
        grid.add(iban,constraints);
        ++constraints.gridx;

        JTextField ibanText = new JTextField();
        constraints.ipadx =300;
        grid.add(ibanText,constraints);
        ++constraints.gridy;

        constraints.gridx=0;
        constraints.ipadx =0;
        JLabel suma = new JLabel("Dobanda");
        grid.add(suma,constraints);
        ++constraints.gridx;

        JTextField sumaText = new JTextField();
        constraints.ipadx =300;
        grid.add(sumaText,constraints);

        JButton buton = new JButton("Update");
        buton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                Update(ibanText.getText(), Float.valueOf(sumaText.getText()));
            }
        });
        constraints.gridy++;
        constraints.gridx=1;
        constraints.ipadx=150;
        grid.add(buton,constraints);


        frameDobanda.setContentPane(grid);
        frameDobanda.pack();
        frameDobanda.setLocationRelativeTo(null);
        frameDobanda.setVisible(true);

    }

    private void Update(String iban, Float dobanda){
        try {
            p1.MySQLCon connection = new p1.MySQLCon();
            Connection con = null;
            PreparedStatement p = null;
            ResultSet rs = null;
            con=connection.connectDB();
            String query = "{CALL UpdateDobanda(?,?)}";
            CallableStatement stm = con.prepareCall(query);
            stm.setString("ibanD",iban);
            stm.setFloat("dobanda_noua",dobanda);
            rs=stm.executeQuery();
            if(rs.next()){
                FrameMesaj("Dobanda noua a fost setata cu succes");
            }
            else{
                FrameMesaj("Iban incorect. Introduceti un nou iban");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void aprobareCard(ActionEvent e){
        JFrame frameTranz = new JFrame();
        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NORTHWEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        grid.add(getEmptyLabel(new Dimension(250,50)), constraints);
        JLabel titlu = new JLabel("Introduceti ibanul cardului pe care doriti sa il aprobati");
        grid.add(titlu,constraints);
        ++constraints.gridy;

        JTextField tipText = new JTextField();
        constraints.ipadx =300;
        grid.add(tipText,constraints);
        ++constraints.gridy;


        JButton buton = new JButton("Aprobare");
        buton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                Aprobare(tipText.getText());
            }
        });
        constraints.gridy++;
        constraints.gridx=0;
        constraints.ipadx=150;
        grid.add(buton,constraints);


        frameTranz.setContentPane(grid);
        frameTranz.pack();
        frameTranz.setLocationRelativeTo(null);
        frameTranz.setVisible(true);
    }

    private void Aprobare(String iban){
        try {
            p1.MySQLCon connection = new p1.MySQLCon();
            Connection con = null;
            PreparedStatement p = null;
            ResultSet rs = null;
            con=connection.connectDB();
            String sql = "update eliberare_card set aprobare_admin=\"DA\" where numarcard=\"" + iban +"\"";
            p = con.prepareStatement(sql);
            int k=p.executeUpdate();
            if( k!= 0){
                FrameMesaj("Cardul a fost aprobat cu succes");
            }
            else{
                FrameMesaj("Numar card incorect. Introduceti un nou numar");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void lichidareDepozit(ActionEvent e){
        JFrame frameDobanda = new JFrame();
        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NORTHWEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        grid.add(getEmptyLabel(new Dimension(250,50)), constraints);
        JLabel titlu = new JLabel("Introduceti numar depozit");
        grid.add(titlu,constraints);
        ++constraints.gridy;

        JLabel iban = new JLabel("Nr. Depozit");
        grid.add(iban,constraints);
        ++constraints.gridx;

        JTextField ibanText = new JTextField();
        constraints.ipadx =300;
        grid.add(ibanText,constraints);
        ++constraints.gridy;

        JButton buton = new JButton("Lichidare");
        buton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                Lichidare(ibanText.getText());
            }
        });
        constraints.gridy++;
        constraints.gridx=1;
        constraints.ipadx=150;
        grid.add(buton,constraints);


        frameDobanda.setContentPane(grid);
        frameDobanda.pack();
        frameDobanda.setLocationRelativeTo(null);
        frameDobanda.setVisible(true);
    }

    private void Lichidare(String iban){
        try {
            p1.MySQLCon connection = new p1.MySQLCon();
            Connection con = null;
            PreparedStatement p = null;
            ResultSet rs = null;
            con=connection.connectDB();
            String query = "{CALL LichidareDepozit(?)}";
            CallableStatement stm = con.prepareCall(query);
            stm.setString("iban_utilizator",iban);
            rs=stm.executeQuery();
            if(rs.next()){
                FrameMesaj("Depozitul a fost lichidat cu succes");
            }
            else{
                FrameMesaj("Iban incorect. Introduceti un nou iban");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void aprobareDepozit(ActionEvent e){
        JFrame frameTranz = new JFrame();
        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NORTHWEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        grid.add(getEmptyLabel(new Dimension(250,50)), constraints);
        JLabel titlu = new JLabel("Introduceti ibanul depozitului pe care doriti sa il aprobati");
        grid.add(titlu,constraints);
        ++constraints.gridy;

        JTextField tipText = new JTextField();
        constraints.ipadx =300;
        grid.add(tipText,constraints);
        ++constraints.gridy;


        JButton buton = new JButton("Aprobare");
        buton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                AprobareD(tipText.getText());
            }
        });
        constraints.gridy++;
        constraints.gridx=0;
        constraints.ipadx=150;
        grid.add(buton,constraints);


        frameTranz.setContentPane(grid);
        frameTranz.pack();
        frameTranz.setLocationRelativeTo(null);
        frameTranz.setVisible(true);
    }

    private void AprobareD(String iban){
        try {
            p1.MySQLCon connection = new p1.MySQLCon();
            Connection con = null;
            PreparedStatement p = null;
            ResultSet rs = null;
            con=connection.connectDB();
            String sql = "update depozit set aprobat=\"DA\" where iban=\"" + iban +"\"";
            p = con.prepareStatement(sql);
            int k=p.executeUpdate();
            if( k!= 0){
                FrameMesaj("Depozitul a fost aprobat cu succes");
            }
            else{
                FrameMesaj("Iban incorect. Introduceti un nou iban");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private JLabel getEmptyLabel(Dimension d) {
        JLabel label = new JLabel();
        label.setPreferredSize(d);
        return label;
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
}