import java.util.Objects;

public class Line {
    private Point start;
    private Point end;
    private double length;
    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    // Return the length of the line
    public double length() {
        return this.start.distance(this.end);
    }

    // Returns the middle point of the line
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / (double) 2;
        double y = (this.start.getY() + this.end.getY()) / (double) 2;
        return new Point(x, y);
    }

    // Returns the start point of the line
    public Point start() {
        return this.start;
    }

    // Returns the end point of the line
    public Point end() {
        return this.end;
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if (Objects.equals(this.slope(), other.slope())) {
            return false;
        }
        return this.intersectionWith(other) != null;
    }
    public Double slope() {
        Point max = start;
        Point min = end;
        if (this.end.getX() > this.start.getX()) {
            max = end;
            min = start;
        }
        if (max.getX() - min.getX() == 0) {
            return null;
        }
        return (max.getY() - min.getY()) / (max.getX() - min.getX());
    }
    public Double findB(Point point, Double slope) {
        if (slope == null) {
            return null;
        }
        if (slope == 0) {
            return point.getY();
        }
        return point.getY() - (slope * point.getX());
    }
    public boolean isOnLine(Point point) {
        if (point.distance(this.start) > this.length) {
            return false;
        }
        return !(point.distance(this.end) > this.length);
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        Double slp1 = this.slope(), slp2 = other.slope();
        if (Objects.equals(slp1, slp2)) {
            return null;
        }
        Double b1 = findB(this.end, slp1), b2 = findB(other.end, slp2);
        Point p;
        double x, y;
        if (b1 == null) {
            if (b2 == 0) {
                p = new Point(this.end.getX(), other.end.getY());
            } else {
                y = (slp2 * this.end.getX()) + b2;
                p = new Point(this.end.getX(), y);
            }
        } else {
            if (b2 == null) {
                if (b1 == 0) {
                    p = new Point(other.end.getX(), this.end.getY());
                } else {
                    y = (slp1 * other.end.getX()) + b1;
                    p = new Point(other.end.getX(), y);
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

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return this.start.equals(other.end) && this.end.equals(other.start);
    }
}
