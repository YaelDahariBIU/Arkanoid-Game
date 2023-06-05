// 325166510 Yael Dahari
package GameControl;
import CollisionControl.HitListener;
import GameObjects.Ball;
import GameObjects.Block;

/**
 * The Block remover is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    static final int SINGLE = 1;
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game (Game) - the game
     * @param remainingBlocks (Counter) - the remaining blocks
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * The method removes the blocks that are being hit, removes this listener
     * from them and updates the counter.
     *
     * @param beingHit (Block) - the block that's hit
     * @param hitter (Ball) - the ball that's hitting it
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(SINGLE);
    }
}