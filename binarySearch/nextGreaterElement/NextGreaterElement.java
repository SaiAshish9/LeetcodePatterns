package binarySearch.nextGreaterElement;

public class NextGreaterElement {

    private static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] result = nextGreaterElement(nums1, nums2);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
    
}
