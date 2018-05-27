import biuoop.DialogManager;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * GameFlow.
 * @author Elor lichtziger
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private DrawSurface drawSurface;
    private Counter score, numLife;
    private DialogManager dialogManager;
    private File highScoreFile;
    /**
     * GameFlow.
     *
     * constructor.
     *
     * @param ar    an animationRunner.
     * @param ds    the keyboardSensor.
     * @param ks    the drawSurface.
     * @param dm    the dialogManager.
     * @param file  the file of the high scores.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, DrawSurface ds, DialogManager dm, File file) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.drawSurface = ds;
        this.dialogManager = dm;
        this.numLife = new Counter();
        this.numLife.increase(3);
        this.score = new Counter();
        this.highScoreFile = file;
    }
    /**
     * runLevels.
     *
     * run the levels.
     *
     * @param levels             a list of levels information.
     * @param highScoresTable    an highScoresTable object.
     */
    public void runLevels(List<LevelInformation> levels, HighScoresTable highScoresTable) {
        int dx = 100, counter = 1;
        Image img = null;
        /// /loop that runs all over the levels.
        while(this.numLife.getValue() > 0) {
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("enemy.png");
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.err.println(" Something went wrong while reading the blocks image !");
                System.exit(0);
            }
            dx = dx + (dx / 10);
            Velocity v = new Velocity(dx, 0);
            String s = String.valueOf(counter);
            LevelInformation levelInfo = new LevelCreator(s, v, img);
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.drawSurface, this.score, this.numLife);
            level.initialize();
            int remainedBlocks = level.getRemainedBlocks();
            //the level run until there is no remained blocks or life.
            while (remainedBlocks > 0 && this.numLife.getValue() > 0) {
                level.playOneTurn();
                remainedBlocks = level.getRemainedBlocks();
            }
        }
        int lastPlayer = highScoresTable.getHighScores().size() - 1;
        //check if there is no high scores in the table.
        if (highScoresTable.getHighScores().isEmpty()) {
            String name = this.dialogManager.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo player = new ScoreInfo(name, this.score.getValue());
            //add the new result.
            highScoresTable.add(player);
        } else {
            //check if the result need to enter to the table.
            if ((highScoresTable.getHighScores().get(lastPlayer).getScore() < this.score.getValue())
                    || (lastPlayer + 1 < 5)) {
                String name = this.dialogManager.showQuestionDialog("Name", "What is your name?", "");
                ScoreInfo player = new ScoreInfo(name, this.score.getValue());
                highScoresTable.add(player);
            }
        }
        try {
            highScoresTable.save(this.highScoreFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //if there is still life to the player, run the game.
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new LostScreen(this.keyboardSensor, this.score.getValue())));
        this.score = new Counter();
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(highScoresTable, KeyboardSensor.SPACE_KEY, this.keyboardSensor)));
    }
}