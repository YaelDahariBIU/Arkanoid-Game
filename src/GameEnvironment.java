// 325166510 Yael Dahari
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of many objects a Ball can collide with.
 */
public class GameEnvironment {
    static final int EMPTY = 0;
    static final int INVALID = -1;
    static final int FIRST = 0;
    private final List<Collidable> objects;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.objects = new ArrayList<>();
    }

    /**
     * The method adds the given collidable to this environment.
     *
     * @param c (Collidable) - the collidable object
     */
    public void addCollidable(Collidable c) {
        this.objects.add(c);
    }

    /**
     * If this object will not collide with any of the collidable objects in
     * this collection, return null. Else, return the information about
     * the closest collision that is going to occur.
     *
     * @param trajectory (Line) - the trajectory line
     * @return (CollisionInfo) - the closest collision's information
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
}