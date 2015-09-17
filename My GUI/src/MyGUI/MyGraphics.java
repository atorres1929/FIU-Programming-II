/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Adam
 */
public class MyGraphics extends JFrame {
    
    private WhiteBoard board;
    public MyGraphics(){
        board = new WhiteBoard();
        setContentPane(board);
        setTitle("My WhiteBoard");
        setSize(800,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
    }

    public static class WhiteBoard extends JPanel implements MouseListener, MouseMotionListener {
         int    X_POS 		= 75,
                Y_POS 		= 250,
                X_FACTOR	=   5,
		Y_FACTOR	=   10,
		starX[] = {55, 67, 109, 73, 83, 55, 27, 37, 1, 43},
		starY[] = {50, 86, 86, 104, 146, 122, 146, 104, 86, 86};

		int pentX[] = {100, 20, 20, 180, 180};
		int pentY[] = {180, 250, 300, 300, 250};
                
        private Graphics g;
        private Point pos;
        public WhiteBoard(){
            setBackground(Color.WHITE);
            addMouseListener(this);
            addMouseMotionListener(this);
        }
        
         @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.BLACK);
g.drawLine(10, 10, 10, 50);
g.setColor(Color.RED);
g.drawRect(100, 100, 150, 150);
            g.setFont(new Font("Times New Roman", Font.ITALIC, 24));
            g.setColor(Color.BLACK);
            g.drawString("My house", 60, 325);
            g.setColor(Color.YELLOW);
            g.fillPolygon(new Polygon(starX, starY, starX.length));
            g.setColor(Color.BLUE);
            g.fillPolygon(new Polygon(pentX, pentY, pentX.length));
            g.setColor(Color.WHITE);
            g.fillRect(X_POS, Y_POS, 50, 50);
            g.setColor(Color.BLUE);
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            pos = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            g = getGraphics();
            g.setColor(Color.GREEN);
            g.drawOval(e.getX(), e.getY(), X_FACTOR, Y_FACTOR);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            pos = e.getPoint();
            g = getGraphics();
            g.setColor(Color.BLUE);
            g.drawOval(pos.x, pos.y, X_FACTOR, Y_FACTOR);
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {}
        
        @Override
        public void mouseEntered(MouseEvent e) {}
        
        @Override
        public void mouseExited(MouseEvent e) {}
        
        @Override
        public void mouseMoved(MouseEvent e) {}

    }
    
    
}
