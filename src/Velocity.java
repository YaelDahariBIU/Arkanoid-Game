// 325166510 Yael Dahari
/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx (double) - the dx value
     * @param dy (double) - the dy value
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * The method gets an angle and a speed, converts them to dx and dy values
     * and returns a new velocity with said values.
     *
     * @param angle (double) - the angle
     * @param speed (double) - the speed
     * @return (Velocity) - the velocity that matches said angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * The method gets a number and sets it to be the dx value of velocity.
     *
     * @param dx (double) - a number
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * The method gets a number and sets it to be the dy value of velocity.
     *
     * @param dy (double) - a number
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * The method returns the dx value of said velocity.
     *
     * @return (double) - the dx value of said velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * The method returns the dy value of said velocity.
     *
     * @return (double) - the dy value of said velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * The method gets a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p (Point) - the point with position (x, y)
     * @return (Point) - the point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}