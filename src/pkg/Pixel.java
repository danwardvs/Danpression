package pkg;

public class Pixel {
	public int r;
	public int g;
	public int b;
	
	public Pixel(int newR,int newG,int newB) {
		r=newR;
		g=newG;
		b=newB;
	}
	public String toString() {
		return String.format("(%d,%d,%d)",r,g,b);
	}
}
