import java.util.ArrayList;
import java.util.List;

/**
 * LevelInformation.
 * @author Elor lichtziger
 */
public interface LevelInformation {
    /**
     * paddleSpeed.
     *
     * @return the paddle's speed.
     */
    int paddleSpeed();
    /**
     * paddleWidth.
     *
     * @return the paddle's width.
     */
    int paddleWidth();
    /**
     * levelName.
     *
     * @return the level's name.
     */
    String levelName();
    /**
     * getBackground.
     *
     * @return a sprite that contains the background.
     */
    Sprite getBackground();
    /**
     * blocks.
     *
     * @return a list of blocks.
     */
    List<ArrayList<Block>> blocks();
    /**
     * numberOfBlocksToRemove.
     *
     * @return the number of blocks in the level.
     */
    int numberOfBlocksToRemove();
    void changeV(Velocity v);
    void createBlocks();
}