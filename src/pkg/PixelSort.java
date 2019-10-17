package pkg;

import java.util.Arrays;

public class PixelSort {
	
	final static int SAMPLE_SIZE = 7;
	
	public static Coordinate[] Sort(Pixel[] unsorted, int colour) {
		
		Coordinate[] sorted = new Coordinate[SAMPLE_SIZE];
		
		for(int i=0; i<SAMPLE_SIZE; i++) {
			if(colour==0)
				sorted[i] = new Coordinate(unsorted[i].r,i);
			if(colour==1)
				sorted[i] = new Coordinate(unsorted[i].g,i);
			if(colour==2)
				sorted[i] = new Coordinate(unsorted[i].b,i);

		}
		Arrays.sort(sorted);
		
		
		return sorted;
		
	}
}
