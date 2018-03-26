package draw;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class client_1 implements Runnable{
	Socket socket = null; 
	ObjectOutputStream os = null; 
	ObjectInputStream is = null;
	public client_1(String ip,int port) throws IOException, ClassNotFoundException {
		socket = new Socket(ip, port); 
		os = new ObjectOutputStream(socket.getOutputStream()); 
		is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
	}
	public void writeObj(DPoints p) throws IOException{
		os.writeObject(p); 
		os.flush(); 
	}
	DPoints d;
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
		try {
			d =(DPoints) is.readObject();
			if (d != null) { 
				System.out.println(d.getX()+" "+d.getY()); 
				
			} 
		} catch (IOException | ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			break;
		} 
		}
	}
	public DPoints getdpoints(){
		return d;
		
	}
} 




