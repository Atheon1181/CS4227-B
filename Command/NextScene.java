package Command;

import javafx.scene.control.Button;

public class NextScene extends Button implements ICommand {
    INavigationControl scene;

    public NextScene(INavigationControl scene) {
        this.scene = scene;
    }

    @Override
    public void execute() {
        scene.previous();

    }
}
