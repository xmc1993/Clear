package clear.ui.test;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TestJScrollPane extends JFrame {

 private static final long serialVersionUID = 1L;

 public TestJScrollPane() {
  super("TestJScrollPane");
  JPanel p = new JPanel();
  p.setLayout(null);
  this.setLayout(null);
  this.setSize(300, 300);
//  this.setBounds(100, 200, 300, 400);

  JLabel label = new JLabel("����ǳ��Java Swing �������");

  JPanel panel = new JPanel();
  panel.add(label);
  panel.setBackground(Color.blue);
  JScrollPane scrollPane = new JScrollPane(panel);
 // scrollPane.setBounds(100, 100, 100, 300);
  scrollPane.setSize(200, 100);
  /**
   * Ҫ�ӹ�������Ҫ��panel�Ŀ�ߴ���scrollPane�Ŀ��..��ֻҪ���µ�..ֻҪ�ߴ��ھ�����..
   */
  
  panel.setPreferredSize(new Dimension(scrollPane.getWidth() - 50, scrollPane.getHeight()*2));
  scrollPane.setLocation(0, 0);
  
  scrollPane.setBackground(Color.blue);
  p.add(scrollPane);
  p.setSize(200, 100);
  p.setVisible(true);
  p.setBackground(Color.red);
  p.setLocation(0, 0);;
  this.add(p);
  panel.revalidate(); //������������,�ҵĿ�߱���
  this.setVisible(true);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }

 public static void main(String[] args) {
  new TestJScrollPane();
 }
}
