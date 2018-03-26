package chatpanel;

import java.net.Socket;

public class playersdata {
String name;
int score;
Socket s;
public playersdata(String name, int score) {
	super();
	this.name = name;
	this.score = score;
//	this.s = s;
}

}
