package AbstractFactory;

import Leaderboard.Models.Leaderboard;
import Leaderboard.UI.LeaderBoardOptions;
import javafx.scene.Parent;
import javafx.scene.Scene;
import  Maze.Player;
import javafx.stage.Stage;

import java.util.ArrayList;

public abstract class MazeWinScene extends Scene {

    protected long timer=0;
    protected long points=0;
    protected ArrayList<Player>players;
    protected Player Winner;
    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public MazeWinScene(Parent parent, ArrayList<Player>players) {
        super(parent);
        this.players=players;
    }
    public void updateUI(){}

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getWinner() {
        return Winner;
    }

    public void setWinner(Player winner) {
        this.Winner = winner;
    }
    public void saveUser(String mode,String val){
        Leaderboard lb=new Leaderboard();
        lb.saveToLeaderBoard(mode+".txt",
               "Ollie",
                val+"",
                "--");

    }
    public void showLB(){
        Stage s=new LeaderBoardOptions();
        s.show();
    }
}
