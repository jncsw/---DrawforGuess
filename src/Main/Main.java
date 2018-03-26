package Main;

import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JWindow;

import database.data;
import login.Room;
import login.login;
import login.reg;
import windows.windows;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub 
		
		new windows();
		windows.stop();
		new Room("Íæ¼Ò");
	}
}

