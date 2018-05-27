import biuoop.DialogManager;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShowStartGame implements Task<Void> {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private DrawSurface drawSurface;
    private DialogManager dialogManager;
    private File highScoreFile;
    private HighScoresTable highScoresTable;
    private List<LevelInformation> levels;
    private String[] args;

    public ShowStartGame(String[] args, AnimationRunner animationRunner, KeyboardSensor keyboardSensor, DrawSurface drawSurface, DialogManager dialogManager, File highScoreFile, HighScoresTable highScoresTable) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.drawSurface = drawSurface;
        this.dialogManager = dialogManager;
        this.highScoreFile = highScoreFile;
        this.highScoresTable = highScoresTable;
        //this.levels = new List<LevelInformation>();
        this.args = args;
    }
    public Void run() {
        GameFlow gameFlow = new GameFlow(this.animationRunner, this.keyboardSensor, this.drawSurface, this.dialogManager, this.highScoreFile);
        gameFlow.runLevels(this.levels, this.highScoresTable);
        return null;
    }
}
