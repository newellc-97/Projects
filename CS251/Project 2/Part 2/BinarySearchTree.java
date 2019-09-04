/**
 * The binary search tree class contains only two fields: the root and the size. The size should be incremented with
 * addition of a node. The root is used as the initial reference point for all other nodes. The root is initially null
 * because the binary tree is initially empty. The first node which is inserted into the tree should take the place of
 * the root. This class is where a majority of the functions which are necessary to implement are located.
 */

public class BinarySearchTree {
    private Node root;
    private int size = 0;

    /**
     * Constructor for a binary search tree. Root is initially null because the tree is empty.
     */
    public BinarySearchTree(){
        root = null;
    }

    /**
     * Getter function for the tree height where the tree height is the maximum number of links from a leaf node
     * to the root. If the tree is empty, then the height is -1.
     * @return  the height of the tree or -1 if the tree is empty.
     */
    public int getTreeHeight(){
        if(this.getSize() == 0) {
            return -1;
        }
        else {
            return(getTreeHeightRec(this.root) - 1);
        }

    }

    public int getTreeHeightRec(Node node) {
        if(node == null) {
            return 0;
        }
        else {
            int leftHeight = getTreeHeightRec(node.getLeft());
            int rightHeight = getTreeHeightRec(node.getRight());

            if(leftHeight > rightHeight) {
                return (leftHeight + 1);
            }
            else {
                return (rightHeight + 1);
            }
        }
    }

    /**
     * Function returns the depth of a node where the depth is defined by the number of links
     * from the root. The depth of the root is 0. If the node is not in the tree than a depth
     * of -1 will be returned.
     * @param searchedKey The key of the node for which we are searching
     * @return The height of the node or -1 if the node is not in the tree
     */
    public int getNodeDepth(String searchedKey) {
        if(this.searchNode(searchedKey) == null) {
            return -1;
        }

        Node rootNode = this.root;
        return getNodeDepthRec(searchedKey, rootNode, 0);
    }

    public int getNodeDepthRec(String key, Node node, int depth) {
        if(node == null) {
            return 0;
        }
        else if(node.getKey().equals(key)) {
            return depth;
        }

        int newDepth = getNodeDepthRec(key, node.getLeft(), depth+1);
        if(newDepth != 0) {
            return newDepth;
        }
        newDepth = getNodeDepthRec(key, node.getRight(), depth+1);

        return newDepth;
    }

    /**
     * Inserts a new node into the tree
     * @param newScore A Score Object containing the player name and score
     */
    public void insertNode(Score newScore) {
        this.root = insertNodeRec(newScore, this.root);
        this.size++;
    }

    public Node insertNodeRec(Score newScore, Node root) {
        if(root == null) {
            root = new Node(newScore);
            return root;
        }

        if(newScore.getUid().compareToIgnoreCase(root.getKey()) < 0) {
            root.setLeft(insertNodeRec(newScore, root.getLeft()));
        }
        else if(newScore.getUid().compareToIgnoreCase(root.getKey()) > 0) {
            root.setRight(insertNodeRec(newScore, root.getRight()));
        }
        else if(newScore.getUid().compareToIgnoreCase(root.getKey()) == 0) {
            root.setData(newScore.getScore());
            this.size--;
        }

        return root;
    }

    /**
     * A function which returns the Node who's key is given as a parameter
     * @param key Name of the player contained in the node we are searching for
     * @return The node containing that player's name
     */
    public Node searchNode(String key){
        Node rootNode = this.root;

        while (rootNode != null) {
            if (rootNode.getKey().compareToIgnoreCase(key) < 0) {
                rootNode = rootNode.getRight();
            }
            else if (rootNode.getKey().compareToIgnoreCase(key) > 0) {
                rootNode = rootNode.getLeft();
            }
            else
                return rootNode;
        }
        return null;
    }

    /**
     * Prints out the inorder traversal of the tree using the utility function InOrderTraversal
     */
    public void printInOrderTraversal(){
        printInOrderTraversalRec(this.root);
    }

    public void printInOrderTraversalRec(Node node) {
        if(node != null) {
            Node left = node.getLeft();
            Node right = node.getRight();

            printInOrderTraversalRec(left);
            System.out.println(node.getKey());
            printInOrderTraversalRec(right);
        }
    }

    /**
     * A getter function for the size of the tree
     * @return the size of the tree
     */
    public int getSize() {
        return this.size;
    }
}
