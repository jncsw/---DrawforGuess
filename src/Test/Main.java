package Test;

import database.data;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		data d =new data();
		d.connect();
		for(int i = 0;i<12;i++){
		String s = d.getQA();
		System.out.println(s);}
		d.cls();
	}

}
