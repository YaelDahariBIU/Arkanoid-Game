// 325166510 Yael Dahari
package GeometryPrimitives;
import GameControl.GameLevel;
import GameControl.SpriteControl.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Circle.
 */
public class Circle implements Sprite {
    private final Point center;
    private final int radius;
    private final Color color;
    private final boolean toFill;

    /**
     * Instantiates a new Circle.
     *
     * @param center (Point) - the center
     * @param radius (int) - the radius
     * @param color (Color) - the color
     * @param toFill (boolean) - the to fill
     */
    public Circle(Point center, int radius, Color color, boolean toFill) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.toFill = toFill;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        if (toFill) {
            d.fillCircle((int) center.getX(), (int) center.getY(), radius);
        } else {
            d.drawCircle((int) center.getX(), (int) center.getY(), radius);
        }
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
