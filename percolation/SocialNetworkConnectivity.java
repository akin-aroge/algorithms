/*
 * Social Netwok Connectivity - Given a social network containing N members and a log file containing M
 * timestamps at which times pairs of members formed friendships, design an
 * algorithm to determine the earliest time at which all members are connected
 * (i.e., every member is a friend of a friend of a friend ... of a friend).
 * Assume that the log file is sorted by timestamp and that friendship is an
 * equivalence relation. The running time of your algorithm should be MlogN or
 * better and use extra space proportional to N.
 * */

/*
 * Solution Brief - This solution employs the union-find data structure, with
 * each of the points in the data structure represents a network member. SInce
 * the log fiel is ordered, the unions are added in that order. After every union,
 * the number of connected components of the union-find data structure is
 * checked. The members are all connected if there is only one component of the
 * union-find points. */

/* key modifications:
 * - the number of connected connected components is tracked.
 **/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

class SocialNetworkConnectivity {
    private WeightedQuickUnionUF uf;
    private int numComponents;

    public SocialNetworkConnectivity(int n) {
        uf = new WeightedQuickUnionUF(n);
        numComponents = n;
    }

    public void beFriend(int f1, int f2) {
        if (!uf.connected(f1, f2)) {
            numComponents--;
            // uf.union(f1, f2);
        }
        uf.union(f1, f2);
    }

    public boolean isFullyConnected() {
        return this.numComponents == 1;
    }

    public static void main(String[] args) {

        int n = StdIn.readInt();
        SocialNetworkConnectivity soc = new SocialNetworkConnectivity(n);

        int count = 0;
        int timeOfFullConnect = 0;
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            soc.beFriend(p, q);
            count++;

            // get count value as soon as full connection is established
            // second condition ensures value is only changed once
            if (soc.isFullyConnected() && timeOfFullConnect == 0) {
                timeOfFullConnect = count;
            }

            // todo: how about when the logs do not achieve full connection?

        }
        StdOut.println(timeOfFullConnect);
    }

}
