/**
 * CollisionInfo.
 * @author Elor lichtziger
 */
public class CollisionInfo {
    private Rectangle rect;
    private Collidable collide;
    private Line line;
    /**
     * CollisionInfo.
     *
     * constructor.
     *
     * @param c     the Collidable object the collision occur with.
     * @param l     the line that collide with the collidable object.
     */
    public CollisionInfo(Collidable c, Line l) {
        this.collide = c;
        this.rect = c.getCollisionRectangle();
        this.line = l;
    }
    /**
     * CollisionInfo.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.line.closestIntersectionToStartOfLine(this.rect);
    }
    /**
     * CollisionInfo.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collide;
    }
}