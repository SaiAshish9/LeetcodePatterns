package binarySearch.q69;

public class Sqrt {

    public static int mySqrt(int x) {
        if (x == 0)
            return 0;
        if (x == 1)
            return 1;
        int left = 0, right = x;
        int res = 0;

        while (left <= right) {
            int mid = left + (right - left + 1) / 2;
            if (mid <= x / mid) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }

}
