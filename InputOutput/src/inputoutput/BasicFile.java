package inputoutput;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BasicFile
{
    private File f;
    private File backup;
    private int characters = 0;
    private int numbers = 0;
    private int words = 0;
    private int lines = 0;
    String path;
    private ArrayList<String> filesAndDirectories;
    private long size;
    
    

    public BasicFile()
    {
        JFileChooser choose = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        choose.setFileFilter(filter);
        int status = choose.showOpenDialog(null);
        try
        {
            if (status != JFileChooser.APPROVE_OPTION) 
                throw new IOException();
            
            f = choose.getSelectedFile();
            if (!f.exists()) 
                throw new FileNotFoundException();
            makeBackup();
            size = f.length();
            path = f.getAbsolutePath();
            File temp = new File (f.getParent());
            File[] files = temp.listFiles();
            if (files.length > 0)
                filesAndDirectories = new ArrayList<String>();
            for (File file : files) {
                if (file.isDirectory()) {
                    filesAndDirectories.add("[DIR] " + file.getName());
                }
                if (file.isFile()) {
                    filesAndDirectories.add("[FILE] " + file.getName());
                }
            }
            
            
        }
        catch(FileNotFoundException e)
        {
            display(e.toString(), "File not found ....");
        }
        catch(IOException e)
        {
            display(e.toString(),  "Approve option was not selected");
        }
    }
    
    private void makeBackup() throws FileNotFoundException{
        try {
            JOptionPane.showMessageDialog(null, "Select or create a backup File.");
            JFileChooser choose = new JFileChooser(".");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
            choose.setFileFilter(filter);
            choose.showSaveDialog(null);
            String path = choose.getSelectedFile().getName();
            if (!path.endsWith(".txt"))
                path += ".txt";
            DataInputStream in = new DataInputStream(new FileInputStream(f));
            DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
            while (in.available() > 0)
                out.writeByte(in.readByte());
            in.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(BasicFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void display(String msg, String s)
    {
        JOptionPane.showMessageDialog(null, msg, s, JOptionPane.ERROR_MESSAGE);
    }
    public String search(String param) throws IOException{
        LineNumberReader reader = new LineNumberReader(new FileReader(f));
        String currentLine = reader.readLine().toLowerCase();
        String found = "";
        while(currentLine != null){
            if (currentLine.contains(param)){
                found += reader.getLineNumber()+": "+currentLine+"\n";
                currentLine = reader.readLine().toLowerCase();       
            }
            else if (reader.ready()){
                currentLine = reader.readLine().toLowerCase();
            }else
                break;
        }
        System.out.println(found);
        if (!found.equals(""))
            return found;
        return "The specified String is not within "+getName();
    }
    public String getContents() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(f));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        tokenizer.eolIsSignificant(true);
//        String currentLine = reader.readLine();
        while(tokenizer.ttype != StreamTokenizer.TT_EOF){
            tokenizer.nextToken();
            if (tokenizer.ttype == StreamTokenizer.TT_EOF)
                lines++;
            if (tokenizer.ttype == StreamTokenizer.TT_EOL)
                lines ++;    
            else if (tokenizer.ttype == StreamTokenizer.TT_NUMBER){
                String num = String.valueOf(tokenizer.nval);
                if (num.endsWith(".0"))
                    num = num.substring(0,num.indexOf("."));
                characters += num.length();
                words++;
                numbers ++;
            }
            else if (tokenizer.ttype == StreamTokenizer.TT_WORD){
                characters += tokenizer.sval.length();
                words++;
            }
        }
        
        return "File: "+f.getName()+" has "+lines+" lines, "+words+" words, "+numbers+" numbers, "+characters+" characters (approximately)";
    }

    public String getFileSize(){
        return String.valueOf(size);
    }

    String getPath() {
        return f.getAbsolutePath();
    }

    String getName() {
        return f.getName();
    }
    String getDirectories(){
        String temp = "";
        if (filesAndDirectories.isEmpty())
            return temp;
        for (String s: filesAndDirectories){
            temp+= s+"\n";
        }
        return temp;
    }
    String getParent(){
        return f.getParent();
    }
    
}