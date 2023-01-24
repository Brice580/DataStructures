import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.io.File;

/**
 * This class is the driver class for the Storm Server. It creates a Hashmap of storms with String key values and can be
 * manipulated by the user using a menu. The table can be organized in order of windspeeds or precipitation levels. Also,
 * the hashmap can be stored at the termination of the program and be accessed again upon restart. The menu allows the user
 * to add, remove, print, terminate with or without saving, or edit the table.
 *
 * @author Brice Joseph, 114588946, R03
 *
 * Assignment: HW #6
 */
public class StormStatServer implements Serializable {

    private static HashMap<String,Storm> database;

    /**
     * Main method which creates a user-friendly, menu-driven, application for the Storm server
     * @param args
     * Default constructor for main method
     * @throws KeyNotFoundException
     * Throws this exception if the key value in the hash table is not found
     */
    public static void main(String[] args) throws KeyNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the StormStatServer, we may not be able to make it rain, but we sure can tell you when it happened!");
        System.out.println();

        File file = new File("hurricane.ser");
        if(file.exists()){
            try {
               database = Storm.readToFile();
               System.out.println("hurricane.ser was found and loaded!");

            } catch (IOException e) {
                System.out.println("File not found");
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found!");
            }
        }
        else {

            database = new HashMap<String, Storm>();

        }

        System.out.println("Menu:\n" +
                "A) Add A Storm\n" +
                "L) Look Up A Storm\n" +
                "D) Delete A Storm\n" +
                "E) Edit Storm Data\n" +
                "R) Print Storms Sorted By Rainfall\n" +
                "W) W-Print Storms by Windspeed\n" +
                "X) Save and quit\n" +
                "Q) Quit and delete saved data");

        boolean condition = true;
        while (condition) {

            System.out.println("Please select an option: ");
            String option = scan.nextLine().toUpperCase();

            switch(option){
                /**
                 * Case A: Allows the user to add a new storm to the database
                 * Postcondition: Storm is added
                 */
                case "A":
                    try{
                    System.out.println("Please enter a name: ");
                    String name = scan.nextLine().toUpperCase();

                    for (String key : database.keySet()){
                        if(name.equals(key))
                            throw new InvalidNameException();
                    }

                    System.out.println("Enter a date:");
                    String text = scan.nextLine();

                    if(!text.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")&&!text.matches("^[0-9]{4}-[0-9]{1}-[0-9]{2}$")
                            &&!text.matches("^[0-9]{4}-[0-9]{1}-[0-9]{1}$")&&!text.matches("^[0-9]{4}-[0-9]{2}-[0-9]{1}$")){
                            throw new InvalidSyntaxException();
                    }
                    if(text.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$") && Integer.parseInt(text.substring(5,7)) > 12){
                        throw new InvalidSyntaxException();
                    }
                    if(text.matches("^[0-9]{4}-[0-9]{2}-[0-9]{1}$") && Integer.parseInt(text.substring(5,7)) > 12){
                            throw new InvalidSyntaxException();
                    }
                    if(text.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$") && Integer.parseInt(text.substring(8,10)) > 31){
                        throw new InvalidSyntaxException();
                    }
                    if(text.matches("^[0-9]{4}-[0-9]{1}-[0-9]{2}$") && Integer.parseInt(text.substring(7,9)) > 31){
                        throw new InvalidSyntaxException();
                    }
                    if(text.charAt(0) == '0'){
                            throw new InvalidSyntaxException();
                        }
                    if(text.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$") ||text.matches("^[0-9]{4}-[0-9]{2}-[0-9]{1}$") ){
                        if(text.charAt(5) == '0' && text.charAt(6) == '0'){
                            throw new InvalidSyntaxException();
                        }
                    }

                    if(text.matches("^[0-9]{4}-[0-9]{1}-[0-9]{2}$") || text.matches("^[0-9]{4}-[0-9]{1}-[0-9]{1}$") ){
                        if(text.charAt(5)=='0')
                            throw new InvalidSyntaxException();
                    }

                    if(text.matches("^[0-9]{4}-[0-9]{1}-[0-9]{2}$")){
                        if(text.charAt(7) == '0' && text.charAt(8) =='0')
                            throw new InvalidSyntaxException();
                    }

                    if(text.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")){
                        if(text.charAt(8) == '0' && text.charAt(9) == '0')
                            throw new InvalidSyntaxException();
                    }








                    System.out.println("Please enter precipitation (cm): ");
                    double precip = Double.parseDouble(scan.nextLine());

                    System.out.println("Please enter windspeed (km/h): ");
                    double windspeed = Double.parseDouble(scan.nextLine());

                    Storm newStorm = new Storm(name,precip,windspeed,text);
                    database.put(name, newStorm);
                        System.out.println(name +" added.");


                    } catch (InvalidSyntaxException e) {
                        System.out.println("Invalid Syntax!");
                    } catch (InvalidNameException e) {
                        System.out.println("Storm with name already exists!");
                    }
                    break;
                /**
                 * Case L: Looks for a storm based on key value
                 * Postcondition: Storm is searched
                 */
                case "L":
                    try{
                        System.out.println("Please enter a name: ");
                        String name = scan.nextLine().toUpperCase();

                        if(database.isEmpty() || !database.containsKey(name))
                            throw new KeyNotFoundException();

                        System.out.println(database.get(name));

                    } catch (KeyNotFoundException e) {
                        System.out.println("Invalid Key");
                    }

                    break;
                /**
                 * Case D: deletes a storm from the table
                 * Postcondition: Storm is deleted
                 */
                case "D":

                    try{
                        System.out.println("Please enter a name: ");
                        String name = scan.nextLine().toUpperCase();

                        if(database.isEmpty() || !database.containsKey(name))
                            throw new KeyNotFoundException();

                        database.remove(name);
                        System.out.println("Storm "+name+" has been removed!");

                    } catch (KeyNotFoundException e) {
                        System.out.println("Invalid Key!");
                    }

                    break;
                /**
                 * Case E: Edit a storm and its values
                 * Postcondition: Storm is edited
                 */
                case "E":
                    try{
                        System.out.println("Please enter a name: ");
                        String name = scan.nextLine().toUpperCase();


                        if(database.isEmpty() || !database.containsKey(name))
                            throw new KeyNotFoundException();

                        System.out.println("In Edit Mode, press enter without any input to leave data unchanged.");
                        System.out.println("Please enter a date: ");
                        String date = scan.nextLine();
                        if(!date.equals("")) {
                            if(!date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")&&!date.matches("^[0-9]{4}-[0-9]{1}-[0-9]{2}$")
                                    &&!date.matches("^[0-9]{4}-[0-9]{1}-[0-9]{1}$")&&!date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{1}$")){
                                throw new InvalidSyntaxException();
                            }
                            if(date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$") && Integer.parseInt(date.substring(5,7)) > 12){
                                throw new InvalidSyntaxException();
                            }
                            if(date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{1}$") && Integer.parseInt(date.substring(5,7)) > 12){
                                throw new InvalidSyntaxException();
                            }
                            if(date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$") && Integer.parseInt(date.substring(8,10)) > 31){
                                throw new InvalidSyntaxException();
                            }
                            if(date.matches("^[0-9]{4}-[0-9]{1}-[0-9]{2}$") && Integer.parseInt(date.substring(7,9)) > 31){
                                throw new InvalidSyntaxException();
                            }
                            if(date.charAt(0) == '0'){
                                throw new InvalidSyntaxException();
                            }
                            if(date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$") ||date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{1}$") ){
                                if(date.charAt(5) == '0' && date.charAt(6) == '0'){
                                    throw new InvalidSyntaxException();
                                }
                            }

                            if(date.matches("^[0-9]{4}-[0-9]{1}-[0-9]{2}$") || date.matches("^[0-9]{4}-[0-9]{1}-[0-9]{1}$") ){
                                if(date.charAt(5)=='0')
                                    throw new InvalidSyntaxException();
                            }

                            if(date.matches("^[0-9]{4}-[0-9]{1}-[0-9]{2}$")){
                                if(date.charAt(7) == '0' && date.charAt(8) =='0')
                                    throw new InvalidSyntaxException();
                            }

                            if(date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")){
                                if(date.charAt(8) == '0' && date.charAt(9) == '0')
                                    throw new InvalidSyntaxException();
                            }

                            database.get(name).setDate(date);
                        }

                        System.out.println("Please enter precipitation (cm): ");
                        String precip1= scan.nextLine();
                        if(!precip1.equals("")) {
                            double precip = Double.parseDouble(precip1);
                            database.get(name).setPrecipitation(precip);
                            }

                        System.out.println("Please enter windspeed (km/h): ");
                        String wind = scan.nextLine();
                        if(!wind.equals("")) {
                            double wind1 = Double.parseDouble(wind);
                            database.get(name).setWindspeed(wind1);
                        }

                        System.out.println(name+" Added!");



                    }catch (InvalidSyntaxException ex ){
                        System.out.println("Invalid Format!");
                    }catch (KeyNotFoundException ex){
                        System.out.println("Key not found!");
                    }

                    break;
                /**
                 * Case R: Prints out the storms in a table based on precipitation levels
                 * Postcondition: Database is printed
                 */
                case "R":
                    try {
                        if(database.isEmpty())
                            throw new KeyNotFoundException();

                        ArrayList<Storm> stormValues = new ArrayList<>(database.values());
                        
                        Collections.sort(stormValues, new PrecipitationComparator());
                        printTable(stormValues);
                    } catch (KeyNotFoundException e) {
                        System.out.println("Database is empty!");
                    }

                    break;

                /**
                 * Case W: prints out the storms in a table based on windspeed levels
                 * Postcondition: Database is printed
                 */
                case "W":

                    try{
                        if(database.isEmpty())
                            throw new KeyNotFoundException();
                        ArrayList<Storm> stormWind = new ArrayList<>(database.values());

                        Collections.sort(stormWind,new WindSpeedComparator());
                        printTable(stormWind);

                    }catch (KeyNotFoundException ex){
                        System.out.println("Database is empty!");
                    }

                    break;
                /**
                 * Case X: Terminates program and saved the database in a file
                 * Postcondition: Program quits and data is saved
                 */
                case "X":

                    try {
                        Storm.writeFromFile(database);

                        System.out.println("File saved to hurricane.ser; feel free to use the weather channel in the meantime.");
                        condition = false;

                    } catch (IOException e) {
                        System.out.println("File not found");
                    }

                    break;
                /**
                 * Case Q: Program terminated without saving
                 * Postcondition: Program quits
                 */
                case "Q":
                    file.deleteOnExit();
                    System.out.println("Goodbye!");
                    condition = false;
                    break;
            }


        }


    }

    /**
     * Method used for printing out the table
     * @param stormValues
     * Takes in a list of values from the database
     * Postcondition: Table is printed
     */
    public static void printTable(ArrayList<Storm> stormValues) {
        System.out.printf("%-15s%-15s%-15s%-15s\n","Name","Windspeed",
                "Rainfall","Date");
        System.out.println("---------------------------------"
                + "--------"
                + "---------------");
        for (Storm stormValue : stormValues) {
            if (stormValue != null)
                System.out.printf("%-15s%-15s%-15s%-15s\n", stormValue.getName(), stormValue.getWindspeed(),
                        stormValue.getPrecipitation(), stormValue.getDate());
        }
    }

}
