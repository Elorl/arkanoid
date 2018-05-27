import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * HighScoresAnimation.
 * @author Elor lichtziger
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable table;
    private java.util.List<ScoreInfo> scoreInfos;
    private String endKey;
    private Boolean stop;
    private KeyboardSensor keyboard;
    /**
     * HighScoresAnimation.
     *
     * constructor.
     *
     * @param scores    HighScoresTable object.
     * @param endKey    an endKey.
     * @param k         KeyboardSensor object.
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey, KeyboardSensor k) {
        this.table = scores;
        this.endKey = endKey;
        this.scoreInfos = this.table.getHighScores();
        this.keyboard = k;
        this.stop = false;
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
        d.setColor(new Color(255, 254, 149));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(0, 0, 0));
        d.fillRectangle(50, 20, 196, 40);
        d.setColor(new Color(255, 254, 149));
        d.drawText(50, 50, "High Scores", 36);
        d.setColor(new Color(255, 113, 26));
        d.drawText(100, 130, "Player Name", 30);
        d.drawText(500, 130, "Score", 30);
        d.fillRectangle(100, 135, 500, 4);
        d.setColor(new Color(0, 0, 0));
        d.drawRectangle(100, 135, 500, 4);
        d.setColor(new Color(0, 6, 80));
        int y = 50;
        //draw all the records.
        for (int i = 0; i < this.table.getHighScores().size(); i++) {
            String name = this.scoreInfos.get(i).getName();
            String score = String.valueOf(this.scoreInfos.get(i).getScore());
            d.drawText(100, 170 + (y * i), name, 30);
            d.drawText(500, 170 + (y * i), score, 30);
        }
        d.setColor(Color.black);
        d.drawText(560, 580, "Press space to continue", 15);
        //check if the end key is pressed.
        if (this.keyboard.isPressed(this.endKey)) {
            this.stop = true;
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
        return this.stop;
    }
}