package inputoutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.ArrayList;

class TestFile
{
        static BufferedWriter writer;
	public static void main(String[] arg)
	{
            boolean done = false;
            BasicFile f = null;
            File output = null;
            String currentFile = "None";
            String menu = "\nEnter option\n1. Open File\n2. Search File\n3. Read File Attributes\n4.Quit";
            while(!done)
            {
                String s = JOptionPane.showInputDialog("Current File: "+currentFile+menu);
                try
                {
                    int i = Integer.parseInt(s);
                    switch(i)
                    {
                        case 1:
                            f = new BasicFile();
                            currentFile = f.getName();
                            display(f.getContents());
                            output = new File(f.getPath()+"output.txt");
                            writer = new BufferedWriter(new FileWriter(output, true));
                            break;
                            
                        case 2:
                            if (f == null){
                                JOptionPane.showMessageDialog(null, "You must open a file first.");
                                break;
                            }
                            String param = JOptionPane.showInputDialog(null, "Enter the string you wish to search for:");
                            if (param.equals("")){
                                JOptionPane.showMessageDialog(null, "You must enter an input.");
                                break;
                            }
                            writer.write(f.search(param.toLowerCase()));
                            display(f.search(param.toLowerCase()));
                            break;
                            
                        case 3:
                            if (f == null){
                                JOptionPane.showMessageDialog(null, "You must open a file first.");
                                break;
                            }
                            writer.write(display(f));
                            break;

                        case 4:
                            done = true;
                            break;
                            
                        default:
                            display("This option is underfined", "Error");
                            break;
                    }
                }
                catch(NumberFormatException | NullPointerException | IOException e)
                {
                    e.printStackTrace();
                    display(e.toString(), "Error");
                }
            }
	}

	public static void display(String s, String err)
	{
            JOptionPane.showMessageDialog(null, s, err, JOptionPane.ERROR_MESSAGE);
	}
	public static void display(String s)
	{
            JOptionPane.showMessageDialog(null, s, "Content", JOptionPane.INFORMATION_MESSAGE);
	}
	public static String display(BasicFile f)
	{
            String s = f.getFileSize() + " bytes" + "\n" + f.getPath();
            String fn = f.getName();
            s += f.getDirectories();
            JOptionPane.showMessageDialog(null, s , "Filename: " + fn, JOptionPane.INFORMATION_MESSAGE);
            return s;
	}
}