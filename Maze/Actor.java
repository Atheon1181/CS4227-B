package Maze;

public interface  Actor {
    public void moveUp();
    public void moveDown();
    public void moveRight();
    public void moveLeft();
    public void movePlayer(Directions dir,Cell next);


}
