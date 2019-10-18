package pkg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.awt.*;  
import javax.swing.JFrame;  
  

public class Loader extends Canvas{
	
	final static int width = 320;
	final static int height = 240;
	static byte[] fileContents;
	static int[]converted_contents = new int[width*height];

	static Pixel[] sample = new Pixel[7];
	
	public static void main(String[] args) throws IOException {
		
		sample[0] = new Pixel(18,11,6);
		sample[1] = new Pixel		(229,184,131);
		sample[2] = new Pixel		(72,36,5);
		sample[3] = new Pixel		(167,162,156);
		sample[4] = new Pixel		(123,102,80);
		sample[5] = new Pixel		(177,188,195);
		sample[6] = new Pixel		(49,54,35);
		
		Path path = Paths.get("red.dat");
		fileContents =  Files.readAllBytes(path);
		
//		
//		for(int i=0; i<(width*height)/3; i+=3) {
//			byte[] chunk = new byte[3];
//			chunk[0] = fileContents[i];
//			chunk[1] = fileContents[i+1];
//			chunk[2] = fileContents[i+2];
//			int[] converted_chunk = Chunk.DecodeChunk(chunk);
//			
//			for(int j=0; j<8; j++) {
//				converted_contents[((i/3)*8) + j] = converted_chunk[j];
//						
//			}
//		}
		
		
		for(byte elem:fileContents) {
			//System.out.println(elem);
		}
		
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
    			//System.out.printf("this>");
        		//System.out.println(fileContents[w*(h+1)]);
    			
    			// Chunk code
    			//int new_index = converted_contents[w+(h*width)];
    			
    			// NonChunkCode
    			int new_index = fileContents[w+(h*width)];

    			if(new_index==7) {
    				new_index=6;
    			}
    			
    			
    			g.setColor(new Color(sample[new_index].r,sample[new_index].g,sample[new_index].b));
        		g.drawLine(w, h, w, h);
        		

    		}
    	}
    }  
}
