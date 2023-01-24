/**
 * Implements a command object called SafariHome that will be pushed into the stack
 * when the Safari application is called. It has print methods and a method to check for
 * validity of stack. This class implements the command interface
 * @author Brice Joseph, 114588946
 */

public class SafariHome implements Command{

    /**
     * Default Constructor which creates a command object
     * Postcondition: SafariHome command is created
     */
    public SafariHome(){

    }

    /**
     * A method that puts the command in a format for printing the Stack debug
     * @return
     * returns the command in a printable form
     */
    public String toString(){

        return "->SafariHome";
    }
    @Override
    /**
     * A method that checks if the current state of the stack will allow for this command to be pushed
     * @throws
     * Throws InvalidCommand Exception if the command is not valid
     * @return
     * returns true if it is valid and false if not
     * Postcondition:
     * The commmand is checked for validity
     */
    public boolean validCommand(CommandStack stack) {

        if(stack.peek() instanceof Home) {

            return true;
        }
        else try {
            throw new InvalidCommandException();
        } catch (InvalidCommandException e) {
            System.out.println("Invalid command!");
        }

        return false;
        //make this so you can actually check before adding
    }

    @Override
    /**
     * This method returns the command in a format to be printed when displaying content of the current screen
     * @return
     * Returns a printable form of the command
     * Postcondition:
     * Command is printed
     */
    public String toShortString() {

        return "Safari Home";
    }
}


