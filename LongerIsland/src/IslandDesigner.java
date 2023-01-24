import java.util.Scanner;

/**
 * This method takes uses the Island Network and lets you run dfs on it or find max flow using the hashmaps created as
 * well as their neighbor hashmaps. Creates a user friendly menu that allow the user to pick what option they want.
 *
 * @author Brice Joseph, 114588946, R03
 */
public class IslandDesigner {

    /**
     * Main method which creates a user interface for the Island network
     * @param args
     * Default parameter
     */
    public static void main(String[] args){

        System.out.println("Welcome to the Island Designer, because, when you're trying to stay above water, Seas get degrees!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an URL: ");
        String URL = scanner.nextLine();

        System.out.println("Map loaded!\n");


        System.out.println("Cities: ");
        IslandNetwork.loadFromFile(URL);

        boolean condition = true;
        System.out.println("Menu:\n" +
                "D) Destinations reachable (Depth First Search)\n" +
                "F) Maximum Flow\n" +
                "S) Shortest Path (Extra Credit)\n" +
                "Q) Quit");
        while(condition){

            System.out.println();
            System.out.println("Please select an option: ");
            String option  = scanner.nextLine().toUpperCase();

            switch(option){
                /**
                 * Dfs method
                 */
                case "D":
                    try {

                        System.out.println("Please enter a starting city: ");
                        String choice = scanner.nextLine();
                        if(IslandNetwork.graph.get(choice) == null)
                            throw new KeyNotFoundException();
                        System.out.println("DFS starting from "+choice+":");
                        IslandNetwork.dfs(choice);
                        IslandNetwork.resetDiscoveredVisited();
                    }catch (KeyNotFoundException ex){
                        System.out.println("Invalid name, please enter exactly as stated!");
                    }


                    break;

                /**
                 * Finding max flow method
                 */
                case "F":

                    try {

                        System.out.println("Please enter a starting city: ");
                        String source = scanner.nextLine();
                        System.out.println("Please enter a destination: ");
                        String destination = scanner.nextLine();
                        if(IslandNetwork.graph.get(source) == null || IslandNetwork.graph.get(destination) == null )
                            throw new KeyNotFoundException();
                        IslandNetwork.maxFlow(source, destination);
                        System.out.println();
                        System.out.println("(I wasn't able to finish this method, but I followed most of the code in the pseudocode)");
                    }catch (KeyNotFoundException ex){
                        System.out.println("Invalid name, please enter exactly ad stated!");
                    }





                    break;
                /**
                 * Quits the program
                  */
                case "Q":
                    System.out.println("Goodbye!");
                    condition = false;

                    break;

            }
        }
    }

}
