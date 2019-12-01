package Adapter;

import Maze.Cell;
import Maze.Player;
import javafx.scene.canvas.GraphicsContext;

import java.awt.event.KeyEvent;
import java.util.UUID;

public class IceTrap extends Cell {
    int numTimersRunning=0;

    public IceTrap(int i, int j, double h, double w) {
        super(i, j, h, w);
        setTrap(true);
        super.id= UUID.randomUUID().toString();
    }

    public void freezePlayer(Player player) {
        int i = 3;
        numTimersRunning++;
        while (i > 0){
            try {
                i--;
                Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
            } catch (InterruptedException e) {
            }
        }
        numTimersRunning--;
    }

    public void defrost(GraphicsContext gc){
        clear();
        Cell c=new Cell(this.getI(),this.getJ(),this.getWidth(),this.getHeight());
        c.setWalls(this.getWalls());
        c.visited=true;
        c.drawRectangle(super.gc);
    }

    private void keyPressed(KeyEvent e) {
        if(numTimersRunning > 0)
            return;
    }
}
