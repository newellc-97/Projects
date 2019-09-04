import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static String parseName(String line){
        if (line == null) {
            return null;
        }
        return line.split(" ")[1];
    }

    public static int parseScore(String line){
        if (line == null) {
            return -1;
        }
        return Integer.parseInt(line.split(" ")[2]);
    }

    public static String parseCommand(String line){
        if (line == null) {
            return null;
        }
        return line.split(" ")[0];
    }
    public static void main(String[] args) {
        String filename = args[0];
        BufferedReader reader;

        RedBlackTree bst = new RedBlackTree();
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null){

                String command = parseCommand(line);
                if (command.equals("i")) {
                    Score currentScore = new Score(parseName(line), parseScore(line));
                    bst.insertNode(currentScore);
                }
                else if (command.equals("treeheight")){
                    int treeHeight = bst.getTreeHeight();
                    if (treeHeight == -1) System.out.println("Tree is empty");
                    else System.out.println("Height of tree: " + treeHeight);
                }
                else if (command.equals("nodedepth")){
                    int nodeHeight = bst.getNodeDepth(parseName(line));
                    if (nodeHeight == -1) System.out.printf("%s not in tree\r\n", parseName(line));
                    else System.out.printf("Depth of %s: %d\r\n", parseName(line), nodeHeight);
                }
                else if (command.equals("search")){
                    Node foundNode = bst.searchNode(parseName(line));
                    if (foundNode == null){
                        System.out.printf("%s not in tree\r\n", parseName(line));
                    }
                    else System.out.printf("Score of %s: %d\r\n", parseName(line), foundNode.getData());
                }
                else if (command.equals("size")){
                    System.out.printf("Size of tree: %d\r\n", bst.getSize());
                }
                else if (command.equals("iot")){
                    bst.printInOrderTraversal();
                }
                else if (command.equals("validate")){
                    bst.validate();
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
