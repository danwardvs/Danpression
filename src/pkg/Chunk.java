package pkg;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Chunk {
	public static void main(String[] args) {
		int[] test_chunk = {7,1,7,3,4,5,6,7};
		test_chunk = DecodeChunk(EncodeChunk(test_chunk));
		
		for(int elem:test_chunk)
			System.out.println(elem);
	}
	
	public static void WriteChunk() throws IOException {

		FileOutputStream fos = new FileOutputStream("chunk.dat");
        BufferedOutputStream out = new BufferedOutputStream(fos);
		
		int[] test_chunk = {7,1,2,3,4,5,6,7};
		
		System.out.println("its not an easy street");
		

	
		out.write(test_chunk[0]*32 + test_chunk[1]*4 + (test_chunk[2]>>1));
		out.write(   ((test_chunk[2]>>2) & 1)*128 + test_chunk[3]*16 + test_chunk[4]*2 + (test_chunk[5]>>2));
		out.write(   ((test_chunk[5]>>1) & 1)*128 + ((test_chunk[5]>>2) & 1)*64 + test_chunk[6]*8 + test_chunk[7]);

	
		
		
		
      

        out.close();
        fos.close();
	}
	
	public static void ReadChunk() throws IOException {
		
		byte[] fileContents;
		int[] results = new int[8];
		Path path = Paths.get("chunk.dat");
		fileContents =  Files.readAllBytes(path);
		
		results[0] = ((fileContents[0]>>7) & 1)*4 + ((fileContents[0]>>6) & 1)*2 + ((fileContents[0]>>5) & 1);
		results[1] = ((fileContents[0]>>4) & 1)*4 + ((fileContents[0]>>3) & 1)*2 + ((fileContents[0]>>2) & 1);
		results[2] = ((fileContents[0]>>1) & 1)*4 + ((fileContents[0]>>0) & 1)*2 + ((fileContents[1]>>7) & 1);
		results[3] = ((fileContents[1]>>6) & 1)*4 + ((fileContents[1]>>5) & 1)*2 + ((fileContents[1]>>4) & 1);
		results[4] = ((fileContents[1]>>3) & 1)*4 + ((fileContents[1]>>2) & 1)*2 + ((fileContents[1]>>1) & 1);
		results[5] = ((fileContents[1]>>0) & 1)*4 + ((fileContents[2]>>7) & 1)*2 + ((fileContents[2]>>6) & 1);
		results[6] = ((fileContents[2]>>5) & 1)*4 + ((fileContents[2]>>4) & 1)*2 + ((fileContents[2]>>3) & 1);
		results[7] = ((fileContents[2]>>2) & 1)*4 + ((fileContents[2]>>1) & 1)*2 + ((fileContents[2]>>0) & 1);



		
		
		for(int elem:results)
			System.out.println(elem);
	}
	public static int[] DecodeChunk(byte[] chunk) {
		
		int[] results = new int[8];
		
		results[0] = ((chunk[0]>>7) & 1)*4 + ((chunk[0]>>6) & 1)*2 + ((chunk[0]>>5) & 1);
		results[1] = ((chunk[0]>>4) & 1)*4 + ((chunk[0]>>3) & 1)*2 + ((chunk[0]>>2) & 1);
		results[2] = ((chunk[0]>>1) & 1)*4 + ((chunk[0]>>0) & 1)*2 + ((chunk[1]>>7) & 1);
		results[3] = ((chunk[1]>>6) & 1)*4 + ((chunk[1]>>5) & 1)*2 + ((chunk[1]>>4) & 1);
		results[4] = ((chunk[1]>>3) & 1)*4 + ((chunk[1]>>2) & 1)*2 + ((chunk[1]>>1) & 1);
		results[5] = ((chunk[1]>>0) & 1)*4 + ((chunk[2]>>7) & 1)*2 + ((chunk[2]>>6) & 1);
		results[6] = ((chunk[2]>>5) & 1)*4 + ((chunk[2]>>4) & 1)*2 + ((chunk[2]>>3) & 1);
		results[7] = ((chunk[2]>>2) & 1)*4 + ((chunk[2]>>1) & 1)*2 + ((chunk[2]>>0) & 1);	
		
		return results;
	}
	
	public static byte[] EncodeChunk(int[] chunk) {
		
		byte[] results = new byte[3];
		
		results[0] = (byte)(chunk[0]*32 + chunk[1]*4 + (chunk[2]>>1));
		results[1] = (byte)(   ((chunk[2]>>2) & 1)*128 + chunk[3]*16 + chunk[4]*2 + (chunk[5]>>2));
		results[2] = (byte)(   ((chunk[5]>>1) & 1)*128 + ((chunk[5]>>2) & 1)*64 + chunk[6]*8 + chunk[7]);

		return results;
		
	}
}
