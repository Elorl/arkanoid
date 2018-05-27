import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment.
 * @author Elor lichtziger
 */
public class GameEnvironment {
    private List<Collidable> collideObjects;
    /**
     * GameEnvironment.
     *
     * constructor.
     */
    public GameEnvironment() {
        this.collideObjects = new ArrayList<>();
    }
    /**
     * addCollidable.
     *
     * add the given collidable to the environment.
     *
     * @param c     the collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collideObjects.add(c);
    }
    /**
     * getClosestCollision.
     *
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory    the line start where the ball at and end in the next step point.
     *
     * @return collision point info.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double temp, d;
        int pointIndex = 0, collideIndex;
        Point start = trajectory.start(), end = trajectory.end();
        d = start.distance(end);
        //array of points.
        ArrayList points = new ArrayList<Point>(), index = new ArrayList<>();
        //loop that runs all over the collidable objects and check if they are collide with the ball
        for (int i = 0; i < this.collideObjects.size(); i++) {
            Point intersection;
            Collidable collide = (Collidable) this.collideObjects.get(i);
            Rectangle rect = collide.getCollisionRectangle();
            //get the closest intersection points between the collidable object and the ball.
            intersection = trajectory.closestIntersectionToStartOfLine(rect);
            //check if there is intersection point.
            if (intersection != null) {
                points.add(intersection);
                //save the collidable object index.
                index.add(i);
            }
        }
        //check if there is intersection points.
        if (points.isEmpty()) {
            return null;
        } else {
            //loop that runs all over the intersection points and find the closest one.
            for (int i = 0; i < points.size(); i++) {
                Point intersectPoint = (Point) points.get(i);
                temp = start.distance(intersectPoint);
                if (temp <= d) {
                    d = temp;
                    pointIndex = i;
                }
            }
            //find out which collidable object is collid with the ball first.
            collideIndex = (int) index.get(pointIndex);
            Collidable collidableInfo = (Collidable) this.collideObjects.get(collideIndex);
            return new CollisionInfo(collidableInfo, trajectory);
        }
    }
    /**
     * removeFromList.
     *
     * remove the collidable object from the list if the list contains the object.
     *
     * @param c     a Collidable.
     */
    public void removeFromList(Collidable c) {
        if (this.collideObjects.contains(c)) {
            this.collideObjects.remove(c);
        }
    }
}