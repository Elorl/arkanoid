/**
 * Velocity class.
 * @author Elor lichtziger
 */
public class Velocity {
    private double dxVelocity, dyVelocity;
    /**
     * Point.
     *
     * class Line's constructor.
     *
     * @param dx    the delta of x values.
     * @param dy    the delta of y values.
     */
    public Velocity(double dx, double dy) {
        this.dxVelocity = dx;
        this.dyVelocity = dy;
    }
    /**
     * applyToPoint.
     *
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p    point.
     *
     * @return the apple point.
     */
    public Point applyToPoint(Point p) {
        Point newP;
        double x = p.getX() + this.dxVelocity;
        double y = p.getY() + this.dyVelocity;
        newP = new Point(x, y);
        return newP;
    }
    /**
     * Velocity.
     *
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param angle    the move angle.
     * @param speed    the move speed.
     *
     * @return velocity object with dx and dy values.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, -dy);
    }
    /**
     * changeDx.
     *
     * change the dx value to the negative value of dx.
     *
     */
    public void changeDx() {
        this.dxVelocity = -dxVelocity;
        this.dxVelocity = this.dxVelocity + (this.dxVelocity / 10);
    }
    /**
     * changeDy.
     *
     * change the dy value to the negative value of dy.
     *
     */
    public void changeDy() {
        this.dyVelocity = -dyVelocity;
    }
    /**
     * getDx.
     *
     * @return the Dx value.
     */
    public double getDx() {
        return this.dxVelocity;
    }
    /**
     * getDx.
     *
     * @return the Dy value.
     */
    public double getDy() {
        return this.dyVelocity;
    }
}