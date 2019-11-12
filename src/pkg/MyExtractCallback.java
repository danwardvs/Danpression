package pkg;

import java.util.Arrays;

import net.sf.sevenzipjbinding.ExtractAskMode;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IArchiveExtractCallback;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.SevenZipException;

public class MyExtractCallback implements IArchiveExtractCallback {
    private int hash = 0;
    private int size = 0;
    private int index;
    private boolean skipExtraction;
    private IInArchive inArchive;

    public MyExtractCallback(IInArchive inArchive) {
        this.inArchive = inArchive;
    }

    public ISequentialOutStream getStream(int index, 
            ExtractAskMode extractAskMode) throws SevenZipException {
        this.index = index;
        skipExtraction = (Boolean) inArchive
                .getProperty(index, PropID.IS_FOLDER);
        if (skipExtraction || extractAskMode != ExtractAskMode.EXTRACT) {
            return null;
        }
        return new ISequentialOutStream() {
            public int write(byte[] data) throws SevenZipException {
                hash ^= Arrays.hashCode(data);
                size += data.length;
                return data.length; // Return amount of proceed data
            }
        };
    }

	@Override
	public void setCompleted(long arg0) throws SevenZipException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTotal(long arg0) throws SevenZipException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepareOperation(ExtractAskMode arg0) throws SevenZipException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOperationResult(ExtractOperationResult arg0) throws SevenZipException {
		// TODO Auto-generated method stub
		
	}
}