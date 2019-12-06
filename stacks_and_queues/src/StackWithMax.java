import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackWithMax extends LinkedListStack<Integer> {
    // private LinkedListStack<Integer> valueStack = new LinkedListStack<Integer>();
    private LinkedListStack<Integer> maxStack = new LinkedListStack<Integer>();


    public void push(Integer item) {
        super.push(item);
        Integer lastMax = max();  // calling max here will be problematic with first item
        if (item >= lastMax) {
            maxStack.push(item);
        }

    }

    public Integer pop() {
        if (!super.isEmpty()) {
            Integer item = super.pop();
            if (item.equals(max())) {
                maxStack.pop();
            }
            return item;
        }
        return null;
    }

    public Integer max() {
        if (maxStack.isEmpty()) {
            return Integer.MIN_VALUE;  // lowest integer to compare with
        } else {
            return maxStack.peek();
        }

    }

    public static void main(String[] args) {

        StackWithMax q = new StackWithMax();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                q.push(Integer.parseInt(item));
            } else {
                if (!q.isEmpty()) {
                    StdOut.print(q.pop() + " ");
                }
            }
        }
        StdOut.println("(" + q.size() + " left on queue and max is "
                + q.max() + " )");
    }
}
