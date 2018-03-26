package conn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable{
	Socket s;
	PrintWriter pw ;
	BufferedReader br;
	private String namec;
	public void setName(String n){
		this.namec=n;
	}
	public Client(String ip,int n) throws IOException{
		s=new Socket(ip,n);
		br = new BufferedReader(
	        new InputStreamReader(
			          s.getInputStream()));
		pw =new PrintWriter(
	        new BufferedWriter(
	    	          new OutputStreamWriter(
	    	            s.getOutputStream())), true);
	}
	public int logpw(String s) throws IOException{
		pw.println(s);
		pw.flush();
		String ss = br.readLine();
		int xx = Integer.parseInt(ss);
		return xx;
	}
	public String addpw(String s) throws IOException{
		pw.println(s);
		pw.flush();
		String ss = br.readLine();
		return ss;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String s=  br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
