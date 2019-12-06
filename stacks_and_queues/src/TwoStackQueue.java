/*
 * TwoStackQueue: implements a queue data structure that supported by
 * two stack setup.*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TwoStackQueue<Item> {

    private LinkedListStack<Item> s1 = new LinkedListStack<Item>();
    private LinkedListStack<Item> s2 = new LinkedListStack<Item>();

    private int N = 0;

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void enqueue(Item item) {
        s1.push(item);
        N++;
    }

    public Item deque() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        Item item = s2.pop();
        N--;
        return item;
    }

    public static void main(String[] args) {

        TwoStackQueue<String> q = new TwoStackQueue<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                q.enqueue(item);
            } else {
                if (!q.isEmpty()) {
                    StdOut.print(q.deque() + " ");
                }
            }
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }

}
