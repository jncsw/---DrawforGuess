package chatpanel;

public class T {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "\\players nan qqq 111\naaa 123";
		String name = "nan";
		String st = s.substring("\\players".length()+name.length()+2);
		System.out.println(st);
	}

}
