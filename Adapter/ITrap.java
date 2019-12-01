package Adapter;

import Maze.Player;
import javafx.scene.canvas.GraphicsContext;

public interface ITrap {
    public void destroyTrap(GraphicsContext gc);
    public void triggerTrap(Player player);
}
