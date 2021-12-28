import student.TestCase;

/**
 * Test the functionality of the BinTree class and the functionality of the
 * various BinNodes
 * 
 * @author Cuong Ngo
 * @version 07/28/2020
 *
 */
public class BinTreeTest extends TestCase {
    
    private BinTree bt; // The BinTree used in all test classes

    /**
     * Sets up the BinTree
     */
    public void setUp() {
        bt = new BinTree();
    }


    /**
     * Test when the tree is empty and contains only the empty Node
     */
    public void testEmpty() {
        assertEquals("E", bt.getRoot().getType());
    }


    /**
     * Test inserting 1 element into the tree
     */
    public void testInsertRoot() {
        bt.insert("2", "3", "two");

        LeafNode actual = (LeafNode)bt.getRoot();

        assertEquals("two", actual.getName());
        assertEquals(2, actual.getX());
        assertEquals(3, actual.getY());
    }


    /**
     * Test inserting 2 elements into the tree, which will cause them to split
     * into a left and right child
     */
    public void testInsert2() {
        bt.insert("2", "3", "two");
        bt.insert("800", "8", "eight");

        InternalNode actualRoot = (InternalNode)bt.getRoot();
        assertEquals("I", actualRoot.getType());

        LeafNode rightChild = (LeafNode)actualRoot.getRight();
        assertEquals("eight", rightChild.getName());
        assertEquals(800, rightChild.getX());
        assertEquals(8, rightChild.getY());

        LeafNode leftChild = (LeafNode)actualRoot.getLeft();
        assertEquals("two", leftChild.getName());
        assertEquals(2, leftChild.getX());
        assertEquals(3, leftChild.getY());
    }

    /**
     * Test inserting 3 elements into the tree
     * Tree should look like this:
     *    O
     *  /   \
     * two   O
     *      / \
     *  eight  thousand
     */
    public void testInsert3() {
        bt.insert("2", "3", "two");
        bt.insert("800", "8", "eight");

        bt.insert("900", "1000", "thousand");
        
        InternalNode actualRoot = (InternalNode)bt.getRoot();
        InternalNode rightInternal = (InternalNode)actualRoot.getRight();
        assertEquals("I", actualRoot.getType());
        assertEquals("I", rightInternal.getType());

        LeafNode rightChild = (LeafNode)rightInternal.getRight();
        assertEquals("thousand", rightChild.getName());
        assertEquals(900, rightChild.getX());
        assertEquals(1000, rightChild.getY());

        LeafNode leftChild = (LeafNode)rightInternal.getLeft();
        assertEquals("eight", leftChild.getName());
        assertEquals(800, leftChild.getX());
        assertEquals(8, leftChild.getY());
    }


    /**
     * Test inserting 4 elements into the tree, making it with height 3
     */
    public void testInsert4() {
        bt.insert("2", "3", "two");
        bt.insert("800", "8", "eight");
        bt.insert("900", "1000", "thousand");
        bt.insert("100", "900", "nine");

        InternalNode actualRoot = (InternalNode)bt.getRoot();
        assertEquals("I", actualRoot.getType());

        // Testing the right branch
        InternalNode rightInternal = (InternalNode)actualRoot.getRight();
        assertEquals("I", rightInternal.getType());

        LeafNode rightChild = (LeafNode)rightInternal.getRight();
        assertEquals("thousand", rightChild.getName());
        assertEquals(900, rightChild.getX());
        assertEquals(1000, rightChild.getY());

        LeafNode leftChild = (LeafNode)rightInternal.getLeft();
        assertEquals("eight", leftChild.getName());
        assertEquals(800, leftChild.getX());
        assertEquals(8, leftChild.getY());

        // Testing the left branch
        InternalNode leftInternal = (InternalNode)actualRoot.getLeft();
        assertEquals("I", leftInternal.getType());

        leftChild = (LeafNode)leftInternal.getLeft();
        assertEquals("two", leftChild.getName());
        assertEquals(2, leftChild.getX());
        assertEquals(3, leftChild.getY());

        rightChild = (LeafNode)leftInternal.getRight();
        assertEquals("nine", rightChild.getName());
        assertEquals(100, rightChild.getX());
        assertEquals(900, rightChild.getY());
    }


    /**
     * Test inserting on boundary x=256
     */
    public void testInsertSplit() {
        bt.insert("2", "3", "two");
        bt.insert("256", "2", "three");

        InternalNode actualRoot = (InternalNode)bt.getRoot();
        assertEquals("I", actualRoot.getType());

        // Testing the left branch
        InternalNode leftInternal = (InternalNode)actualRoot.getLeft();
        assertEquals("I", leftInternal.getType());

        // Testing the left branch
        InternalNode llInternal = (InternalNode)leftInternal.getLeft();
        assertEquals("I", llInternal.getType());

        LeafNode leftChild = (LeafNode)llInternal.getLeft();
        assertEquals("two", leftChild.getName());
        assertEquals(2, leftChild.getX());
        assertEquals(3, leftChild.getY());

        LeafNode rightChild = (LeafNode)llInternal.getRight();
        assertEquals("three", rightChild.getName());
        assertEquals(256, rightChild.getX());
        assertEquals(2, rightChild.getY());
    }


    /**
     * Test the find() method on a Empty BinTree
     */
    public void testFindEmpty() {
        assertEquals("Record could not be printed. It does not exist.", bt.find(
            "0", "0"));
    }


    /**
     * Test the find() method on a BinTree with 1 Node
     */
    public void testFindRoot() {
        bt.insert("2", "3", "two");
        assertEquals("two 2 3", bt.find("2", "3"));
        assertEquals("Record could not be printed. It does not exist.", bt.find(
            "2", "1"));
        assertEquals("Record could not be printed. It does not exist.", bt.find(
            "1", "1"));
    }


    /**
     * Test the find method when the tree had to split by x then y levels
     */
    public void testFindSplit() {
        bt.insert("2", "3", "bro");
        bt.insert("256", "2", "three");

        assertEquals("bro 2 3", bt.find("2", "3"));
        assertEquals("three 256 2", bt.find("256", "2"));
    }


    /**
     * Test the find method, trying to find all 4 elements in the tree to make
     * sure they exist
     */
    public void testFind4() {
        bt.insert("511", "0", "left");
        bt.insert("512", "0", "right");
        bt.insert("500", "400", "fifo");
        bt.insert("10", "10", "ten");

        assertEquals("left 511 0", bt.find("511", "0"));
        assertEquals("right 512 0", bt.find("512", "0"));
        assertEquals("fifo 500 400", bt.find("500", "400"));
        assertEquals("ten 10 10", bt.find("10", "10"));
    }


    /**
     * Test the insert() method on cases where inserting 2 elements. One on the
     * left of x boundary line and the other on the right of x boundary line
     */
    public void testInsertXEdge() {
        bt.insert("511", "0", "left");
        bt.insert("512", "0", "right");

        InternalNode actualRoot = (InternalNode)bt.getRoot();
        assertEquals("I", actualRoot.getType());

        LeafNode rightChild = (LeafNode)actualRoot.getRight();
        assertEquals("right", rightChild.getName());
        assertEquals(512, rightChild.getX());
        assertEquals(0, rightChild.getY());

        LeafNode leftChild = (LeafNode)actualRoot.getLeft();
        assertEquals("left", leftChild.getName());
        assertEquals(511, leftChild.getX());
        assertEquals(0, leftChild.getY());

        assertEquals("left 511 0", bt.find("511", "0"));
        assertEquals("right 512 0", bt.find("512", "0"));
    }


    /**
     * Test the find() method on cases where inserting 2 elements. One on the
     * upper of y boundary line and the other on the lower of y boundary line
     */
    public void testFindYEdge() {
        bt.insert("0", "511", "left");
        bt.insert("0", "512", "right");

        assertEquals("left 0 511", bt.find("0", "511"));
        assertEquals("right 0 512", bt.find("0", "512"));
    }


    /**
     * Test print() method on a Empty BinTree
     */
    public void testPrintEmpty() {
        // Empty tree
        bt.print();
        String output = systemOut().getHistory();
        assertEquals("E 0 0 1024 1024\n", output);
    }

    /**
     * Test the print() method on BinTree with 1 element
     */
    public void testPrintRoot() {
        bt.insert("1", "2", "yessir");
        bt.print();
        String output = systemOut().getHistory();
        assertEquals("yessir 1 2\n", output);
    }


    /**
     * Test the print() method on complicated insertion of 2 Cities right next
     * to each other with x and y difference of 1. Causing a lot of splits
     */
    public void testPrintBreakCode() {

        // Inserting 2 elements very close to each other, causing code to split
        // numerous times
        bt.insert("1", "2", "yessir");
        bt.insert("2", "3", "n");

        bt.print();
        String output = systemOut().getHistory();
        String expected = "I 0 0 1024 1024\n" + 
            "  I 0 0 512 1024\n" + 
            "    I 0 0 512 512\n" + 
            "      I 0 0 256 512\n" + 
            "        I 0 0 256 256\n" + 
            "          I 0 0 128 256\n" + 
            "            I 0 0 128 128\n" + 
            "              I 0 0 64 128\n" + 
            "                I 0 0 64 64\n" + 
            "                  I 0 0 32 64\n" + 
            "                    I 0 0 32 32\n" + 
            "                      I 0 0 16 32\n" + 
            "                        I 0 0 16 16\n" + 
            "                          I 0 0 8 16\n" + 
            "                            I 0 0 8 8\n" + 
            "                              I 0 0 4 8\n" + 
            "                                I 0 0 4 4\n" + 
            "                                  yessir 1 2\n" + 
            "                                  n 2 3\n" + 
            "                                E 0 4 4 4\n" + 
            "                              E 4 0 4 8\n" + 
            "                            E 0 8 8 8\n" + 
            "                          E 8 0 8 16\n" + 
            "                        E 0 16 16 16\n" + 
            "                      E 16 0 16 32\n" + 
            "                    E 0 32 32 32\n" + 
            "                  E 32 0 32 64\n" + 
            "                E 0 64 64 64\n" + 
            "              E 64 0 64 128\n" + 
            "            E 0 128 128 128\n" + 
            "          E 128 0 128 256\n" + 
            "        E 0 256 256 256\n" + 
            "      E 256 0 256 512\n" + 
            "    E 0 512 512 512\n" + 
            "  E 512 0 512 1024\n";
        assertEquals(expected, output);
    }
    

    /**
     * Test insert() that causes Multiple split in the left subtree in a small
     * world
     */
    public void testSmallWorld() {       
        // Make a 128x128 world
        bt.setH(128);
        bt.setW(128);
        
      
        bt.insert("37", "80", "Bburg");
        bt.insert("41", "96", "Omaha");
        
        String expected = "I 0 0 128 128\n" + 
                            "  I 0 0 64 128\n" + 
                            "    E 0 0 64 64\n" + 
                            "    I 0 64 64 64\n" + 
                            "      E 0 64 32 64\n" + 
                            "      I 32 64 32 64\n" + 
                            "        Bburg 37 80\n" + 
                            "        Omaha 41 96\n" + 
                            "  E 64 0 64 128\n";
       
        bt.print();
        String actual = systemOut().getHistory();
        
        assertEquals(expected, actual);
    }   
    
    
    
    /**
     * Test remove() method after inserting 2 elements and removing one of them
     */
    public void testRemoveXEdge1() {
        bt.insert("511", "0", "left");
        bt.insert("512", "0", "right");
                
        bt.remove("511", "0");
        
        // Check content of tree after removing left node
        bt.print();
        String actualString = systemOut().getHistory();
        String expected = "right 512 0\n";
        assertEquals(expected, actualString);
    }
    
    /**
     * Test remove() method after inserting 2 elements and removing both of them
     */
    public void testRemoveXEdge2() {
        bt.insert("511", "0", "left");
        bt.insert("512", "0", "right");

        bt.remove("511", "0");
        bt.remove("512", "0");

        bt.print();
        String actualString = systemOut().getHistory();
        String expected = "E 0 0 1024 1024\n";
        assertEquals(expected, actualString);

    }
    
    
    /**
     * Test removing from empty tree
     */
    public void testRemoveEmpty() {
        String actualString = bt.remove("750", "750");
        String expected = "Record could not be removed. It does not exist.";
        assertEquals(expected, actualString);
    }

    
    /**
     * Test the regionsearch method when tree has 1 valid element
     */
    public void testSimpleSearchRoot() {
        bt.insert("0", "0", "zero");
        bt.regionsearch("0", "0", "1024", "1024");

        // Found city so print out its info and num nodes visited
        String actual = systemOut().getHistory();
        String expected = "zero 0 0\n1 nodes visited\n";

        assertEquals(expected, actual);
    }


    /**
     * Test the regionsearch method when tree has 1 valid element but not in the
     * search region
     */
    public void testSimpleSearchNotFound() {
        bt.insert("511", "0", "zero");
        bt.regionsearch("512", "0", "1024", "1024");

        // Not in region so only the number of Nodes visited were printed
        String actual = systemOut().getHistory();
        String expected = "1 nodes visited\n";

        assertEquals(expected, actual);
    }
    

    /**
     * Test regionSearching an the entire world
     */
    public void testSearchEntireWorld() {
        bt.insert("2", "3", "two");
        bt.insert("800", "8", "eight");
        
        bt.regionsearch("0", "0", "1024", "1024");
        String actual = systemOut().getHistory();
        String expected = "two 2 3\n" + 
            "eight 800 8\n" + 
            "3 nodes visited\n";
        
        assertEquals(expected, actual);
    }
    
    /**
     * Inserting 2 cities and searches an empty region
     */
    public void testSearchEmpty() {
        bt.insert("2", "23", "two");
        bt.insert("513", "23", "513");
        
        bt.regionsearch("900", "0", "100", "10");
        
        String actual = systemOut().getHistory();
        String expected = "2 nodes visited\n";
        
        assertEquals(expected, actual);
    }
    
    /**
     * Test searching a small 2x2 world
     */
    public void testSmallRegion() {
        bt.setH(2);
        bt.setW(2);
        
        bt.insert("0", "0", "zero");
        bt.insert("0", "1", "zero one");
        bt.insert("1", "0", "one zero");
        bt.insert("1", "1", "one one");
        
        bt.regionsearch("0", "0", "1", "1");
        
        String expected = "zero 0 0\n3 nodes visited\n";
        String actual = systemOut().getHistory();
        
        assertEquals(expected, actual);
    }
 
}
