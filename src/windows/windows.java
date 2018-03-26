package windows;

import java.awt.*;

import javax.swing.*;

public class windows extends JWindow {
	static JWindow win;
	static int num=500;
	public static  void stop(){
		win.setVisible(false);
		Thread.interrupted();
	}
	public windows() throws InterruptedException{
		win = new JWindow();
		win.setLocationRelativeTo(null);
		win.setSize(num,num);
		 int windowWidth = win.getWidth();
	     int windowHeight = win.getHeight(); 
	     Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth  = screenSize.width;
		int screenHeight = screenSize.height;
		this.setBounds(screenWidth/2-windowWidth, screenHeight/2-windowHeight/2,2* num,num);
		String path = "111.jpg";
		ImageIcon background = new ImageIcon(path);
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);  
		this.getLayeredPane().add(label);
		setVisible(true);
		Thread.sleep(2000);
		setVisible(false);
	}

}
