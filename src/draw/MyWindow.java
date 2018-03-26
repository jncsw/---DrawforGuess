package draw;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


@SuppressWarnings("serial")
public class MyWindow extends JFrame implements ActionListener,MouseMotionListener,MouseListener{
	JLabel label = new JLabel();
	JMenuBar mb = new JMenuBar(),mb2 = new JMenuBar();// 创建菜单栏组件
	JMenu mu = new JMenu("文件(F)");// 创建菜单组件
	JMenu qu = new JMenu("颜色(C)");
	JMenu ru = new JMenu("帮助(H)");
	boolean pb = true;
	JPanel jpanel1 = new JPanel(), jpanel2 = new JPanel(),
           jpanel3 = new JPanel(), jpanel4 = new JPanel();
	JButton btn1 = new JButton("铅        笔"), btn2 = new JButton("橡         皮"),
			btn3 = new JButton("颜        料"), btn4 = new JButton("直         线"),
			btn5 = new JButton("矩         形"), btn6= new JButton("圆         形"),
	        btn7 = new JButton("椭         圆"), btn8 = new JButton("圆角矩形"),
	        btn9 = new JButton("多  边  形");

	static public JLabel jl1 = new JLabel("");
	static public JLabel jl2 = new JLabel("");
	static public JLabel jl3 = new JLabel("");
	static JPanel jp = new JPanel();
   static int w = 20;
	
	
	JSeparator seperate1=new JSeparator(),seperate2=new JSeparator();//加入下划线
	JMenuItem  openfile, save, saveas, exit, help, about,coloredit;
	Box box = Box.createVerticalBox();

	Vector point= null;//点向量组
	Color color = new Color(0,0,0);
	Point mark = new Point(-1, -1, 15, color);//点截断标志
	int flag=1;//判断当前所用的工具,默认为铅笔
	int x=-1,y=-1;//当前点的坐标
	int n=1;
	public MyWindow() {
		super();
		openfile = new JMenuItem("打开(O)");
		save = new JMenuItem("保存(S)");
		saveas = new JMenuItem("另存为(D)");
		exit = new JMenuItem("退出(X)");
		coloredit = new JMenuItem("编辑颜色(E)"); 
		help = new JMenuItem("帮助主题(H)");
		about = new JMenuItem("关于画图(M)");

		//给按钮设置背景图片
		btn1.setIcon(new ImageIcon("D:\\你画我猜BUTTON背景图片\\铅笔.jpg"));
		btn2.setIcon(new ImageIcon("D:\\你画我猜BUTTON背景图片\\橡皮擦.jpg"));
		btn3.setIcon(new ImageIcon("D:\\你画我猜BUTTON背景图片\\选择颜色.jpg"));
		btn4.setIcon(new ImageIcon("D:\\你画我猜BUTTON背景图片\\直线.jpg"));
		btn5.setIcon(new ImageIcon("D:\\你画我猜BUTTON背景图片\\矩形.jpg"));
		btn6.setIcon(new ImageIcon("D:\\你画我猜BUTTON背景图片\\圆.jpg"));
		btn7.setIcon(new ImageIcon("D:\\你画我猜BUTTON背景图片\\椭圆.jpg"));
		btn9.setIcon(new ImageIcon("D:\\你画我猜BUTTON背景图片\\多边形.jpg"));
		btn8.setIcon(new ImageIcon("D:\\你画我猜BUTTON背景图片\\圆角矩形.jpg"));
		
		
		
		jp.add(jl1);
	    jp.add(jl2);
	    jp.add(jl3);
	    jp.setBounds(100, 100, 200, 100);
	    this.add(jp);
//		this.setUndecorated(true);
		
		
		// 将选项加入到菜单栏里
		mu.add(openfile);
		mu.add(save);
		mu.add(saveas);
		mu.add(seperate1);// 加入分割线
		mu.add(exit);
		qu.add(coloredit);
		ru.add(help);
		ru.add(seperate2);
		ru.add(about);
		mb.add(mu);
		mb.add(qu);
		mb.add(ru);
		
		box.add(btn1);
		box.add(new JSeparator());
		box.add(btn2);
		box.add(new JSeparator());
		box.add(btn3);
		box.add(new JSeparator());
		box.add(btn4);
		box.add(new JSeparator());
		box.add(btn5);
		box.add(new JSeparator());
		box.add(btn6);
		box.add(new JSeparator());
		box.add(btn7);
		box.add(new JSeparator());
		box.add(btn8);
		box.add(new JSeparator());
		box.add(btn9);
		jpanel1.add(box);
		//p2.add(label);
		//label.setBackground(Color.white);
		
		
		// 增加监视器
		openfile.addActionListener(this);
		save.addActionListener(this);
		saveas.addActionListener(this);
		exit.addActionListener(this);
		coloredit.addActionListener(this);
		about.addActionListener(this);
		help.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn6.addActionListener(this);
		btn7.addActionListener(this);
		btn8.addActionListener(this);
		btn9.addActionListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);

		// 设置窗口位置和大小
		setTitle("画图");
		setBounds(100, 100, 1000, 700);
		setBackground(jpanel4.getBackground());// 设置窗口背景颜色
		setJMenuBar(mb);// 将菜单栏加入到窗口
		
		mb2.setBackground(Color.lightGray);
		mb2.add(label);
		add(mb2,BorderLayout.SOUTH);
		
		point = new Vector();
		jpanel1.setBackground(Color.gray);
		jpanel2.setBackground(Color.gray);
		jpanel3.setBackground(Color.gray);
		jpanel4.setBackground(Color.white);
		this.add(jpanel1,BorderLayout.WEST);
		this.add(jpanel2,BorderLayout.NORTH);
		this.add(jpanel3,BorderLayout.EAST);
		this.add(jpanel4,BorderLayout.CENTER);
//		setResizable(false);
		this.setVisible(true);// 显示窗口		
	}
	//响应事件
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == coloredit) {
			Color newColor = JColorChooser.showDialog(this, "调色板",
					jpanel4.getBackground());
			if (jpanel4.getBackground() != null) {
				jpanel4.setBackground(newColor);
			}
		}else if (e.getSource() == btn3) {
			Color newColor = JColorChooser.showDialog(this, "调色板",color);
		    color=newColor;
		    flag=1;
		}
		
		else if(e.getSource()==btn1){
				flag=1;//铅笔
			}else if(e.getSource()==btn2){
				flag=2;//橡皮
			}else if(e.getSource()==btn3){
				flag=3;//颜料
			}else if(e.getSource()==btn4){
				flag=4;//直线
			}else if(e.getSource()==btn5){
				flag=5;//矩形
			}else if(e.getSource()==btn6){
				flag=6;//圆形
			}else if(e.getSource()==btn7){
				flag=7;//椭圆
			}else if(e.getSource()==btn8){
				flag=8;//圆角矩阵
			}else if(e.getSource()==btn9){
				flag=9;//多边形
			}
		}

	public void paint(Graphics g) {
		if(pb){
			super.paint(g);
			pb=false;
		}
		Graphics2D grap = (Graphics2D) g;
		Point p1, p2;
		n = point.size();
		if (flag == 0){
			g.clearRect(0, 0, getSize().width, getSize().height);// 清除
		    repaint();
		}
		for (int i = 0; i < n-1; i++) {
			p1 = (Point) point.elementAt(i);
			p2 = (Point) point.elementAt(i + 1);
			grap.setColor(p1.color);
			if (p1.tool == p2.tool) {
				switch (p1.tool) {
				case 1:// 画笔
					Line2D line1 = new Line2D.Double(p1.x, p1.y, p2.x, p2.y);
					
					grap.draw(line1);
					break;
				case 2:// 橡皮
					setBackground(jpanel4.getBackground());
//					Color c = grap.getColor();
//					grap.setColor(Color.WHITE);
//					Line2D line11 = new Line2D.Double(p1.x, p1.y, p2.x, p2.y);
//					grap.setColor(c);
					g.clearRect(p1.x, p1.y, w, w);
					break;
				case 4:// 画直线
					Line2D line2 = new Line2D.Double(p1.x, p1.y, p2.x, p2.y);
					grap.draw(line2);
					break;
				case 5:// 画矩形
					Rectangle2D rect = new Rectangle2D.Double(p1.x, p1.y,
							Math.abs(p2.x - p1.x), Math.abs(p2.y - p1.y));
					grap.draw(rect);
					break;
				case 6:// 画圆
Arc2D circle = new Arc2D.Double(p1.x, p1.y,
							Math.abs(p2.x - p1.x), Math.abs(p2.x - p1.x),0,360,Arc2D.OPEN);
					grap.draw(circle);
					break;
				case 7:// 画椭圆
					Ellipse2D ellipse = new Ellipse2D.Double(p1.x, p1.y,
							Math.abs(p2.x - p1.x), Math.abs(p2.y - p1.y));
					grap.draw(ellipse);
					break;
				case 8:// 画圆角矩形
					RoundRectangle2D rect_round = new RoundRectangle2D.Double(
							p1.x, p1.y, Math.abs(p2.x - p1.x), Math.abs(p2.y
									- p1.y), 20, 10);
					grap.draw(rect_round);
					break;
				case 9:// 画多边形
					int px[]={p1.x,p2.x,p1.y,p2.y};
					int py[]={p1.y,p2.y,p2.x,p1.x};
					g.drawPolyline(px, py, 3);
					break;
				case 15:// 截断，跳过
					i = i + 1;
					break;
				default:
				}
			}
		}
	}
	
	 public void update(Graphics g)
	 {
	  paint(g);
	 }
	public void mouseDragged(MouseEvent e) {
		  Point p3 ;
		  switch(flag){
		   case 1://铅笔
		   case 2://橡皮
		     x = (int)e.getX();
		     y = (int)e.getY();
		     p3 = new Point(x, y, flag,color);
		     point.addElement(p3);
		     repaint();
		     break;
		   default :
		  }
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		pb=true;
		switch (flag) {
		case 1: // 铅笔
			label.setText("                    用一个像素宽画任意形状的线条");
			break;
		case 2: // 橡皮
			label.setText("                    使用橡皮擦擦去图片的一部分");
			break;
		case 3:
		    label.setText("                    选择画笔颜色");
		    break;
		case 4: // 直线
			label.setText("                    画一条直线");
			break;
		case 5: // 矩形
			label.setText("                    画一个矩形");
			break;
		case 6: // 圆
			label.setText("                    画一个圆");
			break;
		case 7: // 椭圆
			label.setText("                    画一个椭圆");
			break;
		case 8: // 圆角矩形
			label.setText("                    画一个圆角矩形");
			break;
		case 9: // 多边形
			label.setText("                    画一个多边形");
			break;
		default:
			label.setText("                    欢迎使用！");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	public void mousePressed(MouseEvent e) {
	
		Point p1;
		switch(flag){
		   case 4://直线
		   case 5: //矩形
		   case 6: //圆
		   case 7: //椭圆
		   case 8: //圆角矩形
		   case 9: //多边形
			     x = (int)e.getX();
			     y = (int)e.getY();
			     p1 = new Point(x, y, flag, color);
			     point.addElement(p1);
			     break;
		   default :
		  } 
	}

	public void mouseReleased(MouseEvent e) {
		
		Point p2;
		switch (flag) {
		case 1: // 铅笔
		case 2: // 橡皮
			point.addElement(mark);
			break;
		case 4: // 直线
		case 5: // 矩形
		case 6: // 圆
		case 7: // 椭圆
		case 8: // 圆角矩形
		case 9: // 多边形
			x = (int) e.getX();
			y = (int) e.getY();
			p2 = new Point(x, y, flag, color);
			point.addElement(p2);
			point.addElement(mark);
//			repaint();
			break;
		default:
		}
	}
	
	public static void main(String[] args) {
		
		MyWindow win=new MyWindow();	
		Counter cou = new Counter();
		Thread t = new Thread(cou);
		t.start();
	}
	
	
        

		
	}
class Counter implements Runnable{
	public void run() {
		long time = 1 * 36000; // 自定义倒计时时间
		long hour = 0;
		long minute = 0;
		long seconds = 0;
		
		while (time >= 0) {
			hour = time / 3600;
			minute = (time - hour * 3600) / 60;
			seconds = time - hour * 3600 - minute * 60;
			MyWindow.jl1.setText(hour + "时");
			MyWindow.jl2.setText(minute + "分");
			MyWindow.jl3.setText(seconds + "秒");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time--;
		}
		
	}
	
	
	
	
	
	
	
}
