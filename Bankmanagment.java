import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Bankmanagment {
    private static ArrayList<User>users = new ArrayList<>();
    private static JFrame HomeScreen;

    private static Font buttonsizes(int sz){
        Font buttonFont = new Font("Arial", Font.BOLD, sz); // Create a larger font
        return buttonFont;
    }

   

    private static void creatHomeScreen(){


        HomeScreen = new JFrame("Welcome To CHUNU Bank");
        HomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HomeScreen.setSize(800, 800);
        HomeScreen.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JButton creatAccountBtn = new JButton("Creat Account");
        creatAccountBtn.setPreferredSize(new Dimension(10, 10));
        creatAccountBtn.setFont(buttonsizes((30)));

        JButton LoginBtn = new JButton("Log in");
        LoginBtn.setPreferredSize(new Dimension(10, 10));
        LoginBtn.setFont(buttonsizes(30));

        JButton exitBtn = new JButton("Exit");
        exitBtn.setPreferredSize(new Dimension(10, 10));
        exitBtn.setFont(buttonsizes(30));

        

        panel.add(creatAccountBtn);
        panel.add(LoginBtn);
        panel.add(exitBtn);

        HomeScreen.add(panel);


        HomeScreen.setVisible(true);
        
    }

    public static void main(String[] args){
         creatHomeScreen();    
    }
}
