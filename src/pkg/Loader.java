package pkg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.awt.*;  
import javax.swing.JFrame;  
  

public class Loader extends Canvas{
	
	static int width = 725;
	static int height = 1007;
	static byte[] fileContents;
	static int[]converted_contents = new int[width*height];

	static Pixel[] sample = new Pixel[7];
	static Coordinate[] sample_sorted_red = new Coordinate[7];
	static Coordinate[] sample_sorted_green = new Coordinate[7];
	static Coordinate[] sample_sorted_blue = new Coordinate[7];

	static Random rand = new Random();
	
	public static void main(String[] args) throws IOException {
		
		
		
		sample[0] = new Pixel(171,118,114);
		sample[1] = new Pixel(240,210,208);
		sample[2] = new Pixel(103,73,75);
		sample[3] = new Pixel(234,177,183);
		sample[4] = new Pixel(217,194,188);
		sample[5] = new Pixel(78,46,47);
		sample[6] = new Pixel(147,89,85);
		
		Path path = Paths.get("output.dan");
		fileContents =  Files.readAllBytes(path);
		
		System.out.println(fileContents.length);
		
		for(int i=0; i<(width*height)/3; i+=3) {
			byte[] chunk = new byte[3];
			chunk[0] = fileContents[i];
			chunk[1] = fileContents[i+1];
			chunk[2] = fileContents[i+2];
			int[] converted_chunk = Chunk.DecodeChunk(chunk);
			
			for(int j=0; j<8; j++) {
				converted_contents[((i/3)*8) + j] = converted_chunk[j];
						
			}
		}
		
		sample_sorted_red = PixelSort.Sort(sample,0);
		sample_sorted_green= PixelSort.Sort(sample,1);

		sample_sorted_blue = PixelSort.Sort(sample,2);

		for(Coordinate elem:sample_sorted_green) {
			System.out.println(elem);
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
    			
    			
//    			
//    			for(int i=0; i<7; i++) {
//    				if(sample_sorted_red[i].y==new_index) {
//    					//System.out.println(sample_sorted[i]);
//    					if(i+1!=7) {
//    						//System.out.println(sample_sorted[i+1]);
//    						upper_sample_index = sample_sorted_red[i+1].y;
//    					}
//    				}
//    				
//    			}
//    	
//    			if(upper_sample_index!=7) {
//    				
//    				if(sample[upper_sample_index].r - sample[new_index].r>0)
//    					new_r = rand.nextInt(sample[upper_sample_index].r - sample[new_index].r) + sample[new_index].r;
//    				else
//    					new_r = sample[new_index].r;
//
//    			}
//    			if(upper_sample_index==7) {
//    				
//    				if((255 - sample[sample_sorted_red[6].y].r)>0)
//    					new_r = rand.nextInt(255 - sample[sample_sorted_red[6].y].r) + sample_sorted_red[6].x;
//    				else 
//    					new_r = sample_sorted_red[6].x;
//    				
//
//    			}
//    			
//    			upper_sample_index = 7;
//    			
//    			for(int i=0; i<7; i++) {
//    				if(sample_sorted_green[i].y==new_index) {
//    					//System.out.println(sample_sorted[i]);
//    					if(i+1!=7) {
//    						//System.out.println(sample_sorted[i+1]);
//    						upper_sample_index = sample_sorted_green[i+1].y;
//    					}
//    				}
//    				
//    			}
//    	
//    			if(upper_sample_index!=7) {
//    				if(sample[upper_sample_index].g - sample[new_index].g>0)
//    					new_g = rand.nextInt(sample[upper_sample_index].g - sample[new_index].g) + sample[new_index].g;
//    				else
//    					new_g = sample[new_index].g;
//    			}
//    			if(upper_sample_index==7) {
//    				if(255 - sample[sample_sorted_green[6].y].g>0)
//    					new_g = rand.nextInt(255 - sample[sample_sorted_green[6].y].g) + sample_sorted_green[6].x;
//    				else {
//    					new_g = sample_sorted_green[6].x;
//    				}
//
//    			}
//    	
//    			
//    			upper_sample_index = 7;
//    			
//    			for(int i=0; i<7; i++) {
//    				if(sample_sorted_blue[i].y==new_index) {
//    					if(i+1!=7) {
//    						upper_sample_index = sample_sorted_blue[i+1].y;
//    					}
//    				}
//    				
//    			}
//    	
//    			if(upper_sample_index!=7) {
//
//    				if((sample[upper_sample_index].b - sample[new_index].b)>0)
//    					new_b = rand.nextInt(sample[upper_sample_index].b - sample[new_index].b) + sample[new_index].b;
//    				else
//    					new_b = sample[new_index].b;
//
//    			}
//    			if(upper_sample_index==7) {
//    				if(255 - sample[sample_sorted_blue[6].y].b>0)
//    					new_b = rand.nextInt(255 - sample[sample_sorted_blue[6].y].b) + sample_sorted_blue[6].x;
//    				else
//    					new_b = sample_sorted_blue[6].x;
//    			}
//    			
    			g.setColor(new Color(new_r,new_g,new_b));
        		g.drawLine(w, h, w, h);
       		

    		}
    	}
    }  
}
