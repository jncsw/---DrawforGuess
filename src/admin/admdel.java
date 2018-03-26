package admin;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.GapContent;

import database.data;
import std.stdframe;

public class admdel extends stdframe implements ActionListener{
	static JLabel qa = new JLabel("题目");
	static JTextField text = new JTextField(20);
	static JButton del = new JButton("删除题目");
	static int n = 1,nn,ans;
	static String s;
	static JLabel notice  = new JLabel("");
	public void actionPerformed(ActionEvent e){
		if(n==1){
			notice.setText("确定要删除吗？");
			del.setText("确定");
			n--;
		}else{
			n=1;
			del.setText("删除题目");
			s=text.getText().trim();
			if(s.equals("")){
				notice.setText("请输入要删除的题目！");
			}else{
				try {
					nn=data.operate("qa_table","question",s);
					if(nn==-1){
						notice.setText("该题目还未添加！");
					}else{
						ans=data.deldata("qa_table","question",s);
						if(ans==0){
							notice.setText("删除成功！");
						}else{
							notice.setText("删除失败，请重试！");
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
	}
	public admdel() {
		super(4);
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		int w = this.getWidth();
		int h  =this.getHeight();
		
		
		qa.setForeground(new Color(0,0,0));
		notice.setForeground(new Color(0,0,0));
		qa.setBounds(w*3/10,h/10 , 150, 40);
		text.setBounds(w*6/10,h/10, 150, 40);
		del.setBounds(w*4/10, h/2, 100,40);
		notice.setBounds(w*3/10,h*6/10,150,40);
		this.add(qa);
		this.add(text);
		del.addActionListener(this);
		this.add(del);
		this.add(notice);
		this.setVisible(true);
	}
}
