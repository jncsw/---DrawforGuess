package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import conn.Client;
import conn.Server;
import database.data;
import std.stdframe;

public class clientcon  extends stdframe{
	JTextField jtf;
	JTextField jtf2;
	JLabel notice ;
	Client c ;
	public clientcon(){
		super(8,"设置");
		jtf = new JTextField("127.0.0.1");
		JLabel jl = new JLabel("IP地址：");
		jtf2 = new JTextField();
		JLabel jl2 = new JLabel("接口：");
		JButton jb = new JButton("确定");
		notice = new JLabel("");
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean b = true;
				String s = jtf.getText();
				String i = jtf2.getText();
				if(b)
				try {
					c = new Client(s, Integer.parseInt(i));
//					new Thread(c).start();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					notice.setText("请输入正确的端口号！");
					b=false;
//					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					notice.setText("IP地址或端口号错误，请重新输入。");
					b=false;
//					e.printStackTrace();
				}
				
				if(b){
					clientcon.this.dispose();
					new login(2,c,s.trim()+" "+i.trim());
				}
				
			}
		});
		
		
		int w = this.getWidth();
		int h  =this.getHeight();
		
		notice.setBounds(w*4/10,h*8/10,300,40);
		jl.setBounds(w*3/10,h/10,100,40);
		jl2.setBounds(w*3/10,h*4/10,100,40);
		jtf.setBounds(w*4/10,h/10,300,40);
		jtf2.setBounds(w*4/10,h*4/10,300,40);
		jb.setBounds(w*4/10,h*6/10,100,40);
		this.setLayout(null);
		this.add(jl);
		this.add(jl2);
		this.add(jtf);
		this.add(jtf2);
		this.add(jb);
		this.add(notice);
		
		this.setVisible(true);
	}
}
