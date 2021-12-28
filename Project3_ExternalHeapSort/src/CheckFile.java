import java.io.*;

/**
 * CheckFile: Check to see if a file is sorted. This assumes that each record is
 * a pair of short ints with the first short being the key value
 *
 * @author CS3114 Instructor and TAs
 * @version 03/15/2016
 */

public class CheckFile {
    /**
     * This is an empty constructor for a CheckFile object.
     */
    public CheckFile() {
        // empty constructor
    }


    /**
     * This method checks a file to see if it is properly sorted.
     *
     * @param filename
     *            a string containing the name of the file to check
     * @return true if the file is sorted, false otherwise
     * @throws Exception
     *             either and IOException or a FileNotFoundException
     */
    public boolean checkFile(String filename) throws Exception {
        boolean isError = false;
        DataInputStream in = new DataInputStream(new BufferedInputStream(
            new FileInputStream(filename)));
        // Prime with the first record
        short key2 = in.readShort();

        //System.out.println(key2);

        in.readShort();
        int reccnt = 0;
        try {
            while (true) {
                short key1 = key2;
                reccnt++;
                key2 = in.readShort();
                in.readShort();
                if (key1 > key2) {
                    isError = true;
                }
            }
        }
        catch (EOFException e) {
            System.out.println(reccnt + " records processed");
        }
        in.close();
        return !isError;
    }
}
