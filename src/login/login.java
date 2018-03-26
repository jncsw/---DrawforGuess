package login;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.LineBorder;

import admin.admin;
import chatpanel.server;
import conn.Client;
import conn.Server;
import database.data;
import draw.MainFrame;
import draw.client_1;
import draw.server_1;
import login.reg;
import std.stdButton;


public class login {
	static JTextField na;
	static JPasswordField pa;
	static JFrame frame;
	static JLabel jl= new JLabel("");
	static boolean t = true;
	static ImageIcon loginicon = new ImageIcon("source/img/icons/login.png");
	static ImageIcon logouticon = new ImageIcon("source/img/icons/logout.png");
	static int x;
	static String cons ;
	static Object oo ;
	public login(int xx,Object o,String s){
		this.cons=s;
		x=xx;
		this.oo=o;
		frame = new JFrame(" 你  画  我  猜 ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//背景音乐
		File music = new File("source\\music\\login.wav");
		try {
			URL url = music.toURL();
			AudioClip ac = Applet.newAudioClip(url);
			ac.loop();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		//加入图片
		String path = "source/img/bg.jpg";
		ImageIcon background = new ImageIcon(path);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth  = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setBounds(screenWidth/4,screenHeight/4 , screenWidth/2, screenHeight/2);
		int fw = frame.getWidth();
		int fh = frame.getHeight();
		
		background.setImage(background.getImage()
				.getScaledInstance(screenWidth/2, screenHeight/2, Image.SCALE_DEFAULT ));
		JLabel bgl = new JLabel(background);
		bgl.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());  
		JPanel imagePanel; 
		imagePanel = (JPanel) frame.getContentPane();  
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());  
		
		frame.getLayeredPane().setLayout(null);  
		// 把背景图片添加到分层窗格的最底层作为背景  
		frame.getLayeredPane().add(bgl, new Integer(Integer.MIN_VALUE));
		loginicon.setImage(loginicon.getImage().getScaledInstance(70,40, Image.SCALE_SMOOTH ));
		logouticon.setImage(logouticon.getImage().getScaledInstance(70,40, Image.SCALE_SMOOTH ));
		JButton btn = new stdButton(loginicon,"登录");
		btn.addActionListener(new listener());
//		btn.requestFocus();  //设置焦点
		btn.setBounds(fw*35/100, fh*64/100, 70, 40);
		JButton regbtn = new stdButton(logouticon,"注册");
		regbtn.addActionListener(new reglistener());
		regbtn.setBounds(fw*55/100, fh*64/100,  70, 40);
		
		
		na = new JTextField(20);
		na.setBounds(fw*33/100,fh*36/100,fw*37/100,fh*7/100);
//		na.addFocusListener(new FocusListener() {
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				// TODO Auto-generated method stub
//			}
//			@Override
//			public void focusGained(FocusEvent arg0) {
//				// TODO Auto-generated method stub
//				if(t){
//					na.setText("");
//					t=false;
//				}
//			}
//		});
		pa = new JPasswordField("",20);
		pa.setBounds(fw*33/100,fh*50/100,fw*37/100,fh*7/100);
		
		
		na.setBackground(new Color(131,172,44));
		pa.setBackground(new Color(131,172,44));
		na.setBorder(BorderFactory.createLineBorder(new Color(48,90,26), 3,false));
		pa.setBorder(BorderFactory.createLineBorder(new Color(48,90,26), 3,false));
		
		jl.setBounds(fw*33/100,fh*80/100,fw*67/100,fh*7/100);
//		jl.setFont(new Font("方正硬笔楷书简体", Font.BOLD, 20));
		jl.setForeground(new Color(46,247,0));
		
		frame.setLayout(null);
		frame.add(na);
		frame.add(pa);
		frame.add(btn);
		frame.add(regbtn);
		frame.add(jl);
		frame.setLayout(null);
		btn.requestFocus();
		frame.setVisible(true);
	}

}
class listener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		String name = login.na.getText().trim();
		String pass = login.pa.getText().trim();
		login.na.setText("");
		login.pa.setText("");
		int sco=0;
		if(login.x==1){
			Server s=  (Server) login.oo;
		try {
			sco = data.operate(1, name, pass);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (sco==-1){
			login.jl.setText("用户名或密码错误，请重新输入!");			
		}else{
			login.jl.setText("");
			if(name.equals("admin")){
//				login.frame.dispose();
				new admin();
			}else{
				login.frame.dispose();
				try {
					InetAddress ia=InetAddress.getLocalHost();
					 System.out.println(ia.getHostAddress());
					 int port = Integer.parseInt(login.cons);
					 server ser = new server(port);
					 new Thread(ser).start();
					 server_1 s1 = new server_1(port+2);
					 new Thread(s1).start();
					 try {
						client_1 c1 = new client_1(ia.getHostAddress(), port+2);
						new Thread(c1).start();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					new MainFrame(name, sco,ia.getHostAddress()+" "+port);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				////////////
			}
			}
		}else if(login.x==2){
			Client c = (Client)login.oo;
			try {
				sco = c.logpw("\\login "+name+" "+pass);
				if (sco==-1){
					login.jl.setText("用户名或密码错误，请重新输入!");			
				}else{
					login.jl.setText("");
					login.frame.dispose();
					try {
						Scanner in = new Scanner(login.cons);
						String ip = in.next();
						String ports = in.next();
						int port = Integer.parseInt(ports);
						client_1 c1 = new client_1(ip, port+2);
						new Thread(c1).start();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						try {
							new MainFrame(name, sco,login.cons);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
class reglistener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		new reg(login.x,login.oo);
	}
}
