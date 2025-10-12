package backtracking.q93;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {

    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        List<String> segments = new ArrayList<>();
        dfs("0000", 0, segments, result);
        System.out.println(result); // Output: ["0.0.0.0"]
    }

    private static void dfs(String s, int start, List<String> segments, List<String> result) {
        if (segments.size() == 4) {
            if (start == s.length()) {
                result.add(String.join(".", segments));
            }
            return;
        }

        for (int len = 1; len <= 3; len++) {
            if (start + len > s.length())
                break;

            String segment = s.substring(start, start + len);

            if (isValid(segment)) {
                segments.add(segment);
                dfs(s, start + len, segments, result);
                segments.remove(segments.size() - 1); 
            }
        }
    }

    private static boolean isValid(String segment) {
        if (segment.length() == 0 || segment.length() > 3)
            return false;
        if (segment.startsWith("0") && segment.length() > 1)
            return false;

        int value = Integer.parseInt(segment);
        return value >= 0 && value <= 255;
    }

}

// Complexity Analysis
// Time Complexity: O(1)
// The maximum number of segments is 4, and each segment can have a length of
// 1 to 3. Therefore, the total number of possible segments is limited, leading
// to a constant time complexity.   
