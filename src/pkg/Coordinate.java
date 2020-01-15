package pkg;

public class Coordinate implements Comparable<Coordinate> {
	public int x;
	public int y;
	
	public Coordinate(int newX, int newY) {
		x=newX;
		y=newY;
	}
	
	public String toString() {
		return String.format("(%d,%d)",x,y);
	}
	
	public int compareTo(Coordinate compareCoordinate) {
		//ascending order
		return this.x - compareCoordinate.x;
		
	}	
}
