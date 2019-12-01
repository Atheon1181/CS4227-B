package AbstractFactory.TreasureHunt;

import AbstractFactory.*;
import Maze.Board;
import Maze.MazePreference;
import Maze.Player;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TreasureHuntMazeFactory implements MazeFactory {
    @Override
    public MazeMenuBar createMenuBar(ArrayList<Player> players) {
        return new TreasureHuntMenuBar(players);
    }

    @Override
    public Board createBoard(double height, double width, int controllers, MazePreference preference) {
        return new TreasureHuntBoard(height,width,controllers,preference) ;
    }

    @Override
    public MazeWinScene createWinScene(Stage stageParent, Parent parent, long timeleft,ArrayList<Player>players) {
        return new TreasureHuntWinScene(stageParent,parent,timeleft,players);
    }

    @Override
    public MazeLoseScene createLoseScene(Stage stageParent, Parent parent, long timeleft) {
        return new TreasureHuntLoseScene(stageParent,parent,timeleft);
    }

    @Override
    public MazeScene createMazeScene(Stage stageParent, Parent parent) {
        return new TreasureHuntMazeScene(stageParent,parent);
    }
}
