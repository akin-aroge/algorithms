/* *****************************************************************************
 *  Name:              F. Akindele Aroge
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

/* Given a set of N integers S={0,1,...,N−1} and a sequence of requests of the
following form:
  - Remove x from S
  - Find the successor of x: the smallest y in S such that y≥x.
  */

/* Approach:
 * The union-find data structure is employed assign successor of each integer.
 * With an integer removed, the integer is connected to the next data in the
 * list (if available) */

import java.util.Arrays;

public class SuccessorWithDelete {

    private int[] id;
    private int[] size;
    private int[] maxMate;

    public SuccessorWithDelete(int n) {
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

    public void remove(int p) {
        if (p == id.length - 1) {
            id[p] = p;  // behaviour when last digit is removed?
        }
        else {
            union(p, p + 1);
        }
    }

    public int successor(int p) {

        // use find function (not the root function because the root function
        // may be wrong due to weighting, such that the max is not at the root)
        return find(p);  //
    }


    public static void main(String[] args) {
        // int n = StdIn.readInt();
        int n = 5;

        SuccessorWithDelete swd = new SuccessorWithDelete(n);
        System.out.println(swd.successor(n - 1));

        swd.remove(1);
        // swd.remove(2);
        swd.remove(3);
        swd.remove(4);


        System.out.println(swd.successor(0));
        System.out.println(swd.successor(1));
        System.out.println(swd.successor(2));
        System.out.println(swd.successor(3));
        System.out.println(Arrays.toString(swd.id));


    }
}
