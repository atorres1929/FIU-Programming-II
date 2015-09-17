/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyGUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Adam
 */
public class BasicFile {
    
    private String contents = "";
    private String name;
    public static final int OPEN = 1;
    public static final int SAVE = 0;
    public BasicFile(int mode, String contents){
        switch(mode){
            case 1:
                open();
                break;
            case 0:  
                try {
                    save(contents);
                } catch (IOException ex) {
                    Logger.getLogger(BasicFile.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }
    private void save(String contents) throws IOException{
        boolean choosingPath = true;
        String path = null;
        while (choosingPath){
            JFileChooser chooser = new JFileChooser("\\C:");
            chooser.showSaveDialog(null);
            path = chooser.getSelectedFile().getAbsolutePath();

            if (!path.endsWith(".txt"))
                path += ".txt";

            int option;
            if ((new File(path).exists())){
                option = JOptionPane.showConfirmDialog(null, "Are you sure you want to override?" + "\n" + path);            
                if (option == JOptionPane.NO_OPTION) 
                    continue;
                else if (option == JOptionPane.CANCEL_OPTION)
                   return;
                else
                    choosingPath = false;
            }
            else
                choosingPath = false;
        }
        BufferedWriter wr = new BufferedWriter(new FileWriter(path));
        try {
            wr.write(contents);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (wr != null)wr.close();
            } catch (IOException ex) {
                
            }
        }
    }
    private void open(){
        JFileChooser chooser = new JFileChooser("\\C:");
        chooser.showOpenDialog(null);
        BufferedReader br = null;
        try {
            name = chooser.getSelectedFile().getName();
            String sCurrentLine;
            br = new BufferedReader(new FileReader(chooser.getSelectedFile()));
            while ((sCurrentLine = br.readLine()) != null) {
                contents += sCurrentLine + "\n";
            }

        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                
            }
        }
    }
    public String getContents(){return contents;}
    public String getName(){return name;}
}
