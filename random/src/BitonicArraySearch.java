/* Search in a bitonic array. An array is bitonic if it is comprised of an increasing
sequence of integers followed immediately by a decreasing sequence of integers. Write
a program that, given a bitonic array of nn distinct integer values, determines whether
a given integer is in the array.

Standard version: Use ∼3lgn compares in the worst case.
Signing bonus: Use ∼2lgn compares in the worst case (and prove that no algorithm can
guarantee to perform fewer than ∼2lgn compares in the worst case).
 * */

/*
 * solution - https://stackoverflow.com/questions/19372930/given-a-bitonic-array-and-element-x-in-the-array-find-the-index-of-x-in-2logn*/

import java.util.Arrays;

public class BitonicArraySearch {

    public static boolean inArray(int[] array, int value) {

        int N = array.length;

        if (N == 0) {
            return false;
        }

        if (N == 1) {
            return array[0] == value;
        }

        if (N <= 3) {
            for (int item : array) {
                if (item == value) {
                    return true;
                }
            }
            return false;
        }

        int mid = N / 2 - 1;

        if (array[mid] == value) {
            return true;
        }

        // get neighbours
        int mid_right = array[mid + 1];
        int mid_left = array[mid - 1];

        if (array[mid] < value && mid_left > array[mid]) {
            return inArray(Arrays.copyOfRange(array, 0, mid), value);
        } else if (array[mid] < value && mid_right > array[mid]) {
            return inArray(Arrays.copyOfRange(array, mid + 1, N), value);
        }

        if (array[mid] > value) {
            // we have two array to check (on either side of mid
            boolean leftBinSearch = binarySearch(Arrays.copyOfRange(array, 0, mid), value);
            boolean rightBinSearch = binarySearch(Arrays.copyOfRange(array, mid + 1, N), value);

            return leftBinSearch || rightBinSearch;

        }

        return false;

    }

    public static boolean binarySearch(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 5, 4, 1};
        System.out.println(inArray(nums, 5));
    }
}
