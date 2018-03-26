package chatpanel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class inf_of_player {
	public inf_of_player(String name,String score) {
		// TODO 自动生成的构造函数存根
		JPanel jp= new JPanel();
		jp.setBounds(0, 0, 100, 30);
		JLabel jbn=new JLabel("玩家"+name);
		JLabel jbs=new JLabel("得分"+score);
		jp.add(jbn);
		jp.add(jbs);
		
		
	}
}
