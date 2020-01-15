package pkg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

public class Saver {
	
	public static void Write(String sourcePath, String destPath) throws IOException {
		CompressArchive.compress(destPath,Saver.processData(sourcePath));

	}
	
	public static byte[] processData(String sourcePath) throws IOException {
		
		final int SAMPLE_SIZE = 7;
		
		BufferedImage img = null;
        
        img = ImageIO.read(new File(sourcePath));
  
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
		
		
		for(int i=0; i<SAMPLE_SIZE; i++) {
			
			 int rgb = img.getRGB(sample_locs[i].x, sample_locs[i].y);
             int red = (rgb >> 16 ) & 0x000000FF;
             int green = (rgb >> 8 ) & 0x000000FF;
             int blue = (rgb) & 0x000000FF;
			
			samples[i] = new Pixel(red,green,blue);
		}
	
        ByteArrayOutputStream out = new ByteArrayOutputStream( );
		
        out.write(Header.convertHeader(width,height,samples));
        
		for (int h = 0; h<height; h++)
        {
            for (int w = 0; w<width; w++)
            {
                
                int rgb = img.getRGB(w, h);
                int red = (rgb >> 16 ) & 0x000000FF;
                int green = (rgb >> 8 ) & 0x000000FF;
                int blue = (rgb) & 0x000000FF;
                
                int rank = 0;
                
                int fitness[] = new int[SAMPLE_SIZE];
                
                for(int i = 0; i<SAMPLE_SIZE; i++) {
                	fitness[i] = Math.abs(red - samples[i].r) + Math.abs(green - samples[i].g) + Math.abs(blue - samples[i].b);
                }
                for(int i = 0; i<SAMPLE_SIZE; i++) {
                	if(fitness[i]<fitness[rank])
                		rank = i;
                }
                
                
                out.write(rank);
            }
        }
		return out.toByteArray();
	}
}
