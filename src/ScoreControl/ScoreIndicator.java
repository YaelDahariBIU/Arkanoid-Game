// 325166510 Yael Dahari
package ScoreControl;
import GameControl.Counter;
import GameControl.GameLevel;
import GameControl.SpriteControl.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Score indicator is in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    static final int X = 350;
    static final int Y = 25;
    static final int FONT = 20;
    private final Counter currentScore;

    /**
     * Instantiates a new Score indicator.
     *
     * @param currentScore (Counter) - the current score
     */
    public ScoreIndicator(Counter currentScore) {
        this.currentScore = currentScore;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(X, Y, "Score: " + currentScore.getValue(), FONT);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    @Override
    public boolean isABall() {
        return false;
    }
}
