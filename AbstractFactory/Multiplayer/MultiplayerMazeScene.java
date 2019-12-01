package AbstractFactory.Multiplayer;

import AbstractFactory.MazeLoseScene;
import AbstractFactory.MazeScene;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MultiplayerMazeScene extends MazeScene {
    public MultiplayerMazeScene(Stage stageParent, Parent parent) {
        super(parent);
        super.timed=false;
    }
    @Override
    public void winLogic(){
        //First Player Wins
    }
}
