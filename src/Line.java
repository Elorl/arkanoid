import java.util.List;

/**
 * Line class.
 * @author Elor lichtziger
 */
public class Line {
    private double m, n;
    private Point start, end;
    /**
     * Point.
     *
     * class Line's secondary constructor.
     *
     * @param start    the start point of the line.
     * @param end    the end point of the line.
     */
    public Line(Point start, Point end) {
        this(start.getX(), start.getY(), end.getX(), end.getY());
    }
    /**
     * Point.
     *
     * class Line's main constructor.
     *
     * @param x1    the x coordinate of the start point.
     * @param y1    the y coordinate of the start point.
     * @param x2    the x coordinate of the end point.
     * @param y2    the y coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        double temp;
        //check if the slope is infinite.
        if (x2 - x1 == 0) {
            this.m = Double.POSITIVE_INFINITY;

        } else {
            this.m = (y2 - y1) / (x2 - x1);
        }
        this.n = y1 - (m * x1);
        //create the start and end point objects.
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * length.
     *
     * @return  Return the length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }
    /**
     * middle.
     *
     * @return  Return the middle point of the line.
     */
    public Point middle() {
        Point middlePoint;
        double middleX, middleY;
        //calculate the line's middle x and y values.
        middleX = (this.start.getX() + this.end.getX()) / 2;
        middleY = (this.start.getY() + this.end.getY()) / 2;
        //create a new point object.
        middlePoint = new Point(middleX, middleY);
        return middlePoint;
    }
    /**
     * start.
     *
     * @return  Return the start point of the line.
     */
    public Point start() {
        return this.start;
    }
    /**
     * end.
     *
     * @return  Return the end point of the line.
     */
    public Point end() {
        return this.end;
    }
    /**
     * isIntersecting.
     *
     * check if two lines are intersecting with each other.
     *
     * @param other    line.
     *
     * @return  Returns true if the lines intersect, false otherwise. */
    public boolean isIntersecting(Line other) {
        double intersectionX, intersectionY = 0;
        Point tempPoint;
        boolean flagThis = false, flagOther = false;
        //check if the x coordinates are sorted.
        if (this.start.getX() > this.end.getX()) {
            flagThis = true;
            this.changePoints(flagThis);
        }
        if (other.start.getX() > other.end.getX()) {
            flagOther = true;
            other.changePoints(flagOther);
        }
        //check if the slope is infinity so the line is like "x = num".
        if (this.m == Double.POSITIVE_INFINITY || other.m == Double.POSITIVE_INFINITY) {
            //check if just one of the slops is infinite.
            if (this.m == Double.POSITIVE_INFINITY && other.m != Double.POSITIVE_INFINITY) {
                //check if the x value of the line("x = num") is not between the x values of the other line.
                if (this.start.getX() < other.start.getX() || this.start.getX() > other.end.getX()) {
                    this.changePoints(flagThis);
                    other.changePoints(flagOther);
                    return false;
                }
                intersectionY = (other.m * this.start.getX()) + other.n;
                //check if just one of the slops is infinite.
            } else if (this.m != Double.POSITIVE_INFINITY && other.m == Double.POSITIVE_INFINITY) {
                //check if the x value of the line("x = num") is not between the x values of the other line.
                if (other.start.getX() < this.start.getX() || other.start.getX() > this.end.getX()) {
                    this.changePoints(flagThis);
                    other.changePoints(flagOther);
                    return false;
                }
                intersectionY = (this.m * other.start.getX()) + this.n;
            }
            //check if the y value of intersecting point is not between the y values of the other line.
            if ((intersectionY >= other.start.getY() && intersectionY <= other.end.getY())
                    || (intersectionY >= other.end.getY() && intersectionY <= other.start.getY())) {
                //check if the y value of intersecting point is not between the y values of the this line.
                boolean valReturn =  ((intersectionY >= this.start.getY() && intersectionY <= this.end.getY())
                        || (intersectionY >= this.end.getY() && intersectionY <= this.start.getY()));
                this.changePoints(flagThis);
                other.changePoints(flagOther);
                return valReturn;
                //the y value of intersecting point is not between the y values of the lines.
            } else {
                this.changePoints(flagThis);
                other.changePoints(flagOther);
                return false;
            }
            //check if the slopes are equals.
        } else if (this.m == other.m) {
            this.changePoints(flagThis);
            other.changePoints(flagOther);
            return false;
            //the lines are not parallel or parallel to y axe.
        } else {
            intersectionX = (other.n - this.n) / (this.m - other.m);
            boolean valReturn = ((intersectionX >= this.start.getX()) && (intersectionX <= this.end.getX())
                    && (intersectionX >= other.start.getX()) && (intersectionX <= other.end.getX()));
            this.changePoints(flagThis);
            other.changePoints(flagOther);
            return valReturn;
        }
    }
    /**
     * intersectionWith.
     *
     * @param other    point.
     *
     * @return  Returns the intersection point if the lines intersect and null otherwise. */
    public Point intersectionWith(Line other) {
        double intersectionX = 0, intersectionY = 0;
        //check if the line are intersecting.
        if (this.isIntersecting(other)) {
            //check if one of the slopes is infinite so the line is like "x = num".
            if (this.m == Double.POSITIVE_INFINITY || other.m == Double.POSITIVE_INFINITY) {
                if (this.m == Double.POSITIVE_INFINITY && other.m != Double.POSITIVE_INFINITY) {
                    intersectionX = this.start.getX();
                    intersectionY = (other.m * intersectionX) + other.n;
                    intersectionX = Math.round(intersectionX * Math.pow(10, 10)) / Math.pow(10, 10);
                    intersectionY = Math.round(intersectionY * Math.pow(10, 10)) / Math.pow(10, 10);
                } else {
                    intersectionX = other.start.getX();
                    intersectionY = (this.m * intersectionX) + this.n;
                    intersectionX = Math.round(intersectionX * Math.pow(10, 10)) / Math.pow(10, 10);
                    intersectionY = Math.round(intersectionY * Math.pow(10, 10)) / Math.pow(10, 10);
                } //check if the lines are not parallel to y axes.
            } else {
                intersectionX = (other.n - this.n) / (this.m - other.m);
                intersectionY = (this.m) * intersectionX + this.n;
                intersectionX = Math.round(intersectionX * Math.pow(10, 10)) / Math.pow(10, 10);
                intersectionY = Math.round(intersectionY * Math.pow(10, 10)) / Math.pow(10, 10);
            }
            //create new point object.
            Point intersection = new Point(intersectionX, intersectionY);
            return intersection;
        }
        return null;
    }
    /**
     * equals.
     *
     * @param other    line.
     *
     * @return  return true is the lines are equal, false otherwise. */
    public boolean equals(Line other) {
        boolean equalLine;
        //check if the the start point of this line is equals to a point in the other line
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return ((this.end.equals(other.start)) || (this.end.equals(other.end)));
        } else {
            return false;
        }
    }
    /**
     * changePoints.
     *
     * change between the start and end points of one line.
     *
     * @param flag    boolean variable that say to the method if to change the points.
     *
     */
    public void changePoints(boolean flag) {
        Point tempPoint;
        if (flag) {
            tempPoint = this.start;
            this.start = this.end;
            this.end = tempPoint;
        }
    }
    /**
     * closestIntersectionToStartOfLine.
     *
     * @param rect    Rectangle.
     *
     * @return The closest point to the start point of a line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //get the intersection points list between the rectangle and the line.
        List intersectionPoints = rect.intersectionPoints(this);
        int sizeOfList = intersectionPoints.size();
        double d = this.distanceLine(), tempD;
        //check if there is no intersection point.
        if (intersectionPoints.isEmpty()) {
            return null;
        } else {
            Point realPoint = null;
            //loop that runs all over the intersection points and return the closest one.
            for (int i = 0; i < sizeOfList; i++) {
                Point tempPoint = (Point) intersectionPoints.get(i);
                tempD = this.start.distance(tempPoint);
                if (tempD <= d) {
                    d = tempD;
                    realPoint = tempPoint;
                }
            }
            return realPoint;
        }
    }
    /**
     * onTheLine.
     *
     * @param point    point.
     *
     * @return True if the point is on the line, false if is not.
     */
    public boolean onTheLine(Point point) {
        double y1, y2, x1, x2;
        //check if the points are sorted by the x values.
        if (this.start.getX() <= this.end.getX()) {
            x1 = this.start.getX();
            x2 = this.end.getX();
        } else {
            x1 = this.end.getX();
            x2 = this.start.getX();
        }
        //check if the points are sorted by the y values.
        if (this.start.getY() <= this.end.getY()) {
            y1 = this.start.getY();
            y2 = this.end.getY();
        } else {
            y1 = this.end.getY();
            y2 = this.start.getY();
        }
        //check if the x coordinate of the point is in the range of the line's x coordinates.
        if (point.getX() >= x1 && point.getX() <= x2) {
            //check if the line is like "x=5".
            if ((this.m == Double.POSITIVE_INFINITY))  {
                //check if the y coordinate of the point is in the range of the line's y coordinates.
                return ((point.getY() >= y1) && (point.getY() <= y2));
            } else {
                //check if the x coordinate of the point is in the range of the line's x coordinates.
                return (point.getY() == (this.m * point.getX()) + this.n);
            }
        } else {
            return false;
        }
    }
    /**
     * distanceLine.
     *
     * @return the distance between the start and end point of the line.
     */
    public double distanceLine() {
        return (this.start.distance(this.end));
    }
}