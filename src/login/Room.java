package login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.server.ServerCloneException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chatpanel.server;
import draw.MainFrame;
import std.stdButton;

public class Room extends JFrame implements ActionListener {
	String nam;
	int score ;
	public Room(String name){
		super("选择游戏类型");
		nam = name;
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth  = screenSize.width;
		int screenHeight = screenSize.height;
		this.setBounds(screenWidth/4,screenHeight/4 , screenWidth/2, screenHeight/2);
		
		
		ImageIcon background = new ImageIcon("source/img/bg/1.jpg");
		background.setImage(background.getImage()
				.getScaledInstance(screenWidth/2, screenHeight/2, Image.SCALE_DEFAULT ));
		JLabel bgl = new JLabel(background);
		bgl.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());  
		JPanel imagePanel; 
		imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());  
		
		this.getLayeredPane().setLayout(null);  
		// 把背景图片添加到分层窗格的最底层作为背景  
		this.getLayeredPane().add(bgl, new Integer(Integer.MIN_VALUE));
		
		this.setLayout(null);
		int w = this.getWidth();
		int h = this.getHeight();
		
		
//		JPanel jpl = new JPanel();
//		JPanel jpr = new JPanel();
		
		JLabel lab = new JLabel("欢迎您，"+name+"！");
		lab.setBounds(w/3, h/10, w/3, h/5);		
		this.add(lab);
		
//		jpl.setLayout(new GridLayout(4,1));
		lab.setForeground(new Color(253,235,195));
		
		int bw = 130;int bh = 40;
		
		ImageIcon r1 = new ImageIcon("source/img/btn/r1.jpg");
		ImageIcon r2 = new ImageIcon("source/img/btn/r2.jpg");
		ImageIcon r3 = new ImageIcon("source/img/btn/r3.jpg");
		r1.setImage(r1.getImage().getScaledInstance(bw, bh, Image.SCALE_DEFAULT ));
		r2.setImage(r2.getImage().getScaledInstance(bw, bh, Image.SCALE_DEFAULT ));
		r3.setImage(r3.getImage().getScaledInstance(bw, bh, Image.SCALE_DEFAULT ));
		

		
		JButton create = new stdButton(r1,"创建游戏");
		JButton join = new stdButton(r2,"加入游戏");
		JButton exit = new stdButton(r3,"离开游戏");
		exit.addActionListener(this);
		create.addActionListener(this);
		join.addActionListener(this);
		
		create.setBounds(w/10, h*3/10,bw, bh);
		this.add(create);
		
		join.setBounds(w*4/10, h/2, bw,bh);
		this.add(join);
		
		exit.setBounds(w*7/10, h*7/10, bw,bh);
		this.add(exit);
		
//		jpl.add(lab);
//		jpl.add(create);
//		jpl.add(join);
//		jpl.add(exit);
		
		this.setUndecorated(true);
		
//		this.add(jpl);
//		this.add(jpr);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="离开游戏"){
			this.dispose();
		}else if(e.getActionCommand()=="创建游戏"){
			Room.this.dispose();
			new servercon();
		}
			else if(e.getActionCommand()=="加入游戏"){
				try {
					Room.this.dispose();
					new clientcon();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
		}
}}