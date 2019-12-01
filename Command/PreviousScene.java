package Command;

import javafx.scene.control.Button;

public class PreviousScene extends Button implements ICommand {

    INavigationControl scene;

    public PreviousScene(INavigationControl scene) {
        this.scene = scene;
    }

    @Override
    public void execute() {
        scene.previous();

    }
}
