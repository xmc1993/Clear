package clear.ui.Frame;

import javax.swing.*;

import clear.ui.network.LunchClient;

import java.awt.*;
import java.awt.event.*;

public class LunchFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String message="";
	JTextField cmd = new JTextField();
	JTextArea area = new JTextArea();
	JButton button = new JButton("发送请求");
	
	boolean bConnected = false;
	
	public LunchFrame(){
		initFrame();
	}
	private void initFrame(){
		
		Container content = getContentPane();
		
		content.setLayout(null);
		
		//输出数据流
		cmd.setBounds(40,10,200,30);
		button.setBounds(280,10,90,23);
		area.setBounds(40,70,300,160);
		content.add(cmd) ;
		content.add(button) ;
		content.add(area);
		button.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LunchClient client = new LunchClient();
				
				//area.append(client.connect(cmd.getText())+"\r\n") ;
			
			}
			
		});
		setTitle("模拟客户端") ;
		setSize(400,300);
		setLocation(200,100);
		setVisible(true);
		
	}
	public static void main(String[] args){
		new LunchFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
