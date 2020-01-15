package pkg;

import java.io.IOException;
import java.io.RandomAccessFile;

import net.sf.sevenzipjbinding.IOutCreateArchive7z;
import net.sf.sevenzipjbinding.IOutCreateCallback;
import net.sf.sevenzipjbinding.IOutItem7z;
import net.sf.sevenzipjbinding.ISequentialInStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.OutItemFactory;
import net.sf.sevenzipjbinding.impl.RandomAccessFileOutStream;
import net.sf.sevenzipjbinding.util.ByteArrayStream;
import pkg.CompressArchiveStructure.Item;

public class CompressArchive {
    /**
     * The callback provides information about archive items.
     */
    private final class MyCreateCallback 
            implements IOutCreateCallback<IOutItem7z> {

        public void setOperationResult(boolean operationResultOk)
                throws SevenZipException {
            // Track each operation result here
        }

        public void setTotal(long total) throws SevenZipException {
            // Track operation progress here
        }

        public void setCompleted(long complete) throws SevenZipException {
            // Track operation progress here
        }

        public IOutItem7z getItemInformation(int index,
                OutItemFactory<IOutItem7z> outItemFactory) {
            IOutItem7z item = outItemFactory.createOutItem();

            if (items[index].getContent() == null) {
                // Directory
                item.setPropertyIsDir(true);
            } else {
                // File
                item.setDataSize((long) items[index].getContent().length);
            }

            item.setPropertyPath(items[index].getPath());

            return item;
        }

        public ISequentialInStream getStream(int i) throws SevenZipException {
            if (items[i].getContent() == null) {
                return null;
            }
            return new ByteArrayStream(items[i].getContent(), true);
        }
    }

    private Item[] items = new Item[1];

    public static void main(String[] args) {
        if (args.length == 1) {
            new CompressArchive().compress(args[0]);
            return;
        }
        System.out.println("Usage: java CompressNonGeneric7z <archive>");
    }


    private void compress(String filename) {
        items[0] = new Item("test.dnu","ncie datea");


        boolean success = false;
        RandomAccessFile raf = null;
        IOutCreateArchive7z outArchive = null;
        try {
        	
            raf = new RandomAccessFile(filename, "rw");

            // Open out-archive object
            outArchive = SevenZip.openOutArchive7z();

            // Configure archive
            outArchive.setLevel(1);
            outArchive.setSolid(true);

            // Create archive
            outArchive.createArchive(new RandomAccessFileOutStream(raf), 1, 
                    new MyCreateCallback());

            success = true;
        } catch (SevenZipException e) {
            System.err.println("7z-Error occurs:");
            // Get more information using extended method
            e.printStackTraceExtended();
        } catch (Exception e) {
            System.err.println("Error occurs: " + e);
        } finally {
            if (outArchive != null) {
                try {
                    outArchive.close();
                } catch (IOException e) {
                    System.err.println("Error closing archive: " + e);
                    success = false;
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e);
                    success = false;
                }
            }
        }
        if (success) {
            System.out.println("Compression operation succeeded");
        }
    }
}