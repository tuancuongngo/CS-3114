import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * A memory manager package for variable length
 * records, and a spatial data structure to support
 * geographical queryies.
 * 
 * @author Cuong Ngo (ngoct)
 * @author Yongjae Lim (yongjae)
 * 
 * @version 08/05/2020
 */
public class HeapSort {

    // Heap to do sorting on
    private static MaxHeap heap;

    // BufferPool for MaxHeap to access
    private static BufferPool pool;

    // RAF to read input file
    private static RandomAccessFile file;


    /**
     * This is the entry point of the application
     * 
     * @param args
     *            Command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // Name of input file
        String fileName = args[0];

        // Random Access File containing unsorted data
        file = new RandomAccessFile(fileName, "rw");

        // File to write Statistics to
        File statFile = new File(args[2]);

        // Initialize BufferPool
        int poolSize = Integer.parseInt(args[1]);
        pool = new BufferPool(poolSize, file, (int)file.length());

        // Initialize Heap
        int numRecord = (int)file.length() / 4;
        heap = new MaxHeap(pool, numRecord, numRecord);

        // Calls HeapSort and tracks sorting time
        long startTime = System.currentTimeMillis();
        sort(heap, numRecord);
        long endTime = System.currentTimeMillis();
        long sortTime = endTime - startTime;

        // Flush pool after sorting is complete
        pool.flush();

        // Call helper method to write statistics to standard output and output
        // file
        writeStats(statFile, fileName, sortTime);

        // Close file
        file.close();
    }


    /**
     * Helper method to write the Statistics of the HeapSort to standard output
     * and to specified statistics file
     * 
     * @param output
     *            the specified output stats file
     * @param inputFileName
     *            the name of the input file to sort
     * @param sortTime
     *            the time it took to sort the file
     */
    public static void writeStats(
        File output,
        String inputFileName,
        long sortTime)
        throws IOException {

        int numRecord = (int)file.length() / 4; // Each record is 4 byte long
        for (int i = 0; i < numRecord / BufferPool.recPerBuffer; i++) {
            if (i > 0 && i % 8 == 0) {
                System.out.println();
            }

            int index = i * BufferPool.recPerBuffer * 4;

            // Get to position of current record key in the file
            file.seek(index);
            int key = file.readUnsignedShort();

            // Get to position of current record value in the file
            file.seek(index + 2);
            int value = file.readUnsignedShort();

            // Prints to Standard output
            Record headRec = new Record(key, value);
            System.out.print(headRec.getKey() + " " + headRec.getValue()
                + "    ");
        }

        // Clears the content of output file (so it doesn't append text to the
        // same stat file every time the same test case for sorting is called
        FileWriter writer = new FileWriter(output, false);
        writer.close();

        // Now write the new Stats to the cleared empty file
        writer = new FileWriter(output, true);

        writer.write("File name: " + inputFileName);
        writer.write("\nCache Hits: " + pool.getCacheHit());
        writer.write("\nCache Misses: " + pool.getCacheMiss());
        writer.write("\nDisk Reads: " + pool.getDiskRead());
        writer.write("\nDisk Writes: " + pool.getDiskWrite());
        writer.write("\nTime to Sort: " + sortTime + "\n");
        writer.close();
    }


    /**
     * Method that sorts the specified array using HeapSort
     * 
     * @param maxed
     *            the maxHeap to call HeapSort on
     * @param heapSize
     *            the size of the heap
     * @throws IOException
     */
    public static void sort(MaxHeap maxed, int heapSize) throws IOException {
        for (int i = 0; i < heapSize; i++) {
            maxed.removemax(); // Removemax places max at end of heap
        }
    }

}
