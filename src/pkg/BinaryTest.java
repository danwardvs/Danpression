package pkg;

 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;

public class BinaryTest {

	public static void main(String[] args) throws IOException {
		
		System.out.println("buttzzzzzzzzzzzzzzzzzzzz");
		
		FileOutputStream fos = new FileOutputStream("data.dat");
        BufferedOutputStream out = new BufferedOutputStream(fos);
       
            out.write(256);
            

        out.close();
        fos.close();
        
	}
}
