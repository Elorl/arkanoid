import biuoop.DrawSurface;
import java.awt.Color;
/**
 * BackgroundLevel1.
 * @author Elor lichtziger
 */
public class BackgroundLevel implements Sprite {
    private String levelName;
    /**
     * BackgroundLevel1.
     *
     * constructor.
     *
     * @param levelName     the name of the level.
     */
    public BackgroundLevel(String levelName) {
        this.levelName = levelName;
    }
    /**
     * drawOn.
     *
     * draw the background of the level.
     *
     * @param d     a DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        //draw the top rectangle with the level's name.
        int width = d.getWidth();
        int height = 20;
        d.setColor(java.awt.Color.getHSBColor(10, 0, 50));
        d.fillRectangle(0, 0, width, height);
        String levelTitle = String.format("Level Name: %s", this.levelName);
        d.setColor(Color.black);
        d.drawText(620, 15, levelTitle, 15);
    }
    /**
     * timePassed.
     *
     */
    public void timePassed(double dt) {
        return;
    }
}
