package conn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import chatpanel.playersdata;
import chatpanel.socketdata;

public class Server implements Runnable{
    ServerSocket ss;
    ArrayList<socketdata> soc = new ArrayList<>();
    
    Socket s;
	private String name;
	BufferedReader in ;
	PrintWriter out;
	socketdata sd;
  public Server(int n) throws IOException {
	 ss=new ServerSocket(n);
	 InetAddress ia=InetAddress.getLocalHost();
	 System.out.println(ia.getHostAddress());
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
		new Thread(new ReadSend(in,out,soc)).start();
	}
	}catch (Exception e) {
		// TODO: handle exception
	}
	
}
}
