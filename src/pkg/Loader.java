package pkg;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.awt.*;  
import javax.swing.JFrame;  
  

public class Loader extends Canvas{
	
	static int width;
	static int height;
	static int[]converted_contents;
	static byte[] data;
	
	static Pixel[] sample = new Pixel[7];
	static Coordinate[] sample_sorted_red = new Coordinate[7];
	static Coordinate[] sample_sorted_green = new Coordinate[7];
	static Coordinate[] sample_sorted_blue = new Coordinate[7];
	
	public static void processData(byte[] data) {
		
		Loader.data = data;
		
		byte[] split_bytes = new byte[4];
		
		for(int i=0; i<4; i++) {
			split_bytes[i] = data[i];
		}
		
		
		width = ByteBuffer.wrap(split_bytes).getInt();
		
		for(int i=4; i<8; i++) {
			split_bytes[i-4] = data[i];
		}
		height = ByteBuffer.wrap(split_bytes).getInt();
		
		for(int i=0; i<7; i++) {
			sample[i] = new Pixel(data[8+(i*3)] & 0xFF,data[8+(i*3)+1] & 0xFF,data[8+(i*3)+2] & 0xFF);
		}

		Loader m=new Loader();  
        JFrame f=new JFrame();  
        f.add(m);  
        f.setSize(width,height);  
        //f.setLayout(null);  
        f.setVisible(true);  
	}
	
	public static void Load(String destPath) throws IOException {
		
		// Compressed data loading
		processData(ExtractArchive.Extract(destPath));
		
	}

    public void paint(Graphics g) {
    	
    	for(int h=0; h<height; h++) {
    		for(int w=0; w<width; w++) {
    			int header_size = 29;
    			int new_index = data[header_size+w+(h*width)];
    			
    			if(new_index==7)
    				new_index=6;
    			
    			int new_r = sample[new_index].r;
    			int new_g = sample[new_index].g;
    			int new_b = sample[new_index].b;
    			
    		   			
    			g.setColor(new Color(new_r,new_g,new_b));
        		g.drawLine(w, h, w, h);
       		

    		}
    	}
    }  
}
