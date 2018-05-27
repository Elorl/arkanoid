import biuoop.DrawSurface;

import java.awt.*;

/**
 * LivesIndicator.
 * @author Elor lichtziger
 */
public class LivesIndicator implements Sprite {
    private Counter numLife;
    /**
     * LivesIndicator.
     *
     * constructor.
     *
     * @param numLife   a Counter.
     */
    public LivesIndicator(Counter numLife) {
        this.numLife = numLife;
    }
    /**
     * drawOn.
     *
     * Draw the live indicator.
     *
     * @param d the drawSurface.
     */
    public void drawOn(DrawSurface d) {
        String gamelife = String.format("Lives: %s", this.numLife.getValue());
        d.setColor(Color.black);
        d.drawText(50, 15, gamelife, 15);
    }
    /**
     * timePassed.
     *
     * @param dt    the frames per second value.
     */
    public void timePassed(double dt) {
        return;
    }
    /**
     * addToGame.
     *
     * add the livesIndicator to the Sprites list in the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
