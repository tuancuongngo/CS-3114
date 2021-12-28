/**
 * 
 */

/**
 * Class that represents a City.
 * Stores a name of the City and its x and y coordinates
 * 
 * @author Cuong Ngo
 * @version 07/28/2020
 */
public class City {
    private String name; // Name of the city
    private int x; // x coordinate of the city
    private int y; // y coordinate of the city


    /**
     * Default Constructor that sets a Name, x, and y coordinates for a City
     * 
     * @param xCo
     *            The x coordinate of the city
     * 
     * @param yCo
     *            The y coordinate of the city
     * 
     * @param name
     *            The name of the city
     */

    public City(int xCo, int yCo, String name) {
        x = xCo;
        y = yCo;
        this.name = name;
    }


    /**
     * Getter for city name
     * 
     * @return name of the city
     */
    public String getCity() {
        return name;
    }


    /**
     * Getter for x coordinate
     * 
     * @return the x coordinate of the city
     */
    public int getX() {
        return x;
    }


    /**
     * Getter for y coordinate
     * 
     * @return the y coordinate of the city
     */
    public int getY() {
        return y;
    }
}
