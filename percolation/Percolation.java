/*
 * Assignment 1: Percolation
 * Author:       Fabusuyi Akindele Aroge
 *
 * Data type, percolation models the percolation system and employs the WeightedQuickUnionUF.
 * This data type is required to use the Union-find algorithm to determine when a given N-by-N system percolates
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;  // the length of the n-by-n grid
    private final int gridsize;  // the number of sites in the grid
    private final int virtual_top_idx;  // virtual top
    private final int virtual_bottom_idx;  // virtual bottom
    private boolean isPercolated;  // the percolation state of the system - percolated or not
    private final WeightedQuickUnionUF qUF;  // weightedQuickUnionFind variable
    private boolean[] grid;  // grid open or not
    private int n_ofOpenSites;  // number of sites that are open


    //    create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("input should be greater than 1");
        }
        this.n = n;
        this.gridsize = n * n;
        this.grid = new boolean[gridsize];
        this.isPercolated = false;
        this.virtual_top_idx = 0;
        this.virtual_bottom_idx = gridsize + 1;
        this.qUF = new WeightedQuickUnionUF(gridsize + 2);
        this.n_ofOpenSites = 0;
    }

    // open site (row, col) if it is not open already
    public void open(int i, int j) {
        verifyIndex(i, j);

        if (!isOpen(i, j)) {
            grid[ijTo1D(i, j)] = true;  //open
            n_ofOpenSites++;

            //        if the site is at the top row, connect to virtual top
            if (i == 1) {
                qUF.union(ijTo1D(i, j), virtual_top_idx);

            }
            //        connect to virtual bottom if in the last row
            if (i == n) {
                qUF.union(ijTo1D(i, j), virtual_bottom_idx);
            }

            //        get the neighbours
            final int top;
            final int right;
            final int bottom;
            final int left;

            if (i == 1) {
                top = -1;
            }
            else {
                top = ijTo1D(i - 1, j);
            }
            if (j == n) {
                right = -1;
            }
            else {
                right = ijTo1D(i, j + 1);
            }
            if (i == n) {
                bottom = -1;
            }
            else {
                bottom = ijTo1D(i + 1, j);
            }
            if (j == 1) {
                left = -1;
            }
            else {
                left = ijTo1D(i, j - 1);
            }

            //            connect to open neighbours
            if (top != -1 && isOpen(i - 1, j)) {
                qUF.union(ijTo1D(i, j), top);
            }
            if (right != -1 && isOpen(i, j + 1)) {
                qUF.union(ijTo1D(i, j), right);
            }
            if (bottom != -1 && isOpen(i + 1, j)) {
                qUF.union(ijTo1D(i, j), bottom);
            }
            if (left != -1 && isOpen(i, j - 1)) {
                qUF.union(ijTo1D(i, j), left);
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int i, int j) {
        verifyIndex(i, j);
        return grid[ijTo1D(i, j)];
    }

    // is site (row, col) full?
    public boolean isFull(int i, int j) {
        verifyIndex(i, j);
        if (isOpen(i, j)) {
            return qUF.connected(ijTo1D(i, j), virtual_top_idx);
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return this.n_ofOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (!isPercolated) {
            isPercolated = qUF.connected(virtual_top_idx, virtual_bottom_idx);
        }
        return isPercolated;
    }

    //    map from a 2-dimensional (row, column) pair to a 1-dimensional union find object index
    private int ijTo1D(int i, int j) {
        return n * (i - 1) + j - 1;
    }

    private void verifyIndex(int i, int j) {
        if (i < 1 || i > n) {
            throw new IllegalArgumentException("row index  i out of bounds");
        }
        if (j < 1 || j > n) {
            throw new IllegalArgumentException("column index j out of bounds");
        }
    }

    public static void main(String[] args) {   // test client (optional)
        Percolation p = new Percolation(4);
        p.open(1, 2);
        p.open(1, 1);
        System.out.print(p.n_ofOpenSites);
    }
}
