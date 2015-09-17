/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyGUI;

import static MyGUI.MyJFrame.clipboard;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Adam
 */
public class DisplayText extends MyJFrame implements ActionListener{
    
    public JTextArea text;
    private JScrollPane scroll;
    public DisplayText(String name, String contents){
        container.removeAll();
        container.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        scroll = new JScrollPane();
        text = new JTextArea();
        text.setFont(new Font("monospaced", Font.PLAIN, 14));
        text.setLineWrap(false);
        text.setVisible(true);
        scroll.getViewport().add(text);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVisible(true);
        container.add(scroll, BorderLayout.CENTER);
        if (name != null)
            setTitle(name);
        else
            setTitle("New File");
        if (contents != null)
            text.setText(contents);
        
    }
    public String selectText() {
        String result = "";
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                result = (String)contents.getTransferData(DataFlavor.stringFlavor);
            }
            catch (UnsupportedFlavorException | IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    public String getClipboardContents() {
        String result = "";
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                result = (String)contents.getTransferData(DataFlavor.stringFlavor);
            }
            catch (UnsupportedFlavorException | IOException ex){
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public void insertText(int where, String what) throws NullPointerException{
        text.insert(what, where);
    }
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        switch(e.getActionCommand()){
            case "Copy":
                try{
                    StringSelection stringSelection = new StringSelection(text.getSelectedText());
                    clipboard.setContents(stringSelection, this);
                    break;
                }
                catch(NullPointerException ex){
                    ex.printStackTrace();
                }
                break;
            case "Paste":
                try{
                    insertText(text.getCaretPosition(), getClipboardContents());
                }catch(NullPointerException ex){
                    ex.printStackTrace();
                }
                break;
            case "Save As":
                BasicFile file = new BasicFile(BasicFile.SAVE, text.getText());
                break;
            default:
                break;
        }
    }
}
