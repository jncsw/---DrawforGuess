package admin;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import database.data;
import std.stdframe;

public class admadd extends stdframe implements ActionListener{
	
	static JLabel name= new JLabel("��Ŀ��");
	static JLabel tips= new JLabel("���ͣ�");
	static JTextField na = new JTextField(20);
	static JTextField tip = new JTextField(20);
	static JLabel notice = new JLabel("");
	static String s,ss,st;
	static int n,rn;
	public void actionPerformed(ActionEvent e){
		try {
			ss=na.getText().trim();
			st=tip.getText().trim();
			if(ss.equals("")){
				notice.setText("��Ŀ����Ϊ�գ�");
			}
			else if(st.equals("")){
				notice.setText("��ʾ����Ϊ�գ�");
			}else{
				n=data.operate("qa_table","question",ss);
				if(n==-1){
					rn=data.insdata(ss, st);
					if(rn==0){
						notice.setText("��ӳɹ���");
						na.setText("");
						tip.setText("");
					}else{
						notice.setText("���ʧ�ܣ������ԣ�");
					}
				}else{
					notice.setText("����Ŀ�Ѵ��ڣ�");
					
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public admadd() {
		super(3);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton ad = new JButton("�����Ŀ");
		// TODO Auto-generated constructor stub
//		Toolkit kit = Toolkit.getDefaultToolkit();
//		Dimension screenSize = kit.getScreenSize();
//		int screenWidth  = screenSize.width;
//		int screenHeight = screenSize.height;
//		
//		ImageIcon background = new ImageIcon("source/img/bg/3.jpg");
//		background.setImage(background.getImage()
//				.getScaledInstance(screenWidth/2, screenHeight/2, Image.SCALE_DEFAULT ));
//		JLabel bgl = new JLabel(background);
//		bgl.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());  
//		JPanel imagePanel; 
//		imagePanel = (JPanel) this.getContentPane();  
//		imagePanel.setOpaque(false);
//		imagePanel.setLayout(new FlowLayout());  
//		
//		this.getLayeredPane().setLayout(null);  
//		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
//		this.getLayeredPane().add(bgl, new Integer(Integer.MIN_VALUE));
		
		
		
		this.setLayout(null);
		
		int w = this.getWidth();
		int h = this.getHeight();
		
		int fw=100; int fh = 40;
		int ww = 150;
		
		name.setBounds(w*3/10,h/10, fw, fh);
		na.setBounds(w*6/10,h/10, ww, fh);
		tips.setBounds(w*3/10,h*3/10, fw, fh);
		tip.setBounds(w*6/10,h*3/10, ww, fh);
		
		ad.setBounds(w*4/10, h*6/10, 100,40);
		
		notice.setBounds(w*3/10, h*8/10 ,w*4/10,30);
		
		this.add(name);
		this.add(na);
		this.add(tips);
		this.add(tip);
		this.add(ad);
		ad.addActionListener(this);
		this.add(notice);
		this.setVisible(true);
	}
}
