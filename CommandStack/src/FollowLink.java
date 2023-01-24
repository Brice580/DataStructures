import java.util.Scanner;


/**
 * This class represents the Follow Link command for safari with string methods for printing and valid command method for
 * checking validity
 *
 * @author Brice Joseph, 114588946, R03
 *
 */
public class FollowLink implements Command{

    /**
     * String link is the link for the Follow Link command which the user inputs
     */
    private String link;


    /**
     * Public constructor for the command
     * @param scanner
     * Uses scanner to assign the data field destination
     * Postcondition:
     * Command is created
     */
    public FollowLink(Scanner scanner){
        System.out.println("Please enter a link: ");
        link = scanner.nextLine();

    }
    /**
     * Getter for the String of the command
     * @return
     * Returns the destination
     */
    public String getLink(){
        return link;
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

        if(stack.peek() instanceof SafariHome && !(stack.isEmpty()))
            return true;

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

        return "link";
    }
    /**
     * This method prints out the command for the Stack Debug format
     * @returns
     * Returns the Stack Debug display text
     * Postcondition:
     * Command is printed
     */
    public String toString(){
        return "->L:"+getLink();
    }
}


