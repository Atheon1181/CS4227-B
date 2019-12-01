package AbstractFactory;

import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class MazeLoseScene extends Scene {
    protected long timer=0;
    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }
    public MazeLoseScene(Parent parent) {
        super(parent);
    }
    public void updateUI(){}
}
