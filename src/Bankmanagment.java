package p;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import javax.swing.*;


public class Bankmanagment {
    private static ArrayList<User>Users = new ArrayList<>();
    private static JFrame HomeScreen;
    private static int framesize = 600;



    public static void main(String[] args) {
        creatHomeScreen();
    }

    private static Font buttonSizes(int sz){
        return new Font("Arial", Font.BOLD, sz);

    }

    private static Font labelSizes(int sz){
        return new Font("Arial", Font.BOLD, sz);
    }


    private static void creatHomeScreen(){

        HomeScreen = new JFrame("Welcome To CHUNU Bank");
        HomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HomeScreen.setSize(framesize, framesize);
        HomeScreen.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 20, 100, 20));


        JButton creatAccountBtn = new JButton("Creat Account");
        creatAccountBtn.setFont(buttonSizes((30)));

        JButton LoginBtn = new JButton("Log in");
        LoginBtn.setFont(buttonSizes(30));

        JButton exitBtn = new JButton("Exit");
        exitBtn.setFont(buttonSizes(30));

        creatAccountBtn.addActionListener(e -> {
            HomeScreen.dispose();
            CreatAccountFrame creatAcc = new CreatAccountFrame();
            creatAcc.setVisible(true);
        });

        LoginBtn.addActionListener(e -> {
            HomeScreen.dispose();
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
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
            setSize(framesize, framesize);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(new GridLayout(9, 2, 30, 20));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            long accountNumber = new Random().nextLong() % 10000000000L;
            if(accountNumber < -1)accountNumber *= -1;
            int labelsz = 25;

            JLabel label1 = new JLabel("Account Number ");
            label1.setFont(labelSizes(labelsz));
            JLabel label12 = new JLabel(Long.toString(accountNumber));
            label12.setFont(labelSizes(labelsz));
            panel.add(label1); panel.add(label12);


            JLabel label2 = new JLabel("Name  ");
            label2.setFont(labelSizes(labelsz));
            NameField = new JTextField();
            NameField.setFont(labelSizes(labelsz));
            panel.add(label2); panel.add(NameField);

            JLabel label3 = new JLabel("Email  ");
            label3.setFont(labelSizes(labelsz));
            emailField = new JTextField();
            emailField.setFont(labelSizes(labelsz));
            panel.add(label3);  panel.add(emailField);

            JLabel label4 = new JLabel("Phone ");
            label4.setFont(labelSizes(labelsz));
            phoneField = new JTextField();
            phoneField.setFont(labelSizes(labelsz));
            panel.add(label4); panel.add(phoneField);

            JLabel label5 = new JLabel("Date of Birth  ");
            label5.setFont(labelSizes(labelsz));
            DateOfBirthField = new JTextField();
            DateOfBirthField.setFont(labelSizes(labelsz));
            panel.add(label5); panel.add(DateOfBirthField);

            JLabel label6 = new JLabel("Gender  ");
            label6.setFont(labelSizes(labelsz));
            Genderfield = new JComboBox<>(new String[]{"Male", "Female", "Other"});
            Genderfield.setFont(labelSizes(labelsz));
            panel.add(label6); panel.add(Genderfield);

            JLabel label7 = new JLabel("Account Type  ");
            label7.setFont(labelSizes(labelsz));
            accountTypeField = new JComboBox<>(new String[]{"Current", "Savings"});
            accountTypeField.setFont(labelSizes(labelsz));
            panel.add(label7); panel.add(accountTypeField);

            JLabel label8 = new JLabel("6 Digit Pin  ");
            label8.setFont(labelSizes(labelsz));
            pinField = new JPasswordField();
            pinField.setFont(labelSizes(labelsz));
            panel.add(label8); panel.add(pinField);
            
            JButton createBtn = new JButton("Creat Account");
            createBtn.setFont(labelSizes(30));
            JButton backBtn = new JButton("Back");
            backBtn.setFont(labelSizes(30));
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

            user.SaveUser();

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
            setSize(framesize, framesize);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(new GridLayout(3, 2, 50, 50));
            panel.setBorder(BorderFactory.createEmptyBorder(100, 30, 100, 30));

            int sz = 25;

            JLabel label1 = new JLabel("Account Number");
            label1.setFont(labelSizes(sz));
            label1.setPreferredSize(new Dimension(1, 1));
            AccountNumberField = new JTextField();
            AccountNumberField.setFont(labelSizes(sz));
            panel.add(label1); panel.add(AccountNumberField);

            JLabel label2 = new JLabel("PIN          ");
            label2.setFont(labelSizes(sz));
            pinField = new JPasswordField();
            pinField.setFont(labelSizes(sz));
            panel.add(label2); panel.add(pinField);

            JButton loginBtn = new JButton("Log in");
            loginBtn.setFont(labelSizes(sz));
            loginBtn.addActionListener(e -> {
                login();
            });
            JButton backBtn = new JButton("Back");
            backBtn.setFont(labelSizes(sz));

            backBtn.addActionListener(e -> {
                this.dispose();
                HomeScreen.setVisible(true);
            });

            panel.add(loginBtn); panel.add(backBtn);
            add(panel);
        }

        private void login() {
            try {
                String accountNumber = AccountNumberField.getText();
                int pin = Integer.parseInt(new String(pinField.getPassword()));
                User user = User.loadUser(accountNumber);
                
                if (user != null && Objects.equals(user.GetaccountNumber(), accountNumber) && user.Getpin() == pin) {
                    AccountFrame accFrame = new AccountFrame(user);
                    this.dispose();
                    accFrame.setVisible(true);
                    return;
                }
            
                JOptionPane.showMessageDialog(this, "Invalid account number or pin !", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    static class AccountFrame extends JFrame {
        

        public AccountFrame(User user) {
            
            setTitle("Account Information");
            setSize(framesize, framesize);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            JPanel infopanel = new JPanel(new GridLayout(6, 2, 20, 20));
            infopanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

            int sz = 20;
            JLabel label1 = new JLabel("Account Number :");
            label1.setFont(labelSizes(sz));
            JLabel AccountNumberField = new JLabel(user.GetaccountNumber());
            AccountNumberField.setFont(labelSizes(sz));
            infopanel.add(label1);
            infopanel.add(AccountNumberField);

            JLabel label2 = new JLabel("Name           :");
            label2.setFont(labelSizes(sz));
            JLabel NameField = new JLabel(user.Getname());
            NameField.setFont(labelSizes(sz));
            infopanel.add(label2);
            infopanel.add(NameField);

            JLabel label3 = new JLabel("Balance        :");
            label3.setFont(labelSizes(sz));
            JLabel accountBalance = new JLabel(Double.toString(user.GetBalance()));
            accountBalance.setFont(labelSizes(sz));
            infopanel.add(label3);
            infopanel.add(accountBalance);

            JLabel label4 = new JLabel("Email          :");
            label4.setFont(labelSizes(sz));
            JLabel emailField = new JLabel((user.Getemail()));
            emailField.setFont(labelSizes(sz));
            infopanel.add(label4);
            infopanel.add(emailField);

            JLabel label5 = new JLabel("Number         :");
            label5.setFont(labelSizes(sz));
            JLabel numberField = new JLabel((user.Getphone()));
            numberField.setFont(labelSizes(sz));
            infopanel.add(label5);
            infopanel.add(numberField);

            JLabel label6 = new JLabel("Date Of Birth  :");
            label6.setFont(labelSizes(sz));
            JLabel dateFeild = new JLabel((user.GetDateOfBirth()));
            dateFeild.setFont(labelSizes(sz));
            infopanel.add(label6);
            infopanel.add(dateFeild);






            JPanel buttonpanel = new JPanel(new GridLayout(3, 1, 10, 10));
            buttonpanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

            JButton withdrawBtn = new JButton("Withdraw");
            withdrawBtn.setFont(labelSizes(sz));
            withdrawBtn.addActionListener(e -> {
                this.dispose();
                TransactionFrame withdraw = new TransactionFrame();
                withdraw.WithDraw(user);
            });
            buttonpanel.add(withdrawBtn);


            JButton depositBtn = new JButton("Deposit");
            depositBtn.setFont(labelSizes(sz));
            depositBtn.addActionListener(e -> {
                this.dispose();
                TransactionFrame deposit = new TransactionFrame();
                deposit.Deposit(user);
            });
            buttonpanel.add(depositBtn);

            JButton backBtn = new JButton("Back");
            backBtn.setFont(labelSizes(sz));
            backBtn.addActionListener(e -> {
                this.dispose();
                HomeScreen.setVisible(true);
            });
            buttonpanel.add(backBtn);


            JPanel container = new JPanel(new BorderLayout());
            container.add(infopanel, BorderLayout.CENTER);
            container.add(buttonpanel, BorderLayout.SOUTH);

            add(container, BorderLayout.CENTER);

            setVisible(true);
        }
    }

    static class TransactionFrame extends JFrame {
        private boolean checkPin(User user, int pin){
            return user.Getpin() == pin;
        }

        private void BackToInfo(User user){
            this.dispose();
            AccountFrame accFrame = new AccountFrame(user);
            accFrame.setVisible(true);
        }

        public void Deposit(User user){
            setTitle("Deposit");
            setSize(framesize, framesize);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(new GridLayout(3, 2, 20, 20));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

            int sz = 30;

            JLabel label1 = new JLabel("Enter Amount : ");
            label1.setFont(labelSizes(sz));
            JTextArea amountField = new JTextArea();
            amountField.setFont(labelSizes(sz));
            panel.add(label1); panel.add(amountField);

            JLabel label2 = new JLabel("PIN : ");
            label2.setFont(labelSizes(sz));
            JPasswordField pinField = new JPasswordField();
            pinField.setFont(labelSizes(sz));
            panel.add(label2); panel.add(pinField);

            JButton DepositBtn = new JButton("Deposit");
            DepositBtn.setFont(labelSizes(sz));
            DepositBtn.addActionListener(e -> {
                try{
                    int pin = Integer.parseInt(new String(pinField.getPassword()));
                    double amount = Double.parseDouble(amountField.getText());
                    if(checkPin(user, pin)){
                        JOptionPane.showMessageDialog(this, "Money Deposited Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        user.addDeposit(amount);
                        BackToInfo(user);
                    }else{
                        JOptionPane.showMessageDialog(this, "Invalid PIN !", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Invalid Input format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            JButton backBtn = new JButton("Back");
            backBtn.setFont(labelSizes(sz));
            backBtn.addActionListener(e -> {
                BackToInfo(user);
            });

            panel.add(DepositBtn); panel.add(backBtn);

            add(panel, BorderLayout.CENTER);
            setVisible(true);
        }

        public void WithDraw(User user){
            setTitle("Withdraw");
            setSize(framesize, framesize);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel(new GridLayout(3, 2, 20, 20));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            int sz = 30;
            JLabel label1 = new JLabel("Enter Amount : ");
            label1.setFont(labelSizes(sz));
            JTextArea AmountField = new JTextArea();
            AmountField.setFont(labelSizes(sz));
            panel.add(label1); panel.add(AmountField);

            JLabel label2 = new JLabel("PIN : ");
            label2.setFont(labelSizes(sz));
            JPasswordField pinField = new JPasswordField();
            pinField.setFont(labelSizes(sz));
            panel.add(label2); panel.add(pinField);

            JButton DepositBtn = new JButton("Withdraw");
            DepositBtn.setFont(labelSizes(sz));
            DepositBtn.addActionListener(e -> {
                try{
                    int pin = Integer.parseInt(new String(pinField.getPassword()));
                    double amount = Double.parseDouble(AmountField.getText());
                    if(checkPin(user, pin)){
                        if(amount <= user.GetBalance()){
                            JOptionPane.showMessageDialog(this, "Money Withdrawn Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            user.subtractDeposit(amount);
                        }else{
                            JOptionPane.showMessageDialog(this, "You are poor, LOL", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        BackToInfo(user);
                    }else{
                        JOptionPane.showMessageDialog(this, "Invalid PIN !", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Invalid Input format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            JButton backBtn = new JButton("Back");
            backBtn.setFont(labelSizes(sz));
            backBtn.addActionListener(e -> {
                BackToInfo(user);
            });
            panel.add(DepositBtn); panel.add(backBtn);
            add(panel, BorderLayout.CENTER);
            setVisible(true);
        }


    }
}

