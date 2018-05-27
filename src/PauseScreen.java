import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * PauseScreen.
 * @author Elor lichtziger
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * PauseScreen.
     *
     * constructor.
     *
     * @param k         the keboardSensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    /**
     * doOneFrame.
     *
     * is in charge of the logic that belong to this animation.
     *
     * @param d     a drawsurface.
     * @param dt    a dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0x2C33DB));
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
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