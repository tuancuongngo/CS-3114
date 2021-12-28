/**
 * 
 */

/**
 * Class that represents a rectangular region
 * Mainly used in regionsearch method
 * 
 * @author Tuan Cuong
 * @version 07/28/2020
 */
public class Region {

    private int x; // Upper left x coordinate
    private int y; // Upper left y coordinate
    private int w; // Width of region
    private int h; // Height of region


    /**
     * Constructor that initializes a Rectangular Region
     * 
     * @param xCo
     *            the x upper left coordinate
     * @param yCo
     *            the y upper left coordinate
     * @param width
     *            the width of region
     * @param height
     *            the height of region
     */
    public Region(int xCo, int yCo, int width, int height) {
        x = xCo;
        y = yCo;
        w = width;
        h = height;
    }


    /**
     * Checks whether 2 rectangular regions intersect
     * 
     * @param other
     *            the other rectangular region
     * @return True if they do intersect
     */
    public boolean intersect(Region other) {
        return this.x < other.getX() + other.getW() && this.x + this.w > other
            .getX() && this.y < other.getY() + other.getH() && this.y
                + this.h > other.getY();
    }


    /**
     * Checks if a City lies in the rectangular Region
     * 
     * @param c
     *            the City object
     * @return True if CIty lies in region
     */
    public boolean cityIntersect(City c) {
        return c.getY() < (this.getY() + this.getH()) && this.getX() <= c.getX()
            && this.getY() <= c.getY() && c.getX() < (this.getX() + this
                .getW());
    }


    /**
     * Getter for upper left x coordinate of Region
     * 
     * @return the upper left x coordinate of Region
     */
    public int getX() {
        return x;
    }


    /**
     * Getter for upper left y coordinate of Region
     * 
     * @return the upper left y coordinate of Region
     */
    public int getY() {
        return y;
    }


    /**
     * Getter for Width of Region
     * 
     * @return Width of Region
     */
    public int getW() {
        return w;
    }


    /**
     * Getter for Height of Region
     * 
     * @return Height of Region
     */
    public int getH() {
        return h;
    }
}
