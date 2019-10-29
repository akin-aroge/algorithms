/*
 * Assignment 1: Percolation
 * Author:       Fabusuyi Akindele Aroge
 *
 * Data type, percolation models the percolation system and employs the WeightedQuickUnionUF.
 * This data type is required to use the Union-find algorithm to determine when a given N-by-N system percolates
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int trials;
    private final int n;
    private double[] thresholds;
    private double mean;
    private double stdev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int n,
                            int trials) {    // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        this.n = n;
        this.thresholds = new double[trials];

        performMonteCarlo();
    }

    private void performMonteCarlo() {
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            thresholds[i] = (double) p.numberOfOpenSites() / (n * n);
        }
        mean = StdStats.mean(thresholds);
        if (trials == 1) {
            stdev = Double.NaN;
        }
        else {
            stdev = StdStats.stddev(thresholds);
            ;
        }
        confidenceLo = mean - 1.96 * stddev() / Math.sqrt(trials);
        confidenceHi = mean + 1.96 * stddev() / Math.sqrt(trials);

    }

    public double mean() {                          // sample mean of percolation threshold
        return mean;
    }

    public double stddev() {                        // sample standard deviation of percolation threshold
        return stdev;
    }

    public double confidenceLo() {                  // low  endpoint of 95% confidence interval
        return confidenceLo;
    }

    public double confidenceHi() {                  // high endpoint of 95% confidence interval
        return confidenceHi;
    }

    public static void main(String[] args) {        // test client (described below)
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        //         int n = 200;
        //         int trials = 100;
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.printf("%% java-algs4 PercolationStats %d %d\n", n, trials);
        StdOut.printf("mean                     = %1.16f\n", ps.mean());
        StdOut.printf("stddev                   = %1.16f\n", ps.stddev());
        StdOut.printf("95%% confidence interval = %1.16f, %1.16f\n", ps.confidenceLo(),
                      ps.confidenceHi());
    }
}
