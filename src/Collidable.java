/**
 * Collidable.
 * @author Elor lichtziger
 */
public interface Collidable {
    /**
     * getCollisionRectangle.
     *
     * @return  the rectangle the collision occur with him.
     */
    Rectangle getCollisionRectangle();
    /**
     * hit.
     *
     * @param currentVelocity   the ball's current velocity.
     * @param collisionPoint    the collision point between the ball and the collidable object.
     * @param hitter            the ball that hit the object.
     *
     * @return the velocity after the hit with the Collidable object.
     */
    void hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}