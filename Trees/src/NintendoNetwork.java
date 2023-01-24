import java.util.Scanner;

/**
 * Main driver class that creates the Nintendo network and offers a menu with various options for users
 *
 * @author Brice Joseph, 114588946, R03
 */
public class NintendoNetwork {

    public static NetworkTree tree = new NetworkTree();

    public static NetworkNode saved = null;

    /**
     * Main method that generates a menu with commands using methods in the Tree class
     * @param args
     * Default parameter
     * Postcondition:
     * Network is created and is able to be used
     */
    public static void main(String[] args){



        Scanner scan = new Scanner(System.in);




        System.out.println("Welcome to the Nintendo Manager");
        System.out.println("L - Load from file\n" +
                "P - Print\n" +
                "C - Cursor to child(index number)\n" +
                "A - Add child(index, type, prompt for text)\n" +
                "U - Cursor up(to parent)\n" +
                "X - Cut/Delete cursor\n" +
                "V - Paste Subtree(index number)\n" +
                "R - Cursor to root\n" +
                "S - Save to Text File\n" +
                "Q - Quit");

        boolean condition = true;

        while(condition){

            System.out.println("Please select an option: ");
            String option = scan.nextLine().toUpperCase();

            switch (option){

                /**
                 * Case x: cuts the cursor
                 */
                case "X":
                    try {
                        if (tree.getCursor() != null) {

                            saved = new NetworkNode(tree.getCursor().getName(), tree.getCursor().isBroken());
                            saved = tree.cutCursor();
                            System.out.println("Cursor cut!");
                        }
                        else
                            throw new IllegalArgumentException();
                    }catch (IllegalArgumentException ex){
                        System.out.println("Invalid");
                    }
                    break;
                /**
                 * Case v: pastes the cut cursor to the tree
                 */
                case "V":
                    try {
                        if (saved != null) {
                            System.out.println("Please enter an index: ");
                            int indexcut = Integer.parseInt(scan.nextLine());
                                if ( indexcut != 1&&indexcut-1 < 1 ) {
                                    throw new IllegalArgumentException();
                                }
                            tree.addChild(indexcut-1,saved);
                        }
                    }catch (IllegalArgumentException x){
                        System.out.println("Invalid command!");
                    }




                    break;
                /**
                 * Case a: adds a child into the tree
                  */
                case "A": //check for nintendo nodes dont forget
                    try {
                        if (tree.getRoot() != null && !tree.getCursor().isNintendo()) {


                            System.out.println("Please enter an index: ");
                            int index1 = Integer.parseInt(scan.nextLine());

                            try {
                                if (index1 == 0 || index1 > tree.getMAXCHILDREN())
                                    throw new IllegalArgumentException();

                                if ((index1 != 1 &&tree.getCursor().getChildren(index1 - 1) == null) ||
                                        (index1!= 1&&tree.getCursor().getChildren(index1 - 1).isNintendo()))
                                    throw new IllegalArgumentException();

                            System.out.println("Please enter a device name: ");
                            String name1 = scan.nextLine();
                            System.out.println("Is this Nintendo(Y/N): ");
                            String answer = scan.nextLine().toUpperCase();


                            if (answer.equals("Y")) {
                                NetworkNode newN = new NetworkNode(name1, false);
                                tree.addChild(index1 - 1, newN);
                                newN.setNintendo(true);
                            } else if (answer.equals("N")) {
                                NetworkNode newN = new NetworkNode(name1, false);
                                tree.addChild(index1 - 1, newN);

                            }
                            } catch (IllegalArgumentException ex) {
                                System.out.println("Invalid index");
                            }

                        } else {
                            try {
                                throw new IllegalArgumentException();
                            } catch (IllegalArgumentException ex) {
                                System.out.println("No tree");
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid");
                    }
                    break;
                /**
                 * Case c: moves the cursor to a index
                  */
                case "C":
                    try {
                        if (tree.getRoot() == null) {
                            throw new IllegalArgumentException();
                        }
                            else{
                                System.out.println("Please enter an index: ");
                                int index = scan.nextInt();

                                    if (index!= 1 && tree.getCursor().getChildren(index - 1) == null)
                                        throw new IllegalArgumentException();
                                    else {
                                        tree.cursorToChild(index);
                                        System.out.println("Cursor is moved to " + tree.getCursor().getName());
                                    }

                        }
                    }catch (IllegalArgumentException ex){
                        System.out.println("Invalid");
                    }

                    break;
                /**
                 * Case L: loads the file to the console and makes the tree file in a readable format
                 */
                case "L":
                    System.out.println("Please enter file name: ");
                    String input = scan.nextLine();
                    tree.readFromFile(input);
                    tree.setCursor(tree.getRoot());

                    break;
                /**
                 * Case P: prints out the tree in a readable format
                 */
                case "P":
                    try {
                        if (tree.getRoot() == null) {
                            throw new IllegalArgumentException();
                        }
                    }catch (IllegalArgumentException ex){
                        System.out.println("Tree is null!");

                    }
                    if(tree.getRoot() !=null)
                        System.out.println(tree);
                    break;

                /**
                 * Case q: quits the application
                 */
                case "Q":
                    System.out.println("See you next time!");
                    condition = false;
                    break;
                /**
                 * Case r: moves the cursor to the root
                 */
                case "R":
                    try {
                        if (tree.getRoot() != null && tree.getCursor() != tree.getRoot()) {

                            tree.cursorToRoot();
                            System.out.println("Cursor moved to root!");
                        }
                        else
                            throw new IllegalArgumentException();
                    }catch (IllegalArgumentException e){
                        System.out.println("Invalid");
                    }
                    break;
                /**
                 * Case u: moves the cursor to the parent of the node
                 */
                case "U":
                    try{
                        if(tree.getRoot() == null){
                            throw new IllegalArgumentException();
                        }

                        if (tree.getCursor() == tree.getRoot()) {
                            throw new IllegalArgumentException();
                        }
                        else{
                            tree.cursorToParent();
                            System.out.println("Cursor moved to "+tree.getCursor().getName());
                        }
                    }catch (IllegalArgumentException ex){
                        System.out.println("Cursor is at at root!");
                    }

                    break;
                /**
                 * Case s: saves the file in tree format in a new file
                 */
                case "S":
                    try{
                        if(tree.getRoot() == null){
                            throw new IllegalArgumentException();
                        }
                    }catch (IllegalArgumentException ex) {
                        System.out.println("No tree");
                    }

                    System.out.println("Please enter a file name: ");
                    String file = scan.nextLine();

                    tree.writeToFile(tree, file);


                    break;

            }





        }



    }
}
