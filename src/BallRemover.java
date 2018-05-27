/**
 * BallRemover.
 * @author Elor lichtziger
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    /**
     * BallRemover.
     *
     * constructor.
     *
     * @param game              a game.
     */
    public BallRemover(GameLevel game) {
        this.game = game;
    }
    /**
     * hitEvent.
     *
     * constructor.
     *
     * @param beingHit              a game.
     * @param hitter      a counter, symbol how much ball there is in the game.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove the ball from the sprites list.
        this.game.removeSprite(hitter);
    }
}
