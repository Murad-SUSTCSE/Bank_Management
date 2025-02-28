package out;

import java.io.*;

public class User implements Serializable{
    
    private String name;
    private String email;
    private String phone;
    private String accountNumber;
    private String Gender;
    private String DateOfBirth;
    private String accountType;
    private int pin;
    private double AccountBalance;

    public User(String name, String email, String phone, String accountNumber, String Gender, 
                            String DateOfBirth, String accountType, int pin, double AccountBalance){


                this.name = name;
                this.email = email;
                this.phone = phone;
                this.accountNumber = accountNumber;
                this.Gender = Gender;
                this.DateOfBirth = DateOfBirth;
                this.accountType = accountNumber;
                this.pin = pin;
                this.AccountBalance = AccountBalance; 
    }

    public String Getname(){return name;}
    public String Getemail(){return email;}
    public String Getphone(){return phone;}
    public String GetaccountNumber(){return accountNumber;}
    public String GetGender(){return Gender;}
    public String GetDateOfBirth(){return DateOfBirth;}
    public String GetaccountType(){return accountType;}
    public double GetBalance(){return AccountBalance;}
    public int Getpin(){return pin;}
    public void addDeposit(double amount){
        AccountBalance += amount;
    }

    public void subtractDeposit(double amount){
        AccountBalance -= amount;
    }

    public void SaveUser(){
        File folder = new File("Users");
        if(!folder.exists())folder.mkdir();

        try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Users/" + accountNumber + ".dat"))){
            o.writeObject(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static User loadUser(String AccountNumber){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("Users/" + AccountNumber + ".dat"))) {
            return (User) in.readObject();
        } catch (Exception e) {
            return null;
        }
    }

}
