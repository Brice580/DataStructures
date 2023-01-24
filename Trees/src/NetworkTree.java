import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class builds the tree for the Network nodes and the Nintendo network.
 * The tree has a root(head of list) and cursor as well as many useful methods such as add and cut and read from file/
 *
 * @author  Brice Joseph, R03, 114588946
 */
public class NetworkTree extends NetworkNode {


    private NetworkNode root;
    private NetworkNode cursor;

    /**
     * Default constructor where root and cursor are set to null and cursor is set to root
     * Postcondition:
     * Tree is created
     */
    public NetworkTree() {
        root = null;
        cursor = root;
    }

    /**
     * Getter for the cursor
     *
     * @return Returns the node the cursor is currenlty at
     */
    public NetworkNode getCursor() {
        return cursor;
    }

    /**
     * Sets the cursor to a node
     *
     * @param cursor Takes in the node and set cursor to that node
     */
    public void setCursor(NetworkNode cursor) {
        this.cursor = cursor;
    }

    /**
     * Gets the root of the tree
     *
     * @return Reutrns the root of the tree
     */
    public NetworkNode getRoot() {
        return root;
    }

    /**
     * Moves the cursor to the root of the tree
     * Postcondition:
     * Cursor is moved to the root
     */
    public void cursorToRoot() {

        try {
            if (root != null) {
                cursor = root;
            } else
                throw new NullPointerException();
        } catch (Exception ex) {
            System.out.println("Tree is empty!");
        }

    }

    /**
     * Cuts the cursor as well as its subtrees
     *
     * @return The cursor is cut and everything is was attached to was removed
     */
    public NetworkNode cutCursor() {
        try {
            if (cursor == null || root == null) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Cursor is null!");
        }
        NetworkNode saved = cursor;
        NetworkNode nodePTR = cursor;

        if (cursor == root) {
            for (int i = 0; i < getMAXCHILDREN(); i++) {
                cursor.setChild(i, null);
            }
        } else {
            cursor = cursor.getParent();
            for (int j = 0; j < getMAXCHILDREN() - 1; j++) {
                if (cursor.getChildren(j) == nodePTR) {
                    cursor.setChild(j, null);
                    for (int k = j; k < getMAXCHILDREN() - 1; k++) {
                        cursor.setChild(k, cursor.getChildren(k + 1));
                    }
                    break;
                }
            }

        }


        return saved;

    }

    /**
     * Adds a child into the tree
     *
     * @param index Takes the index in which the index in the array will be changed
     * @param node  Takes the node that will be inserted in said index
     * @throws IllegalArgumentException Throws this exception if the index is greater than the array size or creates a hole in the array
     *                                  Postcondition:
     *                                  Node is added
     */
    public void addChild(int index, NetworkNode node) throws IllegalArgumentException {

        try {
            if (index > cursor.getMAXCHILDREN() || (index != 0 && cursor.getChildren(index - 1) == null))
                throw new IllegalArgumentException();

            if (root == null) {
                root = node;
                cursor = root;
            } else if (cursor.getChildren(0) == null && index == 0) { //check for case with empty array

                cursor.setChild(index, node);
                node.setParent(cursor);
                cursor = node;
            } else if (cursor.getChildren(0) != null) {
                for (int i = 0; i < getMAXCHILDREN(); i++) {
                    if (i == index) {
                        if (cursor.getChildren(i + 1) == null && cursor.getChildren(i) == null) {
                            cursor.setChild(index, node);
                            node.setParent(cursor);
                            cursor = node;

                        }
                        if (cursor.getChildren(i + 1) == null && cursor.getChildren(i) != null) {
                            try {
                                for (int j = getMAXCHILDREN() - 1; j > index; j--) {


                                    cursor.setChild(j, cursor.getChildren(j - 1));

                                }
                                cursor.setChild(index, node);
                                node.setParent(cursor);
                                cursor = node;
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                System.out.println("Invalid Index!");
                            }

                        } else {
                            try {
                                for (int j = getMAXCHILDREN() - 1; j > index; j--) {


                                    cursor.setChild(j, cursor.getChildren(j - 1));

                                }
                                cursor.setChild(index, node);
                                node.setParent(cursor);
                                cursor = node;
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                System.out.println("Invalid Index!");
                            }

                        }


                    }
                }

            }


        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid index!");
        }
    }

    /**
     * Moves cursor to the child at given index
     *
     * @param index Takes in the index to move the cursor too
     *              Postcondition:
     *              Cursor is moved
     */
    public void cursorToChild(int index) {

        try {
            if (index > getMAXCHILDREN() || cursor.getChildren(index - 1) == null)
                throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid index!");
        }

        cursor = cursor.getChildren(index - 1);

    }

    /**
     * Moves the cursor to the Parent of the cursor
     * Postcondition:
     * Cursor is moved to parent
     */
    public void cursorToParent() {
        try {

            if (cursor == root) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Cursor is at root!");
        }

        cursor = cursor.getParent();


    }

    /**
     * A file with the tree specs is read and translated into a tree using many cases
     *
     * @param fileName Takes the file name of the file to read
     * @return Returns the tree that matches the details of the file
     */
    public NetworkTree readFromFile(String fileName) {


        File file = new File(fileName);

        try {

            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                if (!Character.isDigit(data.charAt(0)) && !Character.isDigit(data.charAt(1))) {
                    root = new NetworkNode(data, false);


                } else if (Character.isDigit(data.charAt(0)) && !Character.isDigit(data.charAt(1))) {


                    root.setChild(Character.getNumericValue(data.charAt(0)) - 1, new NetworkNode(data.substring(1), false));
                    root.getChildren(0).setParent(root);
                    for (int i = 0; i < getMAXCHILDREN(); i++) {
                        if (root.getChildren(i) != null) {
                            root.getChildren(i).setParent(root);
                        }
                    }


                } else if (Character.isDigit(data.charAt(1)) && !Character.isDigit(data.charAt(2))) {
                    if (data.charAt(0) == '1') {
                        NetworkNode temp = root.getChildren(0);
                        temp.setChild(Character.getNumericValue(data.charAt(1)) - 1,
                                (new NetworkNode(data.substring(2), false)));
                        root.getChildren(0).getChildren(Character.getNumericValue(data.charAt(1)) - 1).setParent(root.getChildren(0));
                    } else if (data.charAt(0) == '2') {
                        root.getChildren(1).setChild(Character.getNumericValue(data.charAt(1)) - 1, (
                                new NetworkNode(data.substring(2), false)));
                        root.getChildren(1).getChildren(Character.getNumericValue(data.charAt(1)) - 1).setParent(root.getChildren(1));

                    } else if (data.charAt(0) == '3') {
                        root.getChildren(2).setChild(Character.getNumericValue(data.charAt(1)) - 1, (
                                new NetworkNode(data.substring(2), false)));
                        root.getChildren(2).getChildren(Character.getNumericValue(data.charAt(1)) - 1).setParent(root.getChildren(2));

                    } else if (data.charAt(0) == '4') {
                        root.getChildren(3).setChild(Character.getNumericValue(data.charAt(1)) - 1,
                                (new NetworkNode(data.substring(2), false)));
                        root.getChildren(3).getChildren(Character.getNumericValue(data.charAt(1)) - 1).setParent(root.getChildren(3));

                    } else if (data.charAt(0) == '5') {
                        root.getChildren(4).setChild(Character.getNumericValue(data.charAt(1)) - 1,
                                (new NetworkNode(data.substring(2), false)));
                        root.getChildren(4).getChildren(Character.getNumericValue(data.charAt(1)) - 1).setParent(root.getChildren(4));

                    } else if (data.charAt(0) == '6') {
                        root.getChildren(5).setChild(Character.getNumericValue(data.charAt(1)) - 1,
                                (new NetworkNode(data.substring(2), false)));
                        root.getChildren(5).getChildren(Character.getNumericValue(data.charAt(1)) - 1).setParent(root.getChildren(5));

                    }


                } else if (Character.isDigit(data.charAt(2))) {
                    if (data.charAt(0) == '1') {
                        if (data.charAt(1) == '1') {
                            root.getChildren(0).getChildren(0).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(0).getChildren(0).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(0).getChildren(0));
                        } else if (data.charAt(1) == '2') {
                            root.getChildren(0).getChildren(1).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(0).getChildren(1).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(0).getChildren(1));

                        } else if (data.charAt(1) == '3') {
                            root.getChildren(0).getChildren(2).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(0).getChildren(2).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(0).getChildren(2));

                        } else if (data.charAt(1) == '4') {
                            root.getChildren(0).getChildren(2).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(0).getChildren(2).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(0).getChildren(3));

                        } else if (data.charAt(1) == '5') {
                            root.getChildren(0).getChildren(2).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(0).getChildren(2).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(0).getChildren(4));

                        } else if (data.charAt(1) == '6') {
                            root.getChildren(0).getChildren(2).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(0).getChildren(2).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(0).getChildren(5));

                        }

                    } else if (data.charAt(0) == '2') {
                        if (data.charAt(1) == '1') {
                            root.getChildren(1).getChildren(0).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(1).getChildren(0).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(1).getChildren(0));
                        } else if (data.charAt(1) == '2') {
                            root.getChildren(1).getChildren(1).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(1).getChildren(1).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(1).getChildren(1));

                        } else if (data.charAt(1) == '3') {
                            root.getChildren(1).getChildren(2).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(1).getChildren(2).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(1).getChildren(2));

                        } else if (data.charAt(1) == '4') {
                            root.getChildren(1).getChildren(2).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(1).getChildren(2).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(1).getChildren(3));

                        } else if (data.charAt(1) == '5') {
                            root.getChildren(1).getChildren(2).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(1).getChildren(2).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(1).getChildren(4));

                        } else if (data.charAt(1) == '6') {
                            root.getChildren(1).getChildren(2).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(1).getChildren(2).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(1).getChildren(5));

                        }

                    } else if (data.charAt(2) == '3') {
                        if (data.charAt(1) == '1') {
                            root.getChildren(2).getChildren(0).setChild(Character.getNumericValue(data.charAt(2) - 1), (
                                    new NetworkNode(data.substring(3), false)));
                            root.getChildren(2).getChildren(0).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(2).getChildren(0));
                        } else if (data.charAt(1) == '2') {
                            root.getChildren(2).getChildren(1).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(2).getChildren(1).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(2).getChildren(1));

                        } else if (data.charAt(1) == '3') {
                            root.getChildren(2).getChildren(2).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(2).getChildren(2).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(2).getChildren(2));

                        } else if (data.charAt(1) == '4') {
                            root.getChildren(2).getChildren(2).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(2).getChildren(2).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(2).getChildren(3));

                        } else if (data.charAt(1) == '5') {
                            root.getChildren(2).getChildren(2).setChild(Character.getNumericValue(data.charAt(2) - 1),
                                    (new NetworkNode(data.substring(3), false)));
                            root.getChildren(2).getChildren(2).getChildren
                                    (Character.getNumericValue(data.charAt(2) - 1)).setParent(root.getChildren(2).getChildren(4));

                        }


                    }


                }
            }
            System.out.println("File loaded!");
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return null;
    }

    /**
     * Writes the tree to a file in a readable format
     *
     * @param tree     Takes the tree that will be translated
     * @param fileName Takes the file name that will be created to store data
     *                 Postcondition:
     *                 Tree is written
     */
    public void writeToFile(NetworkTree tree, String fileName) {

        try {
            if (tree == null && tree.getRoot() != null)
                throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            System.out.println("Tree is null!");
        }

        try {
            File file = new File(fileName);
            PrintWriter writer = new PrintWriter(file);

            int count = 1;
            int count2 = 11;
            int countp = 11;
            int count3 = 111;


            writer.println(tree.getRoot().getName());

            for (int i = 0; i < getMAXCHILDREN(); i++) {
                if (root.getChildren(i) != null) {
                    String name2 = root.getChildren(i).getName();

                    writer.println(count + name2);
                    count++;
                    for (int j = 0; j < getMAXCHILDREN(); j++) {
                        if (root.getChildren(i).getChildren(j) != null) {
                            String name1 = root.getChildren(i).getChildren(j).getName();


                            if (count == 2) {
                                writer.println(count2 + name1);
                                countp++;
                            } else if (count == 3) {
                                count2 = count2 + 10;
                                writer.println(count2 + name1);

                            }


                            for (int k = 0; k < getMAXCHILDREN(); k++) {
                                if (root.getChildren(i).getChildren(j).getChildren(k) != null) {
                                    String name3 = root.getChildren(i).getChildren(j).getChildren(k).getName();

                                    writer.println(name3);
                                }
                            }

                        }
                    }
                }


            }


            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }


    }

    /**
     * Prints out the tree in a readable format with the cursor
     *
     * @return Returns the tree in a readable format
     */
    public String toString() {

        if (cursor == root) {
            System.out.println("->" + root.getName());
        } else {
            System.out.println(" " + root.getName());
        }

        for (int i = 0; i < getMAXCHILDREN(); i++) {
            if (root.getChildren(i) != null) {
                String name2 = root.getChildren(i).getName();
                if (cursor == root.getChildren(i))
                    System.out.println(" ->" + name2);
                else if (root.getChildren(i).isNintendo())
                    System.out.println(" -" + name2);
                else if (!root.getChildren(i).isNintendo())
                    System.out.println("  " + name2);
                for (int j = 0; j < getMAXCHILDREN(); j++) {
                    if (root.getChildren(i).getChildren(j) != null) {
                        String name1 = root.getChildren(i).getChildren(j).getName();
                        if (cursor == root.getChildren(i).getChildren(j))
                            System.out.println("   ->" + name1);
                        else if (root.getChildren(i).getChildren(j).isNintendo())
                            System.out.println("   -" + name1);
                        else if (!root.getChildren(i).getChildren(j).isNintendo())
                            System.out.println("    " + name1);
                        for (int k = 0; k < getMAXCHILDREN(); k++) {
                            if (root.getChildren(i).getChildren(j).getChildren(k) != null) {
                                String name3 = root.getChildren(i).getChildren(j).getChildren(k).getName();
                                if (cursor == root.getChildren(i).getChildren(j).getChildren(k))
                                    System.out.println("     ->" + name3);
                                else if (root.getChildren(i).getChildren(j).getChildren(k).isNintendo())
                                    System.out.println("      -" + name3);
                                else if (!root.getChildren(i).getChildren(j).getChildren(k).isNintendo())
                                    System.out.println("       " + name3);
                                for (int z = 0; z < getMAXCHILDREN(); z++) {
                                    if (root.getChildren(i).getChildren(j).getChildren(k).getChildren(z) != null) {
                                        String name4 = root.getChildren(i).getChildren(j).getChildren(k).getChildren(z).getName();
                                        if (cursor == root.getChildren(i).getChildren(j).getChildren(k).getChildren(z))
                                            System.out.println("        ->" + name4);
                                        else if (root.getChildren(i).getChildren(j).getChildren(k).getChildren(z).isNintendo())
                                            System.out.println("        -" + name4);
                                        else if (!root.getChildren(i).getChildren(j).getChildren(k).getChildren(z).isNintendo())
                                            System.out.println("         " + name4);
                                    }

                                }
                            }

                        }
                    }
                }


            }
        }
        return "";

    }
}
