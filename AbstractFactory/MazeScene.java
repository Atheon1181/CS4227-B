package AbstractFactory;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class MazeScene extends Scene {
    protected boolean timed=true;
    protected long timer=30000;
    protected boolean foward=false;

    public MazeScene(Parent parent) {
        super(parent);
        getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());
    }

    public boolean isTimed() {
        return timed;
    }

    public void setTimed(boolean timed) {
        this.timed = timed;
    }

    public long getTime() {
        return timer;
    }

    public void setTime(long time) {
        this.timer = time;
    }

    public void winLogic(){}

    public boolean isFoward() {
        return foward;
    }

    public void setFoward(boolean foward) {
        this.foward = foward;
    }
}
