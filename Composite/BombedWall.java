package Composite;

import Facade.CollisionDetector;
import Maze.Player;
import javafx.scene.paint.Color;

public class BombedWall implements Wall {
    private final Color wallColor=Color.RED;
    private final double wallWidth=3.0;

    @Override
    public boolean isWall() {
        return false;
    }

    @Override
    public void setWall(boolean wall) {

    }

    @Override
    public Wall getWall() {
        return this;
    }

    @Override
    public void hitWall(Player player) {
        if(!player.isShielded())player.takeDamage(1);
        else player.drawPlayer();
        player.setShielded(false);

        CollisionDetector collision = new CollisionDetector(player);
        collision.detectWallHit();
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
        return "BombedWall{" +
                "wallColor=" + wallColor +
                ", wallWidth=" + wallWidth +
                '}';
    }
}
