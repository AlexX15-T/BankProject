

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;

public class ClientFrame extends JFrame {

    JLabel message;
    Container container = getContentPane();

    private Connection con;
    private JButton afisareSold;
    private JButton afisareContacteFavorite;
    private JButton efectuareaTranzactie;
    private JButton istoricTranzactii;
    private JButton exit;
    private JButton creareDepozit;


    public ClientFrame( String username, Connection c ) {

        con = c;

        message = new JLabel("You are successfully logged in, " + username + " !");

        message.setHorizontalAlignment(JLabel.CENTER);
        message.setVerticalAlignment(JLabel.CENTER);

        container.setLayout(new GridLayout(2,4,0,0));

        creareDepozit = new JButton("Creare Depozit");
        afisareSold = new JButton ("Afisare Sold");
        afisareContacteFavorite = new JButton ("Afisare Contacte Favorite");
        efectuareaTranzactie = new JButton ("Efectuare Tranzactie");
        istoricTranzactii = new JButton ("Istoric tranzactii");
        exit = new JButton ("Iesire");


        creareDepozit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreareDepozit(con, username);
            }
        });



        afisareSold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {


                try{
                    Statement stm = con.createStatement();
                    String sql = "select sold from cont c join client_ k on (c.iban=k.cont_iban)  where k.nume =" + '"' + username + '"';
                    ResultSet rs = stm.executeQuery(sql);

                    if(rs.next()){
                        String sold = "Soldul dvs. este de " + rs.getString("sold") + " lei!";

                        JPanel panel = new JPanel();
                        JFrame frame = new JFrame();
                        frame.setSize(300,300);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.add(panel);
                        panel.setLayout(null);

                        JButton exit = new JButton("Iesire");
                        JLabel titlu = new JLabel(sold);

                        exit.setBounds(100,165,80,25);

                        titlu.setBounds(0,0,250,100);
                        titlu.setHorizontalAlignment(JLabel.CENTER);
                        titlu.setVerticalAlignment(JLabel.CENTER);
                        panel.add(titlu);
                        panel.add(exit);


                        exit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                frame.dispose();
                            }
                        });

                        frame.setVisible(true);

                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }

            }
        });

        afisareContacteFavorite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                String showNames = "Numele persoanelor din lista dvs. de contacte favorite sunt: ";
                ArrayList < String > all = new ArrayList< String >();

                try {
                    Statement stm = con.createStatement();
                    String sql = "select nume_pers, iban_pers from contacte_favorite where utilizator =" + '"' + username + '"';
                    ResultSet rs = stm.executeQuery(sql);


                    while (rs.next()) {

                        String Name = rs.getString("nume_pers");
                        String Iban = rs.getString("iban_pers");

                        all.add(Name + " " + Iban);

                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


                JPanel panel = new JPanel();
                JFrame frame = new JFrame();
                frame.setSize(600, 300);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(panel);
                panel.setLayout(null);

                JButton exit = new JButton("Iesire");
                JLabel titlu = new JLabel(showNames);

                DefaultListModel<String> model = new DefaultListModel<>();
                JList <String> list = new JList<>( model );

                for ( int i = 0; i < all.size(); i++ ){
                    model.addElement(all.get(i));
                }

                frame.add(list);

                exit.setBounds(100, 165, 80, 25);

                titlu.setBounds(50, 0, 400, 100);
                titlu.setHorizontalAlignment(JLabel.CENTER);
                titlu.setVerticalAlignment(JLabel.CENTER);


                panel.add(titlu);
                panel.add(exit);


                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });

                frame.setVisible(true);

            }

        });

        efectuareaTranzactie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EfectuareTranzatie(con, username);
            }
        });

        istoricTranzactii.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    new IstoricTranzactii(con, username);
            }
        });


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {dispose();}
        });


        this.setTitle("Meniu Clienti");
        this.setVisible(true);
        this.setBounds(500, 500, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1000,400);
        setBounds();
        addComponents();
    }

    public void setBounds() {
        message.setBounds(10, 10, 400, 30);

    }

    public void addComponents() {
        container.add(message);
        container.add(afisareSold);
        container.add(afisareContacteFavorite);
        container.add(efectuareaTranzactie);
        container.add(istoricTranzactii);
        container.add(creareDepozit);
        container.add(exit);

    }

}