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
	JMenuBar mb = new JMenuBar(),mb2 = new JMenuBar();// �����˵������
	JMenu mu = new JMenu("�ļ�(F)");// �����˵����
	JMenu qu = new JMenu("��ɫ(C)");
	JMenu ru = new JMenu("����(H)");
	boolean pb = true;
	JPanel jpanel1 = new JPanel(), jpanel2 = new JPanel(),
           jpanel3 = new JPanel(), jpanel4 = new JPanel();
	JButton btn1 = new JButton("Ǧ        ��"), btn2 = new JButton("��         Ƥ"),
			btn3 = new JButton("��        ��"), btn4 = new JButton("ֱ         ��"),
			btn5 = new JButton("��         ��"), btn6= new JButton("Բ         ��"),
	        btn7 = new JButton("��         Բ"), btn8 = new JButton("Բ�Ǿ���"),
	        btn9 = new JButton("��  ��  ��");

	static public JLabel jl1 = new JLabel("");
	static public JLabel jl2 = new JLabel("");
	static public JLabel jl3 = new JLabel("");
	static JPanel jp = new JPanel();
   static int w = 20;
	
	
	JSeparator seperate1=new JSeparator(),seperate2=new JSeparator();//�����»���
	JMenuItem  openfile, save, saveas, exit, help, about,coloredit;
	Box box = Box.createVerticalBox();

	Vector point= null;//��������
	Color color = new Color(0,0,0);
	Point mark = new Point(-1, -1, 15, color);//��ضϱ�־
	int flag=1;//�жϵ�ǰ���õĹ���,Ĭ��ΪǦ��
	int x=-1,y=-1;//��ǰ�������
	int n=1;
	public MyWindow() {
		super();
		openfile = new JMenuItem("��(O)");
		save = new JMenuItem("����(S)");
		saveas = new JMenuItem("���Ϊ(D)");
		exit = new JMenuItem("�˳�(X)");
		coloredit = new JMenuItem("�༭��ɫ(E)"); 
		help = new JMenuItem("��������(H)");
		about = new JMenuItem("���ڻ�ͼ(M)");

		//����ť���ñ���ͼƬ
		btn1.setIcon(new ImageIcon("D:\\�㻭�Ҳ�BUTTON����ͼƬ\\Ǧ��.jpg"));
		btn2.setIcon(new ImageIcon("D:\\�㻭�Ҳ�BUTTON����ͼƬ\\��Ƥ��.jpg"));
		btn3.setIcon(new ImageIcon("D:\\�㻭�Ҳ�BUTTON����ͼƬ\\ѡ����ɫ.jpg"));
		btn4.setIcon(new ImageIcon("D:\\�㻭�Ҳ�BUTTON����ͼƬ\\ֱ��.jpg"));
		btn5.setIcon(new ImageIcon("D:\\�㻭�Ҳ�BUTTON����ͼƬ\\����.jpg"));
		btn6.setIcon(new ImageIcon("D:\\�㻭�Ҳ�BUTTON����ͼƬ\\Բ.jpg"));
		btn7.setIcon(new ImageIcon("D:\\�㻭�Ҳ�BUTTON����ͼƬ\\��Բ.jpg"));
		btn9.setIcon(new ImageIcon("D:\\�㻭�Ҳ�BUTTON����ͼƬ\\�����.jpg"));
		btn8.setIcon(new ImageIcon("D:\\�㻭�Ҳ�BUTTON����ͼƬ\\Բ�Ǿ���.jpg"));
		
		
		
		jp.add(jl1);
	    jp.add(jl2);
	    jp.add(jl3);
	    jp.setBounds(100, 100, 200, 100);
	    this.add(jp);
//		this.setUndecorated(true);
		
		
		// ��ѡ����뵽�˵�����
		mu.add(openfile);
		mu.add(save);
		mu.add(saveas);
		mu.add(seperate1);// ����ָ���
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
		
		
		// ���Ӽ�����
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

		// ���ô���λ�úʹ�С
		setTitle("��ͼ");
		setBounds(100, 100, 1000, 700);
		setBackground(jpanel4.getBackground());// ���ô��ڱ�����ɫ
		setJMenuBar(mb);// ���˵������뵽����
		
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
		this.setVisible(true);// ��ʾ����		
	}
	//��Ӧ�¼�
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == coloredit) {
			Color newColor = JColorChooser.showDialog(this, "��ɫ��",
					jpanel4.getBackground());
			if (jpanel4.getBackground() != null) {
				jpanel4.setBackground(newColor);
			}
		}else if (e.getSource() == btn3) {
			Color newColor = JColorChooser.showDialog(this, "��ɫ��",color);
		    color=newColor;
		    flag=1;
		}
		
		else if(e.getSource()==btn1){
				flag=1;//Ǧ��
			}else if(e.getSource()==btn2){
				flag=2;//��Ƥ
			}else if(e.getSource()==btn3){
				flag=3;//����
			}else if(e.getSource()==btn4){
				flag=4;//ֱ��
			}else if(e.getSource()==btn5){
				flag=5;//����
			}else if(e.getSource()==btn6){
				flag=6;//Բ��
			}else if(e.getSource()==btn7){
				flag=7;//��Բ
			}else if(e.getSource()==btn8){
				flag=8;//Բ�Ǿ���
			}else if(e.getSource()==btn9){
				flag=9;//�����
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
			g.clearRect(0, 0, getSize().width, getSize().height);// ���
		    repaint();
		}
		for (int i = 0; i < n-1; i++) {
			p1 = (Point) point.elementAt(i);
			p2 = (Point) point.elementAt(i + 1);
			grap.setColor(p1.color);
			if (p1.tool == p2.tool) {
				switch (p1.tool) {
				case 1:// ����
					Line2D line1 = new Line2D.Double(p1.x, p1.y, p2.x, p2.y);
					
					grap.draw(line1);
					break;
				case 2:// ��Ƥ
					setBackground(jpanel4.getBackground());
//					Color c = grap.getColor();
//					grap.setColor(Color.WHITE);
//					Line2D line11 = new Line2D.Double(p1.x, p1.y, p2.x, p2.y);
//					grap.setColor(c);
					g.clearRect(p1.x, p1.y, w, w);
					break;
				case 4:// ��ֱ��
					Line2D line2 = new Line2D.Double(p1.x, p1.y, p2.x, p2.y);
					grap.draw(line2);
					break;
				case 5:// ������
					Rectangle2D rect = new Rectangle2D.Double(p1.x, p1.y,
							Math.abs(p2.x - p1.x), Math.abs(p2.y - p1.y));
					grap.draw(rect);
					break;
				case 6:// ��Բ
Arc2D circle = new Arc2D.Double(p1.x, p1.y,
							Math.abs(p2.x - p1.x), Math.abs(p2.x - p1.x),0,360,Arc2D.OPEN);
					grap.draw(circle);
					break;
				case 7:// ����Բ
					Ellipse2D ellipse = new Ellipse2D.Double(p1.x, p1.y,
							Math.abs(p2.x - p1.x), Math.abs(p2.y - p1.y));
					grap.draw(ellipse);
					break;
				case 8:// ��Բ�Ǿ���
					RoundRectangle2D rect_round = new RoundRectangle2D.Double(
							p1.x, p1.y, Math.abs(p2.x - p1.x), Math.abs(p2.y
									- p1.y), 20, 10);
					grap.draw(rect_round);
					break;
				case 9:// �������
					int px[]={p1.x,p2.x,p1.y,p2.y};
					int py[]={p1.y,p2.y,p2.x,p1.x};
					g.drawPolyline(px, py, 3);
					break;
				case 15:// �ضϣ�����
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
		   case 1://Ǧ��
		   case 2://��Ƥ
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
		case 1: // Ǧ��
			label.setText("                    ��һ�����ؿ�������״������");
			break;
		case 2: // ��Ƥ
			label.setText("                    ʹ����Ƥ����ȥͼƬ��һ����");
			break;
		case 3:
		    label.setText("                    ѡ�񻭱���ɫ");
		    break;
		case 4: // ֱ��
			label.setText("                    ��һ��ֱ��");
			break;
		case 5: // ����
			label.setText("                    ��һ������");
			break;
		case 6: // Բ
			label.setText("                    ��һ��Բ");
			break;
		case 7: // ��Բ
			label.setText("                    ��һ����Բ");
			break;
		case 8: // Բ�Ǿ���
			label.setText("                    ��һ��Բ�Ǿ���");
			break;
		case 9: // �����
			label.setText("                    ��һ�������");
			break;
		default:
			label.setText("                    ��ӭʹ�ã�");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	public void mousePressed(MouseEvent e) {
	
		Point p1;
		switch(flag){
		   case 4://ֱ��
		   case 5: //����
		   case 6: //Բ
		   case 7: //��Բ
		   case 8: //Բ�Ǿ���
		   case 9: //�����
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
		case 1: // Ǧ��
		case 2: // ��Ƥ
			point.addElement(mark);
			break;
		case 4: // ֱ��
		case 5: // ����
		case 6: // Բ
		case 7: // ��Բ
		case 8: // Բ�Ǿ���
		case 9: // �����
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
		long time = 1 * 36000; // �Զ��嵹��ʱʱ��
		long hour = 0;
		long minute = 0;
		long seconds = 0;
		
		while (time >= 0) {
			hour = time / 3600;
			minute = (time - hour * 3600) / 60;
			seconds = time - hour * 3600 - minute * 60;
			MyWindow.jl1.setText(hour + "ʱ");
			MyWindow.jl2.setText(minute + "��");
			MyWindow.jl3.setText(seconds + "��");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time--;
		}
		
	}
	
	
	
	
	
	
	
}
