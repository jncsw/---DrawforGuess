package chatpanel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
	Socket s;
	PrintWriter pw ;
	BufferedReader br;
	private String namec;

	client(String namec) throws IOException{
		this.namec=namec;
		s=new Socket("127.0.0.1",9999);
		br = new BufferedReader(
	        new InputStreamReader(
			          s.getInputStream()));
		pw =new PrintWriter(
	        new BufferedWriter(
	    	          new OutputStreamWriter(
	    	            s.getOutputStream())), true);
//	chat_panel p=new chat_panel(800, 500,br,pw,namec);
//	Thread t = new Thread(p);
//	t.start();
	}
}
