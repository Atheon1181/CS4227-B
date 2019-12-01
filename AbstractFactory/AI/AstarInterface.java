package AbstractFactory.AI;

import Maze.Cell;

import java.util.ArrayList;

public interface AstarInterface {
    public int calcHeuristics(Cell current, Cell target);
    public Object checkNeighbours(Cell thisCell);


}
