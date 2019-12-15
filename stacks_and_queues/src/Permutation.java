import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {


    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        // String str = StdIn.readString();
        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            q.enqueue(item);
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }

    }
}
