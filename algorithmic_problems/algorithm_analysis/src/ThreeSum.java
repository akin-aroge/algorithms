/*3-Sum  problem - Given N distinct integers, how many triples sum to exactly zero?

The naive algorithm which performs an exhaustive enumeration is O(n^3).
 * */

/*Solution - The solution provided attempts a O(n^2) solution (assuming assume that
 you can sort the n integers in time proportional to n^2 or better).

 Assuming a sorted array, the algorithm tests the triplets in an intelligent manner (bypassing
 the need to do a binary search. So it goes through each item (until the third to the last) and
 then progressively checks for the other two by checking from the left and right ends.
  */

public class ThreeSum {

    public static int count(int[] s) {

        int N = s.length;
        int count = 0;

        for (int i = 0; i < N - 3; i++) {

            int first = s[i];
            // pick the remaining two items from the two ends
            int k = i + 1;
            int l = N - 1;

            while (k < l) {
                int second = s[k];
                int third = s[l];

                if (first + second + third == 0) {
                    System.out.println(first + " " + second + " " + " " + third);
                    count++;
                    k = k + 1;
                    l = l - 1;
                } else if (first + second + third > 0) {
                    l = l - 1;

                } else {

                    k = k + 1;
                }
            }

        }

        return count;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{-4, -2, 0, 1, 2, 3, 4, 6};
        System.out.println(count(nums));
    }
}
