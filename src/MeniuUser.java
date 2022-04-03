import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MeniuUser extends JPanel {
    private JTextField jcomp1;
    private JButton jcomp2;
    private JButton jcomp3;
    private JButton jcomp4;
    private JButton jcomp5;
    private JButton jcomp6;

    public MeniuUser() {
        //construct components
        jcomp1 = new JTextField(5);
        jcomp2 = new JButton("002");
        jcomp3 = new JButton("003");
        jcomp4 = new JButton("004");
        jcomp5 = new JButton("005");
        jcomp6 = new JButton("006");

        //adjust size and set layout
        setPreferredSize(new Dimension(944, 563));
        GridLayout layout = new GridLayout(3, 2, 0, 0);
        setLayout(layout);

        //add components
        add(jcomp1);
        add(jcomp2);
        add(jcomp3);
        add(jcomp4);
        add(jcomp5);
        add(jcomp6);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("MeniuUser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MeniuUser());
        frame.pack();
        frame.setVisible(true);
    }
}
