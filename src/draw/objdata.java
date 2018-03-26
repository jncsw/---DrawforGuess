package draw;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class objdata {
	ObjectInputStream is ; 
	ObjectOutputStream os; 
	Socket s;
	public objdata(ObjectInputStream is, ObjectOutputStream os, Socket s) {
		super();
		this.is = is;
		this.os = os;
		this.s = s;
	}
	
}
