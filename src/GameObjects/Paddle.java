// 325166510 Yael Dahari
package GameObjects;
import CollisionControl.Collidable;
import GeometryPrimitives.Point;
import GeometryPrimitives.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
import GameControl.GameLevel;
import GameControl.SpriteControl.Sprite;
import Movement.Velocity;
/**
 * The GameObjects.Paddle is the player in the game. It is a rectangle that is
 * controlled by the arrow keys, and moves according to the player key
 * presses. It should implement the GameControl.SpriteControl.Sprite and the
 * CollisionDetection.Collidable interfaces. It should also
 * know how to move to the left and to the right.
 */
public class Paddle implements Sprite, Collidable {
    static final int EDGE = 10;
    static final int Y = 550;
    static final int HEIGHT = 50;
    static final int BORDER_WIDTH = 790;
    static final int SPEED = 7;
    static final int SECOND = 2;
    static final int THIRD = 3;
    static final int FOURTH = 4;
    static final int REGIONS = 5;
    static final int ANGLE_1 = -60;
    static final int ANGLE_2 = -30;
    static final int ANGLE_4 = 30;
    static final int ANGLE_5 = 60;
    private final biuoop.KeyboardSensor keyboard;
    private final Rectangle shape;
    private final int speed;
    private final Color color;

    /**
     * Instantiates a new GameObjects.Paddle.
     *
     * @param keyboard (KeyboardSensor) - the keyboard sensor
     * @param width (int) - the paddle's width.
     * @param speed (int) - the paddle's speed
     * @param color (Color) - the paddle's color
     * */
    public Paddle(biuoop.KeyboardSensor keyboard, int width, int speed,
                  Color color) {
        this.keyboard = keyboard;
        Point p = new Point((800 - width) / 2.0, Y);
        this.shape = new Rectangle(p, width, HEIGHT);
        this.speed = speed;
        this.color = color;
    }

    /**
     * The method moves the paddle to the left by 10.
     */
    public void moveLeft() {
        double x = this.shape.getUpperLeft().getX();
        if (x - speed <= speed) {
            this.shape.getUpperLeft().setX(EDGE);
            return;
        }
        this.shape.getUpperLeft().setX(x - speed);
    }

    /**
     * The method moves the paddle to the right by 10.
     */
    public void moveRight() {
        double x = this.shape.getUpperLeft().getX();
        double width = this.shape.getWidth();
        if (x + speed + width >= BORDER_WIDTH) {
            this.shape.getUpperLeft().setX(BORDER_WIDTH - width);
            return;
        }
        this.shape.getUpperLeft().setX(x + speed);
    }

    @Override
    public void timePassed() {
        biuoop.KeyboardSensor keyboard = this.keyboard;
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(),
                (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double x = collisionPoint.getX();
        double paddleX = this.getCollisionRectangle().getUpperLeft().getX();
        double region = this.getCollisionRectangle().getWidth() / REGIONS;
        if (x - paddleX <= region) {
            return Velocity.fromAngleAndSpeed(ANGLE_1, SPEED);
        }
        if (x - paddleX <= SECOND * region) {
            return Velocity.fromAngleAndSpeed(ANGLE_2, SPEED);
        }
        if (x - paddleX <= THIRD * region) {
            return new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }
        if (x - paddleX <= FOURTH * region) {
            return Velocity.fromAngleAndSpeed(ANGLE_4, SPEED);
        }
        return Velocity.fromAngleAndSpeed(ANGLE_5, SPEED);
    }
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}