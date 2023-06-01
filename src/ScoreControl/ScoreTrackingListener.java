// 325166510 Yael Dahari
package ScoreControl;
import CollisionControl.HitListener;
import GameControl.Counter;
import GameObjects.Ball;
import GameObjects.Block;

/**
 * The Score tracking listener updates the score counter when blocks are being
 * hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    static final int SCORE = 5;
    static final int BONUS = 100;
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter (Counter) - the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(SCORE);
    }

    /**
     * The method increases the score by a bonus.
     */
    public void bonusEvent() {
        this.currentScore.increase(BONUS);
    }
}
