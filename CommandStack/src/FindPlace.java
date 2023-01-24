import java.util.Scanner;

/**
 * This class represents the Find Place command for maps with string methods for printing and valid command method for
 * checking validity
 *
 * @author Brice Joseph, 114588946, R03
 *
 */
public class FindPlace implements Command{

    /**
     * String destination is the destination for the Find place command which the user inputs
     */
    private String destination;

    /**
     * Public constructor for the command
     * @param scanner
     * Uses scanner to assign the data field destination
     * Postcondition:
     * Command is created
     */
    public FindPlace(Scanner scanner){
        System.out.println("Please enter a location");
        destination = scanner.nextLine();

    }

    /**
     * Getter for the String of the command
     * @return
     * Returns the destination
     */
    public String getDest(){
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
    public boolean validCommand(CommandStack stack) {

       try {

           if (stack.peek() instanceof MapsHome) {
               return true;


           } else
               throw new InvalidCommandException();
       }catch(InvalidCommandException ex){
           System.out.println("Invalid Command!");
       }

        return false;
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

        return "Showing results for "+getDest()+".";
    }
    /**
     * This method prints out the command for the Stack Debug format
     * @returns
     * Returns the Stack Debug display text
     * Postcondition:
     * Command is printed
     */
    public String toString(){

        return "->F:"+getDest();
    }
}
