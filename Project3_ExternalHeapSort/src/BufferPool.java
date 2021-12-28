import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Class that represents a BufferPool that will be used by MaxHeap to turn the
 * input file into a Heap to be sorted
 * 
 * @author Cuong Ngo (ngoct)
 * @author Yongjae Lim (yongjae)
 * 
 * @version 08/05/2020
 */
public class BufferPool {

    /**
     * Indicate how many Records is stored in a given Buffer
     * This number can be modified for testing purposes to test smaller size of
     * each buffer
     */
    protected static int recPerBuffer = 1024; // Should be 1024 always, or
                                              // CHANGE TO 1 for testing on
                                              // smaller files

    // File used by pool to read in data
    private RandomAccessFile inputFile;

    // Doubly linked list to store Buffers
    private DoublyLinkedList<Buffer> list;

    // Max number of Buffers in the pool at once
    private int maxBufferSize;

    // Current size of pool
    private int currSize;

    // Number of bytes in input file
    private int numBytes;

    // Variables to track statistics
    private int cacheHit;
    private int cacheMiss;
    private int diskRead;
    private int diskWrite;


    /**
     * Default Constructor that initializes a DoublyLinkedList to store each
     * Block of Records and other variables
     * 
     * @param poolSize
     *            the size of the BufferPool
     * @param input
     *            the File to use the BufferPool on
     * @param totalBytes
     *            the total bytes in the file
     */
    public BufferPool(int poolSize, RandomAccessFile input, int totalBytes) {

        // Initializing statistics variables
        cacheHit = 0;
        cacheMiss = 0;
        diskRead = 0;
        diskWrite = 0;

        // Initializing other variables taken from parameters to make a
        // BufferPool
        inputFile = input;
        numBytes = totalBytes;
        maxBufferSize = poolSize;
        list = new DoublyLinkedList<Buffer>();
        currSize = 0;
    }


    /**
     * Gets the Record in the specified position
     * If the record is not in the BufferPool, remove the LRU block and load the
     * new Block that contains specified Record to front of BufferPool
     * 
     * @param pos
     *            the position to find the Record
     * @return the specified Record
     * @throws IOException
     */
    public Record get(int pos) throws IOException {
        return getRec(pos, false);
    }


    /**
     * Insert a modified Record back into the BufferPool at specified position
     * 
     * @param rec
     *            the modified Record
     * @param pos
     *            the Record position in the file
     * @throws IOException
     */
    public void insert(Record rec, int pos) throws IOException {

        // Get the Buffer that contains the Record to be modified, passing in
        // the position, and TRUE because we are modifying a Buffer
        Record toBeMod = getRec(pos, true);
        toBeMod.setKey(rec.getKey());
        toBeMod.setValue(rec.getValue());

        // Buffer is now at the front (position 0), update the buffer with the
        // modified record
        list.getData(0).modify(toBeMod, pos - list.getData(0).getStartIndex());
    }


    /**
     * Private helper method that gets a record at a given position in the File
     * through the BufferPool. This method is used by get() and insert()
     * 
     * @param pos
     *            the Record position
     * @param toModify
     *            whether the Record is being read or being modified
     * @return the specified Record from the file through BufferPool
     * @throws IOException
     */
    private Record getRec(int pos, boolean toModify) throws IOException {

        Buffer currBuff = null;

        // Checks if there is a Buffer with the record in pool by traversing
        // through LinkedList
        for (int i = 0; i < list.getSize(); i++) {

            // Current buffer
            currBuff = list.getData(i);

            // Start and end index of the block of Record in current Buffer
            int startIndex = currBuff.getStartIndex();
            int endIndex = currBuff.getEndIndex();

            // If the specified index belongs to a Record in the block
            // This if will be entered if record is in current buffer
            if (startIndex <= pos && pos < endIndex) {
                // CacheHit
                cacheHit++;

                // If we are getting the Record to modify it
                if (toModify) {
                    currBuff.isModified();
                }

                // Remove the buffer and add it to front of list
                list.remove(i);
                list.add(0, currBuff);

                // Return the Record in the position
                return currBuff.getRecord(pos - startIndex);
            }
        }

        // If not found, fetch from disk, move to front of list, and remove tail
        // (least recent) if pool is full
        if (currSize >= maxBufferSize) {
            deleteTail();
        }

        // Calls helper method to read the block into Pool and add to front of
        // Pool
        currBuff = readDiskBlock(pos);

        // Record is in now buffer
        // If we are getting the Record to modify it
        if (toModify) {
            currBuff.isModified();
        }

        // Add Buffer to front of Pool
        list.add(0, currBuff);
        currSize++;

        // Return the Record in the position
        return currBuff.getRecord(pos - currBuff.getStartIndex());
    }


    /**
     * If a specified Record is not in the BufferPool, this method is called to
     * read the Block of Records from file that contains the specified record
     * into the pool
     * 
     * @param pos
     *            the position of the specified Record
     * @return a Buffer containing the specified Record
     * @throws IOException
     */
    private Buffer readDiskBlock(int pos) throws IOException {

        int startIndex = 0;
        int endIndex = 0;

        Buffer newBuffer = null;
        int numRecord = recPerBuffer; // Should be 1024

        for (int i = 0; i < numBytes / (numRecord * 4); i++) {
            startIndex = i * numRecord;
            endIndex = startIndex + numRecord;

            if (startIndex <= pos && pos < endIndex) {
                byte[] byBlock = new byte[recPerBuffer * 4];

                inputFile.seek(startIndex * 4);
                inputFile.read(byBlock, 0, recPerBuffer * 4);

                // Make a new buffer
                newBuffer = new Buffer(startIndex, byBlock);
            }
        }

        // Every time this method is called, means a cache MISS occurred and
        // disk is accessed
        cacheMiss++;
        diskRead++;
        return newBuffer;
    }


    /**
     * Helper method that deletes the Least Recently Used element
     * 
     * @throws IOException
     */
    public void deleteTail() throws IOException {

        // Deletes tail from linkedList to remove least recently used block
        int lastIndex = list.getSize() - 1;

        // Calls flush() on the LRU item, flush() will check if this Buffer was
        // modified or not and will write data to file if necessary and return
        // boolean indicating whether a disk write was used
        boolean flushed = list.getData(lastIndex).flush(inputFile);

        // If the Buffer was modified then it would need rewriting back to Disk
        if (flushed) {
            diskWrite++;
        }

        // Delete from BufferPool and decrease size
        list.remove(lastIndex);
        currSize--;
    }


    /**
     * Remove all Buffers from Pool, and write back to file if necessary
     * 
     * @throws IOException
     */
    public void flush() throws IOException {

        // Stores the size of the pool
        int poolSize = currSize;

        // Calls deleteTail() repeatedly until pool is empty
        for (int i = 0; i < poolSize; i++) {
            deleteTail();
        }
    }


    /**
     * Getter for number of cache hits in the pool
     * 
     * @return number of cache hits
     */
    public int getCacheHit() {
        return cacheHit;
    }


    /**
     * Getter for number of cache misses in the pool
     * 
     * @return number of cache misses
     */
    public int getCacheMiss() {
        return cacheMiss;
    }


    /**
     * Getter for number of disk reads in the pool
     * 
     * @return number of disk reads
     */
    public int getDiskRead() {
        return diskRead;
    }


    /**
     * Getter for number of disk writes in the pool
     * 
     * @return number of disk writes
     */
    public int getDiskWrite() {
        return diskWrite;
    }

}
