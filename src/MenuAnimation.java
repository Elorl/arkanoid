import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
public class MenuAnimation<T> implements Menu<T>{
    private List<String> keys;
    private List<String> messages;
    private List<T> values;
    private T status;
    private Boolean stop;
    private KeyboardSensor keyboard;
    private String title;

    public MenuAnimation(KeyboardSensor keyboard, String title) {
        this.keys = new ArrayList<String>();
        this.messages = new ArrayList<String>();
        this.values = new ArrayList<T>();
        this.stop = false;
        this.keyboard = keyboard;
        this.title = title;
    }
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.values.add(returnVal);
    }
    public T getStatus() {
        this.stop = false;
        return this.status;
    }
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0x9DC47C));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(255, 249, 255));
        d.fillRectangle(50, 20, 150, 40);
        d.setColor(new Color(0x00520A));
        d.drawText(54, 54, this.title, 36);
        d.setColor(new Color(0x9DC47C));
        d.drawText(53, 53, this.title, 36);
        d.setColor(new Color(0x00520A));
        int y = 50;
        for (int i = 0; i < this.keys.size(); i++) {
            String titleOption = String.format("(%s)  %s", this.keys.get(i), this.messages.get(i));
            d.drawText(100, 130 + (y * i), titleOption, 30);
        }
        for (int j = 0; j < this.keys.size(); j++) {
            if (this.keyboard.isPressed(this.keys.get(j))) {
                this.status = this.values.get(j);
                this.stop = true;
            }
        }
    }
    public boolean shouldStop() {
        return this.stop;
    }
}
