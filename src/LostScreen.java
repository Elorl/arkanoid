import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * LostScreen.
 * @author Elor lichtziger
 */
public class LostScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;
    /**
     * LostScreen.
     *
     * constructor.
     *
     * @param k         the keboardSensor.
     * @param score     counter of the game's score.
     */
    public LostScreen(KeyboardSensor k, int score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
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
        d.setColor(new Color(255, 174, 157));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.fillOval(550, 300, 70, 35);
        d.fillOval(640, 300, 70, 35);
        d.setColor(new Color(63, 100, 219));
        d.fillCircle(580, 310, 10);
        d.fillCircle(665, 310, 10);
        d.setColor(new Color(255, 0, 0));
        d.fillOval(575, 360, 120, 30);
        d.setColor(new Color(255, 174, 157));
        d.fillOval(575, 375, 120, 40);


        d.setColor(Color.black);
        d.fillOval(285, 188, 250, 20);
        d.setColor(new Color(255, 255, 255));
        d.drawText(300, 200, "You lost", 50);
        d.setColor(Color.black);
        d.drawText(60, 580, "Your final score: " + this.score, 20);
        d.drawText(560, 580, "Press space to continue", 15);

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