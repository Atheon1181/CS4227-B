package AbstractFactory.Survival;

import AbstractFactory.*;
import Maze.Board;
import Maze.*;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SurvivalMazeFactory implements MazeFactory {
    @Override
    public MazeMenuBar createMenuBar(ArrayList<Player> players) {
        return new SurvivalMenuBar(players);
    }

    @Override
    public Board createBoard(double height, double width, int controllers, MazePreference preference) {
        return new SurvivalBoard(height,width,controllers,preference) ;
    }

    @Override
    public MazeWinScene createWinScene(Stage stageParent, Parent parent, long timeleft,ArrayList<Player>players) {
        return new SurvivalWinScene(stageParent,parent,timeleft,players);
    }

    @Override
    public MazeLoseScene createLoseScene(Stage stageParent, Parent parent, long timeleft) {
        return new SurvivalLoseScene(stageParent,parent,timeleft);
    }

    @Override
    public MazeScene createMazeScene(Stage stageParent, Parent parent) {
        return new SurvivalMazeScene(stageParent,parent);
    }
}
