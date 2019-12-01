package AbstractFactory;
import  Maze.Player;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MazeMenuBar extends HBox {
    protected long timer=0;
    protected long points=0;
    protected long goalPoints=0;
    protected ArrayList<Player>players;
    protected Button solutionBtn;

    public MazeMenuBar(ArrayList<Player> players) {
        this.players = players;
    }

    public long getTimer() {
        return timer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }
    public void updateUI(){}

    public void updateHealth(Player p) {
    }

    public Button getSolutionBtn() {
        return solutionBtn;
    }

    public void setSolutionBtn(Button solutionBtn) {
        this.solutionBtn = solutionBtn;
    }
}
