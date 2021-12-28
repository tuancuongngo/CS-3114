import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * Class that represents a Buffer object that will store 4096 bytes of data/1024
 * Records taken from input file
 * 
 * @author Cuong Ngo (ngoct)
 * @author Yongjae Lim (yongjae)
 * 
 * @version 08/05/2020
 */
public class Buffer {

    // Dirty bit to determine whether data was modified
    private boolean modified;

    // The byte array of size 4096
    private byte[] dataBlock;

    // The start index of the block of data in the file
    private int startIndex;

    // The end index of the block of data in the file
    private int endIndex;


    /**
     * Default Constructor that takes in the start index and the byte[] of data
     * 
     * @param start
     *            the start index of the block of data in the file
     * @param data
     *            the data the Buffer stores
     */
    public Buffer(int start, byte[] data) {
        modified = false;
        dataBlock = data;
        startIndex = start;
        endIndex = startIndex + BufferPool.recPerBuffer;
    }


    /**
     * Method that writes data back to disk if BufferPool was modified
     * 
     * @param outFile
     *            the file to flush() out to
     * @return true if disk write was needed
     * @throws IOException
     */
    public boolean flush(RandomAccessFile outFile) throws IOException {
        if (modified) {
            // write to correct position in the file
            outFile.seek(startIndex * 4);
            outFile.write(dataBlock);
        }

        return modified;
    }


    /**
     * Sets the dirty bit to indicate the Buffer is modified
     */
    public void isModified() {
        modified = true;
    }


    /**
     * Get the Record start index of the Buffer block
     * 
     * @return the file position of the first Record in this Buffer object
     */
    public int getStartIndex() {
        return startIndex;
    }


    /**
     * Get the Record end index of the Buffer block
     * 
     * @return the file position of the final Record in this Buffer object
     */
    public int getEndIndex() {
        return endIndex;
    }


    /**
     * Get the record in the Buffer at the given position
     * 
     * @param pos
     *            the Record position
     * @return the specified Record
     */
    public Record getRecord(int pos) {

        byte[] key = new byte[2];
        byte[] value = new byte[2];

        // ASK ABOUT HARD CODING

        key[0] = dataBlock[pos * 4];
        key[1] = dataBlock[pos * 4 + 1];
        value[0] = dataBlock[pos * 4 + 2];
        value[1] = dataBlock[pos * 4 + 3];

        ByteBuffer kBuffer = ByteBuffer.wrap(key);
        ByteBuffer vBuffer = ByteBuffer.wrap(value);

        return new Record(kBuffer.getShort(), vBuffer.getShort());
    }


    /**
     * Modify the byte[] array at the given position with data from a Record
     * object
     * 
     * @param mod
     *            the Record with data to modify the byte[] array
     * @param pos
     *            the position in terms of Records, this number will be
     *            converted to position in terms of byte
     */
    public void modify(Record mod, int pos) {
        byte[] modData = mod.toByte();

        for (int i = 0; i < modData.length; i++) {
            dataBlock[pos * 4 + i] = modData[i];
        }

        modified = true;
    }

}
