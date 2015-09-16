/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bankaccount;

import bankaccount.Customer.Address;
import bankaccount.Customer.Name;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Adam
 */
public class Bank {
    
    private final DataBase existingAccounts;
    private final DataBase canceledAccounts;
    public Bank(DataBase active, DataBase canceled){
        existingAccounts = active;
        canceledAccounts = canceled;
        
        existingAccounts.addAccount(new Account(new Customer(new Name("Adam", "Torres"), 
                new Address("5521", "South West 64 Place", "33155", "Miami", "Florida"),
                "2497"),
                500));
        canceledAccounts.addAccount(new Account(new Customer(new Name("John", "Smith"), 
                new Address("6291", "South North 92 Street", "92148", "JavaTown", "California"),
                "9621"),
                600));
    }
    
    public void createAccount(){
        boolean gettingName = true;
        Customer.Name name = null;
        while(gettingName){
            String first = GetData.getString("Enter your first name:");
            String last = GetData.getString("Enter your last name:");
            int correctName = JOptionPane.showConfirmDialog(null, "Is this correct?"
                    + "\n"+first+" "+last);
            if (correctName == JOptionPane.OK_OPTION){
                gettingName = false;
                name = new Customer.Name(first, last);
            }
            else{
                int tryAgain = JOptionPane.showConfirmDialog(null, "Would you like to try again?");
                if (tryAgain != JOptionPane.OK_OPTION){
                    return;
                }
            }
        }
        boolean gettingAddress = true;
        Customer.Address address = null;
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
                address = new Customer.Address(houseNumber, street, zip, city, state);
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
                String deposit = null;
                while (gettingDeposit){
                     deposit = GetData.getString("Enter your first deposit:", "Deposit");
                     if (deposit.equals(""))
                         JOptionPane.showMessageDialog(null, "Please enter a real number");
                     else if (Double.parseDouble(deposit) < 1.0)
                         JOptionPane.showMessageDialog(null, "You cannot have an initial balance"
                                 + "\nless than $1!");
                     else
                         gettingDeposit = false;
                }
                Account account = new Account(customer, Math.round(Double.parseDouble(deposit)*100.00)/100.00);
                existingAccounts.addAccount(account);
                JOptionPane.showMessageDialog(null, "Congratulations! You've created your account!"
                        + "\nYour account number is: "+account.getAccountNumber()
                        + "\nYour initial balance is: $"+account.getBalance()
                        + "\nPlease write it down in a safe place before exiting!");
                creatingAccount = false;
            }
            else{
                JOptionPane.showMessageDialog(null, "Try creating your account again.");
                return;
            }
        }
   
    }
    public void updateAccount(){
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
                String deposit = GetData.getString("Enter the amount you wish to deposit");
                account.deposit(Double.parseDouble(deposit));
                JOptionPane.showMessageDialog(null, "Your new balance is: $"+account.getBalance());
                break;
            case "2":
                String withdraw = GetData.getString("Enter the amount you wish to withdraw");
                account.withdraw(Double.parseDouble(withdraw));
                JOptionPane.showMessageDialog(null, "Your new balance is: "+account.getBalance());
                break;
            default:
                JOptionPane.showMessageDialog(null, "Option not valid! Please enter a number 1-4!");
                break;
        }
        
    }
    public void cancelAccount(){
        String accountNumber = GetData.getString("Enter your account number:");
        if (existingAccounts.findAccount(accountNumber) == null)
            return;
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you wish to cancel your account?", "Confirmation",JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION){  
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
    public void viewAccount(){
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
                break;
                
                
                
        }
        
    }
    private void viewAccount(String accountNumber){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        if (existingAccounts.findAccount(accountNumber) != null){
            Account account = existingAccounts.findAccount(accountNumber);
            JOptionPane.showMessageDialog(null, "Account Number: "+ account.getAccountNumber() + " || Date: "+ dateFormat.format(date)
                    +"\n Customer: "+account.getCustomer().getName()
                    +"\n Balance: $"+account.getBalance());
        }
        
    }

    private void listActiveAccounts() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        
        JTextArea textArea = new JTextArea("---Active Accounts---\n");
        textArea.setLineWrap(false);  
        textArea.setWrapStyleWord(true); 
        textArea.setEditable(false);
        if (!existingAccounts.getAccounts().isEmpty()){
            for (Account a: existingAccounts.getAccounts()){
                textArea.append("\nDate: "+ dateFormat.format(date) +"    ||AC: "+ a.getAccountNumber() +"||   "+ a.toString()
                                +"\nBalance: $"+a.getBalance()+"\n");
            }
        }
        JScrollPane scrollPane = new JScrollPane(textArea);  
        
        scrollPane.setPreferredSize( new Dimension(300, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Active Accounts",  
                                       JOptionPane.YES_NO_OPTION);
    }

    private void listCanceledAccounts() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        
        JTextArea textArea = new JTextArea("---Canceled Accounts---\n");
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        textArea.setEditable(false);
        if (!canceledAccounts.getAccounts().isEmpty()){
            for (Account a: canceledAccounts.getAccounts()){
                  textArea.append("\nDate: "+ dateFormat.format(date) +"    ||AC: "+ a.getAccountNumber() +"||   "+ a.getCustomer().getName());
            }  
        }
        
        JScrollPane scrollPane = new JScrollPane(textArea);  
        
        scrollPane.setPreferredSize( new Dimension(500, 500));
        JOptionPane.showMessageDialog(null, scrollPane, "Canceled Accounts",  
                                       JOptionPane.YES_NO_OPTION);
    }
    
    
    
}
