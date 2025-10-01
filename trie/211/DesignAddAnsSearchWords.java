import java.util.*;

public class DesignAddAnsSearchWords {

    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode() {
            this.children = new TrieNode[26];
            this.isEndOfWord = false;
        }
    }

    private static void insert(TrieNode root, String word) {
        TrieNode curr = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }

            curr = curr.children[index];
        }

        curr.isEndOfWord = true;
    }

    private static boolean search(TrieNode root, String word) {
        TrieNode curr = root;

        for (char ch : word.toCharArray()) {
            int index = ch - 'a';

            if (curr.children[index] == null) {
                return false;
            }

            curr = curr.children[index];
        }

        return curr.isEndOfWord && curr != null;
    }

    private static boolean dfs(TrieNode node, String word, int index) {
        if (node == null) {
            return false;
        }

        if (index == word.length()) {
            return true;
        }

        char ch = word.charAt(index);

        if (ch == '.') {

            for (int i = 0; i < 26; i++) {
                if (dfs(node.children[i], word, index + 1)) {
                    return true;
                }
            }

            return false;

        } else {
            int pos = ch - 'a';
            return dfs(node.children[pos], word, index + 1);
        }

    }

    private static boolean searchWord(TrieNode root, String word) {
        if (word.contains(".")) {
            return dfs(root, word, 0);
        } else {
            return search(root, word);
        }
    }

    public static void main(String... s) {
        List<String> words = Arrays.asList("bad", "dad", "mad");

        List<String> wordsToSearch = Arrays.asList(
                "pad", "bad", ".ad", "b..");
        TrieNode node = new TrieNode();

        for (String word : words) {
            insert(node, word);
        }

        for (String word : wordsToSearch) {
            System.out.println(searchWord(node, word));
        }
    }

}