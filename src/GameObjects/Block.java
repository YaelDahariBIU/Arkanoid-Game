// 325166510 Yael Dahari
package GameObjects;
import CollisionControl.Collidable;
import CollisionControl.HitListener;
import CollisionControl.HitNotifier;
import GameControl.GameLevel;
import GeometryPrimitives.Point;
import GeometryPrimitives.Rectangle;
import Movement.Velocity;
import GameControl.SpriteControl.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Blocks are rectangles that have a color. We can collide into them.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rect;
    private final java.awt.Color color;
    private final List<HitListener> hitListeners;

    /**
     * Instantiates a new GameObjects.Block.
     *
     * @param rect  (GeometryPrimitives.Rectangle) - the shape of the block
     * @param color (Color) - the color of the block
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        boolean collidedVer = false, collidedHor = false;
        double rectX = rect.getUpperLeft().getX(), height = rect.getHeight();
        double rectY = rect.getUpperLeft().getY(), width = rect.getWidth();
        if (Point.doubleEquals(collisionPoint.getX(), rectX + width)
            || Point.doubleEquals(collisionPoint.getX(), rectX)) {
            collidedVer = true;
        }
        if (Point.doubleEquals(collisionPoint.getY(), rectY + height)
            || Point.doubleEquals(collisionPoint.getY(), rectY)) {
            collidedHor = true;
        }
        if (collidedHor || collidedVer) {
            this.notifyHit(hitter);
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
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * The method removes this block from a given game.
     *
     * @param game (Game) - the given game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
