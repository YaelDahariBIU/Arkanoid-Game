// 325166510 Yael Dahari
package GameControl.SpriteControl;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of many sprites.
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Instantiates a new GameControl.SpriteControl.Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * The method adda a given sprite to the collection.
     *
     * @param s (GameControl.SpriteControl.Sprite) - the specified sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * The method calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        int size = this.sprites.size();
        for (int i = 0; i < size; i++) {
            this.sprites.get(i).timePassed();
            size = this.sprites.size();
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
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    public List<Sprite> getSprites() {
        return this.sprites;
    }
}