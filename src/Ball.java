import biuoop.DrawSurface;

/**
 * The type Ball.
 */
public class Ball {
    /**
     * The No velocity.
     */
    static final double NO_VELOCITY = 0.0;
    /**
     * The Change dir.
     */
    static final int CHANGE_DIR = -1;
    private final Velocity velocity;
    private Point center;
    private final int radius;
    private final java.awt.Color color;

    /**
     * Instantiates a new Ball.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(NO_VELOCITY, NO_VELOCITY);
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(NO_VELOCITY, NO_VELOCITY);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.setVelocity(v.getDx(), v.getDy());
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move one step.
     *
     * @param width    the width
     * @param height   the height
     * @param leftEdge the left edge
     * @param downEdge the down edge
     */
    public void moveOneStep(int width, int height, int leftEdge,
                            int downEdge) {
        this.center = this.getVelocity().applyToPoint(this.center);
        boolean collidedVertical = false, collidedHorizontal = false;
        double x = this.center.getX(), y = this.center.getY();
        double radius = this.getSize();
        if ((x - radius) <= leftEdge) {
            this.center.setX(leftEdge + radius);
            collidedVertical = true;
        } else if ((x + radius) >= (width + leftEdge)) {
            this.center.setX(width + leftEdge - radius);
            collidedVertical = true;
        }
        if (y - radius <= downEdge) {
            this.center.setY(downEdge + radius);
            collidedHorizontal = true;
        } else if ((y + radius) >= (height + downEdge)) {
            this.center.setY(height + downEdge - radius);
            collidedHorizontal = true;
        }
        if (collidedVertical) {
            this.setVelocity(CHANGE_DIR * this.getVelocity().getDx(),
                    this.getVelocity().getDy());
        }
        if (collidedHorizontal) {
            this.setVelocity(this.getVelocity().getDx(),
                    CHANGE_DIR * this.getVelocity().getDy());
        }
    }
}