import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] recLines = new Line[4];
        recLines[0] = this.getUpperHor();
        recLines[1] = this.getDownHor();
        recLines[2] = this.getLeftVer();
        recLines[3] = this.getRightVer();
        List<Point> inter = new ArrayList<>();
        for (Line recLine: recLines) {
            if (line.isIntersecting(recLine)) {
                Point common = line.intersectionWith(recLine);
                if (common != null) {
                    inter.add(common);
                }
            }
        }
        return inter;
    }

    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    public Line getUpperHor() {
        Point upperRight = new Point(getUpperLeft().getX() + getWidth(),
                getUpperLeft().getY());
        return new Line(getUpperLeft(), upperRight);
    }
    public Line getDownHor() {
        Point downLeft = new Point(getUpperLeft().getX(),
                getUpperLeft().getY() + getHeight());
        Point downRight = new Point(getUpperLeft().getX() + getWidth(),
                getUpperLeft().getY() + getHeight());
        return new Line(downLeft, downRight);
    }
    public Line getLeftVer() {
        Point downLeft = new Point(getUpperLeft().getX(),
                getUpperLeft().getY() + getHeight());
        return new Line(getUpperLeft(), downLeft);
    }
    public Line getRightVer() {
        Point downRight = new Point(getUpperLeft().getX() + getWidth(),
                getUpperLeft().getY() + getHeight());
        Point upperRight = new Point(getUpperLeft().getX() + getWidth(),
                getUpperLeft().getY());
       return new Line(upperRight, downRight);
    }
}