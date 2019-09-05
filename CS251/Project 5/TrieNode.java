public class TrieNode {
    private String value;
    private TrieNode[] children;
    private boolean wordEnd;

    public TrieNode() {
        this.value = null;
        this.children = new TrieNode[26];
        for (int i = 0; i < this.children.length - 1; i++) {
            this.children[i] = null;
        }
        this.wordEnd = false;
    }

    public TrieNode(String value, boolean wordEnd) {
        this.value = value;
        this.children = new TrieNode[26];
        for (int i = 0; i < this.children.length - 1; i++) {
            this.children[i] = null;
        }
        this.wordEnd = wordEnd;
    }

    public String getValue() { return this.value; }

    public TrieNode[] getChildren() { return this.children; }

    public boolean isWordEnd() { return this.wordEnd; }

    public void setValue(String value) {
        this.value = value;
    }

    public void setWordEnd(boolean val) {
        this.wordEnd = val;
    }
}
