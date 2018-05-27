import java.util.ArrayList;
/**
 * Rectangle class.
 * @author Elor lichtziger
 */
public class Rectangle {
    private Point upLeftPoint, upRightPoint, downLeftPoint, downRightPoint;
    private double widthRec, heightRec;
    private Line top, bottom, left, right;
    /**
     * Rectangle.
     *
     * constructor.
     *
     * @param width         the rectangle's width.
     * @param height        the rectangle's height.
     * @param upperLeft     the upper - left point of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upLeftPoint = new Point(upperLeft.getX(), upperLeft.getY());
        this.widthRec = width;
        this.heightRec = height;
        this.downRightPoint = new Point(this.upLeftPoint.getX()
                + this.widthRec,  this.upLeftPoint.getY() + this.heightRec);
        this.downLeftPoint = new Point(this.upLeftPoint.getX(), this.upLeftPoint.getY() + this.heightRec);
        this.upRightPoint = new Point(this.upLeftPoint.getX() + this.widthRec, this.upLeftPoint.getY());
    }
    /**
     * intersectionPoints.
     *
     * @param line      the line that we check if intersect with the rectangle.
     * @return list of the intersection points.
     */
    public java.util.List intersectionPoints(Line line) {
        ArrayList points = new ArrayList();
        boolean flag = false;
        //create the lines of the rectangle.
        this.top = new Line(this.upLeftPoint, this.upRightPoint);
        this.bottom = new Line(this.downLeftPoint, this.downRightPoint);
        this.left = new Line(this.upLeftPoint, this.downLeftPoint);
        this.right = new Line(this.upRightPoint, this.downRightPoint);
        //check if the line is intersect with the top line.
        if (line.isIntersecting(this.top)) {
            points.add(line.intersectionWith(this.top));
        }
        //check if the line is intersect with the bootom line.
        if (line.isIntersecting(this.bottom)) {
            points.add(line.intersectionWith(this.bottom));
        }
        //check if the line is intersect with the left line.
        if (line.isIntersecting(this.left)) {
            points.add(line.intersectionWith(this.left));
        }
        //check if the line is intersect with the right line.
        if (line.isIntersecting(this.right)) {
            points.add(line.intersectionWith(this.right));
        }
        //loop that run all over the intersection points and cehck if the points are equals.
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                flag = ((Point) points.get(i)).equals((Point) points.get(j));
                if (flag) {
                    points.remove(j);
                    --j;
                    flag = false;
                }
            }
        }
        return points;
    }
    /**
     * getWidth.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.widthRec;
    }
    /**
     * getHeight.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.heightRec;
    }
    /**
     * getUpperLeft.
     *
     * @return the upper - Left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upLeftPoint;
    }
    /**
     * pointOnTop.
     *
     * @param point     point.
     *
     * @return true if the point is on the top line or false if its not.
     */
    public boolean pointOnTop(Point point) {
        return this.top.onTheLine(point);
    }
    /**
     * pointOnBottom.
     *
     * @param point     point.
     *
     * @return true if the point is on the bottom line or false if its not.
     */
    public boolean pointOnBottom(Point point) {
        return this.bottom.onTheLine(point);
    }
    /**
     * pointOnLeft.
     *
     * @param point     point.
     *
     * @return true if the point is on the left line or false if its not.
     */
    public boolean pointOnLeft(Point point) {
        return this.left.onTheLine(point);
    }
    /**
     * pointOnRight.
     *
     * @param point     point.
     *
     * @return true if the point is on the right line or false if its not.
     */
    public boolean pointOnRight(Point point) {
        return this.right.onTheLine(point);
    }
    /**
     * updatePoints.
     *
     * @param upperLeft     the upper - left point of the rectangle.
     *
     * update all the corner - points of the rectangle.
     */
    public void updatePoints(Point upperLeft) {
        this.upLeftPoint = upperLeft;
        this.downRightPoint = new Point(this.upLeftPoint.getX()
                + this.widthRec,  this.upLeftPoint.getY() + this.heightRec);
        this.downLeftPoint = new Point(this.upLeftPoint.getX(), this.upLeftPoint.getY() + this.heightRec);
        this.upRightPoint = new Point(this.upLeftPoint.getX() + this.widthRec, this.upLeftPoint.getY());
    }
}