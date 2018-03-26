package std;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class stdframe extends JFrame{
	public stdframe() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth  = screenSize.width;
		int screenHeight = screenSize.height;
		this.setBounds(screenWidth/4,screenHeight/4 , screenWidth/2, screenHeight/2);
		
	}
	public stdframe(int n){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth  = screenSize.width;
		int screenHeight = screenSize.height;
		this.setBounds(screenWidth/4,screenHeight/4 , screenWidth/2, screenHeight/2);
		
		ImageIcon background = new ImageIcon("source/img/bg/"+n+".jpg");
		background.setImage(background.getImage()
				.getScaledInstance(screenWidth/2, screenHeight/2, Image.SCALE_DEFAULT ));
		JLabel bgl = new JLabel(background);
		bgl.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());  
		JPanel imagePanel; 
		imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());  
		
		this.getLayeredPane().setLayout(null);  
		// 把背景图片添加到分层窗格的最底层作为背景  
		this.getLayeredPane().add(bgl, new Integer(Integer.MIN_VALUE));
			
	}
	public stdframe(int n,String s){
		super(s);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth  = screenSize.width;
		int screenHeight = screenSize.height;
		this.setBounds(screenWidth/4,screenHeight/4 , screenWidth/2, screenHeight/2);
		
		ImageIcon background = new ImageIcon("source/img/bg/"+n+".jpg");
		background.setImage(background.getImage()
				.getScaledInstance(screenWidth/2, screenHeight/2, Image.SCALE_DEFAULT ));
		JLabel bgl = new JLabel(background);
		bgl.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());  
		JPanel imagePanel; 
		imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());  
		
		this.getLayeredPane().setLayout(null);  
		// 把背景图片添加到分层窗格的最底层作为背景  
		this.getLayeredPane().add(bgl, new Integer(Integer.MIN_VALUE));
			
	}

}
