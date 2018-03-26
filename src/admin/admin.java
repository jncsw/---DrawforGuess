package admin;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import database.data;
import login.login;
import std.stdButton;
import std.stdframe;

public class admin extends stdframe  implements ActionListener{
	String s;
	public void actionPerformed(ActionEvent e){
		s=e.getActionCommand();
		if(s.equals("add")){
			new admadd();
		}
		else if(s.equals("del")){
			new admdel();
		}else if(s.equals("exi")){
			this.dispose();
		}
	}
	public admin(){
	super(6);
	try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedLookAndFeelException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JButton ne = new JButton("增加数据");
	JButton de = new JButton("删除数据");
	JButton ex = new JButton("退出登录");
	
	ne.setActionCommand("add");
	de.setActionCommand("del");
	ex.setActionCommand("exi");
	
	ne.addActionListener(this);
	de.addActionListener(this);
	ex.addActionListener(this);
	
	this.setLayout(null);
	JLabel  wel = new JLabel("欢迎您，管理员！");
	
	int w = this.getWidth();
	int h = this.getHeight();
	
	int fw=100; int fh = 40;
	int ww = 150;
	
	
	
	wel.setBounds(w/10,h/10,ww,fh);
	ne.setBounds(w*4/10,h*2/10,fw,fh);
	de.setBounds(w*4/10,h*4/10,fw,fh);
	ex.setBounds(w*4/10,h*6/10,fw,fh);
	
	
	this.add(wel);
	this.add(ne);
	this.add(de);
	this.add(ex);
	
	this.setVisible(true);
	
	}
}
