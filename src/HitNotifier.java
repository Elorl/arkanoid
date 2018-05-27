/**
 * HitNotifier.
 * @author Elor lichtziger
 */
public interface HitNotifier {
    /**
     * addHitListener.
     *
     * Add hl as a listener to hit events.
     *
     * @param hl    a hitListener.
     */
    void addHitListener(HitListener hl);
    /**
     * removeHitListener.
     *
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl    a hitListener.
     */
    void removeHitListener(HitListener hl);
}