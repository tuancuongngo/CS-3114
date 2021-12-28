import java.nio.ByteBuffer;

/**
 * Class that represents a Record object containing a Key and Value. This class
 * implements Comparable to compare 2 Records by their keys
 * 
 * @author Cuong Ngo (ngoct)
 * @author Yongjae Lim (yongjae)
 * 
 * @version 08/05/2020
 */
public class Record implements Comparable<Record> {

    private int key; // The key of the Record
    private int value; // The value of the Record


    /**
     * Default constructor that initializes a Record
     * 
     * @param key
     *            the key of the Record
     * @param value
     *            the value of the Record
     */
    public Record(int key, int value) {
        this.key = key;
        this.value = value;
    }


    /**
     * This method determines which Record, out of 2 Records, is bigger by
     * comparing their keys
     * This method is used by HeapSort
     * 
     * @param other
     *            the other Record to compare with this Record
     * @return an integer.
     *         if this < other return negative int
     *         if this > other return positive int
     *         if this == other return 0
     */
    @Override
    public int compareTo(Record other) {
        return this.key - other.key;
    }


    /**
     * Getter for key of Record
     * 
     * @return the key of Record
     */
    public int getKey() {
        return key;
    }


    /**
     * Getter for value of Record
     * 
     * @return the value of Record
     */
    public int getValue() {
        return value;
    }


    /**
     * Setter for new key
     * 
     * @param newK
     *            the Record's new key
     */
    public void setKey(int newK) {
        key = newK;
    }


    /**
     * Setter for new value
     * 
     * @param newV
     *            the Record's new value
     */
    public void setValue(int newV) {
        value = newV;
    }


    /**
     * Get the key and value of the Record as a byte of array
     * 
     * @return the byte array containing key and value of Record
     */
    public byte[] toByte() {
        // Creating a ByteBuffer to convert the key and values to a byte array
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);

        buffer.putShort((short)key).array();
        byte[] recordA = buffer.putShort(2, (short)value).array();

        return recordA;
    }


    /**
     * Method that returns a Deep Copy of this Record
     * 
     * @return a Deep Copy of this Record
     */
    public Record getDeepCopy() {

        // Get the byte rep of this Record
        byte[] tempData = this.toByte();

        // Extract them into byte[] arrays for keys and values
        byte[] keyB = new byte[2];
        byte[] valueB = new byte[2];

        keyB[0] = tempData[0];
        keyB[1] = tempData[1];
        valueB[0] = tempData[2];
        valueB[1] = tempData[3];

        // Create ByteBuffers to convert byte arrays into keys and values to
        // create a new Record
        ByteBuffer kBuffer = ByteBuffer.wrap(keyB);
        ByteBuffer vBuffer = ByteBuffer.wrap(valueB);

        return new Record(kBuffer.getShort(), vBuffer.getShort());
    }

}
