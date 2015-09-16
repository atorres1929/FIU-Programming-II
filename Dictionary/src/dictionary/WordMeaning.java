/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author atorr208
 */
public class WordMeaning {
    private String word;
    private ArrayList<String> meanings;
    
    public WordMeaning(String word, String meaning){
        meanings = new ArrayList<>();
        this.word = word;
        meanings.add(meaning);
    }
    public void addMeaning(String meaning){
        meanings.add(meaning);
    }
    public ArrayList<String> getMeanings(){return meanings;}
    public String getWord(){return word;}
    public String getMeaning() throws NullPointerException{
        if (meanings.size() == 1)
            return meanings.get(0);
        return null;
    }
    
    @Override
    public String toString(){
        ListIterator<String> iterator = meanings.listIterator();
        String current = iterator.next();
        String result = word+" - "+current;
        while (iterator.hasNext()){
            result += "\n               - "+iterator.next();
        }
        return result;
        
    }
    
}
