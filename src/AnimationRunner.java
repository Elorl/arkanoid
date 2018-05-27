import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * AnimationRunner.
 * @author Elor lichtziger
 */
public class AnimationRunner {
    private GUI gui;
    private double frameRate;
    private biuoop.Sleeper sleeper;
    private double dt;
    /**
     * AnimationRunner.
     *
     * constructor.
     *
     * @param gui   a gui.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.frameRate = 60.0;
        this.sleeper = new biuoop.Sleeper();
        this.dt = 1.0 / this.frameRate;
    }
    /**
     * run.
     *
     * the function that run the animation.
     *
     * @param animation   an animation object.
     */
    public void run(Animation animation) {
        double millisecondsPerFrame =  1000 / this.frameRate;
        //check if the animation should stop.
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            //run the actual animation.
            animation.doOneFrame(d, this.dt);
            //show the animation on the screen.
            this.gui.show(d);
            //timing.
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = (long) millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}