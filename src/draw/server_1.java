package draw;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import chatpanel.read_send;
import chatpanel.socketdata;

public class server_1  implements Runnable{
	ServerSocket ss;
	ObjectInputStream is = null; 
	ObjectOutputStream os = null; 
	static ArrayList<objdata> soc = new ArrayList<>();
	Socket s;
	public server_1(int p) throws IOException{
		ss=new ServerSocket(p);
		
	}
	
	 static void invoke(final Socket socket){
		new Thread(new Runnable() { 
			public void run() { 
			ObjectInputStream is = null; 
			ObjectOutputStream os = null; 
			try { 
				is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream())); 
				os = new ObjectOutputStream(socket.getOutputStream()); 
				while(true){
				Object obj = is.readObject(); 
				for(objdata o:server_1.soc){
					os=o.os;
					os.writeObject(obj); 
					os.flush(); 
				}
				}
				} catch (IOException ex) { 
			
				} catch(ClassNotFoundException ex) { 
			
				} finally { 
					try { 
						is.close(); 
					} catch(Exception ex) {} 
					try { 
						os.close(); 
					} catch(Exception ex) {} 
					try { 
						socket.close(); 
					} catch(Exception ex) {} 
				} 
				} 
			}).start(); 
		}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				s=ss.accept();
				is = new ObjectInputStream(new BufferedInputStream(s.getInputStream())); 
				os = new ObjectOutputStream(s.getOutputStream()); 
				objdata d = new objdata(is, os, s);
				soc.add(d);
				
				invoke(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
	} 

	
