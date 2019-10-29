/* *****************************************************************************
 *  Name:              Akin Aroge
 *  Coursera User ID:
 *  Last modified:     10/19/2019
 **************************************************************************** */

/* Union-find with specific canonical element. Add a method find() to the
 * union-find data type so that find(i) returns the largest element in the
 * connected component containing i. The operations, union(), connected(), and
 * find() should all take logarithmic time or better.
 *
 * For example, if one of the connected components is {1,2,6,9}, then the find()
 * method should return 9 for each of the four elements in the connected
 * components because 9 is larger 1, 2, and 6.
 * */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class WQUFWithFindLargest {

    private int[] id;
    private int[] size;
    private int[] maxMate;

    public WQUFWithFindLargest(int n) {
        id = new int[n];
        maxMate = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            maxMate[i] = i;
            size[i] = 1;
        }
    }

    public int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        if (rootP == rootQ) {
            return;
        }

        // Weighting: ensuring smaller component is connected to bigger
        if (size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];

            maxMate[rootQ] = Math.max(maxMate[rootP], maxMate[rootQ]);
        }
        else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];

            maxMate[rootP] = Math.max(maxMate[rootP], maxMate[rootQ]);
        }
    }

    public void validate(int i) {
        int nPoints = id.length;
        if (i < 0 || i >= nPoints) {
            throw new IndexOutOfBoundsException("index i should be between 0 "
                                                        + "and " + nPoints);
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int find(int i) {
        int rootI = root(i);
        return maxMate[rootI];
    }


    public static void main(String[] args) {
        int n = StdIn.readInt();

        WQUFWithFindLargest uf = new WQUFWithFindLargest(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            StdOut.println(p + " " + q);

            for (int i = 0; i < n; ++i) {
                StdOut.println("Max in the component containing " + i + " = " +
                                       uf.find(i));
            }
        }
    }
}
