// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.

import java.util.ArrayList;
import java.util.List;

/**
 * BlockRemover.
 * @author Elor lichtziger
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    private List<ArrayList<Block>> arrayBlocks;
    /**
     * BlockRemover.
     *
     * constructor.
     *
     * @param game              the game.
     * @param removedBlocks     a counter of the remaining blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks, List<ArrayList<Block>> arrayBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
        this.arrayBlocks = arrayBlocks;
    }
    /**
     * hitEvent.
     *
     * the logic of the block while a hit occur.
     *
     * @param beingHit      the block.
     * @param hitter        the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
            beingHit.removeHitListener(this);
            this.game.removeCollidable(beingHit);
            this.game.removeSprite(beingHit);
            this.removeFromArray(beingHit);
            this.remainingBlocks.decrease(1);
    }
    public void removeFromArray(Block beingHit) {
        for(ArrayList<Block> array: this.arrayBlocks) {
            for(Block block : array) {
                if(array.contains(beingHit)) {
                    array.remove(beingHit);
                    break;
                }
            }
        }
    }
}