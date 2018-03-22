class TrieNode {
    char c;
    String s;
    boolean isString;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();

    TrieNode () {}
    TrieNode (char c) {
        this.c = c;
    }
}

public class WordDictionary {
    TrieNode root;

    WordDictionary() {
        root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode curr = root;
        HashMap<Character, TrieNode> currChildren = curr.children;
        char[] wordArr = word.toCharArray();
        for (char c : wordArr) {
            if (currChildren.containsKey(c)) {
                curr = currChildren.get(c);
            } else {
                TrieNode newNode = new TrieNode(c);
                currChildren.put(c, newNode);
                curr = newNode;
            }
            currChildren = curr.children;
        }
        curr.s = word;
        curr.isString = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return search(word, root, 0);

    }

    private boolean search(String word, TrieNode root, int start) {
        if (start == word.length()) {
            return root.isString;
        }

        char c = word.charAt(start);
        if (root.children.containsKey(c)) {
            return search(word, root.children.get(c), start + 1);
        } else if (c == '.') {
            for (char key : root.children.keySet()) {
                if (search(word, root.children.get(key), start + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
