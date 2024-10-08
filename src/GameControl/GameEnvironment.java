// 325166510 Yael Dahari
package GameControl;
import CollisionControl.Collidable;
import CollisionControl.CollisionInfo;
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
     * @return (CollisionDetection.CollisionInfo) - the closest collision's information
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
     * The method returns the first collidable from the game's list of objects.
     * According to my implementation of the "initialize()" method in Game, the
     * first object in said list is always the paddle.
     *
     * @return (Paddle) - the game's paddle;
     */
    public Paddle getPaddle() {
        return (Paddle) this.objects.get(FIRST);
    }

    /**
     * The method removes a given collidable from the list of objects.
     *
     * @param c (Collidable) - the given object
     */
    public void removeCollidable(Collidable c) {
        this.objects.remove(c);
    }
}