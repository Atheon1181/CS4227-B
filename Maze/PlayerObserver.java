package Maze;

import javafx.beans.InvalidationListener;

import java.util.Observable;

public class PlayerObserver extends Observable {
    public void update(int health){
        setChanged();
        notifyObservers(health);
    }


}
