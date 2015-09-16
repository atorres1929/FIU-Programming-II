/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary;

/**
 *
 * @author atorr208
 */
public class WordMeaningNode {
    public WordMeaning word;
    public WordMeaningNode next;
    
    public WordMeaningNode(WordMeaning word){
        this.word = word;
        next = null;
    }
    public WordMeaningNode(WordMeaning word, WordMeaningNode next){
        this.word = word;
        this.next = next;
    }
    public boolean hasNext(){
        return next == null;
    }
    public void setLink(WordMeaningNode w){
        next = w;
    }
    public WordMeaning getWordMeaning(){return word;}

    public boolean equals(WordMeaningNode obj) {
        return word.getWord().equals(obj.word.getWord());
    }
    
    
}
