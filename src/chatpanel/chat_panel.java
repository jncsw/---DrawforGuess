package chatpanel;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import draw.MainFrame;

public class chat_panel extends JPanel implements Runnable {
	BufferedReader br;
	PrintWriter pw;
	static JTextField jtf=new JTextField();
	static TextArea ja=new TextArea();
	static String tname;
	playersdata pd;
	
	public chat_panel(int w,int d,String name,String cons) throws IOException{
		Scanner in = new Scanner(cons);
		String ip = in.next();
		String ports = in.next();
		int port = Integer.parseInt(ports);
		Socket s=new Socket(ip,port+1);
		br = new BufferedReader(
		        new InputStreamReader(
				          s.getInputStream()));
			pw =new PrintWriter(
		        new BufferedWriter(
		    	          new OutputStreamWriter(
		    	            s.getOutputStream())), true);
		this.setBounds(0, d, w,d);
		this.tname = name;
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		this.setSize(w, d);
		JButton sendb=new JButton("send");
		sendb.setBounds(5*w/7,5*d/7,w*2/7,d/7);
		sendb.addActionListener(new sendlistener());
		ja.setBounds(0,0,w,5*d/7);
		ja.setEditable(false);
//		JScrollPane jslp = new JScrollPane(ja);
//		jslp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		jtf.setBounds(0,5*d/7,5*w/7,d/7);
		jtf.addKeyListener(new KeyListener() {/////////////////////////////////
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO 自动生成的方法存根
				if(arg0.getKeyChar()==KeyEvent.VK_ENTER){
					String currentwords=jtf.getText();
					jtf.setText("");
					if(!currentwords.equals("")){
						pw.println(chat_panel.this.tname+"说："+currentwords);
						pw.flush();
					}	
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		this.add(sendb);
		this.add(ja);
		this.add(jtf);
		this.setVisible(true);
	}
	public void setarea(String s){
		String ss = ja.getText();
//		ja.setText(ss+s+"\n");
		ja.append(s+"\n");
	}
	
	class sendlistener implements ActionListener{
	public void actionPerformed(ActionEvent arg0) {
		String currentwords=jtf.getText();
		jtf.setText("");
		if(!currentwords.equals("")){
			pw.println(chat_panel.this.tname+"说："+currentwords);
			pw.flush();
		}
	  }
	}
	public void firsts(String name,int soc){
		pw.println("\\addplayer "+name+" "+soc);
		pw.flush();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String s;
		while(true){
			try {
				s = br.readLine();
				Scanner in = new Scanner(s);
				if(s.startsWith("\\players")){
				String op = in.next();
				{
					String name = in.next();
					String sss = s.substring("\\players".length()+name.length()+2);
					sss=sss.replaceAll("[.]", "\n");
					MainFrame.leftlabel.setText(sss);
//					while(in.hasNext()){
//						na=in.next();
//						sco = in.next();
//					}
					this.setarea(name+"进入游戏");
					
				}
				}
				else 
				this.setarea(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}
}
