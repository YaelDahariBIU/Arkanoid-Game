// 325166510 Yael Dahari
package GameObjects;
import CollisionDetection.Collidable;
import GeometryPrimitives.Point;
import GeometryPrimitives.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
import GameControl.Game;
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
    static final int DISTANCE = 10;
    static final int X = 300;
    static final int Y = 550;
    static final int WIDTH = 150;
    static final int HEIGHT = 50;
    static final int BORDER_WIDTH = 790;
    static final int SPEED = 10;
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

    /**
     * Instantiates a new GameObjects.Paddle.
     *
     * @param keyboard (KeyboardSensor) - the keyboard sensor
     */
    public Paddle(biuoop.KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.shape = new Rectangle(new Point(X, Y), WIDTH, HEIGHT);
    }

    /**
     * The method moves the paddle to the left by 10.
     */
    public void moveLeft() {
        double x = this.shape.getUpperLeft().getX();
        if (x - DISTANCE <= DISTANCE) {
            this.shape.getUpperLeft().setX(DISTANCE);
            return;
        }
        this.shape.getUpperLeft().setX(x - DISTANCE);
    }

    /**
     * The method moves the paddle to the right by 10.
     */
    public void moveRight() {
        double x = this.shape.getUpperLeft().getX();
        double width = this.shape.getWidth();
        if (x + DISTANCE + width >= BORDER_WIDTH) {
            this.shape.getUpperLeft().setX(BORDER_WIDTH - width);
            return;
        }
        this.shape.getUpperLeft().setX(x + DISTANCE);
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
        d.setColor(Color.MAGENTA);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(),
                (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
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
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}