import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GameLevel.
 * @author Elor lichtziger
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Ball ball;
    private Paddle paddle;
    private Counter remainedBlocks, remainedBalls, score, numLife;
    private Point paddleStartPoint;
    private Boolean running;
    private AnimationRunner runner;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInfo;
    private DrawSurface drawSurface;
    private Image img;
    private long currentTime;
    private moveBlocks moveBlocks;
    /**
     * GameLevel.
     *
     * constructor.
     *
     * @param levelInfo         the information of the level.
     * @param keyboardSensor    the keboardSensor.
     * @param animationRunner   an animationRunner.
     * @param drawSurface       the drawSurface.
     * @param score             counter of the game's score.
     * @param numLife           counter of the remained life.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     DrawSurface drawSurface, Counter score, Counter numLife) {
        this.levelInfo = levelInfo;
        this.drawSurface = drawSurface;
        this.keyboard = keyboardSensor;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainedBlocks = new Counter();
        this.remainedBalls = new Counter();
        this.score = score;
        this.numLife = numLife;
        this.paddleStartPoint = new Point((this.drawSurface.getWidth() / 2) - (this.levelInfo.paddleWidth() / 2), 590);
        this.runner = animationRunner;
        this.running = true;
        this.currentTime = System.currentTimeMillis();
    }
    /**
     * addCollidable.
     *
     * add a collidable object to the collidables list.
     *
     * @param c a collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * addSprite.
     *
     * add a sprite object to the sprites list.
     *
     * @param s a sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * initialize.
     *
     * initialize the game objects.
     */
    public void initialize() {
        //create paddle.
        this.addSprite(this.levelInfo.getBackground());
        double paddleHeight = 10;
        this.paddle = new Paddle(this.drawSurface, this.keyboard, this.paddleStartPoint,
                this.levelInfo.paddleWidth(), paddleHeight, this.levelInfo.paddleSpeed());
        paddle.addToGame(this);
        //create the blocks in the edge of the window.
        Velocity v = new Velocity(0,0);
        Block top = new Block(new Point(0, 18), 800, 2, v);
        Block bottom = new Block(new Point(0, 615), 800, 10, v);

        //add the blocks to the game.
        top.setColor(java.awt.Color.getHSBColor(10, 0, 50));
        top.addToGame(this);
        bottom.addToGame(this);
        //set a text to the blocks.
        top.setCounter("X");
        bottom.setCounter("X");
        //create the score and life indicators.
        ScoreIndicator scoreindicator = new ScoreIndicator(this.score);
        scoreindicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.numLife);
        livesIndicator.addToGame(this);
        //add listener to the removeBlocks and scoreListener.
        HitListener scoreListener = new ScoreTrackingListener(this.score);
        //create the blocks in the level.
        this.levelInfo.createBlocks();
        HitListener removeBlock = new BlockRemover(this, this.remainedBlocks, this.levelInfo.blocks());
        this.createBlocks(this.levelInfo.blocks(), removeBlock, scoreListener);
        BallRemover removeBall = new BallRemover(this);
        for(ArrayList<Block> array : this.levelInfo.blocks()) {
            for (Block block : array) {
                //add the listener.
                block.addHitListener(removeBall);
                block.addHitListener(removeBlock);
                block.addHitListener(scoreListener);
            }
        }
        this.moveBlocks = new moveBlocks(this.levelInfo.blocks());
    }
    /**
     * doOneFrame.
     *
     * is in charge of the logic that belong to this animation.
     *
     * @param d     a drawsurface.
     * @param dt    the frames per second value.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            long updateTime = System.currentTimeMillis() - this.currentTime;
            long  milliSecondLeftToSleep = 350 - updateTime;
            if(milliSecondLeftToSleep <= 0) {
                int x = this.paddle.getPaddleCenterX();
                int y = this.paddle.getPaddleCenterY() - 10;
                Velocity v = new Velocity(0, -400);
                Ball b = new Ball(x, y, 3, Color.gray, this.environment, v);
                b.addToGame(this);
                this.currentTime = System.currentTimeMillis();
            }

        }
        //draw all the sprites.
        this.moveBlocks.move();
        this.sprites.drawAllOn(d);
        //move all the sprites.
        this.sprites.notifyAllTimePassed(dt);
        //check if the user want to pause the game.
        //check if there is still blocks in the game.
        if (this.remainedBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
    }
    /**
     * playOneTurn.
     *
     * play on turn of the user in the game.
     */
    public void playOneTurn() {
        //move the paddle to the middle of the screen.
        this.paddle.changePaddlePoint(this.paddleStartPoint);
        //run the countdown animation.
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        //loop while that create the animation.
        while (this.running) {
            this.runner.run(this);
        }
    }
    /**
     * shouldStop.
     *
     * is in charge of stopping condition of this animation.
     *
     * @return      true if the animation should stop and false if isn't.
     */
    public boolean shouldStop() {
        return (!this.running);
    }
    /**
     * createBlocks.
     *
     * create the blocks in the level.
     *
     * @param blocks            a list of blocks.
     * @param removeBlock       listener to removeBlock.
     * @param scoreListener     listener to ScoreListener.
     */
    public void createBlocks(List<ArrayList<Block>> blocks, HitListener removeBlock, HitListener scoreListener) {
        //loop that runs all over the blocks.
        for(ArrayList<Block> array : blocks) {
            for (Block block : array) {
                //add the listeners.
                block.addHitListener(removeBlock);
                block.addHitListener(scoreListener);
                block.addToGame(this);
                this.remainedBlocks.increase(1);
            }
        }
    }
    /**
     * removeCollidable.
     *
     * remove the collidable object from the list if the list contains the object.
     *
     * @param c     a Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeFromList(c);
    }
    /**
     * removeSprite.
     *
     * remove the sprite object from the list if the list contains the object.
     *
     * @param s     a sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeFromList(s);
    }
    /**
     * getRemainedBlocks.
     *
     * @return the number of remained blocks in the game.
     */
    public int getRemainedBlocks() {
        return this.remainedBlocks.getValue();
    }
    /**
     * getRRemainedLife.
     *
     * @return the number of remained life in the game.
     */
    public int getRRemainedLife() {
        return this.numLife.getValue();
    }
    public void moveBlocks() {
    }
}