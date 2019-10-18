package pkg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.awt.*;  
import javax.swing.JFrame;  
  

public class Loader extends Canvas{
	
	final static int width = 1920;
	final static int height = 1200;
	static byte[] fileContents;
	static Pixel[] sample = new Pixel[7];
	
	public static void main(String[] args) throws IOException {
		
		sample[0] = new Pixel(147,47,31);
		sample[1] = new Pixel		(187,83,80);
		sample[2] = new Pixel		(116,45,43);
		sample[3] = new Pixel		(83,3,2);
		sample[4] = new Pixel		(255,231,213);
		sample[5] = new Pixel		(161,34,25);
		sample[6] = new Pixel		(105,50,45);
		
		Path path = Paths.get("red.dat");
		fileContents =  Files.readAllBytes(path);
		
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
