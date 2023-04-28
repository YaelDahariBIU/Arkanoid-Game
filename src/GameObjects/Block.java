// 325166510 Yael Dahari
package GameObjects;
import CollisionDetection.Collidable;
import GameControl.Game;
import GeometryPrimitives.Point;
import GeometryPrimitives.Rectangle;
import Movement.Velocity;
import SpriteControl.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Blocks are rectangles that have a color. We can collide into them.
 */
public class Block implements Collidable, Sprite {
    private final Rectangle rect;
    private final java.awt.Color color;
    /**
     * Instantiates a new GameObjects.Block.
     *
     * @param rect (GeometryPrimitives.Rectangle) - the shape of the block
     * @param color (Color) - the color of the block
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rect = rect;
        this.color = color;
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        boolean collidedVer = false, collidedHor = false;
        double rectX = this.rect.getUpperLeft().getX();
        double rectY = this.rect.getUpperLeft().getY();
        double height = this.rect.getHeight();
        double width = this.rect.getWidth();
        if (Point.doubleEquals(collisionPoint.getX(), rectX + width)
            || Point.doubleEquals(collisionPoint.getX(), rectX)) {
            collidedVer = true;
        }
        if (Point.doubleEquals(collisionPoint.getY(), rectY + height)
            || Point.doubleEquals(collisionPoint.getY(), rectY)) {
            collidedHor = true;
        }
        if (collidedVer) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        if (collidedHor) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        return currentVelocity;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
}
