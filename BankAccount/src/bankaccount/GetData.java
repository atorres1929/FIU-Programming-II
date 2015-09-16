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
public class GetData{
        
    public static int getInt(String message, String title){
        boolean gettingAnswer = true;
        String ans = null;
        while (gettingAnswer){
            ans = JOptionPane.showInputDialog(null, message, JOptionPane.PLAIN_MESSAGE);
            if (!ans.equals(""))
                gettingAnswer = false;
            else
                JOptionPane.showMessageDialog(null, "You must enter an integer value!");
        }
        return Integer.parseInt(ans);           
    }
    public static int getInt(String message){
        boolean gettingAnswer = true;
        String ans = null;
        while (gettingAnswer){
            ans = JOptionPane.showInputDialog(null, message, JOptionPane.PLAIN_MESSAGE);
            if (!ans.equals(""))
                gettingAnswer = false;
            else
                JOptionPane.showMessageDialog(null, "You must enter an integer value!");
        }
        return Integer.parseInt(ans);
        
    }
    public static String getString(String message, String title){
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
    }
    public static String getString(String message){
        return JOptionPane.showInputDialog(null, message, JOptionPane.QUESTION_MESSAGE);
    }
        
}
