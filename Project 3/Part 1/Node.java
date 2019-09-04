/**
 * The Node class is the class which will be used to store information in the Binary Search Tree. The key which will be
 * used to sort the data will be the name of the players, and their scores will be the data. Each node has a left and
 * right child which will either be their children nodes or null. If both the left and the right children of the current
 * node are leaf nodes, then the current node is a leaf node.
 */

enum Color {
    Red,Black;
}

public class Node {
    private Node left;
    private Node right;
    private String key;
    private int data;
    private Color color;

    /**
     * Constructor for the Node object taking a raw String and an int value.
     * Both children are initially null making this node a leaf node.
     * @param key String containing the name of the player from a Score object
     * @param data int with the value corresponding to the player's score
     */
    public Node(String key, int data, Color color){
        this.key = key;
        this.data = data;
        this.left = null;
        this.right = null;
        this.color = color;
    }

    /**
     * An alternative constructor which takes a Score object as a parameter.
     * It is not necessary that both constructors are used. Both children are
     * initially null making this node a leaf node.
     * @param score Score object containing a player's name and his/her/their score
     */
    public Node(Score score){
        this.key = score.getUid();
        this.data = score.getScore();
        this.left = null;
        this.right = null;
    }

    /**
     * Gets the left child of the current node
     * @return  the left child of the node
     */
    public Node getLeft(){
        return left;
    }

    /**
     * Gets the right child of the current node
     * @return  the right child of the node
     */
    public Node getRight() {
        return right;
    }

    /**
     * Gets the score of the player stored in this node
     * @return  the score of the player
     */
    public int getData() {
        return data;
    }

    /**
     *
     * @return  the name of the player stored in the node
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @return the color of the current Node
     */

    public Color getColor(){
        return color;
    }
    /**
     * Sets the left child of the current node
     * @param left  The left child node
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Sets the right child of the current node
     * @param right The right child node
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * Sets the score of the player stored in the node
     * @param data  the updated score
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * Sets the color of the current Node
     */
    public void setColor(Color color){
        this.color = color;
    }

}
