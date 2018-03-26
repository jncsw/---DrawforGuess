package chatpanel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server implements Runnable{
    ServerSocket ss;
    ArrayList<socketdata> soc = new ArrayList<>();
    Socket s;
	private String name;
	BufferedReader in ;
	PrintWriter out;
	socketdata sd;
	ArrayList<playersdata> apd=new ArrayList<>();
  public server(int p) throws IOException {
	 ss=new ServerSocket(p+1);
//	 new client(name);
}
  public void setName(String name){
	  this.name=name;
  }
@Override
public void run() {
	// TODO Auto-generated method stub
	try{
	while(true){
		s=ss.accept();
		in = new BufferedReader(
				new InputStreamReader(
						s.getInputStream()));
		out = new PrintWriter(
				new BufferedWriter(
						new OutputStreamWriter(
								s.getOutputStream())), true);
		sd = new socketdata(s, in, out, 0);
		soc.add(sd);
		read_send rs = new read_send(in,soc);
		Thread t = new Thread(rs);
		t.start();
	}
	}catch (Exception e) {
		// TODO: handle exception
	}
}
}
