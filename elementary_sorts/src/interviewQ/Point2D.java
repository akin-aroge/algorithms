package interviewQ;


public class Point2D implements Comparable<Point2D> {

    private double x;
    private double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Point2D that) {
        // compare by starting with x coordinate
        if (this.x > that.x) {
            return 1;
        }
        if (this.x < that.x) {
            return -1;
        }
        return Double.compare(this.y, that.y);

    }

//    public double x() {
//        return this.x;
//    }
//
//    public double y() {
//        return this.y;
//    }

}
