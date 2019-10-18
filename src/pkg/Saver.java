package pkg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;

public class Saver {
	public static void main(String[] args) throws IOException {
		
		final int SAMPLE_SIZE = 7;
		
		BufferedImage img = null;
        
		try {
            img = ImageIO.read(new File("tiger.bmp"));
        } catch (IOException e) {
        	System.out.println(e.toString());
        }
        
		
		int height = img.getHeight();
        int width = img.getWidth();
		
		Coordinate[] sample_locs = new Coordinate[SAMPLE_SIZE];
		
		int width_3 = width/3;
		int height_4 = height/4;
		int width_4 = width/4;
		
		sample_locs[0] = new Coordinate(width_3,height_4);
		sample_locs[1] = new Coordinate(width_3*2,height_4);
		sample_locs[2] = new Coordinate(width_4,height_4*2);
		sample_locs[3] = new Coordinate(width_4*2,height_4*2);
		sample_locs[4] = new Coordinate(width_4*3,height_4*2);
		sample_locs[5] = new Coordinate(width_3,height_4*3);
		sample_locs[6] = new Coordinate(width_3*2,height_4*3);
		
	
		Pixel[] samples = new Pixel[SAMPLE_SIZE];
		Coordinate[] ordered_red = new Coordinate[SAMPLE_SIZE];
		
		for(int i=0; i<SAMPLE_SIZE; i++) {
			
			 int rgb = img.getRGB(sample_locs[i].x, sample_locs[i].y);
             int red = (rgb >> 16 ) & 0x000000FF;
             int green = (rgb >> 8 ) & 0x000000FF;
             int blue = (rgb) & 0x000000FF;
			
			samples[i] = new Pixel(red,green,blue);
			System.out.println(samples[i].toString());
		}
		
		Coordinate[] red_sorted = PixelSort.Sort(samples,0);

		
		
		
		FileOutputStream red_data = new FileOutputStream("red.dat");
        BufferedOutputStream out = new BufferedOutputStream(red_data);
		
        int chunk_count = 0;
        int[] chunk = new int[8];
        
		for (int h = 0; h<height; h++)
        {
            for (int w = 0; w<width; w++)
            {
                
            	
            	
                int rgb = img.getRGB(w, h);
                int red = (rgb >> 16 ) & 0x000000FF;
                int green = (rgb >> 8 ) & 0x000000FF;
                int blue = (rgb) & 0x000000FF;
                
                int rank = SAMPLE_SIZE;
                
                for(int i=0; i<SAMPLE_SIZE; i++) {
                	if(red<red_sorted[i].x) {
                		rank=red_sorted[i].y;
                		break;

                	}
                }
                red_data.write(rank);
                
                
                // Chunk code
               
//                chunk[chunk_count] = rank;
//                
//                chunk_count++;
//                
//                if(chunk_count == 8) {
//                	byte[] encoded_chunk =  Chunk.EncodeChunk(chunk);
//                	chunk_count=0;
//                	red_data.write(encoded_chunk);
//                	/*for(int k=0;k<8;k++) {
//                		chunk[k]=0;
//                	}*/
//                }
//            	
                
               
                
            }
        
        
        }
		out.flush();
        red_data.close();
        
        
        System.out.println("done");
        
       
        
	}
}
