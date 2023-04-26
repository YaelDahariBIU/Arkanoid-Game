/**
 * The type Collision info.
 */
// 325166510 Yael Dahari
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint (Point) - the collision point
     * @param collisionObject (Collidable) - the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * The method returns the collision point.
     *
     * @return (Point) - the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * The method returns the collidable object involved in the collision.
     *
     * @return (Collidable) - the collidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}