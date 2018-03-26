package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import conn.Server;
import database.data;
import std.stdframe;

public class servercon  extends stdframe{
	JTextField jtf;
	JTextField jtf2;
	Server se ;
	JLabel notice ,ip = new JLabel();
	public servercon(){
		super(7,"����");
		String ipadd = "";
		try {
			InetAddress ia=InetAddress.getLocalHost();
			ipadd = "����IP��ַ�ǣ�"+ia.getHostAddress();
			
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ip.setText(ipadd);
		
		jtf = new JTextField("jdbc:mysql://localhost:3306/sdu?user=root&password=");
		JLabel jl = new JLabel("���ݿ⣺");
		jtf2 = new JTextField();
		JLabel jl2 = new JLabel("�ӿڣ�");
		JButton jb = new JButton("ȷ��");
		notice = new JLabel("");
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean b = true;
				String s = jtf.getText();
				String i = jtf2.getText();
				if(b)
				try{
				data.connect(s.trim());
				}catch(Exception e){
					b=false;
					notice.setText("���ݿ����ӳ���");
				}
				if(b)
				try {
					se = new Server(Integer.parseInt(i));
					new Thread(se).start();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					notice.setText("��������ȷ�Ķ˿ںţ�");
					b=false;
//					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					notice.setText("�˿ںű�ռ�ã����������롣");
					b=false;
//					e.printStackTrace();
				}
				
				if(b){
					servercon.this.dispose();
					new login(1,se,i.trim());
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
		ip.setBounds(w/10,h*8/10,200,40);
		this.setLayout(null);
		this.add(jl);
		this.add(jl2);
		this.add(jtf);
		this.add(jtf2);
		this.add(jb);
		this.add(notice);
		this.add(ip);
		this.setVisible(true);
	}
}
