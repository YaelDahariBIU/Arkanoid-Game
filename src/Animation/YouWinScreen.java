// 325166510 Yael Dahari
package Animation;
import GameControl.Counter;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * If the game ended by clearing all the levels, the screen should display
 * "You Win! Your score is X" (X being the final score).
 */
public class YouWinScreen implements Animation {
    private final String score;

    /**
     * Instantiates a new You win screen.
     *
     * @param score (Counter) - the score
     */
    public YouWinScreen(Counter score) {
        this.score = String.valueOf(score.getValue());
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(36, 225, 15));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(170, d.getHeight() / 2,
                "You won! Your score is " + score, 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    @Override
    public long sleepTime() {
        return 0;
    }

    @Override
    public boolean isBonus() {
        return false;
    }

    @Override
    public void activateBonusEvent(DrawSurface d) {
    }
}
