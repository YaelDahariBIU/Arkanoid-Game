// 325166510 Yael Dahari
import java.util.ArrayList;
import java.util.List;

/**
 * Rectangles have an upper-left point that indicates their location, a width
 * and a height so that we'd know their size.
 */
public class Rectangle {
    static final int FIRST = 0;
    static final int SECOND = 1;
    static final int THIRD = 2;
    static final int FOURTH = 3;
    static final int SIZE = 4;
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Instantiates a new Rectangle with location and width/height.
     *
     * @param upperLeft (Point) - the upper left point
     * @param width (double) - the width
     * @param height (double) - the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * The method returns a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line (Line) - the specified line
     * @return (java.util.list(Point)) - the list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] recLines = new Line[SIZE];
        recLines[FIRST] = this.getUpperHor();
        recLines[SECOND] = this.getDownHor();
        recLines[THIRD] = this.getLeftVer();
        recLines[FOURTH] = this.getRightVer();
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

    /**
     * The method returns this rectangle's width.
     *
     * @return (double) - the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * The method returns this rectangle's height.
     *
     * @return (double) - the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * The method returns this rectangle's upper-left point.
     *
     * @return (Point) - the upper-left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * The method returns this rectangle's upper horizontal line.
     *
     * @return (Line) - the upper horizontal line
     */
    public Line getUpperHor() {
        Point upperRight = new Point(getUpperLeft().getX() + getWidth(),
                getUpperLeft().getY());
        return new Line(getUpperLeft(), upperRight);
    }

    /**
     * The method returns this rectangle's down horizontal line.
     *
     * @return (Line) - the down horizontal line
     */
    public Line getDownHor() {
        Point downLeft = new Point(getUpperLeft().getX(),
                getUpperLeft().getY() + getHeight());
        Point downRight = new Point(getUpperLeft().getX() + getWidth(),
                getUpperLeft().getY() + getHeight());
        return new Line(downLeft, downRight);
    }

    /**
     * The method returns this rectangle's left vertical line.
     *
     * @return (Line) - the left vertical line
     */
    public Line getLeftVer() {
        Point downLeft = new Point(getUpperLeft().getX(),
                getUpperLeft().getY() + getHeight());
        return new Line(getUpperLeft(), downLeft);
    }

    /**
     * The method returns this rectangle's right vertical line.
     *
     * @return (Line) - the right vertical line
     */
    public Line getRightVer() {
        Point downRight = new Point(getUpperLeft().getX() + getWidth(),
                getUpperLeft().getY() + getHeight());
        Point upperRight = new Point(getUpperLeft().getX() + getWidth(),
                getUpperLeft().getY());
       return new Line(upperRight, downRight);
    }
}