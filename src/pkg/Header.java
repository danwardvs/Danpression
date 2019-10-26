package pkg;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Header {
		
		FileOutputStream data;
		
		public Header() {
			
		}
		
		public Header(String path) {
			
			try {
				data = new FileOutputStream(path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
		public static void main(String[] args) {
			
			System.out.println("YUEs");
			
		}
		public static byte[] intToByteArray(int value) {
			 return new byte[] {
			            (byte)(value >>> 24),
			            (byte)(value >>> 16),
			            (byte)(value >>> 8),
			            (byte)value};
			
		}
		public static byte[] convertHeader(int width, int height, Pixel[] samples) {
			byte[] header_data = new byte[29];
			byte[] width_data = intToByteArray(width);
			byte[] height_data = intToByteArray(height);

			
			
			for(int i=0; i<4; i++) {
				header_data[i] = width_data[i];
				header_data[i+4] = height_data[i];

			}

			for(int i=0; i<7; i++) {
				
				header_data[(i*3)+8] = (byte)samples[i].r;
				header_data[(i*3)+8] = (byte)samples[i].g;
				header_data[(i*3)+8] = (byte)samples[i].b;
			}
			
			return header_data;
		}
		
		public void write(int width, int height, Pixel[] samples) throws IOException {
			BufferedOutputStream out = new BufferedOutputStream(data);
			out.write(intToByteArray(width));
			out.write(intToByteArray(height));
			
			for(Pixel elem: samples) {
				out.write((byte)elem.r);
				out.write((byte)elem.g);
				out.write((byte)elem.b);
				
			}
			
			
			
			out.close();
		
		}
		public void read(String file_path) throws IOException {
			Path path = Paths.get(file_path);
			byte[] fileContents =  Files.readAllBytes(path);
			
			byte[] split_bytes = new byte[4];
			
			for(int i=0; i<4; i++) {
				split_bytes[i] = fileContents[i];
			}
			int width = ByteBuffer.wrap(split_bytes).getInt();
			
			for(int i=4; i<8; i++) {
				split_bytes[i-4] = fileContents[i];
			}
			int height = ByteBuffer.wrap(split_bytes).getInt();
			
			Pixel samples[] = new Pixel[7];
			
			for(int i=0; i<7; i++) {
				
				int r = fileContents[(i*3)+8] & 0xFF;
				int g = fileContents[(i*3)+1+8] & 0xFF;
				int b = fileContents[(i*3)+2+8] & 0xFF;
				samples[i] = new Pixel(r,g,b);
			}
			for(Pixel elem:samples) {
				System.out.println(elem);
			}
			System.out.println(width);
			System.out.println(height);
			
		}
			
		
}
