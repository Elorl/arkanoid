import biuoop.DrawSurface;

/**
 * Animation.
 * @author Elor lichtziger
 */
public interface Animation {
    /**
     * doOneFrame.
     *
     * is in charge of the logic that belong to this animation.
     *
     * @param d     a drawsurface.
     * @param dt    the frames per second value.
     */
    void doOneFrame(DrawSurface d, double dt);
    /**
     * shouldStop.
     *
     * is in charge of stopping condition of this animation.
     *
     * @return      true if the animation should stop and false if isn't.
     */
    boolean shouldStop();
}