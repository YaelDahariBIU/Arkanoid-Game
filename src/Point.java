public class Point {
    private double x;
    private double y;
    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double dis = Math.pow(this.x - other.getX(), 2)
                + Math.pow(other.y - other.getY(), 2);
        return Math.sqrt(dis);
    }

    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
}
