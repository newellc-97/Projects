/**
 * The RedBlackTree class contains only two fields: the root and the size. The size should be incremented with
 * addition of a node. The root is used as the initial reference point for all other nodes. The root is initially null
 * because the binary tree is initially empty. The first node which is inserted into the tree should take the place of
 * the root. This class is where a majority of the functions which are necessary to implement are located.
 */

public class RedBlackTree {
    private Node root;
    private int size = 0;

    /**
     * Constructor for a binary search tree. Root is initially null because the tree is empty.
     */
    public RedBlackTree(){
        root = null;
    }

    /**
     * Getter function for the root
     * @return  the root
     */
    public Node getRoot() {
        return root;
    }

    /**
     * rotate left around currentNode
     * @param currentNode
     * @return right child of currentNode
     */

    public Node rotateLeft(Node currentNode){

        Node rightChild = currentNode.getRight();
        Node childLeft = rightChild.getLeft();

        rightChild.setLeft(currentNode);
        currentNode.setRight(childLeft);

        return rightChild;
    }

    /**
     * rotate right around currentNode
     * @param currentNode
     * @return left child of currentNode
     */

    public Node rotateRight(Node currentNode){

        Node leftChild = currentNode.getLeft();
        Node childRight = leftChild.getRight();

        leftChild.setRight(currentNode);
        currentNode.setLeft(childRight);

        return leftChild;
    }

    /**
     *
     * @param first
     * @param second
     * swap colors of Nodes first and second
     */

    public void swapColors(Node first, Node second){

        Color temp = first.getColor();
        first.setColor(second.getColor());
        second.setColor(temp);
    }


    /**
     * Getter function for the tree height where the tree height is the maximum number of links from a leaf node
     * to the root. If the tree is empty, then the height is -1.
     * @return  the height of the tree or -1 if the tree is empty.
     */
    public int getTreeHeight(){
        return wrappedGetTreeHeight(root) - 1;
    }

    /**
     * Function returns the depth of a node where the depth is defined by the number of links
     * from the root. The depth of the root is 0. If the node is not in the tree than a depth
     * of -1 will be returned.
     * @param searchedKey The key of the node for which we are searching
     * @return The height of the node or -1 if the node is not in the tree
     */
    public int getNodeDepth(String searchedKey) {
        Node currentNode = root;
        int currentDepth = 0;
        if (searchedKey == null) return -1;
        while (currentNode != null) {
            if (currentNode.getKey().equals(searchedKey)) return currentDepth;
            if (currentNode.getKey().compareTo(searchedKey) > 0) {
                currentNode = currentNode.getLeft();
                currentDepth++;
                continue;
            } else {
                currentNode = currentNode.getRight();
                currentDepth++;
                continue;
            }
        }
        return -1;
    }

    /**
     * Utility function for making the necessary recursive calls to get the height of the tree
     * It is necessary to subtract one from this return value in the wrapper function.
     * @param currentNode The current node for any given recursive call
     * @return 1 more than the height of the tree
     */
    public int wrappedGetTreeHeight(Node currentNode){
        if (currentNode == null) return 0;
        else{
            int leftHeight = wrappedGetTreeHeight(currentNode.getLeft());
            int rightHeight = wrappedGetTreeHeight(currentNode.getRight());

            if (leftHeight < rightHeight){
                return rightHeight + 1;
            }
            else return leftHeight + 1;
        }
    }



    /**
     * Inserts a new node into the tree
     * @param newScore A Score Object containing the player name and score
     */
    public void insertNode(Score newScore) {
        root = wrappedInsertNode(root, newScore);
        size++;

    }

    /**
     * Utility function for making the necessary recursive calls to insert a node into the tree
     * @param newNode The current node at any given point in the recursive call
     * @param newScore The Score Object containing the information we want in our new Node
     * @return Return value only used for recursion, it is a child of the previous node
     */
    public Node wrappedInsertNode(Node newNode, Score newScore){
        if (newNode == null){
            newNode = new Node(newScore);
            return newNode;
        }
        else if (newNode.getKey().compareTo(newScore.getUid()) > 0){
            newNode.setLeft(wrappedInsertNode(newNode.getLeft(), newScore));
        }
        else if (newNode.getKey().compareTo(newScore.getUid()) < 0){
            newNode.setRight(wrappedInsertNode(newNode.getRight(), newScore));
        }
        else {
            newNode.setData(newScore.getScore());
            return newNode;
        }

        if(!rightRedLeftBlack(newNode)) {
            newNode = rotateLeft(newNode);
            swapColors(newNode, newNode.getLeft());
        }
        else if(!grandchildCheck(newNode)) {
            newNode = rotateRight(newNode);
            swapColors(newNode, newNode.getRight());
        }
        else if(!doubleRed(newNode)) {
            if(newNode.getColor() == Color.Black) {
                newNode.setColor(Color.Red);
            }
            else if(newNode.getColor() == Color.Red) {
                newNode.setColor(Color.Black);
            }
            newNode.getLeft().setColor(Color.Black);
            newNode.getRight().setColor(Color.Black);
        }

        return newNode;
    }

    /**
     * A function which returns the Node who's key is given as a parameter
     * @param key Name of the player contained in the node we are searching for
     * @return The node containing that player's name
     */
    public Node searchNode(String key){
        return wrappedSearchNode(root, key);
    }

    /**
     * Utility function that makes the necessary recursive calls to find the node whose key is given as a parameter
     * @param currentNode The current node at any given recursive call
     * @param key The name of the player whose node we are searching for
     * @return the node containing the player with the name given by the key
     */
    public Node wrappedSearchNode(Node currentNode, String key) {
        if (key == null) return null;
        if (currentNode == null || currentNode.getKey().equals(key)) return currentNode;
        if (currentNode.getKey().compareTo(key) > 0){
            return wrappedSearchNode(currentNode.getLeft(), key);
        }
        return wrappedSearchNode(currentNode.getRight(), key);
    }

    /**
     * Prints out the inorder traversal of the tree using the utility function InOrderTraversal
     */
    public void printInOrderTraversal(){
        System.out.println("Begin in order traversal");
        InOrderTraversal(root);
        System.out.println("In order traversal complete");
    }
    /**
     * Utility function for printInorderTraversal of the binary tree to standard out. If the tree was implemented correctly, then this
     * should be in alphabetical order.
     * @param currentNode The starting node used for recursive calls, usually the root
     */
    public void InOrderTraversal(Node currentNode){
        if (currentNode != null){
            InOrderTraversal(currentNode.getLeft());
            System.out.println(currentNode.getKey());
            InOrderTraversal(currentNode.getRight());
        }
    }

    /**
     * A getter function for the size of the tree
     * @return the size of the tree
     */
    public int getSize() {
        return size;
    }

    public boolean rootCheck(){

        if(root.getColor() != Color.Black){
            root.setColor(Color.Black);
        }
        return true;
    }

    //test that both children of each red node are black
    public boolean internalCheck(Node root){

        if(root == null) return true;

        Node left = root.getLeft();
        Node right = root.getRight();

        if(root.getColor() == Color.Red){
            if(left != null && left.getColor() == Color.Red || (right != null && right.getColor() == Color.Red)){
                return false;
            }
        }

        return internalCheck(left) && internalCheck(right);
    }

    //Tests if a node has a red right child and left black child
    public boolean rightRedLeftBlack(Node root) {

        if (root == null) return true;

        Node left = root.getLeft();
        Node right = root.getRight();
        //If both children are leaves, the subtree is valid
        if (left == null && right == null) {
            return true;
        }

        if (right != null && right.getColor() == Color.Red && (left == null || left.getColor() == Color.Black)) {
            return false;
        }
        else {
            return rightRedLeftBlack(left) && rightRedLeftBlack(right);
        }
    }

    //Tests if a node has a red left child and a red left grandchild
    public boolean grandchildCheck(Node root){

        if (root == null) return true;

        Node left = root.getLeft();
        Node right = root.getRight();

        if (left == null && right == null){
            return true;
        }

        if(left != null && left.getColor() == Color.Red &&
                left.getLeft() != null && left.getLeft().getColor() == Color.Red ){
            return false;
        }
        else {
            return grandchildCheck(left) && grandchildCheck(right);
        }
    }

    //Tests if a node's children are both red
    public boolean doubleRed(Node root){

        if(root == null) return true;

        Node left = root.getLeft();
        Node right = root.getRight();

        if(left == null && right == null){
            return true;
        }

        if(left != null && left.getColor() == Color.Red && right != null && right.getColor() == Color.Red){
            return false;
        }
        else {
            return doubleRed(left) && doubleRed(right);
        }

    }

    /*
    Utility function to ensure the tree is valid
     */
    public boolean validate(){

        if(!rootCheck()){
            System.out.println("Root isn't black");
            return false;
        }
        if(!internalCheck(root)){
            System.out.println("A double red was found!");
            return false;
        }

        if(!rightRedLeftBlack(root)){
            System.out.println("A node has a right red child and a black left child");
            return false;
        }

        if(!grandchildCheck(root)){
            System.out.println("A node has a red left child and a red left grandchild");
        }

        if(!doubleRed(root)){
            System.out.println("A node's children are both red");
        }

        System.out.println("Tree is valid");
        return true;
    }
}
