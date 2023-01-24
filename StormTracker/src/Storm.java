import java.io.*;
import java.util.HashMap;

/**
 * This class creates a represents a Storm object that will stored in a hashmap of storms for easy access. Each storm
 * has a name, precipitation level, windspeed, and a date. This class also provides a read and write to
 * file method to be able to serialize the storm data for continuous use.
 *
 * @author Brice Joseph, 114588946, R03
 *
 * Assignment: HW #6
 */
public class Storm implements Serializable {

    private String name;
    private double precipitation;
    private double windspeed;
    private String date;

    /**
     * Constructor for Storm object which initializes the data fields
     */
    public Storm(){
        name= null;
        precipitation = 0.0;
        windspeed = 0.0;
        date = null;

    }

    /**
     * Second constructor for Storm which takes in all datafields as a parameter
     * @param name
     * Name is the what the storm name will be set too
     * @param precipitation
     * Precipitation is what the precipitation levels are
     * @param windspeed
     * Windspeed is the windspeed of the storm
     * @param date
     * Date is the date in which the Storm was formed
     */
    public Storm(String name,double precipitation,double windspeed, String date){
        this.name = name;
        this.precipitation = precipitation;
        this.windspeed = windspeed;
        this.date = date;
    }

    /**
     * This method stores the data structure to a .ser file upon termination of the program and will be accessible the
     * next time the program is started using File and Object streams
     * @param map
     * Map is the Hashmap that will be stored in the file
     * @throws IOException
     * Throws this exception is file is not found
     * Postcondition:
     * Object is stored in file
     */
    public static void writeFromFile(HashMap<String,Storm> map) throws IOException {

        FileOutputStream file = new FileOutputStream("hurricane.ser");

        ObjectOutputStream outStream = new ObjectOutputStream(file);

        outStream.writeObject(map);

        outStream.close();
        file.close();
    }

    /**
     * This method reads the file where the Object from the write method was stored and stores the new value into a
     * Hashmap that has a String as a key and Storm as an object
     * @return
     * This class returns a HashMap with values saved in the save file from the last time the program was ran.
     * @throws IOException
     * Throws this exception if file is not found
     * @throws ClassNotFoundException
     * Throws this exception is Class is not found
     * Postcondition:
     * File is read and saved Hashmap is returned
     */
    public static HashMap<String,Storm> readToFile() throws IOException, ClassNotFoundException {
        HashMap<String, Storm> database = null;
            try {
                FileInputStream input = new FileInputStream("hurricane.ser");
                ObjectInputStream output = new ObjectInputStream(input);
                database = (HashMap<String,Storm>) output.readObject();
                output.close();
                input.close();

            } catch (IOException ioe) {
                System.out.println("File invalid");
            } catch (ClassNotFoundException c) {
                System.out.println("Class not found");
            }
        return database;

    }

    /**
     * This is a getter method for the Date of the Storm
     * @return
     * Returns the date of the storm
     * Postcondition:
     * Date is returned
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter for the Date data field
     * @param date
     * Takes in the date which the new date will be set too
     * Postconditon:
     * Date is set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter for the windspeed data field
     * @return
     * Returns the windspeed of the Storm
     * Postcondition:
     * Windspeed is returned
     */
    public double getWindspeed() {
        return windspeed;
    }

    /**
     * Setter for the windspeed data field
     * @param windspeed
     * Takes in the windspeed which the new date will be set too
     * Postconditon:
     * Windspeed is set
     */
    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }
    /**
     * Getter for the Precipitation data field
     * @return
     * Returns the Precipitation of the Storm
     * Postcondition:
     * Precipitation is returned
     */
    public double getPrecipitation() {
        return precipitation;
    }
    /**
     * Setter for the precipitation data field
     * @param precipitation
     * Takes in the precipitation which the new date will be set too
     * Postconditon:
     * Precipitation is set
     */
    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }
    /**
     * Getter for the Name data field
     * @return
     * Returns the Name of the Storm
     * Postcondition:
     * Name is returned
     */
    public String getName() {
        return name;
    }


    /**
     * This method returns the Storm data in a readable format with all its datafield
     * @return
     * Returns a string representing the Storm
     * Postcondition:
     * Storm is printed in readable format
     */
    public String toString(){
        //Storm JACK: Date 2015-4-12, 43 km/h winds, 214.0 cm of rain

        return "Storm "+getName()+": Date "+getDate()+", "+getWindspeed()+" km/h winds, "+getPrecipitation()+" cm of rain.";
    }
}
