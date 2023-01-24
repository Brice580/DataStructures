import big.data.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Island network class creates an island network which hold a graph of the cities in Longer Island while mapping the string names
 * to the City nodes. This class has a load from file method which load the data values for map and a dfs function which does a depth
 * first search on it. It also has a max flow function to find the longest path from one city to another.
 *
 * @author Brice Joseph 114588946 R03
 */
public class IslandNetwork {

    public static HashMap<String, City> graph = new HashMap<String, City>();
    public static int capacity = 0;

    /**
     * Default constructor which initializes the hashmap of Strings mapped to City nodes
     */
    public IslandNetwork(){
        graph = new HashMap<String, City>();

    }

    /**
     * Load from file method takes in a URL of a xml file and translates it into the a hashmap of cities
     * @param url
     * URL which data will be extracted from
     * @return
     * Returns the island network that was created
     */
    public static IslandNetwork loadFromFile(String url){
        IslandNetwork temp = new IslandNetwork();

        HashMap<String,City> cities = new HashMap<String, City>();

        DataSource ds = DataSource.connectXML(url);
        ds.load();
        String cityNamesStr = ds.fetchString("cities");
        String[] cityNames = cityNamesStr.substring(1,cityNamesStr.length()-1).replace("\"","").split(",");
        String roadNamesStr = ds.fetchString("roads");
        String[] roadNames = roadNamesStr.substring(1,roadNamesStr.length()-1).split("\",\"");

        Arrays.sort(cityNames);
        System.out.println("-------------------");
        for (String cityName : cityNames) {
            System.out.println(cityName);
        }

        for (String cityName : cityNames) {
            cities.put(cityName, new City(cityName));
        }

        System.out.println();

        System.out.printf("%-40s%-15s\n","Roads","Capacity");
        for(int i = 0; i < roadNames.length; i++) {
            String[] split = roadNames[i].replaceAll("\"", "").split(",");

            System.out.printf("%-40s%-15s\n",split[0] + " to " + split[1], split[2]);
        }

       for(String keyValue: cities.keySet()){
           for(int i = 0; i < roadNames.length; i++){
               String[] split = roadNames[i].replaceAll("\"","").split(",");
           if(keyValue.equals(split[0])){
               cities.get(keyValue).setNeighbor(split[1], Integer.parseInt(split[2]));

           }
           }

       }
        temp.setGraph(cities);

        return temp;

    }

    /**
     * Max flow method that returns the max flow of the graph to the user
     * @param from
     * From is the starting node
     * @param to
     * To is the destination node (sink)
     */
    public static void maxFlow(String from, String to){

        //this method has a helper method called findPaths which is written below

        ArrayList<String> maxFlowPaths = new ArrayList<>(); //arraylist to store the paths obtained  by dfs
        maxFlowPaths = findPath(from, to);
        if(!maxFlowPaths.isEmpty()){

        }


        if(maxFlowPaths.size() >= 0 && capacity >= 0){
            int tempCapacity = 0;
            tempCapacity -= capacity; //decrement the capacity

        }
        //int tempCapacity = graph.get(from).getNeighbors().get(to);


        //some recursive call, not sure how to implement


        System.out.println(capacity);
        findPath(from, to);

     }

    /**
     * This method finds a path between two locations
     * @param source
     * Source location
     * @param destination
     * destination location
     * @return
     * returns the path between the places
     */
     public static ArrayList<String> findPath(String source, String destination){

        //for this method I tried my best to follow the pseudo code in the sample page

         ArrayList<String> paths = new ArrayList<String>(); //the paths storage

         if(graph.get(source).getNeighbors().containsKey(destination))
             capacity = graph.get(source).getNeighbors().get(destination);     //if the destination is the neighbor

         paths = dfs(source, destination); //dfs to find the paths

         if(paths.isEmpty()){
             System.out.println("No paths");  //checks if there is no paths
         }

         else{
             int min = 100; //temp value
             for (int i = 1; i < paths.size(); i++) {
                 if (graph.get(source).getNeighbors().get(paths.get(i)) < min) {
                     min = graph.get(source).getNeighbors().get(paths.get(i)); //find the min value of a path
                 }
             }
             capacity = min + capacity; //calculates the capacity

         }



         return paths;
     }


    /**
     * Method to reset the discovered and visited functions of the cities which is useful for dfs
     * Post condition:
     * Boolean values are reset
     */
    public static void resetDiscoveredVisited(){

        for(String name: graph.keySet()){
            graph.get(name).setDiscovered(false);
            graph.get(name).setVisited(false);
        }
    }

    /**
     * DFS method that runs a depth first search on the graph
     * @param from
     * From is the city node which the dfs will start at
     * @param to
     * To is the city which dfs ends
     * @return
     * Returns the dfs at given node
     */
    public static ArrayList<String> dfs(String from, String to){

        graph.get(from).setVisited(true);
        ArrayList<String> string = new ArrayList<String>();
        String[] keys = graph.get(from).getNeighbors().keySet().toArray(new String[0]);
        Arrays.sort(keys);

        for(int i=0; i<graph.get(from).getNeighbors().size(); i++){
            if(!graph.get(keys[i]).isDiscovered()){
                graph.get(keys[i]).setDiscovered(true);
                string.add(keys[i]);

                dfs(keys[i]);
                if(keys[i].equals(to))
                    break;

            }

        }

        return string;

    }

    /**
     * DFS method that runs a depth first search on the graph
     * @param from
     * From is the city node which the dfs will start at
     * @return
     * Returns the dfs at given node
     */
    public static List<String> dfs(String from){

        graph.get(from).setVisited(true);

        String[] keys = graph.get(from).getNeighbors().keySet().toArray(new String[0]);
        Arrays.sort(keys);

        for(int i=0; i<graph.get(from).getNeighbors().size(); i++){
            if(!graph.get(keys[i]).isDiscovered()){
                graph.get(keys[i]).setDiscovered(true);
                System.out.print(graph.get(keys[i]).getName()+", ");
                dfs(keys[i]);

            }

        }

        return null;

    }

    /**
     * Getter for the graph
     * @return
     * Returns the graph
     */
    public HashMap<String, City> getGraph() {
        return graph;
    }

    /**
     * Setter for the graph
     * @param graph
     * Takes in a hashmap of strings and city nodes and sets it as the graph
     */
    public void setGraph(HashMap<String, City> graph) {
        this.graph = graph;
    }
}
