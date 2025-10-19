package randomized.q384;

import java.util.Random;

public class ShuffleAnArray {

    private int[] original;
    private int[] array;
    private Random rand;

    public ShuffleAnArray(int[] nums) {
        original = nums.clone();
        array = nums.clone();
        rand = new Random();
    }

    public int[] reset() {
        array = original.clone();
        return array;
    }

    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            int swapIdx = i + rand.nextInt(array.length - i);
            int temp = array[i];
            array[i] = array[swapIdx];
            array[swapIdx] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
      int[] nums = {1, 2, 3, 4, 5};
      ShuffleAnArray shuffleAnArray = new ShuffleAnArray(nums); 
      int[] shuffled = shuffleAnArray.shuffle();
      System.out.println("Shuffled array: ");
      for (int num : shuffled) {
          System.out.print(num + " ");  
      }            
    }
    
}

// Your ShuffleAnArray object will be instantiated and called as such:
// ShuffleAnArray obj = new ShuffleAnArray(nums);
// int[] param_1 = obj.reset();
// int[] param_2 = obj.shuffle();

// Complexity Analysis
// Time Complexity:
// The time complexity for both the reset and shuffle methods is O(n), where n is the length of the array. The reset method involves copying the original array, and the shuffle method iterates
// through the array to perform the shuffling.
// Space Complexity:
// The space complexity is O(n) as well, since we are storing two arrays of size n: the original array and the current array.
