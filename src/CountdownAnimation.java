import biuoop.DrawSurface;

import java.awt.*;

/**
 * CountdownAnimation.
 * @author Elor lichtziger
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds, iterations;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Boolean stop;
    private biuoop.Sleeper sleeper;
    /**
     * CountdownAnimation.
     * constructor.
     *
     * @param numOfSeconds  How much seconds to wait until the level will start.
     * @param countFrom     from which number start to count.
     * @param gameScreen    a sprite.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new biuoop.Sleeper();
        this.stop = false;
        this.iterations = countFrom;
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
        //the term to stop the countdown.
        if (this.countFrom == 0) {
            this.stop = true;
        }
        long milliSecondLeftToSleep;
        //convert the number to string.
        String countdown = String.format("%s", this.countFrom);
        long startTime = System.currentTimeMillis(); // timing
        this.gameScreen.drawAllOn(d);
        //draw the background and the counter.
        d.setColor(new Color(4, 10, 34));
        d.fillCircle(388, 290, 30);
        d.setColor(new Color(253, 253, 255));
        d.drawText(380, d.getHeight() / 2, countdown, 32);
        long usedTime = System.currentTimeMillis() - startTime;
        long howMuchTime = (long) (this.numOfSeconds / this.countFrom);
        if (this.countFrom == this.iterations) {
            milliSecondLeftToSleep = 0;
        } else {
            milliSecondLeftToSleep =  (long) (this.numOfSeconds  / this.iterations) * 1000;
        }
        if (milliSecondLeftToSleep > 0) {
            this.sleeper.sleepFor(milliSecondLeftToSleep);
        }
        this.countFrom--;
    }
    /**
     * shouldStop.
     *
     * is in charge of stopping condition of this animation.
     *
     * @return      true if the animation should stop and false if isn't.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}