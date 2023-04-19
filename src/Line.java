// 325166510 Yael Dahari
import java.util.Objects;
/**
 * A line (actually a line-segment) connects two points - a start point and an
 * end point. Lines have lengths, and may intersect with other lines. It can
 * also tell if it is the same as another line segment.
 */
public class Line {
    static final double DOUBLE_THRESHOLD = 0.000001;
    static final double NO_SLOPE = 0.0;
    static final double ONE_POINT = 0.0;
    static final int NONE = 0;
    static final int SINGLE = 1;
    static final int INFINITE = 2;
    static final double HALFWAY = 2;
    private final Point start;
    private final Point end;
    private final double length;

    /**
     * Instantiates a new Line.
     *
     * @param start (Point) - the starting point of the line
     * @param end (Point) - the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.length = start.distance(end);
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 (double) - the x value of the first point
     * @param y1 (double) - the y value of the first point
     * @param x2 (double) - the x value of the second point
     * @param y2 (double) - the y value of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.length = this.start().distance(this.end());
    }

    /**
     * The function returns the length of the line.
     *
     * @return (double) - the length of the line
     */
    public double length() {
        return this.length;
    }

    /**
     * The function returns the middle point of the line.
     *
     * @return (Point) - the middle point of the line
     */

    public Point middle() {
        double x = (this.start().getX() + this.end().getX()) / HALFWAY;
        double y = (this.start().getY() + this.end().getY()) / HALFWAY;
        return new Point(x, y);
    }

    /**
     * The function returns the start point of the line.
     *
     * @return (Point) - the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * The function returns the end point of the line.
     *
     * @return (Point) - the end point of the line
     */
    public Point end() {
        return this.end;
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
    public boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < DOUBLE_THRESHOLD;
    }

    /**
     * The method arranges this line and its start and end points according to
     * its y value (if the 'y's are the same we'll do it with the x value),
     * and returns the new line with start as the min and end as the max.
     *
     * @return (Line) - a line with start as the min and end as the max.
     */
    public Line arrangeInOrder() {
        if (this.slope() == null || this.slope() != NO_SLOPE) {
            if (this.start().getY() > this.end().getY()) {
                return new Line(this.end(), this.start());
            }
        } else {
            if (this.start().getX() > this.end().getX()) {
                return new Line(this.end(), this.start());
            }
        }
        return this;
    }

    /**
     * The function checks how many intersections points exist between two
     * lines with the same slope.
     *
     * @param other (Point) - another given point
     * @return (int) - 0 if there are no intersections, 1 if there's a single
     * one and 2 if there are infinite
     */
    public int numOfIntersections(Line other) {
        if (this.equals(other)) {
            return INFINITE;
        }
        boolean toChange = false;
        Line l1 = this.arrangeInOrder(), l2 = other.arrangeInOrder(), temp;
        if (l1.slope() == null || l1.slope() != NO_SLOPE) {
                if (l2.end().getY() > l1.end().getY()) {
                    toChange = true;
                }
        } else if (l2.end().getX() > l1.end().getX()) {
            toChange = true;
        }
        if (toChange) {
            temp = l1;
            l1 = l2;
            l2 = temp;
        }
        /* if the min point in line 1 is the same as the max point in line 2
         and the max in line 1 isn't on line 2 and vice versa it means they
         have a singular intersection point */
        Point min1 = l1.start(), max1 = l1.end();
        Point min2 = l2.start(), max2 = l2.end();
        if (min1.equals(max2) && !l1.isOnLine(min2) && !l2.isOnLine(max1)) {
            return SINGLE;
        }
        if (max1.equals(min2) && !l1.isOnLine(max2) && !l2.isOnLine(min1)) {
            return SINGLE;
        }
        if ((l1.length() == ONE_POINT && l2.isOnLine(min1))
                || (l2.length() == ONE_POINT && l1.isOnLine(min2))) {
            return SINGLE;
        }
        /* if the distance from the min in line a to the start and end points
        of line b isn't bigger than line b's length, the lines overlap */
        if (min1.distance(min2) <= l2.length()
            && min1.distance(max2) <= l2.length()) {
            return INFINITE;
        }
        if (max2.distance(min1) <= l1.length()
            && max2.distance(max1) <= l1.length()) {
            return INFINITE;
        }
        return NONE;
    }

    /**
     * The function checks if this line intersects with a given line and
     * returns true or false accordingly.
     *
     * @param other (Line) - the other line
     * @return (boolean) - true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Double slp1 = this.slope(), slp2 = other.slope();
        double b1 = findB(this.end(), slp1), b2 = findB(other.end(), slp2);
        // if they have the same formula we check the num of intersections
        if ((slp1 != null && slp2 != null && doubleEquals(slp1, slp2))
                || Objects.equals(slp1, slp2)) {
            if (doubleEquals(b1, b2)) {
                return numOfIntersections(other) != NONE;
              // if the slopes equal but the "b" isn't, they don't intersect
            } else {
                return false;
            }
        }
        // if the slopes are different
        Point inter = this.intersectionWith(other);
        return inter != null;
    }

    /**
     * The function calculates and returns the slope of the line (if the
     * slope cannot be calculated - line: x = b - it returns null).
     *
     * @return (Double) - the slope of the line
     */
    public Double slope() {
        Point max = this.start();
        Point min = this.end();
        if (min.getX() > max.getX()) {
            max = this.end();
            min = this.start();
        }
        // if the line is: x = b
        if (doubleEquals(max.getX(), min.getX())) {
            return null;
        }
        // if the line is: y = b
        if (doubleEquals(max.getY(), min.getY())) {
            return NO_SLOPE;
        }
        return (max.getY() - min.getY()) / (max.getX() - min.getX());
    }

    /**
     * The function gets a point and a slope and calculates the "b" factor of
     * the line formula and returns its value.
     *
     * @param point (Point) - a point on the line
     * @param slope (double) - the slope f the line
     * @return (double) - the "b" factor of the line formula
     */
    public double findB(Point point, Double slope) {
        // if the line is: x = b
        if (slope == null) {
            return point.getX();
        }
        // if the line is: y = b
        if (doubleEquals(slope, NO_SLOPE)) {
            return point.getY();
        }
        return point.getY() - (slope * point.getX());
    }

    /**
     * The function gets a point and checks if that point is on this line by
     * calculating its distance from both points of the line and comparing
     * them to the line's length.
     *
     * @param point (Point) - the point to check
     * @return (boolean) true if the point is on this line, otherwise false
     */
    public boolean isOnLine(Point point) {
        if (point.equals(this.start()) || point.equals(this.end())) {
            return true;
        }
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
     * The function gets a line, calculates the single common point between
     * this line and the given one and returns said point.
     *
     * @param other (Line) - the other line
     * @return (Point) - the common point
     */
    public Point commonPoint(Line other) {
        if (this.end().equals(other.start())
                || this.end().equals(other.end())) {
            return new Point(this.end().getX(), this.end().getY());
        }
        return new Point(this.start().getX(), this.start().getY());
    }

    /**
     * The function gets a line, calculates the intersection point between
     * this line and the given one and returns it (if such point doesn't exist
     * or that there's more than one, it returns null).
     *
     * @param other (Line) - the other line
     * @return (Point) - the intersection point if the lines intersect,
     * and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Double slp1 = this.slope(), slp2 = other.slope();
        double b1 = findB(this.end(), slp1), b2 = findB(other.end(), slp2), x;
        Point p;
        if ((slp1 != null && slp2 != null && doubleEquals(slp1, slp2))
                || Objects.equals(slp1, slp2)) {
            if (doubleEquals(b1, b2)) {
                // if they overlap or parallel we return null
                if (numOfIntersections(other) != SINGLE) {
                    return null;
                  // if they intersect just once
                } else {
                    return commonPoint(other);
                }
            }
            // if the slopes equal but the "b" isn't, they don't intersect
            return null;
        }
        // calculating the point the lines would intersect in
        if (slp1 == null) {
            p = new Point(b1, (slp2 * b1) + b2);
        } else {
            if (slp2 == null) {
                p = new Point(b2, (slp1 * b2) + b1);
            } else {
                x = (b2 - b1) / (slp1 - slp2);
                p = new Point(x, (slp1 * x) + b1);
            }
        }
        // if said point is on both lines, they intersect
        if (this.isOnLine(p) && other.isOnLine(p)) {
            return p;
        }
        return null;
    }

    /**
     * The function checks if this line and a given line are equal. Lines are
     * considered equal even if the end of one is equal to the beginning of the
     * other and vice versa.
     *
     * @param other (Line) - the other line
     * @return (boolean) - true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start().equals(other.start())
                && this.end().equals(other.end())) {
            return true;
        }
        return this.start().equals(other.end())
                && this.end().equals(other.start());
    }
}
