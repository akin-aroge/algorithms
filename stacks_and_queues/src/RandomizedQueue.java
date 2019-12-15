import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int N;
    private Item[] a;

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[1];
    }

    private void resize(int cap) {
        Item[] temp = (Item[]) new Object[cap];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }

        a = temp;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {

        if (N == 0) {
            throw new NoSuchElementException();
        }

        int idx = StdRandom.uniform(N);
        Item item = a[idx];
        a[idx] = a[--N];
        a[N] = null;

        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniform(N);

        return a[idx];
    }


    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        int size;
        int[] idxs;

        private RandomizedQueueIterator() {

            size = N;
            idxs = new int[size];

            for (int i = 0; i < size; i++) {
                idxs[i] = i;
            }
            StdRandom.shuffle(idxs);
        }


        public boolean hasNext() {
            return size > 0;
        }

        public Item next() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
            int idx = idxs[--size];
            return a[idx];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<String> q = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                q.enqueue(item);
            } else {
                if (!q.isEmpty()) {
                    StdOut.print(q.dequeue() + " ");
                }
            }
        }
        RandomizedQueue<String> trash = new RandomizedQueue<String>();
        for (int i = 0; i < q.size(); i++) {
            String item = q.sample();
            trash.enqueue(item);
        }

        for (String item : q) {
            StdOut.print(item);
        }
        for (String item : trash) {
            StdOut.print(item);
        }
        StdOut.println("(" + q.size() + " left on queue q and " + trash.size() +
                " left on queue trash");
    }

}
