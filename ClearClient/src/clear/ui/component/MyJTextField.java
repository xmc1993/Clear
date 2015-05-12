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
		// ͨ���ļ��ķ�ʽ��ȡBufferedImage
		try{
		File file = new File("src/images/textfield2.jpg");
		img = ImageIO.read(file);
		// ����ָ��ͼ��Ĵ�С����һ����׼����,�˾��κ�ͼ���С��ȣ���������Сһ�㣬��Ҳ�ɻ��Ʋ��֣�
		Rectangle rect = new Rectangle(0, 0, img.getWidth(null),
				img.getHeight(null));
		/** TexturePaint ���ṩһ�����ڱ�ָ��Ϊ BufferedImage ��������� Shape �ķ�ʽ **/
		texture = new TexturePaint(img, rect);
		// ʹ���ı���͸��
		setOpaque(false);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		/** �ڵ��ø���Ļ�ͼ����ǰ���ƺñ��� **/
		Graphics2D g2 = (Graphics2D) g;
		// Ϊ Graphics2D ���� TexturePaint ����
		g2.setPaint(texture);
		// ��ȡ�ı���ĵĴ�С��������ȫ�����
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Ȼ�����ø���Ļ�ͼ��������������ı��ַ���Ϣ��
		super.paintComponent(g);
	}
	
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Swing Hacks 005����ӱ������ı���");

		JTextField textfield = new MyJTextField();
		textfield.setText("                                            ");
		textfield.setFont(textfield.getFont().deriveFont(25f));//�����ı�����
		frame.getContentPane().add(textfield, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}
}
