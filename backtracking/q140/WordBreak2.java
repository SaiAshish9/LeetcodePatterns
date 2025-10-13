package backtracking.q140;

import java.util.*;

class WordBreak2 {

    private static List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<>());
    }

    private static List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        if (s.length() == 0) {
            return Arrays.asList("");
        }

        List<String> res = new ArrayList<>();
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = dfs(s.substring(word.length()), wordDict, memo);
                for (String sub : sublist) {
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        memo.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

        List<String> result = wordBreak(s, wordDict);
        for (String sentence : result) {
            System.out.println(sentence);
        }
    }
}

// Complexity Analysis
// Time Complexity: O(n^3)
// Space Complexity: O(n^2) for the memoization map and recursion stack.