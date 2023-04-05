/**
 * The type Point.
 */
public class Point {
    /**
     * The Double threshold.
     */
    static final double DOUBLE_THRESHOLD = 0.000000000000001;
    /**
     * The Power of two.
     */
    static final int POWER_OF_TWO = 2;
    private double x;
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the double
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double dis = Math.pow(this.getX() - other.getX(), POWER_OF_TWO)
                + Math.pow(this.getY() - other.getY(), POWER_OF_TWO);
        return Math.sqrt(dis);
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        return doubleEquals(this.getX(), other.getX())
                && doubleEquals(this.getY(), other.getY());
    }

    /**
     * Double equals boolean.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < DOUBLE_THRESHOLD;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }
}
