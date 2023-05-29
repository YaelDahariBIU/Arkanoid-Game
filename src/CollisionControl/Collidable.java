// 325166510 Yael Dahari
package CollisionControl;
import GameObjects.Ball;
import GeometryPrimitives.Point;
import GeometryPrimitives.Rectangle;
import Movement.Velocity;

/**
 * The CollisionDetection.Collidable interface will be used by things that can
 * be collided with.
 */
public interface Collidable {
    /**
     * The method returns the "collision shape" of the object.
     *
     * @return (GeometryPrimitives.Rectangle) - the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * The method notify the object that the ball hdd collided with it at
     * collisionPoint with a given velocity and returns the new velocity
     * expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter (Ball) - the ball that's hitting the collidable
     * @param collisionPoint (GeometryPrimitives.Point) - the collision point
     * @param currentVelocity (Movement.Velocity) - the current velocity
     * @return (Movement.Velocity) - the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}