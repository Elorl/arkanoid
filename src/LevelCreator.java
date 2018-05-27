import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Level1.
 * @author Elor lichtziger
 */
public class LevelCreator implements LevelInformation {
    private int paddleSpeed, paddleWidth, numOfBlockToRemove;
    private List<ArrayList<Block>> blocks;
    private String levelNameS;
    private String levelNum;
    private Velocity v;
    private Image img;
    /**
     * Level1.
     *
     * constructor.
     */
    public LevelCreator(String s, Velocity v, Image img) {
        this.paddleSpeed = 600;
        this.paddleWidth = 100;
        this.numOfBlockToRemove = 50;
        this.levelNum = s;
        this.levelNameS = "Battle no." + this.levelNum;
        this.blocks = new ArrayList<ArrayList<Block>>();
        this.v = new Velocity(v.getDx(), v.getDy());
        this.img = img;
    }
    /**
     * paddleSpeed.
     *
     * @return the paddle's speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**
     * paddleWidth.
     *
     * @return the paddle's width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }
    /**
     * levelName.
     *
     * @return the level's name.
     */
    public String levelName() {
        return this.levelNameS;
    }
    /**
     * getBackground.
     *
     * @return a sprite that contains the background.
     */
    public Sprite getBackground() {
        return new BackgroundLevel(this.levelNameS);
    }
    /**
     * blocks.
     *
     * @return a list of blocks.
     */
    public List<ArrayList<Block>> blocks() {
        return this.blocks;
    }
    /**
     * numberOfBlocksToRemove.
     *
     * @return the number of blocks in the level.
     */
    public int numberOfBlocksToRemove() {
        return this.numOfBlockToRemove;
    }
    public void changeV(Velocity v){

    }
    public void createBlocks() {
        int x = 40, y = 40;
        for (int i = 0; i < 10; i++) {
            ArrayList<Block> columnBlocks = new ArrayList<Block>();
            x = 40 + (i * 50);
            for (int j = 0; j < 5; j++) {
                y = 40 + (j * 40);
                Velocity v = new Velocity(this.v.getDx(), this.v.getDy());
                Block block = new Block(new Point(x, y),40, 30, v);
                block.setImage(this.img);
                block.setCounter("1");
                columnBlocks.add(block);
            }
            this.blocks.add(columnBlocks);
        }
    }
}
