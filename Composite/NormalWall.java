package Composite;

import Maze.Player;
import javafx.scene.paint.Color;

public class NormalWall implements Wall {
    public boolean wall=true;
    public final Color wallColor=Color.DARKGREEN.darker();
    public final double wallWidth=2.0;

    public NormalWall() {

    }

    @Override
    public boolean isWall() {
        return wall;
    }

    @Override
    public void setWall(boolean wall) {
        this.wall=wall;
    }

    @Override
    public Wall getWall() {
        return this;
    }

    @Override
    public void hitWall(Player player) {
        System.out.println(player.getName()+": You Hit normal Wall");
    }

    @Override
    public Color getWallColor() {
        return wallColor;
    }

    @Override
    public double getWallWidth() {
        return wallWidth;
    }

    @Override
    public String toString() {
        return "NormalWall{" +
                "wall=" + wall +
                '}';
    }
}
