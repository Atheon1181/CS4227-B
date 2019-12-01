package AbstractFactory.TimeChallenge;

import Maze.Board;
import Maze.Cell;
import Maze.MazePreference;
import Maze.Player;
import inventory.Models.Inventory;

public class TimeChallengeBoard extends Board {

    public TimeChallengeBoard(double height, double width, int controllers, MazePreference preference) {
        super(height, width,preference);
        setMazePreference(preference);

        super.players=preference.getPlayerInGame();
        super.setPlayer(preference.getPlayerInGame());

    }





}
