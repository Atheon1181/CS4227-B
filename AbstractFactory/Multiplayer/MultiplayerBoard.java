package AbstractFactory.Multiplayer;

import Maze.Board;
import Maze.Cell;
import Maze.MazePreference;
import Maze.Player;
import inventory.Models.Inventory;
import javafx.scene.paint.Color;

public class MultiplayerBoard extends Board {


    public MultiplayerBoard(double height, double width, int controllers, MazePreference preference) {


        super(height, width,preference);
        super.setPlayer(preference.getPlayerInGame());
        setMazePreference(preference); System.out.println(preference);
    }

}
