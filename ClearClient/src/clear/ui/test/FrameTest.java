package clear.ui.test;

import javax.swing.JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
public class FrameTest extends JFrame {
    Container con;
    JComboBox jcb;
    JPanel p;
    public FrameTest(){
        con=this.getContentPane();
        p=new JPanel();
        String []stra={"kelsen","kelsen"};
        jcb=new JComboBox();
        jcb.addItem("kelsen1");
        jcb.addItem("kelsen2");
        jcb.addItem("kelsen3");
        jcb.addItem(stra);
        jcb.setEditable(true);
        p.add(jcb);
        con.add(p);
        this.setSize(500,500);
        this.setUndecorated(true);   //ȥ��JFrame�ı߿�͸�JWindowһ��
        this.setVisible(true);
        jcb.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println(jcb.getSelectedItem());
            }
            
        });
    }
    public static void main(String args[]){
        new FrameTest();
        //jcbt.setUndecorated(false);
    }
}
