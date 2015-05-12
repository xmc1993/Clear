package clear.ui.test;

import java.awt.Color;  
import java.awt.Component;  
import java.awt.Font;
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.RenderingHints;  
  



import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;  
  
public class MyLineBorder extends LineBorder{  
  
  
    private static final long serialVersionUID = 1L;  
      
    public MyLineBorder(Color color, int thickness, boolean roundedCorners) {  
        super(color, thickness, roundedCorners);  
    }  
  
  
  
     public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {  
           
         RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,  
                                                RenderingHints.VALUE_ANTIALIAS_ON);   
         Color oldColor = g.getColor();  
         Graphics2D g2 = (Graphics2D)g;  
         int i;  
         g2.setRenderingHints(rh);  
         g2.setColor(lineColor);  
         for(i = 0; i < thickness; i++)  {  
         if(!roundedCorners)  
             g2.drawRect(x+i, y+i, width-i-i-1, height-i-i-1);  
         else  
             g2.drawRoundRect(x+i, y+i, width-i-i-1, height-i-i-1, 5, 5); //就是这一句  
         }  
         g2.setColor(oldColor);  
    } 
     public static void main(String[] args){
    	 JTextField nameTextField = new JTextField();  
         nameTextField.setBounds(120, 47, 130, 25);  
         //添加边框  
         MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1 , true);//这一句  
         nameTextField.setBorder(myLineBorder);  
         Font font = new Font("某字体",Font.HANGING_BASELINE,15);  
         nameTextField.setFont(font);  
         JFrame frame = new JFrame();
         frame.getContentPane().add(nameTextField);
         
         frame.setVisible(true);
         frame.setSize(100, 100);
     }
}  