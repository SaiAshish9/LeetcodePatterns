package math.q927;

import java.util.ArrayList;
import java.util.List;

public class ThreeEqualParts {

    private static int[] threeEqualParts(int[] arr) {
        int totalOnes = 0;
        for (int num : arr) {
            if (num == 1)
                totalOnes++;
        }

        if (totalOnes % 3 != 0)
            return new int[] { -1, -1 };
        if (totalOnes == 0)
            return new int[] { 0, arr.length - 1 };

        List<Integer> onesIndices = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1)
                onesIndices.add(i);
        }

        int part = totalOnes / 3;
        int i1 = onesIndices.get(0);
        int i2 = onesIndices.get(part);
        int i3 = onesIndices.get(2 * part);

        while (i3 < arr.length &&
                arr[i1] == arr[i2] && arr[i2] == arr[i3]) {
            i1++;
            i2++;
            i3++;
        }

        if (i3 == arr.length)
            return new int[] { i1 - 1, i2 };
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        System.out.println(threeEqualParts(new int[] { 1, 0, 1, 0, 1 })); // [0,3]
        System.out.println(threeEqualParts(new int[] { 1, 1, 0, 1, 1 })); // [-1,-1]
        System.out.println(threeEqualParts(new int[] { 0, 0, 0, 0, 0 })); // [0,4]
    }

}

// Complexity Analysis
// Time Complexity: O(n), where n is the length of the input array arr. We
// traverse the array
// a constant number of times.
// Space Complexity: O(m), where m is the number of 1's in the input array arr.
// We store the indices of all 1's in a list.
