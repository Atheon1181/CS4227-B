package AbstractFactory.Multiplayer;

import AbstractFactory.MazeLoseScene;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MultiplayerLoseScene extends MazeLoseScene {
    private Label time;
    private Stage stageParent;
    private final String TITLE="Multiplayer Maze";
    private final String GAMEOVER="Winner is";
    public MultiplayerLoseScene(Stage stageParent, Parent parent, long timeleft) {
        super(parent);
    }
}
