/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author atorr208
 */
public class Dictionary extends JFrame implements ActionListener{
    
    private JTextArea text;
    private ArrayList<JButton> buttons;
    private WordList list;
    private WordList deleted;
    public Dictionary(){
        setLayout(new GridLayout(4,1));   
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setTitle("Dictionary");
        setBackground(Color.white);
        setLocationRelativeTo(null);
        init();
        setVisible(true);
        list = new WordList(WordList.EXISTING_LIST);
        deleted = new WordList(WordList.DELETED_LIST);
        list.add(new WordMeaning("Hello", "A greeting"));
        list.add(new WordMeaning("Cup", "a container from which we drink."));
        list.add(new WordMeaning("Library", "a collection of books"));
        list.add(new WordMeaning("School", "a place of learning"));
        list.add(new WordMeaning("School", "any group of fish"));
        
    }

    /**Cup – a container from which we drink.
Library – a collection of books
School – a place of learning
            -  any group of fish

     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Dictionary();
    }
    public void init(){
        String[] titles = {"Add Word", "View Existing Words", "Delete Word", "View Deleted Words"};
        buttons = new ArrayList<>();
        for (int i = 0; i < titles.length; i++){
            buttons.add(new JButton(titles[i]));
            buttons.get(i).addActionListener(this);
            add(buttons.get(i));
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Add Word":
                String word = JOptionPane.showInputDialog("Enter the word you wish to add:");
                String meaning = JOptionPane.showInputDialog("Enter the meaning of the word:");
                addWord(word, meaning);
                break;
            case "View Existing Words":
                viewWords();
                break;
            case "Delete Word":
                word = JOptionPane.showInputDialog("Enter the word you wish to be deleted");
                deleteWord(word);
                break;
            case "View Deleted Words":
                viewDeletedWords();
                break;
            default:
                break;
        }
    }

    private void addWord(String word, String meaning) {
        if (word == null || meaning == null || word.equals("") || meaning.equals(""))
            JOptionPane.showMessageDialog(this, "You must enter both a meaning and a word!", "Error!", JOptionPane.ERROR_MESSAGE);
        else{
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            list.add(new WordMeaning(word, meaning));
            JOptionPane.showMessageDialog(this, word + " has been added to the dictionary!");
        }
    }

    private void viewWords() {
        text = new JTextArea();
        text.setEditable(false);
        text.setText(list.toString());
        if (text.getText().equals(""))
            JOptionPane.showMessageDialog(this, "There are no existing words!");
        JScrollPane scrollPane = new JScrollPane(text);  
        scrollPane.setPreferredSize( new Dimension(300, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Existing Words", JOptionPane.PLAIN_MESSAGE);
    }

    private void deleteWord(String word) {
        if (word == null || word.equals("")){
            JOptionPane.showMessageDialog(this, "You must enter a word to be deleted!");
            return;
        }
        boolean success = false;
        try{
            WordMeaning temp = list.remove(word);
            deleted.add(temp);
            if (temp != null)
                success = true;
            else
                throw new NullPointerException();
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(this, "The word you tried to remove was not in the dictionary!"
                    + "\nEnsure proper capitalization!");
        }
        if (success)
            JOptionPane.showMessageDialog(this, "The word: "+word+" has been deleted!");
    }

    private void viewDeletedWords() {
        text = new JTextArea();
        text.setEditable(false);
        text.setText(deleted.toString());
        if (text.getText().equals(""))
            JOptionPane.showMessageDialog(this, "There are no deleted words!");
        JOptionPane.showMessageDialog(this, new JScrollPane(text), "Deleted Words", JOptionPane.PLAIN_MESSAGE);
    }
    
    
}
