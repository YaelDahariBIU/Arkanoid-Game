import biuoop.DrawSurface;

import java.awt.Color;

public class Paddle implements Sprite, Collidable {
    static final int DISTANCE = 10;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle shape;
    public Paddle(biuoop.KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.shape = new Rectangle(new Point(300, 550), 150, 50);
    }

    public void moveLeft() {
        double x = this.shape.getUpperLeft().getX();
        if (x - DISTANCE <= 10) {
            this.shape.getUpperLeft().setX(10);
            return;
        }
        this.shape.getUpperLeft().setX(x - DISTANCE);
    }
    public void moveRight() {
        double x = this.shape.getUpperLeft().getX();
        double width = this.shape.getWidth();
        if (x + DISTANCE + width >= 790) {
            this.shape.getUpperLeft().setX(790 - width);
            return;
        }
        this.shape.getUpperLeft().setX(x + DISTANCE);
    }

    // Sprite
    public void timePassed() {
        biuoop.KeyboardSensor keyboard = this.keyboard;
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    public void drawOn(DrawSurface d) {
        d.setColor(Color.MAGENTA);
        d.fillRectangle((int) this.shape.getUpperLeft().getX(),
                (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double x = collisionPoint.getX();
        double paddleX = this.getCollisionRectangle().getUpperLeft().getX();
        double region = this.getCollisionRectangle().getWidth() / 5;
        if (x - paddleX <= region) {
            return Velocity.fromAngleAndSpeed(-60, 10);
        }
        if (x - paddleX <= 2 * region) {
            return Velocity.fromAngleAndSpeed(-30, 10);
        }
        if (x - paddleX <= 3 * region) {
            return Velocity.fromAngleAndSpeed(0, 10);
        }
        if (x - paddleX <= 4 * region) {
            return Velocity.fromAngleAndSpeed(30, 10);
        }
        return Velocity.fromAngleAndSpeed(60, 10);
    }

    // Add this paddle to the game.
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}