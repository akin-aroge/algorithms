package elementary_sort;

import edu.princeton.cs.algs4.StdIn;

public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;

        int h = 1;

        // using the Knuth sequence 3x+1
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // h-sort array
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (utils.less(a[j], a[j - h])) {
                        utils.exch(a, j, j - h);
                    }
                }
            }
            h = h / 3;
        }

    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert utils.isSorted(a);
        utils.show(a);
    }
}
