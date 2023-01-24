import java.util.Scanner;
/**
 * This class represents the Plan Route command for maps with string methods for printing and valid command method for
 * checking validity
 *
 * @author Brice Joseph, 114588946, R03
 *
 */
public class PlanRoute implements Command{
    /**
     * String source is the source for the plan route command which the user inputs
     * String destination is the destination for the plan route command which the user inputs
     */
protected String source;
protected String destination;
    /**
     * Public constructor for the command
     * @param scanner
     * Uses scanner to assign the data field destination and source
     * Postcondition:
     * Command is created
     */
    public PlanRoute(Scanner scanner){
        System.out.println("Please enter a source: ");
        source = scanner.nextLine();

        System.out.println("Please enter a destination: ");
        destination = scanner.nextLine();
    }
    /**
     * Getter for a String of the command
     * @return
     * Returns the source
     */
    public String getSource(){
        return source;
    }
    /**
     * Getter for a String of the command
     * @return
     * Returns the destination
     */
    public String getDestination(){
        return destination;
    }
    @Override
    /**
     * Checks validity of the command with the condition of the stack
     * @returns
     * returns true if its a valid command and false if not
     * Postcondition:
     * Validity is checked
     */
    public boolean validCommand(CommandStack stack)  {


        if(stack.peek() instanceof MapsHome)
            return true;

            return false;


    }
    /**
     * This method prints out the command for the Stack Debug format
     * @returns
     * Returns the Stack Debug display text
     * Postcondition:
     * Command is printed
     */
    public String toString(){

        return "->P:"+getSource()+"-"+getDestination();
    }
    @Override
    /**
     * This method prints out the command for the Current screen format
     * @returns
     * Returns the Current Screen display text
     * Postcondition:
     * Command is printed
     */
    public String toShortString() {

        return "Planning route from "+getSource()+ " to "+getDestination();
    }
}
