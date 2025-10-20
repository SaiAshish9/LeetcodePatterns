package greedy.boatsToSavePeople;

import java.util.Arrays;

public class BoatsToSavePeople {
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;
        int boats = 0;
        
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++; // Lightest person is saved along with the heaviest
            }
            right--; 
            boats++; 
        }
        return boats;
    }

    public static void main(String[] args) {
        int[] people = {1, 2, 3, 4};
        int limit = 5;
        int result = numRescueBoats(people, limit);
        System.out.println("Minimum number of boats needed: " + result);
    }
}

// Time Complexity: O(n log n) due to sorting
// Space Complexity: O(1)