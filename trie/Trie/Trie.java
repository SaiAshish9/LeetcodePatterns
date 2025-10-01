package trie.Trie;

public class Trie {
    
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;
        
        TrieNode(){
            this.children = new TrieNode[26];
            this.isEndOfWord = false;
        }
    }
    
    private static void insert(String word, TrieNode root){
       TrieNode curr = root;
       char[] chars = word.toCharArray();
       for(char ch: chars){
           int index = ch - 'a';
           if(curr.children[index] == null){
               curr.children[index] = new TrieNode();
           }
           curr = curr.children[index];
       }
       curr.isEndOfWord = true;
    }
    
    private static boolean search(String word, TrieNode root){
       TrieNode curr = root;
       char[] chars = word.toCharArray();
       for(char ch: chars){
           int index = ch - 'a';
           if(curr.children[index] == null){
               return false;
           }
           curr = curr.children[index];
       }
       return curr.isEndOfWord && curr != null;
    }
    
        
    private static boolean startWith(String word, TrieNode root){
       TrieNode curr = root;
       char[] chars = word.toCharArray();
       for(char ch: chars){
           int index = ch - 'a';
           if(curr.children[index] == null){
               return false;
           }
           curr = curr.children[index];
       }
       return true;
    }

    public static void main(String ...s){
        TrieNode trie = new TrieNode();
        insert("apple", trie);
        System.out.println(search("app", trie)); // false
        System.out.println(search("apple", trie)); // true
        System.out.println(startWith("app", trie)); // true
    }
}
