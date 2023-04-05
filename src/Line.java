import java.util.Objects;

/**
 * The type Line.
 */
public class Line {
    /**
     * The Double threshold.
     */
    static final double DOUBLE_THRESHOLD = 0.000000000000001;
    /**
     * The No slope.
     */
    static final double NO_SLOPE = 0.0;
    /**
     * The None.
     */
    static final int NONE = 0;
    /**
     * The Single.
     */
    static final int SINGLE = 1;
    /**
     * The Infinite.
     */
    static final int INFINITE = 2;
    /**
     * The Halfway.
     */
    static final double HALFWAY = 2;
    private final Point start;
    private final Point end;
    private final double length;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.length = start.distance(end);
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.length = this.start().distance(this.end());
    }

    /**
     * Length double.
     *
     * @return the double
     */
// Return the length of the line
    public double length() {
        return this.length;
    }

    /**
     * Middle point.
     *
     * @return the point
     */
// Returns the middle point of the line
    public Point middle() {
        double x = (this.start().getX() + this.end().getX()) / HALFWAY;
        double y = (this.start().getY() + this.end().getY()) / HALFWAY;
        return new Point(x, y);
    }

    /**
     * Start point.
     *
     * @return the point
     */
// Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the point
     */
// Returns the end point of the line
    public Point end() {
        return this.end;
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
     * Sum of intersections int.
     *
     * @param other the other
     * @return the int
     */
    public int sumOfIntersections(Line other) {
        if (this.equals(other)) {
            return INFINITE;
        }
        Line max = this, min = other;
        Point min1 = this.start(), max1 = this.end();
        if (this.start().getY() > this.end().getY()) {
            min1 = this.end();
            max1 = this.start();
        }
        Point min2 = other.start(), max2 = other.end();
        if (other.start().getY() > other.end().getY()) {
            min2 = other.end();
            max2 = other.start();
        }
        if (max2.getY() > max1.getY()) {
            Point temp = max2;
            max2 = max1;
            max1 = temp;
            temp = min2;
            min2 = min1;
            min1 = temp;
            Line tempLine = max;
            max = min;
            min = tempLine;
        }
        if (min1.equals(max2) && !max.isOnLine(min2) && !min.isOnLine(max1)) {
            return SINGLE;
        }
        if (max1.equals(min2) && !max.isOnLine(max2) && !min.isOnLine(min1)) {
            return SINGLE;
        }
        if (min1.distance(min2) <= min.length()
            && min1.distance(max2) <= min.length()) {
            return INFINITE;
        }
        if (max2.distance(min1) <= max.length()
            && max2.distance(max1) <= max.length()) {
            return INFINITE;
        }
        return NONE;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        Double slp1 = this.slope(), slp2 = other.slope();
        double b1 = findB(this.end(), slp1), b2 = findB(other.end(), slp2);
        if ((slp1 != null && slp2 != null && doubleEquals(slp1, slp2))
                || Objects.equals(slp1, slp2)) {
            if (doubleEquals(b1, b2)) {
                return sumOfIntersections(other) != NONE;
            } else {
                return false;
            }
        }
        Point inter = this.intersectionWith(other);
        return inter != null;
    }

    /**
     * Slope double.
     *
     * @return the double
     */
    public Double slope() {
        Point max = this.start();
        Point min = this.end();
        if (min.getX() > max.getX()) {
            max = this.end();
            min = this.start();
        }
        if (doubleEquals(max.getX(), min.getX())) {
            return null;
        }
        if (doubleEquals(max.getY(), min.getY())) {
            return NO_SLOPE;
        }
        return (max.getY() - min.getY()) / (max.getX() - min.getX());
    }

    /**
     * Find b double.
     *
     * @param point the point
     * @param slope the slope
     * @return the double
     */
    public double findB(Point point, Double slope) {
        if (slope == null) {
            return point.getX();
        }
        if (doubleEquals(slope, NO_SLOPE)) {
            return point.getY();
        }
        return point.getY() - (slope * point.getX());
    }

    /**
     * Is on line boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean isOnLine(Point point) {
        if ((doubleEquals(point.distance(this.start()), this.length()))
            && doubleEquals(point.distance(this.end()), this.length())) {
            return true;
        }
        if (point.distance(this.start()) > this.length()) {
            return false;
        }
        return !(point.distance(this.end()) > this.length());
    }

    /**
     * Common point point.
     *
     * @param other the other
     * @return the point
     */
    public Point commonPoint(Line other) {
        if (this.end().equals(other.start())
                || this.end().equals(other.end())) {
            return new Point(this.end().getX(), this.end().getY());
        }
        return new Point(this.start().getX(), this.start().getY());
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
// Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        Double slp1 = this.slope(), slp2 = other.slope();
        double b1 = findB(this.end(), slp1), b2 = findB(other.end(), slp2);
        Point p;
        if ((slp1 != null && slp2 != null && doubleEquals(slp1, slp2))
                || Objects.equals(slp1, slp2)) {
            if (doubleEquals(b1, b2)) {
                if (sumOfIntersections(other) != SINGLE) {
                    return null;
                } else {
                    return commonPoint(other);
                }
            } else {
                return null;
            }
        }
        double x, y;
        if (slp1 == null) {
            if (doubleEquals(slp2, NO_SLOPE)) {
                p = new Point(b1, b2);
            } else {
                y = (slp2 * b1) + b2;
                p = new Point(b1, y);
            }
        } else {
            if (slp2 == null) {
                if (doubleEquals(slp1, NO_SLOPE)) {
                    p = new Point(b2, b1);
                } else {
                    y = (slp1 * b2) + b1;
                    p = new Point(b2, y);
                }
            } else {
                x = (b2 - b1) / (slp1 - slp2);
                y = (slp1 * x) + b1;
                p = new Point(x, y);
            }
        }
        if (this.isOnLine(p) && other.isOnLine(p)) {
            return p;
        }
        return null;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (this.start().equals(other.start())
                && this.end().equals(other.end())) {
            return true;
        }
        return this.start().equals(other.end())
                && this.end().equals(other.start());
    }
}
