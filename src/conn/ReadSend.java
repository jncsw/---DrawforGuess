package conn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import chatpanel.playersdata;
import chatpanel.socketdata;
import database.data;

public class ReadSend  implements Runnable{
	BufferedReader in;
	PrintWriter pw ;
	int sco;
	ArrayList<socketdata> a;
//	ArrayList<playersdata> pd = new ArrayList<>();
	static String players;
	public boolean jug(String s ){
		boolean b = false;
		Scanner in = new Scanner(s);
		String op = in.next();
		if(op.equals("\\login")){
			b=true;
			String name = in.next();
			String pass = in.next();
			try {
				sco = data.operate(1, name, pass);
				pw.println(sco+"");
				pw.flush();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		else if(op.equals("\\reg")){
			b=true;
			String name = in.next();
			String pass = in.next();
			try {
				sco = data.operate(2, name, pass);
				pw.println(sco);
				pw.flush();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else if(op.equals("\\inchk")){
			b=true;
			String nam = in.next();
			try {
				sco = data.operate("sdu_user","User_name",nam);
				pw.println(sco);
				pw.flush();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(op.equals("\\addpalyer")){
			b=true;
			String n=  in.next();
			String df = in.next();
			players=players+n+" "+df+"\n";
//			playersdata pda = new playersdata(n, Integer.parseInt(df));
//			pd.add(pda);
			for(socketdata sd:a){
				PrintWriter pw  = sd.getOut();
				pw.println(players);
				pw.flush();
				
			}
		}
		return b;
		
	}
	public ReadSend(BufferedReader in, PrintWriter pw,ArrayList<socketdata> a) {
		this.in = in;
		this.pw = pw;
		this.a = a;
	}

	@Override
	public synchronized void run() {
		String s;
		while(true){
			 try {
				s = in.readLine();
				if(!jug(s)){
//				for(socketdata soc :a){
//					PrintWriter pw = soc.getOut();
//					pw.println(s);
//					pw.flush();
//				}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
		}
	}

}
