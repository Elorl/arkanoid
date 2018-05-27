import java.util.ArrayList;
import java.util.List;

public class moveBlocks {
    private List<ArrayList<Block>> arrayblocks;
    private ArrayList<Block> left;
    private Block leftBlock;
    private ArrayList<Block> right;
    private Block rightBlock;
    private Velocity v;
    public moveBlocks(List<ArrayList<Block>> arrayblocks) {
        this.arrayblocks = arrayblocks;
        this.v = new Velocity(10, 0);
    }
    public void move () {
        for(ArrayList<Block> array : arrayblocks) {
            if(!(array.isEmpty())) {
                this.left = array;
                break;
            }
        }
        for(ArrayList<Block> array : arrayblocks) {
            if(!(array.isEmpty())) {
                this.right = array;

            }
        }
        for(Block block: this.left) {
            if(!(this.left.isEmpty())) {
                this.leftBlock = block;
            }
        }
        for(Block block: this.right) {
            if(!(this.right.isEmpty())) {
                this.rightBlock = block;
            }
        }
        double leftX = this.leftBlock.getX();
        double rightX = this.rightBlock.getX() + this.rightBlock.getWidth();
        if(leftX <= 0 || rightX >= 800) {
            for (ArrayList<Block> array : arrayblocks) {
                for (Block block : array) {
                    block.changeDx();
                    int y = block.getY() + 20;
                    block.changePoint(new Point(block.getX(), y));
                }
            }
        }
    }
}
