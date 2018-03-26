package std;

import java.awt.event.*;
import javax.swing.*;

public class stdButton extends JButton
{
	public MouseAdapter m = new MouseAdapter()
	{
		public void mousePressed(MouseEvent e)
		{
			setLocation((int)getLocation().getX()+2,(int)getLocation().getY()+2);
		}
		public void mouseReleased(MouseEvent e)
		{
			setLocation((int)getLocation().getX()-2,(int)getLocation().getY()-2);
		}
	};
	JButton a;
	public stdButton()
	{
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		addMouseListener(m);
	}
	public stdButton(ImageIcon icon)
	{
		this();
		setIcon(icon);
	}
	public stdButton(ImageIcon icon,String s)
	{
		this();
		this.setText(s);
		setIcon(icon);
	}
	public stdButton(String s)
	{
		this();
		this.setText(s);
	}
	
	
}
