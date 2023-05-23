package GameControl;

import CollisionControl.HitListener;
import GameObjects.Ball;
import GameObjects.Block;
import GeometryPrimitives.Point;

public class BallRemover implements HitListener {
    static final Point p = new Point(0, 590);
    private Game game;
    private Counter remainingBalls;
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCollisionRectangle().getUpperLeft().equals(p)) {
            this.remainingBalls.decrease(1);
            hitter.removeFromGame(this.game);
        }
    }
    public void addBall() {
        this.remainingBalls.increase(1);
    }
}
