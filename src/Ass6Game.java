import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.*;
import java.util.ArrayList;

/**
 * Ass6Game.
 * @author Elor lichtziger
 */
public class Ass6Game {
    /**
     * main.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Space Invaders", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        ArrayList<LevelInformation> levels = new ArrayList<LevelInformation>();
        File highScoreFile = new File("highscores.txt");
        HighScoresTable highScoresTable = HighScoresTable.loadFromFile(highScoreFile);

        HighScoresAnimation highScoresAnimation = new HighScoresAnimation(highScoresTable, KeyboardSensor.SPACE_KEY, gui.getKeyboardSensor());
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(gui.getKeyboardSensor(), "Space Invaders");
        menu.addSelection("s", "Start game", new ShowStartGame(args, animationRunner, gui.getKeyboardSensor(), gui.getDrawSurface(), gui.getDialogManager(), highScoreFile, highScoresTable));
        menu.addSelection("h", "High scores", new ShowHiScoresTask(animationRunner, highScoresAnimation, gui.getKeyboardSensor()));
        menu.addSelection("e", "Exit", new ShowExit(gui));
        while (true) {
            animationRunner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
}