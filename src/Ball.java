import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Line class.
 * @author Elor lichtziger
 */
public class Ball implements Sprite {
    private Point centerPoint;
    private int radius;
    private java.awt.Color ballcolor;
    private Velocity vBall;
    private GameEnvironment environment;
    private ArrayList<HitListener> listeners;
    /**
     * Ball.
     *
     * class Ball's constructor.
     *
     * @param x         the x coordinate of the ball.
     * @param y         the y coordinate of the ball.
     * @param r         the radius of the ball.
     * @param color     the color of the ball.
     * @param e         the environment object.
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment e, Velocity v) {
        if (x == 0 && y == 0) {
            x = 1;
            y = 1;
        }
        this.centerPoint = new Point(x, y);
        this.radius = r;
        this.ballcolor = color;
        this.environment = e;
        this.listeners = new ArrayList<HitListener>();
        this.vBall = v;
    }
    /**
     * getX.
     *
     * @return the x value of ball's center point. */
    public int getX() {
        return (int) this.centerPoint.getX();
    }
    /**
     * getY.
     *
     * @return the y value of ball's center point. */
    public int getY() {
        return (int) this.centerPoint.getY();
    }
    /**
     * getSize.
     *
     * @return the ball's radius. */
    public int getSize() {
        return this.radius;
    }
    /**
     * getColor.
     *
     * @return the ball's color. */
    public java.awt.Color getColor() {
        return this.ballcolor;
    }
    /**
     * drawOn.
     *
     * draw the ball in is position.
     *
     * @param surface    the surface the balls is in.
     */
    public void drawOn(DrawSurface surface) {
        DrawSurface d = surface;
        int x =  this.getX();
        int y = this.getY();
        int r = this.getSize();
        d.setColor(this.getColor());
        d.fillCircle(x, y, r);
        d.setColor(Color.black);
        d.drawCircle(x, y, r);
    }
    /**
     * setVelocity.
     *
     * set in the ball the velocity value.
     *
     * @param v    velocity object.
     */
    public void setVelocity(Velocity v) {
        this.vBall = v;
    }
    /**
     * setVelocity.
     *
     * set in the ball the velocity value.
     *
     * @param dx    delta x value(the change in the x axe).
     * @param dy    delta y value(the change in the y axe).
     */
    public void setVelocity(double dx, double dy) {
        this.vBall = new Velocity(dx, dy);
    }
    /**
     * getVelocity.
     *
     * @return Return the velocity object.
     */
    public Velocity getVelocity() {
        return this.vBall;
    }
    /**
     * timePassed.
     *
     * move the ball forward.
     *
     * @param dt    the frames per second value.
     */
    public void timePassed(double dt) {
        double x, y;
        x = this.getX() + (dt * this.vBall.getDx());
        y = this.getY() + (dt * this.vBall.getDy());
        //calculate the point the ball will go to if the step is legal.
        Point end = new Point(x, y);
        Line trajectory = new Line(this.centerPoint, end);
        //get the CollisionInfo about the closest collision point betwen the ball and the collidables.
        CollisionInfo info = environment.getClosestCollision(trajectory);
        //check if there is no collidable object that collide with the ball.
        if (info == null) {
            this.centerPoint.changeX(x);
            this.centerPoint.changeY(y);
        } else {
            //get the collision point.
            Point collide = info.collisionPoint();
            //get the collidable object.
            Collidable collideObject = info.collisionObject();
            //calculate the new velocity.
            collideObject.hit(this, collide, this.vBall);
        }
    }
    /**
     * addToGame.
     *
     * add the ball to the Sprite's game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
