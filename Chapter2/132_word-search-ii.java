public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        ArrayList<String> ans = new ArrayList<String>();
        if (board == null || board.length == 0) {
            return ans;
        }
        if (board[0] == null || board[0].length == 0) {
            return ans;
        }
        if (words == null || words.size() == 0) {
            return ans;
        }

        // Create a trie tree of words
        Trie tree = new Trie();
        for (String word : words) {
            tree.insert(word);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                search(board, i, j, tree.root, ans);
            }
        }

        return ans;
    }

    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};

    private void search(char[][] board, int i, int j, TrieNode root, ArrayList<String> ans) {
        if (root.isString) {
            if (!ans.contains(root.s)) {
                ans.add(root.s);
            }
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return ;
        }

        if (!root.children.containsKey(board[i][j])) {
            return ;
        }

        root = root.children.get(board[i][j]);
        char temp = board[i][j];
        board[i][j] = 'X';
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            search(board, x, y, root, ans);
        }
        board[i][j] = temp;
    }
}


class TrieNode {

    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    char c;
    String s;
    boolean isString;

    public TrieNode() {}

    public TrieNode(char c) {
        this.c = c;
    }
}

class Trie {
    TrieNode root;

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
}
