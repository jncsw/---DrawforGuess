package login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import conn.Client;
import database.data;
import std.stdButton;

public class reg {
	static JTextField name = new JTextField("请输入用户名",20);
	static JPasswordField pass = new JPasswordField("",20);
	static JPasswordField passcom = new JPasswordField("",20);
	static JLabel notice = new JLabel(""); ; 
	static boolean t = true;
	static int xx;
	static Object o;
	public reg(int x,Object oo){
		o=oo;
		JFrame register = new JFrame("注册");
		xx=x;
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth  = screenSize.width;
		int screenHeight = screenSize.height;
		register.setBounds(screenWidth/4,screenHeight/4 , screenWidth/2, screenHeight/2);
		
		
		ImageIcon background = new ImageIcon("source/img/bg/2.jpg");
		background.setImage(background.getImage()
				.getScaledInstance(screenWidth/2, screenHeight/2, Image.SCALE_DEFAULT ));
		JLabel bgl = new JLabel(background);
		bgl.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());  
		JPanel imagePanel; 
		imagePanel = (JPanel) register.getContentPane();  
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());  
		register.getLayeredPane().setLayout(null);  
		// 把背景图片添加到分层窗格的最底层作为背景  
		register.getLayeredPane().add(bgl, new Integer(Integer.MIN_VALUE));
		
		
		
		name.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(t){
					name.setText("");
					t=false;
				}
			}
		});
		
		int fw = register.getWidth();
		int fh  = register.getHeight();
		
		
		JLabel user_name = new JLabel("用户名：");
		JLabel user_pass = new JLabel("密码：");
		JLabel user_passcom = new JLabel("确认密码：");		
		JButton jbtn = new stdButton("注册");
		jbtn.addActionListener(new reglis());
		
		int lw = 70;int lh = 30;
		
		user_name.setBounds(fw*3/10,fh/10, lw,lh);
		user_pass.setBounds(fw*3/10,fh*3/10, lw,lh);
		user_passcom.setBounds(fw*3/10, fh*5/10, lw,lh);
		
		int tw = 200;int th = 30;
		
		name.setBounds(fw/2, fh/10,tw,th);
		pass.setBounds(fw/2, fh*3/10,tw,th);
		passcom.setBounds(fw/2, fh*5/10,tw,th);
		
		notice.setBounds(fw*4/10, fh*6/10, 150, 40);
		
		jbtn.setBounds(fw*4/10, fh*7/10, 130, 40);
		register.add(notice);
		register.add(jbtn);
		register.setLayout(null);
		register.add(user_name);
		register.add(user_pass);
		register.add(user_passcom);
		register.add(name);
		register.add(pass);
		register.add(passcom);
		register.setVisible(true);
	}
}
class reglis  implements ActionListener {
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		String pac=reg.passcom.getText().trim();
		String nam = reg.name.getText().trim();
		String pas = reg.pass.getText().trim();
		
		if(nam.equals(""))reg.notice.setText("请输入用户名！");
		if(pas.equals(""))reg.notice.setText("请输入密码！");
		if(!pas.equals(pac)){
			reg.notice.setText("密码输入不一致，请重新输入！");
			reg.pass.setText("");
			reg.passcom.setText("");
		}
		if(reg.xx==1){
		try {
			if(data.operate("sdu_user","User_name",nam)!=-1){
					reg.notice.setText("用户名已存在！");
				} else
			{
			int jug =-1;
				jug = data.operate(2, nam, pas);
//				System.out.println("+++++");
			if(jug==0){
				reg.name.setText("");
				reg.pass.setText("");
				reg.passcom.setText("");
				reg.notice.setText("恭喜，注册成功！");
			}else{
				reg.notice.setText("注册失败，请重试！");
			}
			}
		}		
		 catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		else if(reg.xx==2){
			Client c = (Client) reg.o;
			String sx;
			try {
				sx="\\inchk "+nam;
				int jug = c.logpw(sx);
				if(jug!=-1){
					reg.notice.setText("用户名已存在");
				}else{
				sx = "\\reg "+nam+" "+pas;
				jug = c.logpw(sx);
				if(jug==0){
					reg.name.setText("");
					reg.pass.setText("");
					reg.passcom.setText("");
					reg.notice.setText("恭喜，注册成功！");
				}else{
					reg.notice.setText("注册失败，请重试！");
				}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
	}
}
