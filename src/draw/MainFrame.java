package draw;

import javax.swing.*;

import chatpanel.chat_panel;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class MainFrame implements ActionListener, MouseListener, MouseMotionListener {
	public static JFrame jf = new JFrame("欢迎您参加你画我猜~");
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	ArrayList<DPoints> al = new ArrayList<>();
	int screenWidth = screenSize.width*8/10;
	int screenHeight = screenSize.height*8/10;
	
	JPanel jpanel1 = new JPanel(), jpanel2 = new JPanel(), jpanel3 = new JPanel();
	chat_panel jpchat;
	BufferedImage drawImg = new BufferedImage(screenSize.width * 3 / 4, screenSize.height * 4 / 5,BufferedImage.TYPE_INT_RGB);
	JLabel jppaint;
	
	public static JPanel jpleft = new JPanel();
	public static JTextArea leftlabel = new JTextArea("");
	JPanel toolpanel = new JPanel();
	// Box box2 = Box.createHorizontalBox();
	// Box box3 = Box.createHorizontalBox();
	JButton btn11 = new JButton("红"), btn12 = new JButton("黄"), btn13 = new JButton("蓝"), btn14 = new JButton("绿"),
			btn15 = new JButton("紫"), btn16 = new JButton("更多"), btn21 = new JButton("铅笔"), btn22 = new JButton("橡皮"),
			btn23 = new JButton("圆"), btn24 = new JButton("矩形"), btn25 = new JButton("椭圆"), btn31 = new JButton("粗1"),
			btn32 = new JButton("粗2"), btn33 = new JButton("粗3"), btn34 = new JButton("粗4"), btn35 = new JButton("粗5");
	JLabel notice = new JLabel("提示"), from = new JLabel("类别"), num = new JLabel("111个字");

	static JLabel timer;

	// Vector point= null;//点向量组
	Color color = new Color(0, 0, 0);
	// Point mark = new Point(-1, -1, 15, color);//点截断标志
	// int flaga=1;//判断当前所用的工具,默认为铅笔
	static int color1 = 1;
	static int tool = 1;
	// int x=-1,y=-1;//当前点的坐标
	int ox, oy,fx,fy;
	static int font = 1;
	String name;
	public MainFrame(String name,int score,String s) throws IOException {
		this.name = name;
		
		Graphics2D ggg  = drawImg.createGraphics();
		ggg.drawImage(new ImageIcon("source/img/white.png").getImage(), 0, 0, null);
		jppaint = new JLabel() {

			protected void paintComponent(Graphics g) {
				
//				g.setColor(color.white);
//				jppaint.setFground(Color.white);
				g.fillRect(screenSize.width * 1 / 4, screenSize.height * 1 / 6, screenSize.width * 3 / 4, screenSize.height * 5 / 6);
				g.drawImage(drawImg, 0, 0, null);
			}
		};
		   
		// jpanel1.setBackground(Color.gray);
		// jpanel2.setBackground(Color.gray);
		// jpanel3.setBackground(Color.gray);
		//
		jpanel1.setLayout(new BoxLayout(jpanel1, BoxLayout.X_AXIS));
		jpanel1.add(btn11);
		jpanel1.add(btn12);
		jpanel1.add(btn13);
		jpanel1.add(btn14);
		jpanel1.add(btn15);
		jpanel1.add(btn16);

		jpanel2.setLayout(new BoxLayout(jpanel2, BoxLayout.X_AXIS));
		jpanel2.add(btn21);
		jpanel2.add(btn22);
		jpanel2.add(btn23);
		jpanel2.add(btn24);
		jpanel2.add(btn25);

		jpanel3.setLayout(new BoxLayout(jpanel3, BoxLayout.X_AXIS));
		jpanel3.add(btn31);
		jpanel3.add(btn32);
		jpanel3.add(btn33);
		jpanel3.add(btn34);
		jpanel3.add(btn35);

		// TODO Auto-generated constructor stub
		// 获取分辨率

		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setSize(screenWidth, screenHeight);
		// jf.setUndecorated(true);
		jf.setLocationRelativeTo(null);
		int sW = jf.getWidth();
		int sH = jf.getHeight();
		jf.setLayout(null);
		jf.getContentPane().setBackground(Color.white);
		// 玩家

		jpleft.setBackground(Color.WHITE);
		jpleft.setLayout(new FlowLayout());
		jpleft.setBounds(0, 0, sW / 4, sH / 2);
		leftlabel.setBounds(0, 0, sW / 4, sH / 2);
		leftlabel.setEditable(false);
		jpleft.add(leftlabel);
		// 聊天
		jpchat = new chat_panel(sW / 4, sH / 2,name,s);
		Thread t = new Thread(jpchat);
		t.start();
		jpchat.firsts(name,score);//////////////////////////

		// 画板

		jppaint.setBounds(sW / 4, sH / 6, sW * 3 / 4, sH * 5 / 6);
		jppaint.setBackground(Color.white);
		//jppaint.setLayout(null);

		// 工具
		toolpanel.setBounds(sW / 4, 0, sW * 3 / 4, sH / 6);
		int pW = toolpanel.getWidth();
		int pH = toolpanel.getHeight();
		notice.setBounds(0, 0, pW / 10, pH / 10);
		from.setBounds(pW / 10, 0, pW * 3 / 10, pH / 10);
		num.setBounds(pW * 4 / 10, 0, pW * 3 / 10, pH / 10);
		JButton pre = new JButton("准备");
		pre.setBounds(pW * 7 / 10, 0, pW / 10, pH / 10);
		pre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Thread(new Timer()).start();
			}
		});
		jpanel1.setBounds(0, pH / 10, pW / 3, pH / 10);
		jpanel2.setBounds(pW / 2, pH / 10, pW / 3, pH / 10);
		jpanel3.setBounds(pW * 2 / 3, pH / 10, pW / 3, pH / 10);

		// 计时器
		timer = new JLabel("60");
		timer.setBounds(pW * 4 / 5, 0, pW / 5, pH / 10);

		toolpanel.add(jpanel1);
		toolpanel.add(jpanel2);
		toolpanel.add(jpanel3);
		toolpanel.add(notice);
		toolpanel.add(from);
		toolpanel.add(num);
		toolpanel.add(pre);
		toolpanel.add(timer);
		jppaint.addMouseListener(this);
		jppaint.addMouseMotionListener((MouseMotionListener) this);

		btn11.addActionListener(this);
		btn12.addActionListener(this);
		btn13.addActionListener(this);
		btn14.addActionListener(this);
		btn15.addActionListener(this);
		btn16.addActionListener(this);
		btn21.addActionListener(this);
		btn22.addActionListener(this);
		btn23.addActionListener(this);
		btn24.addActionListener(this);
		btn25.addActionListener(this);
		btn31.addActionListener(this);
		btn32.addActionListener(this);
		btn33.addActionListener(this);
		btn34.addActionListener(this);
		btn35.addActionListener(this);

		addMouseMotionListener(this);
		addMouseListener(this);

		jf.add(toolpanel);
		jf.add(jpleft);
		jf.add(jpchat);
		jf.add(jppaint);
		jf.setVisible(true);

	}

	private void addMouseListener(MainFrame mainFrame) {
		// TODO Auto-generated method stub

	}

	private void addMouseMotionListener(MainFrame mainFrame) {
		// TODO Auto-generated method stub

	}

	// 响应事件
	// @SuppressWarnings("unchecked")
	Color cc;

	public void actionPerformed(ActionEvent e) {

		// if (e.getSource() == btn16) {
		// Color newColor = JColorChooser.showDialog(jppaint, "调色板",color);
		// color=newColor;
		// flaga=1;//更多铅笔颜色
		// }
		
//		if (ox >= 0) {
			if (e.getActionCommand().equals("红")) {
				color1 = 1;
			} else if (e.getActionCommand().equals("黑")) {
				color1 = 2;
			} else if (e.getActionCommand().equals("黄")) {
				color1 = 3;
			}else if (e.getActionCommand().equals("紫")) {
					color1 = 8;

			} else if (e.getActionCommand().equals("蓝")) {
				color1 = 4;
			} else if (e.getActionCommand().equals("绿")) {
				color1 = 5;
			} else if (e.getActionCommand().equals("更多")) {
				cc = JColorChooser.showDialog(jf, "调色板", color);
				color1 = 6;
			} else if (e.getActionCommand().equals("铅笔")) {
				tool = 1;
			}

			else if (e.getActionCommand().equals("橡皮")) {
				color1 = 7;
			} else if (e.getActionCommand().equals("圆")) {
				tool = 3;
			} else if (e.getActionCommand().equals("矩形")) {
				tool = 4;
			} else if (e.getActionCommand().equals("椭圆")) {
				tool = 5;
			} else if (e.getActionCommand().equals("粗1")) {
				font = 11;
			} else if (e.getActionCommand().equals("粗2")) {
				font = 12;
			}

			else if (e.getActionCommand().equals("粗3")) {
				font = 13;
			} else if (e.getActionCommand().equals("粗4")) {
				font = 14;
			} else if (e.getActionCommand().equals("粗5")) {
				font = 15;
			} else if (e.getActionCommand().equals("退出"))
				System.exit(0);
		}



	
	public void mouseDragged(MouseEvent e) {
		// JPanel c=(JPanel)e.getSource();
		Graphics2D g = (Graphics2D) drawImg.createGraphics();
//		if (ox >= 0) {// 移动时ox=-1
			if (color1 == 1) { // 红
				g.setColor(new Color(255, 0, 0));

			} else if (color1 == 3)// 黄
			{
				g.setColor(new Color(255, 255, 0));

			} else if (color1 == 4) {// 蓝
				g.setColor(new Color(0, 0, 255));

			} else if (color1 == 5) {// 绿
				g.setColor(new Color(0, 255, 0));

			}else if (color1 == 8) {// 紫
			    g.setColor(new Color(255, 0, 255));

			} else if (color1 == 6) {// 调色板

				g.setColor(cc);
			}

			else if (color1 == 7) {// 橡皮
				g.setColor(Color.white);

			}
			// else if (color1==8){
			//
			// }
			//
			// else if (color1==9) {
			//
			// }
			// else if(color1==10) {
			//
			// }

			if (font == 11) {// 粗1
				g.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			} else if (font == 12) {// 粗2
				g.setStroke(new BasicStroke(8.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			} else if (font == 13) {// 粗3
				g.setStroke(new BasicStroke(10.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			}

			else if (font == 14) {// 粗4
				g.setStroke(new BasicStroke(20.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			} else if (font == 15) {// 粗5
				g.setStroke(new BasicStroke(30.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			}

			if (tool == 1) {
				ox = e.getX();
				oy = e.getY();
//				int xx = jppaint.getX();
//				int yy = jppaint.getY();
//				if(ox>xx && oy>yy){
				g.drawLine(fx, fy, ox, oy);
				al.add(new DPoints(ox, oy, g.getColor(), g.getStroke(), 1));
				fx = ox;
				fy = oy;
				jppaint.repaint();
//				}
			}

			else if (tool == 3) {// 圆

			} else if (tool == 4) {// 矩形

			} else if (tool == 5) {// 椭圆
				g.fillOval(ox, oy, e.getX(), e.getY());
			}

		}


	public void mouseMoved(MouseEvent e) {
		// ox=-1;oy=-1;
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		fx = e.getX();
		fy = e.getY();
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	class Timer implements Runnable {
		public void run() {
			long time = Long.parseLong(MainFrame.timer.getText());
			while (time >= 0) {
				MainFrame.this.timer.setText(time+"");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				time--;
			}
		}
	}
	 
}