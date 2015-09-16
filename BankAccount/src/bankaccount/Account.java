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
public class Account {

    private final Customer customer;
    private double balance;
    
    public Account(Customer c, double startBalance){
        this.customer = c;
        if (startBalance < 1.0){
            JOptionPane.showMessageDialog(null, "You cannot make an account with an"
                    + "\ninitial balance less than $1!");
            return;
        }
        balance = startBalance;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    public double getBalance(){
        return balance;
    }
    
    public String getAccountNumber(){
        return customer.getAccountNumber();
    }
    
    public void deposit(double amount){
        if (amount <= 1.0){
            JOptionPane.showMessageDialog(null, "You cannot make deposits less than $1!");
        }
        else{
            balance += amount;
            balance = Math.round(balance*100.00)/100.00;
        }
        
    }
    public void withdraw(double amount){
        if (amount > balance){
            JOptionPane.showMessageDialog(null, "You cannot withdraw more "
                    + "\nthan you have in your account!");
        }
        else if (amount <= 0){
            JOptionPane.showMessageDialog(null, "You cannot withdraw an amount"
                    + "\nless than or equal to 0!");
        }
        else{
            balance -= amount;
            balance = Math.round(balance*100.00)/100.00;
        }
    }
    @Override
    public String toString(){
        return customer.toString();
    }
    
}
