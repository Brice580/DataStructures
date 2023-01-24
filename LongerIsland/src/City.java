import java.util.HashMap;

/**
 * This class creates a city object which has a hash map that maps String values to integer values where integers, represent
 * the distance between neighbors and the string is the name of the city. It also contains a boolean for discovered and visited
 * as well as a hashamp for temporary neighbors. It includes a compare function as well as multiple getters and setters
 *
 * @author Brice Joseph, 114588946, R03
 *
 * Assignment: HW #7
 */

public class City implements Comparable<City>{

    private HashMap<String,Integer> neighbor;
    private String name;
    public boolean discovered;
    public boolean visited;
    public HashMap<String, Integer> tempNeighbors;

    /**
     * Default constructor for the city object, which also initializes the hashmap of neighbors and the city name
     * Post condition:
     * City object is created and neighbors is initialized
     */
    public City(){
        neighbor = new HashMap<String, Integer>();
        name = null;

    }

    /**
     * A second City constructor that takes in the parameter name
     * @param name
     * This name will be set to the city name
     * Postcondition:
     * City object is created and city name is set
     */
    public City(String name){
        neighbor = new HashMap<String, Integer>();
        this.name = name;
    }
    @Override
    /**
     * Compare method which compares a city object through its name and neighbors
     * @param o
     * City o is the city which will be compared when method is called
     * @return
     * Returns a integer value if true or false
     * Postcondition:
     * Cities are compared
     */
    public int compareTo(City o) {
        if(this.name.equals(o.name) && this.neighbor == o.neighbor)
            return 1;
        else
            return -1;
    }

    /**
     * Getter for the neighbors of the City
     * @return
     * Returns the neighbor
     */
    public HashMap<String, Integer> getNeighbors() {
        return neighbor;
    }

    /**
     * Setter for the neighbors which adds a neighbor into the hashmap
     * @param name
     * Name of the city
     * @param weight
     * Distance to the city
     */
    public void setNeighbor(String name, int weight) {

        neighbor.put(name,weight);
    }

    /**
     * Getter for the name of the city
     * @return
     * Returns the name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the City
     * @param name
     * This will be the set to the name of the city
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the boolean if the city is discovered
     * @return
     * Returns the truth value for discovered value
     */
    public boolean isDiscovered() {
        return discovered;
    }

    /**
     * Setter for the boolean discovered field
     * @param discovered
     * Takes in true or false depending if the city is discovered
     */
    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    /**
     * Checks if the city is visited
     * @return
     * Returns the truth value if its visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Sets the visited value of the city
     * @param visited
     * Takes in the truth value of the visited field
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
