import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.awt.Image;
import java.util.Map;
import java.util.TreeMap;

/**
 * Block.
 * @author Elor lichtziger
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private String counter;
    private ArrayList<HitListener> listeners;
    private Color color;
    private Image image;
    private double width;
    private double height;
    private Velocity v;
    /**
     * Block.
     *
     * constructor.
     *
     * @param upperLeft    the block's upper - left point.
     * @param width        the block's width.
     * @param height       the block's height.
     * @
     */
    public Block(Point upperLeft, double width, double height, Velocity ve) {
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.listeners = new ArrayList<HitListener>();
        this.color = Color.gray;
        this.image = null;
        this.v = ve;
    }
    /**
     * getCollisionRectangle.
     *
     * @return the rectangle, the ball collision with.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * hit.
     *
     * Draw the block and the text in the center of the block on the surface.
     *
     * @param collisionPoint    the collision point between the block and the ball.
     * @param currentVelocity   the ball's velocity.
     * @param hitter            the ball that hit the block.
     * @return the velocity after the collision occur.
     */
    public void hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.counter.equals("1")) {
            this.counter = "X";
        } else if(this.counter.equals("X")){
            this.counter = "X";
        }
        //notify to the listeners that hit occurred.
        this.notifyHit(hitter);

    }
    /**
     * drawOn.
     *
     * Draw the block and the text in the center of the block on the surface.
     *
     * @param surface the surface.
     */
    public void drawOn(DrawSurface surface) {
        DrawSurface d = surface;
        //get the number of hit points.
            //check if there is a basic("fill") image.
            if (!(this.image == null)) {
                d.drawImage(this.getX(), this.getY(), this.image);
            } else {
                int w = (int) this.width;
                int y = (int) this.height;
                d.setColor(this.color);
                d.fillRectangle(this.getX(), this.getY(), w, y);
            }
    }
    /**
     * timePassed.
     *
     * @param dt    the frames per second value.
     */
    public void timePassed(double dt) {
        double x, y;
        y = this.getY() + (dt * this.v.getDy());
        x = this.getX() + (dt * this.v.getDx());
        this.changePoint(new Point(x, y));
    }
    /**
     * addToGame.
     *
     * add the block to the Sprite and the collidable classes from the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * getX.
     *
     * @return the X coordinate's point that belong to the block's rectangle.
     */
    public int getX() {
        return (int) this.rectangle.getUpperLeft().getX();
    }
    /**
     * getY.
     *
     * @return the Y coordinate's point that belong to the block's rectangle.
     */
    public int getY() {
        return (int) this.rectangle.getUpperLeft().getY();
    }
    /**
     * addHitListener.
     *
     * Add hl as a listener to hit events.
     *
     * @param hl     a HitListener.
     */
    public void addHitListener(HitListener hl) {
        //check if hl listener is already exist in the list.
        if ((!this.listeners.contains(hl)) || this.listeners.isEmpty()) {
            this.listeners.add(hl);
        }
    }
    /**
     * removeHitListener.
     *
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl     a HitListener.
     */
    public void removeHitListener(HitListener hl) {
        //check if the listener is exist in the list.
        if (this.listeners.contains(hl)) {
            this.listeners.remove(hl);
        }
    }
    /**
     * notifyHit.
     *
     * notify all the listeners that hit was occur.
     *
     * @param hitter    a ball that made the hit.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listenersCopy = new ArrayList<HitListener>(this.listeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listenersCopy) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * setImage.
     *
     * set image to the block.
     *
     * @param img     an image.
     */
    public void setImage(Image img) {
        this.image = img;
    }
    /**
     * setCounter.
     *
     * set a string into the block
     *
     * @param c  string that will show on the block.
     */
    public void setCounter(String c) {
        this.counter = c;
    }
    public void setColor(Color c) {
        this.color = c;
    }
    public void changeV(Velocity v) {
        this.v = v;
    }
    public void changePoint(Point p) {
        this.rectangle.updatePoints(p);
    }
    public Velocity getVelocity() {
        return this.v;
    }
    public double getWidth() {
        return this.width;
    }
    public void changeDx () {
        this.v.changeDx();
    }
}
