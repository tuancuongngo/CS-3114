import java.io.IOException;
import java.io.RandomAccessFile;
import student.TestCase;

/**
 * Tests the functionality of a MaxHeap
 * 
 * @author Cuong Ngo (ngoct)
 * @author Yongjae Lim (yongjae)
 * 
 * @version 08/05/2020
 *
 */
public class MaxHeapTest extends TestCase {

    private MaxHeap max;


    /**
     * Sets up a MaxHeap of size 5
     * Each node in MaxHeap contains a Record with key value as below
     * 
     *      ( )
     *      / \
     *    ( ) ( )
     *    / \
     *  ( ) ( )
     */
    public void setUp() throws IOException {
        ByteFileGenerator gen = new ByteFileGenerator();
        gen.generate(5);
        RandomAccessFile file = new RandomAccessFile("generated.dat", "rw");

        BufferPool.recPerBuffer = 1;
        BufferPool pool = new BufferPool(3, file, (int)file.length());
        max = new MaxHeap(pool, 5, 5);
    }


    /**
     * Test the heapsize() method
     */
    public void testInitialHeapSize() {
        assertEquals(5, max.heapsize());
    }


    /**
     * Test removeMax() method until MaxHeap is empty
     * 
     * @throws IOException
     */
    public void testRemoveMax() throws IOException {
        max.removemax();
        assertEquals(4, max.heapsize());

        max.removemax();
        assertEquals(3, max.heapsize());

        max.removemax();
        assertEquals(2, max.heapsize());

        max.removemax();
        assertEquals(1, max.heapsize());

        max.removemax();
        assertEquals(0, max.heapsize());

        // Heap is now empty
        assertNull(max.removemax());
    }


    /**
     * Test the leftChild() method on a invalid position
     */
    public void testLeftChild() {
        assertEquals(-1, max.leftchild(100));
    }


    /**
     * Test the rightChild() method on a valid and invalid position
     */
    public void testRightChild() {
        assertEquals(2, max.rightchild(0));
        assertEquals(-1, max.rightchild(100));
    }


    /**
     * Test inserting into a empty heap
     * 
     * @throws IOException
     */
    public void testInsertEmpty() throws IOException {
        max.removemax();
        max.removemax();
        max.removemax();
        max.removemax();
        max.removemax();

        max.insert(new Record(100, 0));
        assertEquals(1, max.heapsize());

        max.insert(new Record(1000, 0));
        assertEquals(2, max.heapsize());

        max.modify(1, new Record(1001, 0));
    }


    /**
     * Test inserting into a full heap
     * 
     * @throws IOException
     */
    public void testInsertFull() throws IOException {
        max.insert(new Record(100, 0));
        String actual = systemOut().getHistory();
        String expected = "Heap is full\n";

        assertEquals(expected, actual);
    }


    /**
     * Test inserting a Record with the biggest key that gets to become root
     * 
     * @throws IOException
     */
    public void testInsertBig() throws IOException {
        Record newRoot = new Record(100000, 0);

        max.removemax();
        max.insert(newRoot);

        assertEquals(5, max.heapsize());
    }


    /**
     * Test inserting a Record with the smallest key that stays as LeafNode
     * 
     * @throws IOException
     */
    public void testInsertSmall() throws IOException {
        max.removemax();

        // Insert Record with key = 0 so it doesn't need to sift up
        // Record is stored at array position 4
        Record leaf = new Record(0, 0);
        max.insert(leaf);

        assertTrue(max.isLeaf(4));
    }


    /**
     * Test which array position is leaf
     * 
     * The positions which is a leaf are 2, 3, and 4
     */
    public void testIsLeaf() {

        // Is leaf
        assertTrue(max.isLeaf(2));
        assertTrue(max.isLeaf(3));
        assertTrue(max.isLeaf(4));

        // Non leaves
        assertFalse(max.isLeaf(0));
        assertFalse(max.isLeaf(1));

        // Non existent positions
        assertFalse(max.isLeaf(-1));
        assertFalse(max.isLeaf(100));
    }


    /**
     * Test the parent() method of invalid position
     */
    public void testParent() {
        assertEquals(-1, max.parent(-1));
    }


    /**
     * Test remove() method on invalid position
     * 
     * @throws IOException
     */
    public void testRemoveInvalid() throws IOException {
        assertNull(max.remove(-1));
        assertNull(max.remove(100));
    }


    /**
     * Test remove() on last leaf position in the MaxHeap, so it only decrements
     * the size and doesn't need to do rearranging
     * 
     * @throws IOException
     */
    public void testRemoveLeaf() throws IOException {
        max.remove(4);
        assertEquals(4, max.heapsize());
    }


    /**
     * Test siftdown() on invalid positions
     * 
     * @throws IOException
     */
    public void testInvalidSiftDown() throws IOException {
        max.siftdown(1000);
        max.siftdown(-111);

        // MaxHeap unmodified
        assertEquals(5, max.heapsize());
    }


    /**
     * Test remove() on InternalNode containing at position 1, causing
     * rearrangement
     * 
     * @throws IOException
     */
    public void testRemoveInternal() throws IOException {
        max.remove(1);
        assertEquals(4, max.heapsize());
    }


    /**
     * Test the modify() method on a leaf, causing a sift up
     * 
     * @throws IOException
     */
    public void testModifySiftUp() throws IOException {

        max.removemax();
        max.removemax();
        max.removemax();
        max.removemax();
        max.removemax();

        max.insert(new Record(100, 0));
        assertEquals(1, max.heapsize());

        max.insert(new Record(1000, 0));
        assertEquals(2, max.heapsize());

        max.modify(1, new Record(1001, 0));

    }


    /**
     * Test modify() method when modifying a Leaf, no rearrangement needed
     * 
     * @throws IOException
     */
    public void testModifyLeaf() throws IOException {
        Record leaf = new Record(0, 10101);

        // Replaces leafNode at position 4 with new leaf Record
        max.modify(4, leaf);

        // Check if leaf is correct
        assertEquals(0, leaf.compareTo(max.remove(4)));
    }


    /**
     * Test modify() on invalid positions
     * 
     * @throws IOException
     */
    public void testModifyInvalid() throws IOException {
        max.modify(-1, new Record(0, 0));
        max.modify(100, new Record(0, 0));

        // MaxHeap still the same
        assertEquals(5, max.heapsize());

    }


    /**
     * Test modify on leaf, causing sift up action
     * 
     * @throws IOException
     */
    public void testModifyLeafUp() throws IOException {
        Record newRoot = new Record(900000, 0);

        max.modify(4, newRoot);

        // The inserted record is now no longer at position 4 because it got
        // sifted up
        assertTrue(max.remove(4).getKey() < newRoot.getKey());
    }


    /**
     * Test modify() on root, causing siftdown() action
     * 
     * @throws IOException
     */
    public void testModifyRoot() throws IOException {

        // Key = 0
        Record small = new Record(0, 0);

        // This will cause new root to sift down
        max.modify(0, small);

        // the inserted record at root is no longer at root
        assertTrue(max.removemax().getKey() > small.getKey());
    }

}
