import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;




public class Bankmanagment {
    private static ArrayList<User>Users = new ArrayList<>();
    private static JFrame HomeScreen;


    public static void main(String[] args) {
        creatHomeScreen();
    }

    private static Font buttonsizes(int sz){
        Font buttonFont = new Font("Arial", Font.BOLD, sz); // Create a larger font
        return buttonFont;
    }

    private static Font labelsizes(int sz){
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

        creatAccountBtn.addActionListener(e -> {
            HomeScreen.dispose();
            CreatAccountFrame creatAcc = new CreatAccountFrame();
            creatAcc.setVisible(true);
        });
        exitBtn.addActionListener(e -> System.exit(0));



        panel.add(creatAccountBtn);
        panel.add(LoginBtn);
        panel.add(exitBtn);

        HomeScreen.add(panel);
        HomeScreen.setVisible(true);
        
    }

    

    static class CreatAccountFrame extends JFrame {
        private JTextField NameField, DateOfBirthField, emailField, phoneField;
        private JComboBox<String>Genderfield, accountTypeField;
        private JPasswordField pinField;

        private CreatAccountFrame(){
            setTitle("Creat Account");
            setSize(800, 800);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(new GridLayout(9, 2, 30, 20));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            long accountNumber = new Random().nextLong() % 10000000000L;
            if(accountNumber < -1)accountNumber *= -1;
            int labelsz = 25;

            JLabel label1 = new JLabel("Account Number : ");
            label1.setFont(labelsizes(labelsz));
            JLabel label12 = new JLabel(Long.toString(accountNumber));
            label12.setFont(labelsizes(labelsz));
            panel.add(label1); panel.add(label12);


            JLabel label2 = new JLabel("Name : ");
            label2.setFont(labelsizes(labelsz));
            NameField = new JTextField();
            NameField.setFont(labelsizes(labelsz));
            panel.add(label2); panel.add(NameField);

            JLabel label3 = new JLabel("Email : ");
            label3.setFont(labelsizes(labelsz));
            emailField = new JTextField();
            emailField.setFont(labelsizes(labelsz));
            panel.add(label3);  panel.add(emailField);

            JLabel label4 = new JLabel("Phone : ");
            label4.setFont(labelsizes(labelsz));
            phoneField = new JTextField();
            phoneField.setFont(labelsizes(labelsz));
            panel.add(label4); panel.add(phoneField);

            JLabel label5 = new JLabel("Date of Birth : ");
            label5.setFont(labelsizes(labelsz));
            DateOfBirthField = new JTextField();
            DateOfBirthField.setFont(labelsizes(labelsz));
            panel.add(label5); panel.add(DateOfBirthField);

            JLabel label6 = new JLabel("Gender : ");
            label6.setFont(labelsizes(labelsz));
            Genderfield = new JComboBox<>(new String[]{"Male", "Female", "Other"});
            Genderfield.setFont(labelsizes(labelsz));
            panel.add(label6); panel.add(Genderfield);

            JLabel label7 = new JLabel("Account Type : ");
            label7.setFont(labelsizes(labelsz));
            accountTypeField = new JComboBox<>(new String[]{"Current", "Savings"});
            accountTypeField.setFont(labelsizes(labelsz));
            panel.add(label7); panel.add(accountTypeField);

            JLabel label8 = new JLabel("6 Digit Pin : ");
            label8.setFont(labelsizes(labelsz));
            pinField = new JPasswordField();
            pinField.setFont(labelsizes(labelsz));
            panel.add(label8); panel.add(pinField);
            
            JButton createBtn = new JButton("Creat Account");
            createBtn.setFont(labelsizes(30));
            JButton backBtn = new JButton("Back");
            backBtn.setFont(labelsizes(30));
            String accNumber = Long.toString(accountNumber);

             createBtn.addActionListener(e -> creatAccount(accNumber));

            backBtn.addActionListener(e -> {
                this.dispose();
                HomeScreen.setVisible(true);
            });

            panel.add(createBtn);
            panel.add(backBtn);
            add(panel);
        }

        private void creatAccount(String accountNumber){
            String pin = new String(pinField.getPassword());
            if(pin.length() != 6 || pin.matches("\\d")){
                JOptionPane.showMessageDialog(this,"Pin Must be 6 Digits" , "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User user = new User(NameField.getText(), emailField.getText(), phoneField.getText(),
                                 accountNumber, (String)Genderfield.getSelectedItem(), DateOfBirthField.getText(),
                                (String)accountTypeField.getSelectedItem(), Integer.parseInt(pin), 0.0);

            Users.add(user);

            JOptionPane.showMessageDialog(this, "This account is successfully created");
            this.dispose();
            HomeScreen.setVisible(true);
        }


    }

    static class LoginFrame extends JFrame{
        private JTextField AccountNumberField;
        private JPasswordField pinField;


        public LoginFrame(){
            setTitle("Log In");
            setSize(800, 800);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(new GridLayout(3, 2, 20, 20));
            panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            int sz = 30;

            JLabel label1 = new JLabel("Account Number : ");
            label1.setFont(labelsizes(sz));
            AccountNumberField = new JTextField();
            AccountNumberField.setFont(labelsizes(sz));
            panel.add(label1); panel.add(AccountNumberField);

            JLabel label2 = new JLabel("PIN : ");
            label2.setFont(labelsizes(sz));
            pinField = new JPasswordField();
            pinField.setFont(labelsizes(sz));
            panel.add(label2); panel.add(pinField);

            JButton loginBtn = new JButton("Log in");
            loginBtn.setFont(labelsizes(sz));
            JButton backBtn = new JButton("Back");
            backBtn.setFont(labelsizes(sz));

            backBtn.addActionListener(e -> {
                this.dispose();
                HomeScreen.setVisible(true);
            });

            panel.add(loginBtn); panel.add(backBtn);
            add(panel);
        }

        private void login(){
            try {
                String accountNumber = AccountNumberField.getText();
                int pin = Integer.parseInt(new String (pinField.getPassword()));
                for(User user : Users){
                    if(user.GetaccountNumber() == accountNumber && user.Getpin() == pin){
                        // call account show frame
                        this.dispose();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "Invalid account number or pin !", "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Invalid Input format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
}
