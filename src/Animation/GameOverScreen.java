// 325166510 Yael Dahari
package Animation;
import GameControl.Counter;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * If the game ended with the player dying (i.e. all balls fall off the screen),
 * the end screen should display the message "Game Over. Your score is X"
 * (X being the final score).
 */
public class GameOverScreen implements Animation {
    private final String score;

    /**
     * Instantiates a new Game over screen.
     *
     * @param score (Counter) - the score
     */
    public GameOverScreen(Counter score) {
        this.score = String.valueOf(score.getValue());
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(225, 15, 40));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(170, d.getHeight() / 2,
                "Game Over. Your score is " + score, 32);
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
