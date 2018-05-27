/**
 * ScoreTrackingListener.
 * @author Elor lichtziger
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * ScoreTrackingListener.
     *
     * constructor.
     *
     * @param scoreCounter   a Counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * hitEvent.
     *
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit  the block that being hit.
     * @param hitter    the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
            this.currentScore.increase(100);
    }
}