
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * This abstract class creates a instance of CommandStack which is used to create two Applications in
 * its extending classes. It has a method to read the user command and to go back in the stack.
 *
 * @author Brice Joseph, 114588946, R03
 */
public abstract class Application extends CommandStack {
    /**
     * Protected CommandStack stack represents a stack of commands used to
     * create two applications with their own respective commands
     */
    protected CommandStack stack;

    /**
     * This method reads the user input from a scanner and uses that to find a command
     * in the menu presented to the user.
     * @param scanner
     * Scanner is used to read user input
     * Postcondition:
     * Menu commands are executed
     */
    public void readCommand(Scanner scanner){

        boolean condition = true;


        while (condition) {


            if(stack instanceof Maps) {


                System.out.println("Stack Debug: ");
                System.out.println("Current Screen: "+stack.peek().toShortString());

                System.out.println("Maps Options:\n" +
                        "F) Find a place\n" +
                        "P) Plan a route\n" +
                        "N) Start Navigation\n" +
                        "H) Home Screen\n" +
                        "S) Switch to Safari\n" +
                        "B) Back");
            }
            else{
                System.out.println("Stack Debug: ");
                System.out.println("Current Screen: "+stack.peek().toShortString());

                System.out.println("Safari Options:\nG) Google Something \nF) Go to a favorite \nL) Follow a link \n" +
                        "H) Home Screen \nS) Switch to Maps \nB) Back");
            }


            System.out.println();
            System.out.println("Please select an option: ");
            String in2 = scanner.nextLine().toUpperCase();
            switch (in2) {


                case "P":
                    if (stack instanceof Maps) {
                        PlanRoute plan = new PlanRoute(scanner);
                        stack.push(plan);

                    }
                    break;

                case "N":
                    if (stack instanceof Maps && (stack.peek() instanceof FindPlace || stack.peek()
                            instanceof PlanRoute) && !(stack.peek() instanceof StartNavigation) &&
                            !(stack.isEmpty())) {
                        StartNavigation start = new StartNavigation(stack);
                        stack.push(start);


                    } else
                        try {
                            throw new InvalidCommandException();
                        } catch (InvalidCommandException e) {
                            System.out.println("Invalid");
                        }
                    break;
                case "G":
                    if (stack instanceof Safari) {
                        GoogleSomething google = new GoogleSomething(scanner);
                        stack.push(google);

                    } else
                        try {
                            throw new InvalidCommandException();
                        } catch (InvalidCommandException e) {
                            System.out.println("Invalid command!");
                        }
                    break;
                case "L":
                    if (stack instanceof Safari && !(stack.peek() instanceof SafariHome) && !(stack.isEmpty())) {
                        FollowLink fol = new FollowLink(scanner);
                        stack.push(fol);
                    } else
                        try {
                            throw new InvalidCommandException();
                        } catch (InvalidCommandException e) {
                            System.out.println("Invalid Command");
                        }

                    break;
                case "F":
                    if (stack instanceof Safari) {
                        GoToBookmark book = new GoToBookmark(scanner);
                        stack.push(book);
                    } else {
                        try {

                            if (stack.peek() instanceof MapsHome) {
                                FindPlace findplace = new FindPlace(scanner);
                                stack.push(findplace);
                            } else
                                throw new InvalidCommandException();
                        } catch (InvalidCommandException ex) {
                            System.out.println("Invalid Command for this stack!");

                        }

                    }
                    break;

                case "H":

                    condition = false;
                    break;
                case "B":
                    if (stack.peek() instanceof MapsHome || stack.peek() instanceof SafariHome) {
                        condition = false;

                    } else if(!(stack.peek() instanceof Home)) {
                        stack.pop();
                    }else
                        try {
                            throw new EmptyStackException();
                        } catch (EmptyStackException e) {
                            System.out.println("Can't go back, already at Home!");
                        }


                    break;
            }

        }


    }

    /**
     * This method allows the user to go back
     * @throws EmptyStackException
     * This exception is throw if the stack is empty, so there is no way to go back
     * Postcondition:
     * Stack is popped
     */
    public void goBack() throws EmptyStackException {


        if(!(stack.peek() instanceof Home)) {
            stack.pop();
        }else
            try {
                throw new EmptyStackException();
            } catch (EmptyStackException e) {
                System.out.println("Can't go back, already at Home!");
            }

    }



}
