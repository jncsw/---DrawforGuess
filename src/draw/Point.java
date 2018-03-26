package draw;

//import java.awt.Color;

/*class Point{
	private static int x,y;
	Point(int x,int y, int i, Color color){
		setX(x);
		setY(y);
	}
	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		Point.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		Point.y = y;
	}
	
}*/
import java.awt.Color;
import java.io.Serializable;


@SuppressWarnings("serial")
class Point implements Serializable 
{
 int x,y;
 Color color;
 int tool;

 Point(int x, int y, int tool, Color color)
 {
  this.x = x;
  this.y = y;
  this.color = color;
  this.tool = tool;
  }
}


