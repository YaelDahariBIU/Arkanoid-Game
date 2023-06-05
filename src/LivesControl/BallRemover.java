// 325166510 Yael Dahari
package LivesControl;
import CollisionControl.HitListener;
import GameControl.Counter;
import GameControl.GameLevel;
import GameObjects.Ball;
import GameObjects.Block;
import GeometryPrimitives.Point;

/**
 * The Ball remover is in charge of removing balls, and updating an
 * availabe-balls counter.
 */
public class BallRemover implements HitListener {
    static final int SINGLE = 1;
    static final Point P = new Point(0, 590);
    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game (Game) - the game
     * @param remainingBalls (Counter) - the remaining balls
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCollisionRectangle().getUpperLeft().equals(P)) {
            this.remainingBalls.decrease(SINGLE);
            hitter.removeFromGame(this.game);
        }
    }
}
