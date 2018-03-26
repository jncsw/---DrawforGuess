package chatpanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class read_send  implements Runnable{
	BufferedReader in;
	ArrayList<socketdata> a;
	static String players= "";
	public read_send(BufferedReader in, ArrayList<socketdata> a) {
		this.in = in;
		this.a = a;
	}

	@Override
	public void run() {
		String s;
		while(true){
			 try {
				s = in.readLine();
				
				//////////////////////////////
				Scanner in = new Scanner(s);
				String op = in.next();
				if(op.equals("\\addplayer")){
					String n=  in.next();
					String df = in.next();
					players=players+n+" "+df+"..";
					for(socketdata sd:a){
						PrintWriter pw  = sd.getOut();
						pw.println("\\players "+n+" "+players);
						pw.flush();
					}
				}else 
				for(socketdata soc :a){
					PrintWriter pw = soc.getOut();
					pw.println(s);
					pw.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
		}
	}

}
