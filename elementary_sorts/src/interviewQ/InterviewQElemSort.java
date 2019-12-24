package interviewQ;

/*
Intersection of two sets. Given two arrays a[] and b[], each containing
nn distinct 2D points in the plane, design a subquadratic algorithm to count
the number of points that are contained both in array a[] and array b[].

Implementation:
- sort the two arrays of points (using a sub-quadratic sort algorithm)
- step through the two arrays (smartly done since the arrays are now sorted)
    - the number of iteration at this stage is <=  max(a.length, b.length)

*/

import edu.princeton.cs.algs4.StdOut;
import elementary_sort.Shell;

public class InterviewQElemSort {

    public static int countIntersection(Comparable[] a, Comparable[] b) {

        int count = 0;
        int i = 0;
        int j = 0;

        Shell.sort(a);
        Shell.sort(b);

        while (i < a.length && j < b.length) {
            if (a[i].compareTo(b[j]) == 0) {
                count++;
                i++;
                j++;
            } else if (a[i].compareTo(a[j]) > 0) {
                j++;
            } else {
                i++;
            }
        }
        return count;
    }

    public static boolean isPerm(Comparable[] a, Comparable[] b) {

        Shell.sort(a);
        Shell.sort(b);

        int N_a = a.length;
        int N_b = b.length;

        if (N_a != N_b) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i].compareTo(b[i]) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Point2D a1 = new Point2D(0, 0);
        Point2D a2 = new Point2D(0, 1);
        Point2D a3 = new Point2D(1, 1);

        Point2D b1 = new Point2D(0, 0);
        Point2D b2 = new Point2D(0, 1);
        Point2D b3 = new Point2D(1, 2);

        Point2D[] a = new Point2D[3];
        Point2D[] b = new Point2D[3];

        a[0] = a1;
        a[1] = a2;
        a[2] = a3;

        b[0] = b1;
        b[1] = b2;
        b[2] = b3;

        int count = countIntersection(a, b);
        StdOut.println(count);


        StdOut.println(isPerm(a, b));

    }
}
