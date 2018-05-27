import biuoop.KeyboardSensor;

public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor keyboardSensor;

    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor keyboard) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.keyboardSensor = keyboard;
    }

    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, this.highScoresAnimation));
        return null;
    }
}