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
public class WordList {
    public static final int EXISTING_LIST= 0;
    public static final int DELETED_LIST = 1;
    public WordMeaningNode list;
    private final int mode;
    public WordList(int mode){
        list = null;
        this.mode = mode;
    }
    public void add(WordMeaning w)// pre-pending
    {
        WordMeaningNode temp = new WordMeaningNode(w);
        try
        {
            WordMeaningNode aux = list;
            WordMeaningNode back = null;
            boolean found = false;
            boolean same = false;
            while(aux != null && !found){
                if (temp.equals(aux)){
                    found = true;   
                    same = true;
                }
                else if( temp.word.getWord().compareTo(aux.word.getWord()) < 0 )
                    found = true;
                else
                {
                    back = aux;
                    aux = aux.next;
                }
            }
            if (same != true){
                temp.next = aux;
                if (back == null)
                    list = temp;
                else
                    back.next = temp;
            }
            else{
                try{
                    aux.word.addMeaning(w.getMeaning());
                }catch(NullPointerException e){
                    System.out.println("Tried to add meaning to existing word! There is more than one meaning being added!");
                }
            }

        }
        catch(NullPointerException e)
        {

        }
    }
    //Returns the node it removes
    public WordMeaning remove(String word){
        try
        {
            WordMeaningNode aux = list;
            WordMeaningNode back = null;
            boolean found = false;
            while(aux != null && !found){
                if (word.equals(aux.word.getWord())){
                    found = true;   
                }
                else{
                    back = aux;
                    aux = aux.next;
                }
            }
            if (!found)
                return null;
            
            WordMeaning temp = aux.word;
            if (back != null)
                back.next = aux.next;
            if (back == null){
                list = list.next;
            }
            return temp;

        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString()
    {
            String result = "";
            WordMeaningNode current = list;

            while (current != null)
            {   switch(mode){
                    case 0:
                        result += current.word + " \n";
                        break;
                    case 1:
                        try{
                            result += current.word.getWord() + " \n";
                        }catch(NullPointerException e){}
                        break;
                    default:
                        break;
                }
                current = current.next;
            }
            return result;
    }
    
}
