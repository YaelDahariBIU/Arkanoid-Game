// 325166510 Yael Dahari
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of many sprites.
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * The method adda a given sprite to the collection.
     *
     * @param s (Sprite) - the specified sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * The method calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : this.sprites) {
            sprite.timePassed();
        }
    }

    /**
     * The method calls drawOn(d) on all sprites.
     *
     * @param d (DrawSurface) - the surface we're drawing on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }
}