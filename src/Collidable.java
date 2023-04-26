// 325166510 Yael Dahari

/**
 * The Collidable interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * The method returns the "collision shape" of the object.
     *
     * @return (Rectangle) - the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * The method notify the object that we collided with it at collisionPoint
     * with a given velocity and returns the new velocity expected after the
     * hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint (Point) - the collision point
     * @param currentVelocity (Velocity) - the current velocity
     * @return (Velocity) - the new velocity
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}