import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N;


    private class Node {
        Item item;
        Node right;
        Node left;
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        // return first == null; this could lead to insidious bugs
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.right = oldFirst;

        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.left = first;
        }

        N++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.left = oldLast;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.right = last;
        }

        N++;
    }


    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.right;

        N--;

        if (!isEmpty()) {
            first.left = null;
        } else {
            last = null;
        }

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.left;

        N--;
        if (!isEmpty()) {
            last.right = null;
        } else {
            first = null;
        }

        return item;
    }


    // return an iterator over items in order from front to back
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.right;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<String> q = new Deque<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                q.addFirst(item);
                q.addLast("L" + item);
            } else {
                if (!q.isEmpty()) {
                    StdOut.print(q.removeFirst() + " ");
                }
                if (!q.isEmpty()) {
                    StdOut.print(q.removeLast() + " ");
                }
            }
        }
        for (String item : q) {
            StdOut.print(item);
        }

        StdOut.println("\n(" + q.size() + " left on queue)");
    }

}
