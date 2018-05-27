import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * Paddle class.
 * @author Elor lichtziger
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Point paddlePoint;
    private Rectangle rectangle;
    private DrawSurface drawSurface;
    private int speed;
    /**
     * Paddle.
     *
     * constructor.
     *
     * @param drawSurface       the drawSurface.
     * @param keyboardSensor    the keyboardSensor.
     * @param point             the upper left point of the puddle.
     * @param width             the width of the paddle.
     * @param height            the height of the paddle.
     * @param speed             the speed.
     */
    public Paddle(DrawSurface drawSurface, KeyboardSensor keyboardSensor, Point point,
                  double width, double height, int speed) {
        this.keyboard = keyboardSensor;
        this.paddlePoint = new Point(point.getX(), point.getY());
        this.rectangle = new Rectangle(this.paddlePoint, width, height);
        this.drawSurface = drawSurface;
        this.speed = speed;
    }
    /**
     * moveLeft.
     *
     * move the paddle one step left.
     *
     * @param dt    the frames per second value.
     */
    public void moveLeft(double dt) {
        //check if the paddle is not at the limits of the surface.
        if (this.rectangle.getUpperLeft().getX() - (dt * this.speed) >= 0) {
            //change the rectangle location.
            this.rectangle.getUpperLeft().changeX(this.rectangle.getUpperLeft().getX() - (dt * this.speed));
            this.rectangle.updatePoints(this.rectangle.getUpperLeft());
        } else {
            //change the rectangle location to the limit of the surface.
            this.rectangle.getUpperLeft().changeX(0);
            this.rectangle.updatePoints(this.rectangle.getUpperLeft());
        }

    }
    /**
     * moveLeft.
     *
     * move the paddle one step right.
     *
     * @param dt    the frames per second value.
     */
    public void moveRight(double dt) {
        int width = this.drawSurface.getWidth();
        //check if the paddle is near the limits of the surface.
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() + (dt * this.speed) <= width) {
            //change the rectangle location.
            this.rectangle.getUpperLeft().changeX(this.rectangle.getUpperLeft().getX() + (dt * this.speed));
            this.rectangle.updatePoints(this.rectangle.getUpperLeft());
        } else {
            //change the rectangle location to the limit of the surface.
            this.rectangle.getUpperLeft().changeX(width - this.rectangle.getWidth());
            this.rectangle.updatePoints(this.rectangle.getUpperLeft());
        }
            }
    /**
     * timePassed.
     *
     * move the paddle one step.
     *
     * @param dt    the frames per second value.
     */
    public void timePassed(double dt) {
        //check if the left key or right key clicked.
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }
    /**
     * drawOn.
     *
     * draw the paddle on the surface.
     *
     * @param d     the surface object.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        d.setColor(new Color(0xB2AE00));
        d.fillRectangle(x, y, width, height);
    }
    /**
     * getCollisionRectangle.
     *
     * @return the rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * hit.
     *
     * @param hitter            the ball that hit the paddle.
     * @param currentVelocity   the current volocity of the ball.
     * @param collisionPoint    the collision point between the paddle and the ball.
     *
     * @return the velocity after the ball and the paddle collided.
     */
    public void hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        boolean left, right, top, bottom;
        double speedHit = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                + currentVelocity.getDy() * currentVelocity.getDy());
        //check in which line the collision point is.

    }
    /**
     * addToGame.
     *
     * Add the paddle to the game.
     *
     * @param g     the game object.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * changePaddlePoint.
     *
     * change the paddle start point.
     *
     * @param startPoint     a Point.
     */
    public void changePaddlePoint(Point startPoint) {
        //update the paddle point to the start point of the paddle.
        this.paddlePoint = new Point(startPoint.getX(), startPoint.getY());
        //update the rectangle upperLeft point to the start point of the paddle.
        this.rectangle.updatePoints(this.paddlePoint);
    }
    public int getPaddleCenterX() {
        return (int)(this.paddlePoint.getX() + (this.rectangle.getWidth() / 2));
    }
    public int getPaddleCenterY() {
        return (int)(this.paddlePoint.getY() - 10);
    }
}