package chatpanel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class inf_of_player {
	public inf_of_player(String name,String score) {
		// TODO �Զ����ɵĹ��캯�����
		JPanel jp= new JPanel();
		jp.setBounds(0, 0, 100, 30);
		JLabel jbn=new JLabel("���"+name);
		JLabel jbs=new JLabel("�÷�"+score);
		jp.add(jbn);
		jp.add(jbs);
		
		
	}
}
