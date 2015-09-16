/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cryptography;

/**
 *
 * @author Adam
 */
public class Reverser extends Transpose
{
    public Reverser(String s)
    {
        super(s);
    }
    public String reverseText(String word)
    {
        String reversed = "";
        for (int i = word.length()-1; i >= 0; i--){
            reversed += word.charAt(i);
        }
        return reversed;
    }
    public String encode(String word){
        return reverseText(word);
    }
    public String decode(String word)
    {
        return reverseText(word);
    }
}
