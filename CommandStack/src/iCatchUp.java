import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * This is the main driver class which creates two instances of Application and therefore creating
 * two command stacks. This class utilizes the features of the applications Maps and Safari through a stack data
 * struture.
 * @author Brice Joseph, 114588946, R03
 *
 * Assignment HW #3
 */
public class iCatchUp extends Application{

    /**
     * Main method which creates two Applications and utilizes its commands through a user menu
     * @param args
     * Default constructor for main method
     * @throws InvalidCommandException
     * Throws this exception if the command is invalid for the CommandStack
     * Postcondition:
     * Menu drive application is displayed with user interface and applicable menu options
     */
    public static void main(String[] args) {

        Maps maps = new Maps();
        Safari safari = new Safari();
        CommandStack selected = maps;


        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the iPhony pocket telegraph simulator. You are on the home screen.");

        System.out.println();
        Home home = new Home();
        maps.push(home);
        safari.push(home);


        boolean homecondition = true;

        while(homecondition){


            System.out.println("Home Options: \nS) Safari \nM) Maps \nQ) Quit");
            System.out.println("Please select an option: ");
            String in = scanner.nextLine().toUpperCase();


            if(in.equals("S")){
                selected= safari;
                if(safari.peek() instanceof Home) {
                    SafariHome SafariHome = new SafariHome();
                    safari.push(SafariHome);
                }
            }
            else if(in.equals("M")){

                selected = maps;
                if(maps.peek() instanceof Home) {
                    MapsHome MapsHome = new MapsHome();
                    maps.push(MapsHome);
                }



            }
            else if(in.equals("B")){
                try {
                    throw new EmptyStackException();
                }catch(EmptyStackException ex){
                    System.out.println("Already at home screen, can't go back!");
                }
            }

            switch(in){

                case "M":


                    boolean condition = true;


                    while (condition) {


                            if(selected == maps) {


                                System.out.println("Stack Debug: ");
                                System.out.println(maps);
                                System.out.println("Current Screen: "+maps.peek().toShortString());

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
                                System.out.println(safari);
                                System.out.println("Current Screen: "+safari.peek().toShortString());

                                System.out.println("Safari Options:\nG) Google Something \nF) Go to a favorite \nL) Follow a link \n" +
                                        "H) Home Screen \nS) Switch to Maps \nB) Back");
                            }


                        System.out.println();
                        System.out.println("Please select an option: ");
                        String in2 = scanner.nextLine().toUpperCase();

                        switch (in2) {

                            /**
                             * Case P: Represents the Plan Route command which requires user input
                             * Postcondition:
                             * PlanRoute command is pushed to the command stack
                             */
                            case "P":
                                if (selected == maps) {
                                    PlanRoute plan = new PlanRoute(scanner);
                                    selected.push(plan);

                                }
                                break;
                            /**
                             * Case N: Represents the Start Navigation command which grabs info from the last command
                             * Postcondition:
                             * StartNaviagtion command is pushed to the command stack
                             */
                            case "N":
                                if (selected == maps && (maps.peek() instanceof FindPlace || maps.peek()
                                        instanceof PlanRoute) && !(maps.peek() instanceof StartNavigation) &&
                                        !(maps.isEmpty())) {
                                    StartNavigation start = new StartNavigation(maps);
                                    maps.push(start);


                                } else
                                    try {
                                        throw new InvalidCommandException();
                                    } catch (InvalidCommandException e) {
                                        System.out.println("No Route, Invalid Command!");
                                    }
                                break;
                            /**
                             * Case G: Represents the Google Something command which requires user input
                             * Postcondition:
                             * GoogleSomething command is pushed to the command stack
                             */
                            case "G":
                                if (selected == safari) {
                                    GoogleSomething google = new GoogleSomething(scanner);
                                    safari.push(google);

                                } else
                                    try {
                                        throw new InvalidCommandException();
                                    } catch (InvalidCommandException e) {
                                        System.out.println("Invalid command!");
                                    }
                                break;
                            /**
                             * Case L: Represents the Follow Link command which requires user input
                             * Postcondition:
                             * FollowLink command is pushed to the command stack
                             */
                            case "L":
                                if (selected == safari && !(safari.peek() instanceof SafariHome) && !(safari.isEmpty())) {
                                    FollowLink fol = new FollowLink(scanner);
                                    safari.push(fol);
                                } else
                                    try {
                                        throw new InvalidCommandException();
                                    } catch (InvalidCommandException e) {
                                        System.out.println("Invalid Command");
                                    }

                                break;
                            /**
                             * Case F: Represents the Find Place command which requires user input
                             * Postcondition:
                             * FindPlace command is pushed to the command stack
                             */
                            case "F":
                                if (selected == safari) {
                                    GoToBookmark book = new GoToBookmark(scanner);
                                    safari.push(book);
                                } else {
                                    try {

                                        if (selected.peek() instanceof MapsHome) {
                                            FindPlace findplace = new FindPlace(scanner);
                                            selected.push(findplace);
                                        } else
                                            throw new InvalidCommandException();
                                    } catch (InvalidCommandException ex) {
                                        System.out.println("Invalid Command for this stack!");

                                    }

                                }
                                break;
                            /**
                             * Case S: switches between command stacks/applications
                             * Postcondtion:
                             * Application is switched
                             */
                            case "S":
                                if(selected == maps && safari.peek() instanceof Home){
                                    selected = safari;
                                    SafariHome SafariHome = new SafariHome();
                                    safari.push(SafariHome);
                                }

                                else if(selected == safari && maps.peek() instanceof Home){
                                    selected = maps;
                                    MapsHome MapsHome = new MapsHome();
                                    maps.push(MapsHome);
                                }
                                else if(selected == maps) {
                                    selected = safari;

                                } else if (selected == safari) {
                                    selected = maps;
                                }
                                break;
                            /**
                             * Case H: Goes to the main menu
                             * Postcondition:
                             * User is taken back to the home menu
                             */
                            case "H":

                                condition = false;
                                break;
                            /**
                             * Case B: Goes back a command and checks the command stack
                             * Postcondition:
                             * Stack is popped
                             */
                            case "B":
                                if (selected.peek() instanceof MapsHome || selected.peek() instanceof SafariHome) {
                                    condition = false;

                                } else if(!(selected.peek() instanceof Home)) {
                                    selected.pop();
                                }else
                                    try {
                                        throw new EmptyStackException();
                                    } catch (EmptyStackException e) {
                                        System.out.println("Can't go back, already at Home!");
                                    }


                                break;
                        }

                    }


                    break;



                case "S":


                    boolean condition1 = true;


                    while (condition1) {


                        if(selected == maps) {
                            System.out.println("Stack Debug: ");
                            System.out.println(maps);
                            System.out.println("Current Screen: "+maps.peek().toShortString());

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
                            System.out.println(safari);
                            System.out.println("Current Screen: "+safari.peek().toShortString());

                            System.out.println("Safari Options:\nG) Google Something \nF) Go to a favorite \nL) Follow a link \n" +
                                    "H) Home Screen \nS) Switch to Maps \nB) Back");
                        }




                        System.out.println();
                        System.out.println("Please select an option: ");
                        String in2 = scanner.nextLine().toUpperCase();
                        switch (in2) {

                            /**
                             * Case P: Represents the Plan Route command which requires user input
                             * Postcondition:
                             * PlanRoute command is pushed to the command stack
                             */
                            case "P":
                                if (selected == maps) {
                                    PlanRoute plan = new PlanRoute(scanner);
                                    selected.push(plan);

                                }
                                else
                                    try {
                                        throw new InvalidCommandException();
                                    } catch (InvalidCommandException e) {
                                        System.out.println("Invalid Command");
                                    }
                                break;
                            /**
                             * Case N: Represents the Start Navigation command which grabs info from the last command
                             * Postcondition:
                             * StartNaviagtion command is pushed to the command stack
                             */
                            case "N":

                                if (selected == maps && (maps.peek() instanceof FindPlace || maps.peek() instanceof PlanRoute)) {
                                    StartNavigation start = new StartNavigation(maps);
                                    maps.push(start);


                                } else
                                    try {
                                        throw new InvalidCommandException();
                                    } catch (InvalidCommandException e) {
                                        System.out.println("Invalid");
                                    }
                                break;
                            /**
                             * Case G: Represents the Google Something command which requires user input
                             * Postcondition:
                             * GoogleSomething command is pushed to the command stack
                             */
                            case "G":
                                if (selected == safari) {
                                    GoogleSomething google = new GoogleSomething(scanner);
                                    safari.push(google);

                                } else
                                    try {
                                        throw new InvalidCommandException();
                                    } catch (InvalidCommandException ex) {
                                        System.out.println("Invalid command!");
                                    }
                                break;
                            /**
                             * Case L: Represents the Follow Link command which requires user input
                             * Postcondition:
                             * FollowLink command is pushed to the command stack
                             */
                            case "L":
                                if (selected == safari && !(safari.peek() instanceof SafariHome)) {
                                    FollowLink fol = new FollowLink(scanner);
                                    safari.push(fol);
                                } else
                                    try {
                                        throw new InvalidCommandException();
                                    } catch (InvalidCommandException e) {
                                        System.out.println("Invalid Command");
                                    }
                                break;
                            /**
                             * Case F: Represents the Find Place command which requires user input
                             * Postcondition:
                             * FindPlace command is pushed to the command stack
                              */
                            case "F":
                                if (selected == safari) {
                                    GoToBookmark book = new GoToBookmark(scanner);
                                    safari.push(book);
                                } else {
                                    try {

                                        if (selected.peek() instanceof MapsHome) {
                                            FindPlace findplace = new FindPlace(scanner);
                                            selected.push(findplace);
                                        } else
                                            throw new InvalidCommandException();
                                    } catch (InvalidCommandException ex) {
                                        System.out.println("Invalid Command for this stack!");

                                    }

                                }
                                break;

                            /**
                             * Case S: switches between command stacks/applications
                             * Postcondtion:
                             * Application is switched
                             */
                            case "S":
                                if(selected == maps && safari.peek() instanceof Home){
                                    selected = safari;
                                    SafariHome SafariHome = new SafariHome();
                                    safari.push(SafariHome);
                                }

                                else if(selected == safari && maps.peek() instanceof Home){
                                    selected = maps;
                                    MapsHome MapsHome = new MapsHome();
                                    maps.push(MapsHome);
                                }
                                else if(selected == maps) {
                                    selected = safari;

                                } else if (selected == safari) {
                                    selected = maps;
                                }

                                break;
                            /**
                             * Case H: Goes to the main menu
                             * Postcondition:
                             * User is taken back to the home menu
                              */
                            case "H":


                                condition1 = false;
                                break;
                            /**
                             * Case B: Goes back a command and checks the command stack
                             * Postcondition:
                             * Stack is popped
                              */
                            case "B":
                                if (selected.peek() instanceof MapsHome || selected.peek() instanceof SafariHome) {
                                    condition1 = false;

                                } else if(!(selected.peek() instanceof Home)) {
                                    selected.pop();
                                }else
                                    try {
                                        throw new InvalidCommandException();
                                    } catch (InvalidCommandException e) {
                                        System.out.println("Can't go back, already at Home!");
                                    }


                                break;
                        }

                    }





                    break;
                /**
                 * Case Q: Quits the menu and terminates program
                 * Postcondition:
                 * Program closes
                 */
                case "Q":
                    System.out.println("Goodbye.");
                    homecondition =false;
                    break;
            }


        }



        }
    }


