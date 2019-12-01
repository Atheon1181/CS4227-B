package Maze;

import Maze.Item;

public class CoinPile extends Item {
    public CoinPile(int i, int j, double width, double height) {
        super(i,j,width,height);
        super.url="pile.gif";
        super.value=200;
    }
}
