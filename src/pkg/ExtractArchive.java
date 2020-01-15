package pkg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;



public class ExtractArchive {
    
	public static void main(String[] args) {
		ExtractArchive.Extract("tiger.7z");
	}
	
	public static byte[] Extract(String path) {
		
		byte[] data = new byte[0];
		
        RandomAccessFile randomAccessFile = null;
        IInArchive inArchive = null;
        try {
            randomAccessFile = new RandomAccessFile(path, "r");
            inArchive = SevenZip.openInArchive(null, // autodetect archive type
                    new RandomAccessFileInStream(randomAccessFile));

            // Getting simple interface of the archive inArchive
            ISimpleInArchive simpleInArchive = inArchive.getSimpleInterface();

            System.out.println("   Hash   |    Size    | Filename");
            System.out.println("----------+------------+---------");

            for (ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {
                final int[] hash = new int[] { 0 };
                if (!item.isFolder()) {
                    ExtractOperationResult result;

                    final long[] sizeArray = new long[1];
                    
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
                    
                    result = item.extractSlow(new ISequentialOutStream() {
                    	
                        public int write(byte[] data) throws SevenZipException {
                        	
                        	
                            hash[0] ^= Arrays.hashCode(data); // Consume data
                            sizeArray[0] += data.length;
                            
                            try {
								outputStream.write(data);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                            
                            return data.length; // Return amount of consumed data
                        }
                        
                    });
                    
                    data = outputStream.toByteArray();
                    
                    if (result == ExtractOperationResult.OK) {
                        System.out.println(String.format("%9X | %10s | %s", 
                                hash[0], sizeArray[0], item.getPath()));
                     
                        
                       
                    } else {
                        System.err.println("Error extracting item: " + result);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error occurs: " + e);
        } finally {
            if (inArchive != null) {
                try {
                    inArchive.close();
                } catch (SevenZipException e) {
                    System.err.println("Error closing archive: " + e);
                }
            }
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e);
                }
            }
        }
    return data;
	}
}