// 325166510 Yael Dahari
package GeometryPrimitives;
/**
 * A point has an x and a y value, and can measure the distance to other
 * points, and if it is equal to another point.
 */
public class Point {
    static final double DOUBLE_THRESHOLD = 0.000001;
    static final int POWER_OF_TWO = 2;
    static final int INVALID = -1;
    private double x;
    private double y;

    /**
     * Instantiates a new GeometryPrimitives.Point.
     *
     * @param x (double) - the x parameter
     * @param y (double) - the y parameter
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The function calculates and returns the distance of this point to the
     * other point. If the other is null, it returns -1.
     *
     * @param other (GeometryPrimitives.Point) - the other point
     * @return (double) the distance between the points
     */
    public double distance(Point other) {
        if (other == null) {
            return INVALID;
        }
        double dis = Math.pow(this.getX() - other.getX(), POWER_OF_TWO)
                + Math.pow(this.getY() - other.getY(), POWER_OF_TWO);
        return Math.sqrt(dis);
    }

    /**
     * The function checks if this point's x value and y value are equal to
     * the given point's and returns true or false accordingly.
     *
     * @param other (GeometryPrimitives.Point) - the other point
     * @return (boolean) - true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return doubleEquals(this.getX(), other.getX())
                && doubleEquals(this.getY(), other.getY());
    }

    /**
     * The function checks if the absolute value of the subtraction between
     * both numbers is lesser than the threshold and if so, they're
     * considered equals.
     *
     * @param a (double) - the first number
     * @param b (double) - the second number
     * @return (boolean) - true is the numbers are equal, false otherwise
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < DOUBLE_THRESHOLD;
    }

    /**
     * The function returns the x value of this point.
     *
     * @return (double) the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * The function returns the y value of this point.
     *
     * @return (double) the y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * The function sets the x value of this point to a given number.
     *
     * @param x (double) - a given number.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * The function sets the y value of this point to a given number.
     *
     * @param y (double) - a given number.
     */
    public void setY(double y) {
        this.y = y;
    }
}
