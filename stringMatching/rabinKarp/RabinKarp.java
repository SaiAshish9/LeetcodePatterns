package stringMatching.rabinKarp;

public class RabinKarp {
    static final int ALPHABET_SIZE = 256; // Number of characters in the alphabet (ASCII)
    static final int PRIME_MOD = 101;    // A prime number for modulo operation

    static void searchPattern(String pattern, String text) {
        int patternLength = pattern.length();
        int textLength = text.length();
        int patternHash = 0; // Hash value for pattern
        int windowHash = 0; // Hash value for current window of text
        int highOrderMultiplier = 1; // The value of pow(d, M-1) % q
        
        // Precompute (ALPHABET_SIZE^(patternLength-1)) % PRIME_MOD
        for (int i = 0; i < patternLength - 1; i++) {
            highOrderMultiplier = (highOrderMultiplier * ALPHABET_SIZE) % PRIME_MOD;
        }
        
        // Calculate the initial hash value for pattern and first window of text
        for (int i = 0; i < patternLength; i++) {
            patternHash = (ALPHABET_SIZE * patternHash + pattern.charAt(i)) % PRIME_MOD;
            windowHash = (ALPHABET_SIZE * windowHash + text.charAt(i)) % PRIME_MOD;
        }
        
        // Slide the window over the text
        for (int i = 0; i <= textLength - patternLength; i++) {
            // Check the hash values
            if (patternHash == windowHash) {
                boolean exactMatch = true;
                // Check characters one-by-one if hashes match
                for (int j = 0; j < patternLength; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        exactMatch = false;
                        break;
                    }
                }
                if (exactMatch) {
                    System.out.println("Pattern found at index " + i);
                } else {
                    System.out.println("Spurious hit at index " + i);
                }
            }
            
            // Calculate hash for the next window
            if (i < textLength - patternLength) {
                windowHash = (ALPHABET_SIZE * (windowHash - text.charAt(i) * highOrderMultiplier) + text.charAt(i + patternLength)) % PRIME_MOD;
                // Ensure the hash value is positive
                if (windowHash < 0) {
                    windowHash += PRIME_MOD;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "ADABCACADAGBAGA"; 
        String pattern = "ADAB";
        searchPattern(pattern, text);
    }
}

// Spurious hit: False Alarm
// Hash match but characters differ

// Pattern found at index 0
// Pattern found at index 6

/*
 * Complexity Analysis:
Time Complexity:
Average case: O(n + m) where n is text length, m is pattern length
Worst case: O(nm) when many spurious hits occur
Space Complexity: O(1)
 */