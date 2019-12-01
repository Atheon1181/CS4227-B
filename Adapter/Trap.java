package Adapter;

import Facade.PlayerFacade;
import Maze.BoardCells;
import Maze.Cell;
import Maze.Player;
import javafx.scene.canvas.GraphicsContext;

import java.util.UUID;

public class Trap extends Cell {
    private int damage=1;
    public Trap(int i, int j, double h, double w) {

        super(i, j, h, w);
        super.id= UUID.randomUUID().toString();
        setTrap(true);

    }

    public void destroyTrap(GraphicsContext gc){
        clear();
        Cell c=new Cell(this.getI(),this.getJ(),this.getWidth(),this.getHeight());
        c.setSpecialWalls(this.getSpecialWalls());
        c.visited=true;
        c.drawRectangle(super.gc);
        int index= BoardCells.cells.indexOf(this);
        if(index>-1)BoardCells.cells.set(index,c);



    }
    @Override
    public void triggerTrap(Player player)
    {
        player= new PlayerFacade(player).takeDamage(damage);//player.takeDamage(damage);
        destroyTrap(player.getGc());
    }
}
