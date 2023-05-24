// 325166510 Yael Dahari
package CollisionControl;
import GameObjects.Ball;
import GameObjects.Block;

/**
 * The interface Hit listener calls for action whenever a hit is made.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit (Block) - the block being hit
     * @param hitter (Ball) - the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}
