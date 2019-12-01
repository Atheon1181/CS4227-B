package Composite;

import Maze.Player;
import javafx.scene.paint.Color;

public interface Wall {
    public boolean isWall();
    public void setWall(boolean wall);
    public Wall getWall();
    public void hitWall(Player player);
    public Color getWallColor();
    public double getWallWidth();
}
