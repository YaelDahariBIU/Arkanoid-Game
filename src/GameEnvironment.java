import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameEnvironment {
    private List<Collidable> objects;
    public GameEnvironment() {
        this.objects = new ArrayList<>();
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.objects.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidable objects
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point common;
        Rectangle rect;
        List<CollisionInfo> inters = new ArrayList<>();
        for (int i = 0; i < getNumOfObjects(); i++) {
            rect = this.objects.get(i).getCollisionRectangle();
            common =
            trajectory.closestIntersectionToStartOfLine(rect);
            if (common != null) {
                inters.add(new CollisionInfo(common, this.objects.get(i)));
            }
        }
        if (inters.size() == 0) {
            return null;
        }
        Collidable object = inters.get(0).collisionObject();
        Point closest = inters.get(0).collisionPoint();
        double min = trajectory.start().distance(closest), distance;
        for (CollisionInfo col : inters) {
            distance = trajectory.start().distance(col.collisionPoint());
            if (distance < min && distance != -1) {
                object = col.collisionObject();
                closest = col.collisionPoint();
                min = trajectory.start().distance(closest);
            }
        }
        if (min == -1) {
            return null;
        }
        return new CollisionInfo(closest, object);
    }
    public int getNumOfObjects() {
        return this.objects.size();
    }

}