package clear.ui.component;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProgressThread extends Thread{
	private JLabel timeBar,timeLabel;
	private int len;
	private final static int time=1000;
	private JPanel panel;
	
	public ProgressThread(JLabel timeBar,JLabel time,JPanel panel){
		this.timeBar=timeBar;
		timeLabel=time;
		len=420;
	}
	public void run(){
		int x=timeBar.getBounds().x;
		int y=timeBar.getBounds().y;
		int height=timeBar.getBounds().height;
		try {
			sleep(time);  //每隔一秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true){
			if(len<=0){
				break;
			}
			len=len-7;
			timeBar.setBounds(x,y,len,height);	
			timeBar.setText(len/7+"s");
			timeLabel.setText(len/7+"s");
			try {
				sleep(time);  //每隔一秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
