/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyGUI;

import static MyGUI.MyJFrame.clipboard;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 *
 * @author Adam
 */
public class Browser extends MyJFrame{
    
    JTextField addressBar;
    JEditorPane contents;
    public Browser(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        container.removeAll();
        setTitle("My Browser");
        addressBar = new JTextField("http://");
        addressBar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                getThePage(e.getActionCommand());
            }
        });
        container.setLayout(new BorderLayout());
        container.add(addressBar, BorderLayout.NORTH);
        
        contents = new JEditorPane();
        contents.setEditable(false);
        contents.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                        getThePage(e.getURL().toString());
                }
            }
        );
        
        JScrollPane scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getViewport().add(contents);
        container.add(scroll, BorderLayout.CENTER);
        setVisible(true);
    }
    private void getThePage(String page){
        container.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try{
            contents.setPage(page);
            addressBar.setText(page);
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error cannot access specified URL", "Bad URL", JOptionPane.ERROR_MESSAGE);
        }finally{
            container.setCursor(Cursor.getDefaultCursor());
        }
        
    }
    
}
