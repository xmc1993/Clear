package clear.ui.test;
import java.awt.Color; 
import java.awt.Component; 
import java.awt.FlowLayout; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Insets; 
import java.awt.RenderingHints; 
import java.awt.Shape; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.awt.geom.RoundRectangle2D; 

import javax.swing.JFrame; 
import javax.swing.JTextField; 
import javax.swing.border.LineBorder; 

public class MyTextField extends JTextField 
{   
    private static final long serialVersionUID = -1946802815417758252L; 
    public static final Color BUTTON_FOREGROUND_COLOR = Color.YELLOW; 
    public boolean hover; 
    
    static int h = 0;// 从JComponent类获取高宽 
    static int w = 0; 
    
    public MyTextField(int columns){ 
        super(columns); 
        setMargin(new Insets(0,5,0,5)); 
    } 
    
    @Override 
    protected void paintBorder(Graphics g) 
    { 
        h = getHeight();// 从JComponent类获取高宽 
        w = getWidth(); 
        
        Graphics2D g2d = (Graphics2D)g.create(); 
        Shape shape = g2d.getClip(); 
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        g2d.setClip(shape); 
        g2d.drawRoundRect(0, 0, w - 2, h - 2, h, h); 
        //g2d.fill3DRect(0, 0, w-2, h-2, true); 
        g2d.setColor(Color.ORANGE); 
        g2d.dispose(); 
        super.paintBorder(g2d); 
    } 


    public static void main(String[] args) 
    { 
        JFrame jf = new JFrame(); 
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        jf.setSize(300, 200); 
        jf.setLayout(new FlowLayout()); 
        
        MyTextField text = new MyTextField(20); 
        JTextField t1 = new JTextField(20); 
        t1.setBorder(new MyBorder(Color.BLUE,1,true)); 
        
        JTextField t2 = new JTextField(20); 
        t2.setBorder(new LineBorder(Color.RED,3,true)); 
        
        jf.add(text); 
        jf.add(t1); 
        jf.add(t2); 
        jf.setVisible(true); 
    } 
    
    static class MyBorder extends LineBorder{ 

        @Override 
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) 
        { 
            Color oldColor = g.getColor(); 
            int i; 

            g.setColor(lineColor); 
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
            for(i = 0; i < thickness; i++)  { 
            if(!roundedCorners) 
                    g.drawRect(x+i, y+i, width-i-i-1, height-i-i-1); 
            else 
                    g.drawRoundRect(x+i, y+i, width-i-i-1, height-i-i-1, h, h); 
            } 
            g.setColor(oldColor); 
        } 

        public MyBorder(Color color,int thickness,boolean roundedCorners) 
        { 
            super(color,thickness,roundedCorners); 
        } 
        
    } 
    
} 