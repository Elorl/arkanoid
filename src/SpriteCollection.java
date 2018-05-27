import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * SpriteCollection class.
 * @author Elor lichtziger
 */
public class SpriteCollection {
    private ArrayList sprites;
    /**
     * SpriteCollection.
     *
     * constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList();
    }
    /**
     * addSprite.
     *
     * add the sprite to the list of sprite.
     *
     * @param s     a sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * notifyAllTimePassed.
     *
     * call timePassed() on all sprites.
     *
     * @param dt    the frames per second value.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < sprites.size(); i++) {
            ((Sprite) sprites.get(i)).timePassed(dt);
        }
    }
    /**
     * drawAllOn.
     *
     * call drawOn(d) on all sprites.
     *
     * @param d     the surface.
     */
    public void drawAllOn(DrawSurface d) {
        int counter = 0;
        //loop that runs all over the sprites.
        for (int i = 0; i < sprites.size(); i++) {
            ((Sprite) sprites.get(i)).drawOn(d);
        }
    }
    /**
     * removeFromList.
     *
     * remove the Sprite object from the list if the list contains the object.
     *
     * @param s     a Sprite.
     */
    public void removeFromList(Sprite s) {
        if (this.sprites.contains(s)) {
            this.sprites.remove(s);
        }
    }
}
