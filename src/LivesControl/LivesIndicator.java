// 325166510 Yael Dahari
package LivesControl;
import GameControl.Counter;
import GameControl.GameLevel;
import GameControl.SpriteControl.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Lives indicator is in charge of displaying the current num of balls.
 */
public class LivesIndicator implements Sprite {
    static final int X = 50;
    static final int Y = 25;
    static final int FONT = 20;
    private final Counter currentBalls;
    /**
     * Instantiates a new Score indicator.
     *
     * @param currentBalls (Counter) - the current num of balls
     */
    public LivesIndicator(Counter currentBalls) {
        this.currentBalls = currentBalls;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(X, Y, "Lives: " + currentBalls.getValue(), FONT);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
