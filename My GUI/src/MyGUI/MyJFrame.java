/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Adam
 */
public class MyJFrame extends JFrame implements ActionListener, ClipboardOwner{
    
    public static Clipboard clipboard;
    
    //Constants
    private final String title = "My GUI";
    private final String[] MENUS = new String[]{"File", "Tool", "Help"};
    private final String[] FILEITEMS = new String[]{"New", "List Files", "-", "Save As", "-", "Close"};
    private final String[] TOOLITEMS = new String[]{"Sort", "Search", "Edit"};
    private final String[] EDIT = new String[]{"Copy", "Paste"};
    private final String[] buttons = new String[]{"Drawing", "Close", "drawing.jpg", "Browser"};
    private final int HEIGHT = 400;
    private final int WIDTH = 600;
    
    
    public final Dimension size = new Dimension(WIDTH,HEIGHT);
    private final JMenuBar MENUBAR = new JMenuBar();
    private final GridBagLayout layout;
    private GridBagConstraints constraints;
    Container container;
    public MyJFrame() {
        container = getContentPane();
        layout= new GridBagLayout();
        constraints = new GridBagConstraints();
        container.setLayout(layout);
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        container.setBackground(Color.WHITE);
        container.setForeground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setSize(size);
        setLocationRelativeTo(null);
        buildMenu();
        buildButtons();
        setVisible(true);
    }
    
    private void buildMenu(){
        JMenuItem mi;
        JMenu m;
        for (int i = 0; i < MENUS.length; i++) {	//Build and add each menu choice onto the menubar
            m = new JMenu(MENUS[i]); 
            switch(i){
                case 0:
                    for (String FILEITEMS1 : FILEITEMS) {
                        if (FILEITEMS1.equals("-")) {
                            m.addSeparator();
                        } else {
                            m.add(mi = new JMenuItem(FILEITEMS1));
                            mi.addActionListener(this);
                        }    
                    }
                    break;
                case 1:
                    for (String TOOLITEMS1 : TOOLITEMS) {
                        switch (TOOLITEMS1) {
                            case "-":
                                m.addSeparator();
                                break;
                            case "Edit":
                                JMenu edit = new JMenu("Edit");
                                for (String EDIT1 : EDIT) {
                                    edit.add(mi = new JMenuItem(EDIT1));
                                    mi.addActionListener(this);
                                }
                                m.add(edit);
                                break;
                            default:
                                m.add(mi = new JMenuItem(TOOLITEMS1));
                                mi.addActionListener(this);
                                break;
                        }
                    }
                    break;
                default:
                    break;
            }
            MENUBAR.add(m);
            setJMenuBar(MENUBAR);
        }
        
    }
    private void buildButtons(){
        JButton b;
        for (int i = 0; i < buttons.length; i++){
            b = new JButton(buttons[i]);
            switch(i){
                case 0:
                    constraints.gridx = 0;
                    constraints.gridy = 0;
                    constraints.insets = new Insets(0,40,0,150);
                    break;
                case 1:
                    constraints.gridx = 2;
                    constraints.gridy = 0;
                    break;
                case 2:
                    try{
                        URL imgURL = getClass().getResource(buttons[i]);
                        b = new JButton(new ImageIcon(imgURL));
                        constraints.gridx = 0;
                        constraints.gridy = 1;
                        constraints.insets = new Insets(50, 250, 50, 0);
                    }catch(NullPointerException e){
                        e.printStackTrace();
                    }
                    break;
                case 3: 
                    constraints.gridy = 2;
                    constraints.insets = new Insets(0,125,0,125);
                    constraints.fill = GridBagConstraints.HORIZONTAL;
                    constraints.gridwidth = 3;
                    break;
                default:
                    break;
            }
            container.add(b, constraints);
            b.addActionListener(this);
        }  
    }

    public static void main(String[] args) {
        new MyJFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DisplayText frame = null;
        try{
            switch(e.getActionCommand()){
                case "New":
                    frame = new DisplayText("New File",null);
                    frame.setLocation(new Point(frame.getLocation().x+50, frame.getLocation().y+50));
                    break;
                case "List Files":
                    try{
                        BasicFile open = new BasicFile(BasicFile.OPEN, null);
                        frame = new DisplayText(open.getName(), open.getContents());
                        frame.setLocation(new Point(frame.getLocation().x+50, frame.getLocation().y+50));
                    }catch(NullPointerException ex){
                        ex.printStackTrace();
                    }
                    break;
                case "Close":
                    addWindowListener(new WindowAdapter(){
                            @Override
                            public void windowClosing(WindowEvent we) {   
                                JOptionPane.showMessageDialog(null, "The Window is closing", "Window Closing", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);
                            } 
                    });
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    break;
                case "Browser":
                    new Browser();
                    break;
                case "Save":
                    try{
                        
                    }catch(NullPointerException ex){
                        ex.printStackTrace();
                    }
                    break;
                case "Drawing":
                    new MyGraphics();
                    break;
                default:
                    break;
            }
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
