package backtracking.q126;

import java.util.*;

public class WordLadder2 {
    private static boolean isOneLetterDifferent(String word1, String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
                if (diff > 1) return false;
            }
        }
        return diff == 1;
    }

    private static void bfs(List<List<Integer>> adjList, List<List<Integer>> predecessors, int start, int end) {
        int n = adjList.size();
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        predecessors.get(start).add(-1);
        distance[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : adjList.get(current)) {
                if (distance[neighbor] > distance[current] + 1) {
                    distance[neighbor] = distance[current] + 1;
                    queue.offer(neighbor);
                    predecessors.get(neighbor).clear();
                    predecessors.get(neighbor).add(current);
                } else if (distance[neighbor] == distance[current] + 1) {
                    predecessors.get(neighbor).add(current);
                }
            }
        }
    }

    private static void dfs(List<List<String>> result, List<String> path, List<List<Integer>> predecessors,
                     int currentIndex, List<String> wordList) {
        if (currentIndex == -1) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            result.add(validPath);
            return;
        }
        for (int prev : predecessors.get(currentIndex)) {
            path.add(wordList.get(currentIndex));
            dfs(result, path, predecessors, prev, wordList);
            path.remove(path.size() - 1);
        }
    }

    private static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();
        int startIndex = -1, endIndex = -1;

        for (int i = 0; i < n; i++) {
            if (wordList.get(i).equals(beginWord)) startIndex = i;
            if (wordList.get(i).equals(endWord)) endIndex = i;
        }

        if (endIndex == -1) return new ArrayList<>();

        if (startIndex == -1) {
            wordList.add(0, beginWord);
            startIndex = 0;
            endIndex++;
            n++;
        }

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isOneLetterDifferent(wordList.get(i), wordList.get(j))) {
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }

        List<List<Integer>> predecessors = new ArrayList<>();
        for (int i = 0; i < n; i++) predecessors.add(new ArrayList<>());

        bfs(adjList, predecessors, startIndex, endIndex);

        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(result, path, predecessors, endIndex, wordList);

        return result;
    }
    
    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));

        List<List<String>> ladders = findLadders(beginWord, endWord, wordList);

        if (ladders.isEmpty()) {
            System.out.println("No transformation sequence found.");
        } else {
            System.out.println("All shortest transformation sequences:");
            for (List<String> sequence : ladders) {
                System.out.println(sequence);
            }
        }
    }
}

// Complexity Analysis
// Time Complexity: O(N * M^2 + V + E), where N is the number of words in the wordList, M is the length of each word, V is the number of vertices, and E is the number of edges in the graph.
// Space Complexity: O(N^2) for the adjacency list and predecessors list.   