package draw;
import java.awt.Stroke;
import java.awt.Color;

public class DPoints implements java.io.Serializable{
	int x,y;
	Color c;
	Stroke bs;
	int tool;
	public DPoints(int x, int y, Color c, Stroke bs, int tool) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.bs = bs;
		this.tool = tool;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
	public Stroke getBs() {
		return bs;
	}
	public void setBs(Stroke bs) {
		this.bs = bs;
	}
	public int getTool() {
		return tool;
	}
	public void setTool(int tool) {
		this.tool = tool;
	}
	
}
