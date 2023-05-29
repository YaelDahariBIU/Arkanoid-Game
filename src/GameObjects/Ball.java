// 325166510 Yael Dahari
package GameObjects;
import CollisionControl.CollisionInfo;
import GameControl.Game;
import GameControl.GameEnvironment;
import GeometryPrimitives.Line;
import GeometryPrimitives.Point;
import GameControl.SpriteControl.Sprite;
import biuoop.DrawSurface;
import Movement.Velocity;
/**
 * Balls have size (radius), color, and location (a GeometryPrimitives.Point).
 * Balls also know how to draw themselves on a DrawSurface.
 */
public class Ball implements Sprite {
    static final double NO_VELOCITY = 0.0;
//    static final int CHANGE_DIR = -1;
    static final double DISTANCE = 5.0;
    private final Velocity velocity;
    private Point center;
    private final int radius;
    private final java.awt.Color color;
    private GameEnvironment environment;

    /**
     * Instantiates a new GameObjects.Ball.
     *
     * @param center (GeometryPrimitives.Point) - the location of the ball
     * @param r (int) - the ball's radius
     * @param color (java.awt.Color) - the ball's color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(NO_VELOCITY, NO_VELOCITY);
    }

    /**
     * Instantiates a new GameObjects.Ball.
     *
     * @param x (double) - the x value of the ball's location
     * @param y  (double) - the y value of the ball's location
     * @param r (int) - the ball's radius
     * @param color (java.awt.Color) - the ball's color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(NO_VELOCITY, NO_VELOCITY);
    }

    /**
     * The method returns the x value of the ball's location.
     *
     * @return (int) - the x value of the ball's location.
     */

    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * The method returns the y value of the ball's location.
     *
     * @return (int) - the y value of the ball's location.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * The method returns the ball's radius.
     *
     * @return (int) - the ball's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * The method returns the ball's color.
     *
     * @return (int) - the ball's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * The method gets a DrawSurface draws the ball on the given DrawSurface.
     *
     * @param surface (DrawSurface) - the surface we're drawing on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
        this.withinPaddle();
    }

    /**
     * The method gets a velocity and sets the velocity of this ball.
     *
     * @param v (Movement.Velocity) - the velocity we need to set
     */
    public void setVelocity(Velocity v) {
        this.setVelocity(v.getDx(), v.getDy());
    }

    /**
     * The method gets both parameters of velocity and sets the velocity of
     * this ball.
     *
     * @param dx (double) - the dx value of velocity
     * @param dy (double) - the dy value of velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * The method returns the velocity of this ball.
     *
     * @return (Movement.Velocity) - the velocity of this ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The method gets the measurements of a frame and coordinates for its
     * left-down point. It calls moveOneStep() and checks if this ball
     collided or went through the frame and if so, it returns it back to
     * the wall and changes the velocity direction accordingly.
     *
     * @param width (int) - the frame's width
     * @param height (int) - the frame's height
     * @param leftEdge (double) - the frame's left edge
     * @param downEdge (double) - the frame's down edge
     */
    public void moveInFrame(int width, int height, double leftEdge,
                            double downEdge) {
        this.moveOneStep();
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
            this.setVelocity(-this.getVelocity().getDx(),
                    this.getVelocity().getDy());
        }
        if (collidedHorizontal) {
            this.setVelocity(this.getVelocity().getDx(),
                    -this.getVelocity().getDy());
        }
    }

    /**
     * The method applies the velocity to this ball's coordinates and
     * therefore causes it to move a step. It also checks if it's colliding
     * with an object and if so, moves the ball accordingly.
     */
    public void moveOneStep() {
        Point end = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, end);
        CollisionInfo c = this.environment.getClosestCollision(trajectory);
        if (c == null) {
            this.center = end;
            return;
        }
        this.center.setX(moveABit(c.collisionPoint().getX(),
                trajectory.start().getX()));
        this.center.setY(moveABit(c.collisionPoint().getY(),
                trajectory.start().getY()));
        this.setVelocity(c.collisionObject().hit(this, c.collisionPoint(),
                this.getVelocity()));
    }

    /**
     * The method returns the ball's coordinate to "almost" the hit point, but
     * just slightly before it.
     *
     * @param collision (double) - the collision coordinate
     * @param start (double) - the original coordinate
     * @return (double) - the coordinate just before the hit
     */
    private double moveABit(double collision, double start) {
        if (start > collision) {
            return collision + DISTANCE;
        }
        return collision - DISTANCE;
    }

    /**
     * The method sets the environment value of this point to a given value.
     *
     * @param environment (GameControl.GameEnvironment) - the given value
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    @Override
    public boolean isABall() {
        return true;
    }

    /**
     * The method checks if said ball is within the game's paddle and if so,
     * it moves the ball to the border of the paddle.
     */
    public void withinPaddle() {
        Paddle paddle = this.environment.getPaddle();
        double left = paddle.getCollisionRectangle().getUpperLeft().getX();
        double right = left + paddle.getCollisionRectangle().getWidth(),
        upper = paddle.getCollisionRectangle().getUpperLeft().getY();
        double down = upper + paddle.getCollisionRectangle().getHeight();
        if (this.getX() >= left && this.getX() <= right) {
            if (this.getY() >= upper && this.getY() <= down) {
                this.setCenter(new Point(this.getX(), upper));
            }
        }
    }

    /**
     * The method sets the center value of this ball to a given point.
     *
     * @param center (Point) - the specified point
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * The method removes this ball from the given game.
     *
     * @param g (Game) - the given game
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}