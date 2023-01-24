/**
 *This class is a command interface which provides the methods every implementing class needs
 * @author  Brice Joseph, 114588946, R03
 */
public interface Command {

    /**
     * Checks if the command is valid
     * @param stack
     * Stack reference
     * @return
     * Returns true if valid and false if not
     */
    boolean validCommand(CommandStack stack);

    /**
     * ToString method for Stack Debug printing
     * @return
     * Command in stack debug format
     */
    String toString();
    /**
     * ToShortString method for Current Screen printing
     * @return
     * Command in current screen format
     */
    String toShortString();

}
