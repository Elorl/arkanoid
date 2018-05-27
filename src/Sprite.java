import biuoop.DrawSurface;

/**
 * Sprite class.
 * @author Elor lichtziger
 */
public interface Sprite {
    /**
     * drawOn.
     *
     * draw the sprite to the screen.
     *
     * @param d     the surface.
     */
    void drawOn(DrawSurface d);
    /**
     * timePassed.
     *
     * notify the sprite that time has passed and move the sprite a step.
     *
     * @param dt    the frames per second value.
     */
    void timePassed(double dt);
}