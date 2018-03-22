/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
class TrieNode {

    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    char c;
    String s;   // The word generated from root. isString is True
    boolean isString;

    public TrieNode() {}

    public TrieNode(char c) {
        this.c = c;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode curr = root;
        HashMap<Character, TrieNode> currChildren = curr.children;
        char[] wordArr = word.toCharArray();
        for (int i = 0; i < wordArr.length; i++) {
            char c = wordArr[i];
            if (currChildren.containsKey(c)) {
                curr = currChildren.get(c);
            } else {
                TrieNode newNode = new TrieNode(c);
                currChildren.put(c, newNode);
                curr = newNode;
            }
            currChildren = curr.children;
        }
        curr.isString = true;
        curr.s = word;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode pos = searchWordNodePos(word);
        if (pos == null) {
            return false;
        }
        return pos.isString;

    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode pos = searchWordNodePos(prefix);
        return pos != null;
    }

    private TrieNode searchWordNodePos(String s){
        char[] sArr = s.toCharArray();
        TrieNode curr = root;
        HashMap<Character, TrieNode> currChildren = curr.children;

        for (char c : sArr) {
            if (currChildren.containsKey(c)) {
                curr = currChildren.get(c);
                currChildren = curr.children;
            } else {
                return null;
            }
        }
        return curr;
    }
}
