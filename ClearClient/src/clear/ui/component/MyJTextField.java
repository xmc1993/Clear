package clear.ui.component;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class MyJTextField extends JTextField{
	private TexturePaint texture;
	private BufferedImage img;
	public MyJTextField()  {
		// 通过文件的方式获取BufferedImage
		try{
		File file = new File("src/images/textfield2.jpg");
		img = ImageIO.read(file);
		// 根据指定图像的大小创建一个基准矩形,此矩形和图像大小相等（如果想矩形小一点，，也可绘制部分）
		Rectangle rect = new Rectangle(0, 0, img.getWidth(null),
				img.getHeight(null));
		/** TexturePaint 类提供一种用于被指定为 BufferedImage 的纹理填充 Shape 的方式 **/
		texture = new TexturePaint(img, rect);
		// 使此文本框透明
		setOpaque(false);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		/** 在调用父类的绘图方法前绘制好背景 **/
		Graphics2D g2 = (Graphics2D) g;
		// 为 Graphics2D 设置 TexturePaint 属性
		g2.setPaint(texture);
		// 获取文本框的的大小，并将其全部填充
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// 然后重用父类的绘图方法，比如绘制文本字符信息等
		super.paintComponent(g);
	}
	
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Swing Hacks 005：添加背景的文本框");

		JTextField textfield = new MyJTextField();
		textfield.setText("                                            ");
		textfield.setFont(textfield.getFont().deriveFont(25f));//设置文本字体
		frame.getContentPane().add(textfield, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}
}
