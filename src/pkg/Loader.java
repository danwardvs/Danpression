package pkg;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.awt.*;  
import javax.swing.JFrame;  
  

public class Loader extends Canvas{
	
	static int width;
	static int height;
	static byte[] fileContents;
	static int[]converted_contents;

	static Pixel[] sample = new Pixel[7];
	static Coordinate[] sample_sorted_red = new Coordinate[7];
	static Coordinate[] sample_sorted_green = new Coordinate[7];
	static Coordinate[] sample_sorted_blue = new Coordinate[7];

	static Random rand = new Random();
	
	public static void main(String[] args) throws IOException {
		

		Path path = Paths.get("output.dan");
		fileContents =  Files.readAllBytes(path);
		
		byte[] split_bytes = new byte[4];
		
		for(int i=0; i<4; i++) {
			split_bytes[i] = fileContents[i];
		}
		width = ByteBuffer.wrap(split_bytes).getInt();
		
		for(int i=4; i<8; i++) {
			split_bytes[i-4] = fileContents[i];
		}
		height = ByteBuffer.wrap(split_bytes).getInt();
		
		
		System.out.println(width);
		System.out.println(height);
		
		
		
		for(int i=0; i<7; i++) {
			sample[i] = new Pixel(fileContents[8+(i*3)] & 0xFF,fileContents[8+(i*3)+1] & 0xFF,fileContents[8+(i*3)+2] & 0xFF);
			System.out.println(sample[i]);
		}
		
//		converted_contents = new int[width*height];
//	
//		for(int i=0; i<(width*height)/3; i+=3) {
//			byte[] chunk = new byte[3];
//			chunk[0] = fileContents[i+29];
//			chunk[1] = fileContents[i+1+29];
//			chunk[2] = fileContents[i+2+29];
//			int[] converted_chunk = Chunk.DecodeChunk(chunk);
//			
//			System.out.println(i);
//			
//			for(int j=0; j<8; j++) {
//				converted_contents[((i/3)*8) + j] = converted_chunk[j];
//						
//			}
//		}
//		

		Loader m=new Loader();  
        JFrame f=new JFrame();  
        f.add(m);  
        f.setSize(width,height);  
        //f.setLayout(null);  
        f.setVisible(true);  
	}
	
    public void paint(Graphics g) {
    	
    	
    	for(int h=0; h<height; h++) {
    		for(int w=0; w<width; w++) {
    			int header_size = 29;
    			//System.out.printf("this>");
        		//System.out.println(fileContents[w*(h+1)]);
    			
    			// Chunk code
    			//int new_index = converted_contents[w+(h*width)];
    			
    			// NonChunkCode
    			int new_index = fileContents[header_size+w+(h*width)];
    			
    			if(new_index==7)
    				new_index=6;
    			
    			int new_r = sample[new_index].r;
    			int new_g = sample[new_index].g;
    			int new_b = sample[new_index].b;
    			
    			int upper_sample_index = 7;
    			
    		   			
    			g.setColor(new Color(new_r,new_g,new_b));
        		g.drawLine(w, h, w, h);
       		

    		}
    	}
    }  
}
