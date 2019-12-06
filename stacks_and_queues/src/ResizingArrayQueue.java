import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayQueue<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    private int head = 0;
    private int tail = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int size) {
        Item[] temp = (Item[]) new Object[size];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i + head];
        }
        a = temp;
        head = 0;
        tail = N;
    }

    public void enqueue(Item item) {
        if (tail == a.length) {  // tail >= N so tail here
            resize(2 * a.length);
        }
        a[tail++] = item;
        N++;
    }

    public Item dequeue() {
        Item item = a[head];
        a[head] = null;
        head++;
        N--;

        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;

    }

    public static void main(String[] args) {

        ResizingArrayQueue<String> q = new ResizingArrayQueue<String>();

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
        StdOut.println("(" + q.size() + " left on queue)");
    }

}
