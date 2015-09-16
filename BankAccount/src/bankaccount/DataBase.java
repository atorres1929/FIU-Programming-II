/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bankaccount;

import java.util.ArrayList;
import javax.swing.JOptionPane;


public class DataBase {
    
    public ArrayList<Account> accounts;
    public DataBase(){
        accounts = new ArrayList<>();
    }
    
    public ArrayList<Account> getAccounts(){
        return accounts;
    }
    public Account findAccount(String accountNumber){
        for (Account a: accounts){
            if (a.getAccountNumber().equals(accountNumber))
                return a;
        }
        JOptionPane.showMessageDialog(null, "Account not found!");
        return null;
    }
    public boolean containsAccountNumber(String accountNumber){
        for (Account a: accounts){
            if (a.getAccountNumber().equals(accountNumber))
                return true;
        }
        return false;
    }
    public void addAccount(Account a){
        if (a != null)
            accounts.add(a);
    }
    public void removeAccount(Account a){
        accounts.remove(a);
    }
        
    
}
