/**
 * This class creates newtwork nodes for the Nintendo Network that will be representing a tree structure
 * Each node has a name, two booleans for a nintendo device and whether its broken, a parent reference, a children array, and
 * a max capacity of children
 *
 * @author Brice Joseph, 114588946 R03
 */
public class NetworkNode {
    private String name;
    private boolean isNintendo;
    private boolean isBroken;
    private NetworkNode parent;
    private NetworkNode[] children;
    private final int MAXCHILDREN = 9;

    /**
     * Default constructor for the nodes
     * Post condition:
     * Sets all booleans to false and parent to null, also intializes the array of children
     */
    public NetworkNode(){

        isNintendo = false;
        isBroken = false;
        parent = null;
        children = new NetworkNode[MAXCHILDREN];

    }

    /**
     * A second constructor for the node
     * @param name
     * This will be used to set the name of the node
     * @param isBroken
     * This will be used to set the broken status of the node
     * Postcondtion:
     * A new node is created with a name and a broken/unbroken status
     */
    public NetworkNode(String name, boolean isBroken){

        this.name = name;
        this.isBroken = isBroken;
        parent = null;
        children  = new NetworkNode[MAXCHILDREN];
    }

    /**
     * Getter for the number of max children
     * @return
     * returns the max children for an array
     */
    public int getMAXCHILDREN() {
        return MAXCHILDREN;
    }

    /**
     * Getter for the children array
     * @param index
     * this represents the child at the given index
     * @return
     * returns the child at the given index of the array
     *
     */
    public NetworkNode getChildren(int index) {
        return this.children[index];
    }

    /**
     * Setter for the child array
     * @param index
     * Takes the index of the array where child will be stored
     * @param node
     * This is the node that is being inserted into the array
     */
    public void setChild(int index, NetworkNode node){
        this.children[index] = node;
    }

    /**
     * Getter for the parent reference of the node
     * @return
     * returns the parent of the node
     */
    public NetworkNode getParent() {
        return parent;
    }

    /**
     * Setter for the parent of the node
     * @param parent
     * Takes in a node that will be set to the parent
     */
    public void setParent(NetworkNode parent) {
        this.parent = parent;
    }

    /**
     * This method checks is the node is broken
     * @return
     * returns true or false depending on the status of the node
     */
    public boolean isBroken() {
        return isBroken;
    }

    /**
     * Setter for the broken status of the node
     * @param broken
     * This is the truth value of the node's broken status
     */
    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    /**
     * Checks if the node is nintendo
     * @return
     * returns true or false depepnding if the node is nintendo
     */
    public boolean isNintendo() {
        return isNintendo;
    }

    /**
     * Sets the boolean value for the node nintendo status
     * @param nintendo
     * Takes in a boolean for the node
     */
    public void setNintendo(boolean nintendo) {
        isNintendo = nintendo;
    }

    /**
     * Gets the name of the node
     * @return
     * Returns the name of the node
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the node
     * @param name
     * This is the name of the node
     */
    public void setName(String name) {
        this.name = name;
    }
}
