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
public class Transpose extends Cipher
{
    Transpose(String s)
    {
        super(s);
    }
    public String encode(String word)
    {
        StringBuilder result = new StringBuilder(word);
        result.reverse();
        return result.toString();
    }
    public String decode(String word)
    {
        
        StringBuilder result = new StringBuilder(word);
        result.reverse();
        return result.toString();
    }
}