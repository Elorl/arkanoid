import biuoop.DrawSurface;

import java.awt.*;

/**
 * ScoreIndicator.
 * @author Elor lichtziger
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    /**
     * ScoreIndicator.
     *
     * constructor.
     *
     * @param score   a Counter.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**
     * drawOn.
     *
     * Draw the score indicator.
     *
     * @param d the drawSurface.
     */
    public void drawOn(DrawSurface d) {
        String gameScore = String.format("Score: %s", this.score.getValue());
        d.setColor(Color.black);
        d.drawText(350, 15, gameScore, 15);
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
     * add the scoreIndicator to the Sprites list in the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
