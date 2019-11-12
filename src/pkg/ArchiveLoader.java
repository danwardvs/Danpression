package pkg;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.SevenZipNativeInitializationException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

public class ArchiveLoader{
    public static void main(String[] args) throws SevenZipException, FileNotFoundException {
        try {
            SevenZip.initSevenZipFromPlatformJAR();
            System.out.println("7-Zip-JBinding library was initialized");
            RandomAccessFile randomAccessFile = new RandomAccessFile("output.dan", "r");

            IInArchive inArchive = SevenZip.openInArchive(null, // Choose format
                                                                // automatically
                    new RandomAccessFileInStream(randomAccessFile));
            System.out.println(inArchive.getNumberOfItems());

            // Getting simple interface of the archive inArchive
            ISimpleInArchive simpleInArchive = inArchive.getSimpleInterface();

            System.out.println("Opening archive to view");
            for (ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {
                final int[] hash = new int[] { 0 };
                if (!item.isFolder()) {
                    ExtractOperationResult result;

                    final long[] sizeArray = new long[1];
                    result = item.extractSlow(new ISequentialOutStream() {
                        public int write(byte[] data) throws SevenZipException {
                            hash[0] ^= Arrays.hashCode(data); // Consume data
                            for (byte b : data) {
                                //System.out.println(b);
                            }
                            Loader.processData(data);
                            
                            sizeArray[0] += data.length;
                            return data.length; // Return amount of consumed
                                                // data
                        }
                    }, "YOUR PASSWORD HERE");

                    if (result == ExtractOperationResult.OK) {
                        //System.out.println(String.format("%9X | %10s | %s", hash[0], sizeArray[0], item.getPath()));
                    } else {
                        //System.err.println("Error extracting item: " + result);
                    }
                }
            }

        } catch (SevenZipNativeInitializationException e) {
            e.printStackTrace();
        }
    }

}