// 325166510 Yael Dahari
package GameControl.LevelControl;
import GameControl.GameLevel;
import GameControl.SpriteControl.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;

/**
 * The Background has a color and a list of Sprites that contains the objects
 * we want to draw.
 */
public class Background implements Sprite {
    private final Color color;
    private final List<Sprite> objects;

    /**
     * Instantiates a new Background.
     *
     * @param color (Color) - the color
     * @param objects (List-Sprite-) - the objects
     */
    public Background(Color color, List<Sprite> objects) {
        this.color = color;
        this.objects = objects;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle(0, 0, 800, 600);
        for (Sprite object : objects) {
            object.drawOn(d);
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
