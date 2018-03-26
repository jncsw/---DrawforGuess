package chatpanel;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class socketdata {
	Socket s ;
	BufferedReader in;
	PrintWriter out;
	int draw;
	public socketdata(Socket s, BufferedReader in, PrintWriter out, int draw) {
		super();
		this.s = s;
		this.in = in;
		this.out = out;
		this.draw = draw;
	}
	public Socket getS() {
		return s;
	}
	public void setS(Socket s) {
		this.s = s;
	}
	public BufferedReader getIn() {
		return in;
	}
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	public PrintWriter getOut() {
		return out;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	

	
}
