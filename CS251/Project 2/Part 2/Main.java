import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		String filename = args[0];
		BufferedReader reader;
		BinarySearchTree bst = new BinarySearchTree();

		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();

			while (line != null) {
				if(line.equals("size")) {
					System.out.println("Size of tree: " + bst.getSize());
				}
				else if(line.equals("treeheight")) {
					if (bst.getTreeHeight() == -1) {
						System.out.println("Tree is empty");
					}
					else {
						System.out.println("Height of tree: " + bst.getTreeHeight());
					}
				}
				else if(line.equals("iot")) {
					System.out.println("Begin in order traversal");
					bst.printInOrderTraversal();
					System.out.println("In order traversal complete");
				}
				else {
					String[] arr = line.split(" ");

					if (arr[0].equals("i")) {
						Score newScore = new Score(arr[1], Integer.parseInt(arr[2]));
						bst.insertNode(newScore);
					}
					else if(arr[0].equals("nodedepth")) {
						if(bst.getNodeDepth(arr[1]) == -1) {
							System.out.println(arr[1] + " not in tree");
						}
						else {
							System.out.println("Depth of " + arr[1] + ": " + bst.getNodeDepth(arr[1]));
						}
					}
					else if(arr[0].equals("search")) {
						if(bst.searchNode(arr[1]) == null) {
							System.out.println(arr[1] + " not in tree");
						}
						else {
							System.out.println("Score of " + arr[1] + ": " + bst.searchNode(arr[1]).getData());
						}
					}
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
