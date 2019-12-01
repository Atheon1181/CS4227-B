package AbstractFactory;

import Maze.Board;
import Maze.Player;
import Maze.MazePreference;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public interface MazeFactory {
    public MazeMenuBar createMenuBar(ArrayList<Player>players);
    public Board createBoard(double height, double width, int controllers, MazePreference preference);
    public MazeWinScene createWinScene(Stage stageParent,Parent parent,long timeleft,ArrayList<Player>players);
    public MazeLoseScene createLoseScene(Stage stageParent,Parent parent,long timeleft);
    public MazeScene createMazeScene(Stage stageParent,Parent parent);
}
