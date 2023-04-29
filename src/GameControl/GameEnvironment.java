// 325166510 Yael Dahari
package GameControl;
import CollisionDetection.Collidable;
import CollisionDetection.CollisionInfo;
import GameObjects.Paddle;
import GeometryPrimitives.Line;
import GeometryPrimitives.Point;
import GeometryPrimitives.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of many objects a GameObjects.Ball can collide with.
 */
public class GameEnvironment {
    static final int EMPTY = 0;
    static final int INVALID = -1;
    static final int FIRST = 0;
    static final int PADDLE_WIDTH = 150;
    private final List<Collidable> objects;

    /**
     * Instantiates a new GameControl.Game environment.
     */
    public GameEnvironment() {
        this.objects = new ArrayList<>();
    }

    /**
     * The method adds the given collidable to this environment.
     *
     * @param c (CollisionDetection.Collidable) - the collidable object
     */
    public void addCollidable(Collidable c) {
        this.objects.add(c);
    }

    /**
     * If this object will not collide with any of the collidable objects in
     * this collection, return null. Else, return the information about
     * the closest collision that is going to occur.
     *
     * @param trajectory (GeometryPrimitives.Line) - the trajectory line
     * @return (CollisionDetection.CollisionInfo) - the closest collision's
     * information
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point common;
        Rectangle rect;
        List<CollisionInfo> inters = new ArrayList<>();
        for (Collidable collidable : this.objects) {
            rect = collidable.getCollisionRectangle();
            common = trajectory.closestIntersectionToStartOfLine(rect);
            if (common != null) {
                inters.add(new CollisionInfo(common, collidable));
            }
        }
        if (inters.size() == EMPTY) {
            return null;
        }
        Collidable object = inters.get(FIRST).collisionObject();
        Point closest = inters.get(FIRST).collisionPoint();
        double min = trajectory.start().distance(closest), distance;
        for (CollisionInfo col : inters) {
            distance = trajectory.start().distance(col.collisionPoint());
            if (distance < min && distance != INVALID) {
                object = col.collisionObject();
                closest = col.collisionPoint();
                min = trajectory.start().distance(closest);
            }
        }
        if (min == INVALID) {
            return null;
        }
        return new CollisionInfo(closest, object);
    }

    /**
     * The method goes over the collidable objects in the game until it finds
     * the paddle,which is the only object with a 150 width and returns it.
     *
     * @return (Paddle) - the game's paddle;
     */
    public Paddle getPaddle() {
        for (Collidable object : this.objects) {
            if (object.getCollisionRectangle().getWidth() == PADDLE_WIDTH) {
                return (Paddle) object;
            }
        }
        return null;
    }
}