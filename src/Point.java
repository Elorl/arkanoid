/**
 * Point class.
 * @author Elor lichtziger
 */
public class Point {
    private double pointX;
    private double pointY;

    /**
     * Point.
     * <p>
     * class point's constructor.
     *
     * @param x x coordinate value.
     * @param y y coordinate value.
     */
    public Point(double x, double y) {
        this.pointX = x;
        this.pointY = y;
    }

    /**
     * distance.
     * <p>
     * calculate the distance between one point to another.
     *
     * @param other point.
     * @return the distance between two points.
     */
    public double distance(Point other) {
        double x1 = this.pointX;
        double y1 = this.pointY;
        double x2 = other.pointX;
        double y2 = other.pointY;
        double distance = Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
        return distance;
    }

    /**
     * equals.
     * <p>
     * check if the point is equal to another one.
     *
     * @param other point.
     * @return return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        double x1 = this.pointX;
        double y1 = this.pointY;
        double x2 = other.pointX;
        double y2 = other.pointY;
        //return true if the points are equals.
        return ((x1 == x2) && (y1 == y2));
    }

    /**
     * getX.
     *
     * @return the x value of this point.
     */
    public double getX() {
        return this.pointX;
    }

    /**
     * getY.
     *
     * @return the y value of this point.
     */
    public double getY() {
        return this.pointY;
    }

    /**
     * changeX.
     * <p>
     * change the x value.
     *
     * @param x the new X value.
     */
    public void changeX(double x) {
        this.pointX = x;
    }

    /**
     * changeY.
     * <p>
     * change the Y value.
     *
     * @param y the new Y value.
     */
    public void changeY(double y) {
        this.pointY = y;
    }
}
