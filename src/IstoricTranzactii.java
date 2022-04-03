import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class IstoricTranzactii {

    private static Connection con;
    private static String username;

    public IstoricTranzactii(Connection a, String name)
    {
        con = a;
        username = name;

        JFrame f;

        f=new JFrame();

        String data[][] = new String[41][7];

        String column[]={"Angajat Responsabil","Iban Destinatar", "Data", "Timp", "Valoare", "Detaliu", "Status_"};



        try{
            Statement stm = con.createStatement();
            String sql = "select angajat_responsabil, cont_destinatie_iban, data, timp, valoare, detaliu, Status_ from tranzactie_cont c join client_ k on (c.cont_sursa_iban = k.cont_iban)  where k.nume =" + '"' + username + '"' + "order by data";
            ResultSet rs = stm.executeQuery(sql);

            int i = 0;

            while(rs.next()){
                String angajat_ = rs.getString("angajat_responsabil");
                String iban_d = rs.getString("cont_destinatie_iban");
                String data_ = rs.getString("data");
                String timp_ = rs.getString("timp");
                String valoare_ = rs.getString("valoare");
                String detaliu_ = rs.getString("detaliu");
                String status_ = rs.getString("Status_");

                data[i][0] = angajat_;
                data[i][1] = iban_d;
                data[i][2] = data_;
                data[i][3] = timp_;
                data[i][4] = valoare_;
                data[i][5] = detaliu_;
                data[i][6] = status_;
                i++;

            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        JTable jt = new JTable(data,column);
        jt.setBounds(30,40,200,300);

        jt.setShowGrid(true);
        jt.setShowVerticalLines(true);

        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(500,500);
        f.setVisible(true);
        f.setTitle("ISTORIC TRANZACTII");


    }

}
