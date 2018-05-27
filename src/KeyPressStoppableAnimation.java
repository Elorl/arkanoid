import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation.
 * @author Elor lichtziger
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private Boolean stop;
    private boolean isAlreadyPressed;
    /**
     * KeyPressStoppableAnimation.
     *
     * constructor.
     *
     * @param sensor    KeyboardSensor object.
     * @param key       A string.
     * @param animation Animation object.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    /**
     * doOneFrame.
     *
     * is in charge of the logic that belong to this animation.
     *
     * @param d     DrawSurface object.
     * @param dt    the dt value.
     */
    public void doOneFrame(DrawSurface d, double dt) {
       //check if the key is already pressed.
        if (!this.keyboardSensor.isPressed(this.key)) {
           this.isAlreadyPressed = false;
       }
       //check if the key was pressed.
       if (!this.isAlreadyPressed) {
           this.animation.doOneFrame(d, dt);
           if (this.keyboardSensor.isPressed(this.key)) {
               this.stop = true;
           }
       }
    }
    /**
     * shouldStop.
     *
     * is in charge of stopping condition of this animation.
     *
     * @return true if there is need to stop and false if is not.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}