// 325166510 Yael Dahari
package CollisionDetection;
import GeometryPrimitives.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint (GeometryPrimitives.Point) - the collision point
     * @param collisionObject (CollisionDetection.Collidable) - the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * The method returns the collision point.
     *
     * @return (GeometryPrimitives.Point) - the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * The method returns the collidable object involved in the collision.
     *
     * @return (CollisionDetection.Collidable) - the collidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}