/* Stack implementation using a resizing array
 * Resizing ensures the capacity is changed (increased or decreased) as
 * push and pop operations proceed.
 *
 * This also implements and iterator interface to provide an iteration
 * functionality
 * */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ResizingArrayStack<Item> {

    private Item[] a;
    private int N = 0;

    public ResizingArrayStack() {
        a = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int newSize) {
        Item[] temp = (Item[]) new Object[newSize];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    // Implementing iteration interface
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return a[--i];
        }
    }

    public static void main(String[] args) {

        ResizingArrayStack<String> q = new ResizingArrayStack<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                q.push(item);
            } else {
                if (!q.isEmpty()) {
                    StdOut.print(q.pop() + " ");
                }
            }
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}
