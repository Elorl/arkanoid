import biuoop.GUI;

public class ShowExit implements Task<Void> {
    private GUI gui;
    public ShowExit(GUI gui) {
        this.gui = gui;
    }
    public Void run() {
        this.gui.close();
        System.exit(0);
        return null;
    }
}
