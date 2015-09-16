/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bankaccount;

import javax.swing.JOptionPane;

/**
 *
 * @author Adam
 */
public class Main {
    
    public static void main(String[] args){
        boolean running = true;
        DataBase existingAccounts = new DataBase();
        DataBase canceledAccounts = new DataBase();
        Bank bank = new Bank(existingAccounts, canceledAccounts);
        
        while (running){
            String option = GetData.getString("What would you like to do?"
                    + "\n1. Create a new bank account."
                    + "\n2. Update an existing bank account."
                    + "\n3. Cancel an existing account"
                    + "\n4. View existing account information", "Bank of Code");
            
            switch(option){
                case "1":
                    bank.createAccount();
                    break;
                case "2":
                    bank.updateAccount();
                    break;
                case "3":
                    bank.cancelAccount();
                    break;
                case "4":
                    bank.viewAccount();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Option not valid! Please enter a number 1-4!");
                    break;
            }
        
        
        }
    }
    /*
    public static void createAccount(){
        boolean gettingName = true;
        Name name = null;
        while(gettingName){
            String first = GetData.getString("Enter your first name:");
            String last = GetData.getString("Enter your last name:");
            int correctName = JOptionPane.showConfirmDialog(null, "Is this correct?"
                    + "\n"+first+" "+last);
            if (correctName == JOptionPane.OK_OPTION){
                gettingName = false;
                name = new Name(first, last);
            }
            else{
                int tryAgain = JOptionPane.showConfirmDialog(null, "Would you like to try again?");
                if (tryAgain != JOptionPane.OK_OPTION){
                    return;
                }
            }
        }
        boolean gettingAddress = true;
        Address address = null;
        while(gettingAddress){
            String houseNumber = GetData.getString("Enter your house number:");
            String street = GetData.getString("Enter your street:");
            String zip = GetData.getString("Enter your zip code:");
            String city = GetData.getString("Enter your city:");
            String state = GetData.getString("Enter your state:");
            int correctAddress = JOptionPane.showConfirmDialog(null, "Is this correct?"
                    + "\n"+houseNumber+" "+street
                    + "\n"+zip
                    + "\n"+city +", "+ state);
            if (correctAddress == JOptionPane.OK_OPTION){
                gettingAddress = false;
                address = new Address(houseNumber, street, zip, city, state);
            }
            else{
                int tryAgain = JOptionPane.showConfirmDialog(null, "Would you like to try again?");
                if (tryAgain != JOptionPane.OK_OPTION){
                    return;
                }
            }
        }
        
        boolean creatingAccount = true;
        while (creatingAccount){
            
            boolean generatingNumber = true;
            Customer customer = null;
            while (generatingNumber){
                Random gen = new Random();
                String ranNum = String.valueOf(gen.nextInt(100000));
                customer = new Customer(name, address, ranNum);
                if (!existingAccounts.containsAccountNumber(customer.getAccountNumber()) 
                        && !canceledAccounts.containsAccountNumber(customer.getAccountNumber()))
                    generatingNumber = false;
            }
            if (customer == null){
                JOptionPane.showMessageDialog(null, "Something went horribly wrong!");
                return;
            }
             
            int custInfo = JOptionPane.showConfirmDialog(null, "Is this correct?"
                         + "\nName: "+customer.getName()
                         + "\n\nAddress:\n"+customer.getAdresss());

            if (custInfo == JOptionPane.OK_OPTION){
                
                boolean gettingDeposit = true;
                int deposit = -1;
                while (gettingDeposit){
                     deposit = GetData.getInt("Enter your first deposit:", "Deposit");
                     if (deposit <= 1)
                         JOptionPane.showMessageDialog(null, "You cannot have an initial balance"
                                 + "\nless than $1!");
                     else
                         gettingDeposit = false;
                }
               
                Account account = new Account(customer, deposit);
                existingAccounts.addAccount(account);
                JOptionPane.showMessageDialog(null, "Congratulations! You've created your account!"
                        + "\nYour account number is: "+account.getAccountNumber()
                        + "\nYour initial balance is: "+account.getBalance()
                        + "\nPlease write it down in a safe place before exiting!");
                creatingAccount = false;
            }
            else{
                int tryAgain = JOptionPane.showConfirmDialog(null, "Would you like to try again?");
                if (tryAgain != JOptionPane.OK_OPTION){
                    return;
                }
            }
        }
   
    }
    public static void updateAccount(){
        String accountNumber = GetData.getString("Enter your account number:");
        Account account = existingAccounts.findAccount(accountNumber);
        if (account == null){
//            JOptionPane.showMessageDialog(null, "The account was not found!");
            return;
        }
        String option = GetData.getString("Your current balance is: " + account.getBalance()
                + "\nWhat would you like to do?"
                + "\n1. Deposit"
                + "\n2. Withdraw", "Update");
        switch (option){
            case "1":
                int deposit = GetData.getInt("Enter the amount you wish to deposit");
                account.deposit(deposit);
                JOptionPane.showMessageDialog(null, "Your new balance is: "+account.getBalance());
                break;
            case "2":
                int withdraw = GetData.getInt("Enter the amount you wish to withdraw");
                account.withdraw(withdraw);
                JOptionPane.showMessageDialog(null, "Your new balance is: "+account.getBalance());
                break;
            default:
                JOptionPane.showMessageDialog(null, "Option not valid! Please enter a number 1-4!");
        }
        
    }
    public static void cancelAccount(){
        String accountNumber = GetData.getString("Enter your account number:");
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you wish to cancel your account?", "Confirmation",JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.OK_OPTION){  
            JOptionPane.showMessageDialog(null, "We're sorry to see you go!"
                    + "\nYour account has been canceled.");
            Account account = existingAccounts.findAccount(accountNumber);
            existingAccounts.removeAccount(account);
            canceledAccounts.addAccount(account);
        }
        else{
            JOptionPane.showMessageDialog(null, "Thank you for staying with us!");
        }
        
    }
    public static void viewAccount(){
        String option = GetData.getString("What would you like to do?"
                + "\n1. Find a specific account's information"
                + "\n2. List all active accounts"
                + "\n3. List all canceled accounts");
        switch (option){
            case "1":
                String aN = GetData.getString("Enter the number of the account"
                        + "\nyou wish to view.");
                viewAccount(aN);
                break;
            case "2":
                listActiveAccounts();
                break;
            case "3":
                listCanceledAccounts();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Option not valid! Please enter a number 1-4!");
                
                
                
        }
        
    }
    public static void viewAccount(String accountNumber){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Account account = existingAccounts.findAccount(accountNumber);
        JOptionPane.showMessageDialog(null, "Account Number: "+ account.getAccountNumber() + "|| Date: "+ dateFormat.format(date)
                +"\n Customer: "+account.getCustomer().getName()
                +"\n Balance: "+account.getBalance());
    }*/
    
}
