import student.TestCase;

/**
 * Test the functionality of the Region class
 * 
 * @author Cuong Ngo
 * @version 07/20/2020
 *
 */
public class RegionTest extends TestCase {

    /**
     * Sets up common conditions, blank
     */
    public void setUp() {
        // Intentionally left blank
    }


    /**
     * Tests the cityIntersect method on various cities with different bounds on
     * a 1x1 region
     */
    public void testCityIntersectSmall() {
        Region reg = new Region(0, 0, 1, 1);

        City a = new City(-1, 0, "-10");
        City b = new City(0, -1, "0-1");
        City c = new City(-1, -1, "-1-1");
        City d = new City(0, 0, "00");
        City e = new City(2, 1, "21");
        City f = new City(1, 2, "12");
        City g = new City(2, 2, "22");
        City h = new City(1, 1, "11");

        assertFalse(reg.cityIntersect(a));
        assertFalse(reg.cityIntersect(b));
        assertFalse(reg.cityIntersect(c));
        assertTrue(reg.cityIntersect(d));
        assertFalse(reg.cityIntersect(e));
        assertFalse(reg.cityIntersect(f));
        assertFalse(reg.cityIntersect(g));
        assertFalse(reg.cityIntersect(h));
    }


    /**
     * Tests the cityIntersect method on various cities with different bounds on
     * 1024x1024 region
     */
    public void testCityIntersectBig() {
        Region reg = new Region(0, 0, 1024, 1024);

        City a = new City(37000, 80, "-10");
        City b = new City(33303, 99999, "0-1");
        City c = new City(-10, 100, "-1-1");
        City d = new City(10, -1222, "00");
        City e = new City(1024, -1024, "21");
        City f = new City(-10, 10000, "12");

        assertFalse(reg.cityIntersect(a));
        assertFalse(reg.cityIntersect(b));
        assertFalse(reg.cityIntersect(c));
        assertFalse(reg.cityIntersect(d));
        assertFalse(reg.cityIntersect(e));
        assertFalse(reg.cityIntersect(f));

    }

}
