import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;

public class Trie {
    private LinkedList<TrieNode> trie;
    private TrieNode root;
    private String query;
    private ArrayList<String> words;
    private int maxDepth = 0;

    public Trie() {
        this.trie = new LinkedList<>();
        this.root = new TrieNode();
        this.trie.add(this.root);
        this.words = new ArrayList<>();
    }

    /**
     *
     * This function is used to build tree.
     * Inputfile should be read in this function in order to use testcases.
     *
     * @param inputfile
     * @throws Exception
     */
    public void buildTrie (String inputfile) throws Exception{
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(inputfile));
            String line = reader.readLine();
            query = line; //Query for processing

            line = reader.readLine();
            while(line != null) { //Read line-by-line
                this.insert(line, this.root);
                this.words.add(line);

                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * This function is used to traverse the trie and compute the autocomplete wordlist.
     * Output file should be produced here.
     *
     * @param outputfile
     * @throws Exception
     */
    public void autocomplete (String outputfile) throws Exception{
        FileWriter fw = new FileWriter(outputfile);

        if(search(query, root, 0) == -1) { //Query is not found in list
            return;
        }

        Collections.sort(this.words); //Sort words in alphabetical order for output

        for (int i = 0; i <= maxDepth; i++) { //Print out appropriate autocomplete words by weight then alphabetical order
            for (int j = 0; j < this.words.size();j++) {
                if(search(this.words.get(j), root, 0) == i) {
                    if(this.words.get(j).startsWith(query)) {
                        fw.write(this.words.get(j) + " " + i + "\n");
                    }
                }
            }
        }
        fw.close();
    }

    public void insert(String value, TrieNode node) {
        int index;
        String prefix = "";
        for (int i = 0; i < value.length(); i++) {
            index = value.charAt(i) - 'a';

            if(node.getChildren()[index] != null) { //Existing char value
                if(value.equals(node.getChildren()[index].getValue())) { //Value == node
                    return;
                }

                for (int j = 0; j < value.length(); j++) {
                    if (j == node.getChildren()[index].getValue().length()) { //value matches node, but value > node
                        insert(value.substring(j), node.getChildren()[index]); //Send rest of value back in
                        return;
                    }

                    if (value.charAt(j) != node.getChildren()[index].getValue().charAt(j)) { //value differs at certain character
                        if(!node.getChildren()[index].isWordEnd()) { //current node is not a leaf
                            node.getChildren()[index].getChildren()[node.getChildren()[index].getValue().charAt(j) - 'a'] = new TrieNode(node.getChildren()[index].getValue().substring(j), true);
                            for (int k = 0; k < 26; k++) { //Create new node and move previous children down
                                if(node.getChildren()[index].getChildren()[k] != null && k != 12) {
                                    node.getChildren()[index].getChildren()[node.getChildren()[index].getValue().charAt(j) - 'a'].getChildren()[k] = node.getChildren()[index].getChildren()[k];
                                    node.getChildren()[index].getChildren()[k] = null;
                                }
                            }

                            node.getChildren()[index].getChildren()[value.charAt(j) - 'a'] = new TrieNode(value.substring(j), true);
                            node.getChildren()[index].setValue(prefix);
                            maxDepth++;
                            return;
                        }
                        node.getChildren()[index].getChildren()[value.charAt(j) - 'a'] = new TrieNode(value.substring(j), true);
                        node.getChildren()[index].getChildren()[node.getChildren()[index].getValue().charAt(j) - 'a'] = new TrieNode(node.getChildren()[index].getValue().substring(j), true);
                        node.getChildren()[index].setValue(prefix);
                        node.getChildren()[index].setWordEnd(false);
                        maxDepth++;
                        return;
                    }

                    if(j+1 == value.length()) {
                        node.getChildren()[index].getChildren()[node.getChildren()[index].getValue().charAt(j+1) - 'a'] = new TrieNode(node.getChildren()[index].getValue().substring(j+1), true);
                        node.getChildren()[index].setValue(value);
                        node.getChildren()[index].setWordEnd(false);
                        maxDepth++;
                        return;
                    }

                    prefix += value.charAt(j);
                }
            }
            else { //starting character value has not been added previously
                boolean first = true;
                for (int j = 0; j < 26; j++) {
                    if(node.getChildren()[j] != null) {
                        first = false;
                    }
                }
                node.getChildren()[index] = new TrieNode(value, true);
                if(first) {
                    maxDepth++;
                }
                return;
            }
        }
    }

    public int search(String value, TrieNode node, int depth) { //Search for desired keyword, return depth of word
        int index;
        index = value.charAt(0) - 'a';

        if (node.getChildren()[index] != null) { //Existing char value
            if (value.equals(node.getChildren()[index].getValue()) || node.getChildren()[index].getValue().startsWith(value)) { //value matches current prefix
                return depth + 1;
            }

            for (int j = 0; j < value.length(); j++) {
                if (j == node.getChildren()[index].getValue().length()) { //value matches node, but value > node
                    return (search(value.substring(j), node.getChildren()[index], depth + 1));
                }

                if (value.charAt(j) != node.getChildren()[index].getValue().charAt(j)) { //value differs at certain character
                    return -1;
                }
            }

            return depth;
        } else { //value does not exist in trie
            return -1;
        }
    }
}
