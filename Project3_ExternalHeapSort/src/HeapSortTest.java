import student.TestCase;

/**
 * Test the HeapSort class on different sized input files
 * 
 * @author Cuong Ngo (ngoct)
 * @author Yongjae Lim (yongjae)
 * 
 * @version 08/05/2020
 */
public class HeapSortTest extends TestCase {

    /**
     * Test calling HeapSort on a invalid input file
     */
    public void testMainInvalid() {
        HeapSort dum = new HeapSort();
        assertNotNull(dum);
        String[] input = { "invalid", "file", "name" };

        // Passing in invalid argument to main
        Exception thrown = null;
        try {
            HeapSort.main(input);
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof Exception);
        assertEquals("", systemOut().getHistory()); // check that nothing was
                                                    // printed out
    }


    /**
     * Test sorting the given sample input file
     * 
     * @throws Exception
     */
    public void testMainGivenInput() throws Exception {

        String[] input = { "p3_input_sample.dat", "2",
            "sorted_sample_stats.txt" };

        HeapSort.main(input);
        assertEquals("p3_input_sample.dat", input[0]);
    }


    /**
     * Test main method invocation on a generated valid input file
     * that contains 100 blocks and 20 buffers
     * 
     * @throws Exception
     *             if file is invalid
     */
    public void testMainCustomInput() throws Exception {

        ByteFileGenerator gen = new ByteFileGenerator();
        gen.generate(100 * BufferPool.recPerBuffer);

        // String[] input = { "p3_input_sample.dat", "file", "name" };
        String[] input = { "generated.dat", "20", "smallFileStat.txt" };
        // String[] input = { "small.dat", "file", "name" };
        HeapSort.main(input);
        assertEquals("generated.dat", input[0]);
    }


    /**
     * Sorts 7 blocks with a 7 sized buffer pool.
     * 
     * @throws Exception
     */
    public void testMainExactFit() throws Exception {

        ByteFileGenerator gen = new ByteFileGenerator();
        gen.generate(7 * 1024);

        // String[] input = { "p3_input_sample.dat", "file", "name" };
        String[] input = { "generated.dat", "7", "exactFitStat.txt" };
        // String[] input = { "small.dat", "file", "name" };
        HeapSort.main(input);
        assertEquals("generated.dat", input[0]);
    }

}
