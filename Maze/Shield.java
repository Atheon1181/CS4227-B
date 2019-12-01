package Maze;

import Facade.PlayerFacade;
import javafx.scene.canvas.GraphicsContext;

import java.util.UUID;

public class Shield extends Cell {
    private int health=1;
    public Shield(int i, int j, double h, double w) {

        super(i, j, h, w);
        super.id= UUID.randomUUID().toString();
        super.setShield(true);

    }

    public void collectShield(GraphicsContext gc){
        clear();
        Cell c=new Cell(this.getI(),this.getJ(),this.getWidth(),this.getHeight());
        c.setSpecialWalls(this.getSpecialWalls());
        c.visited=true;
        c.drawRectangle(super.gc);
        int index=BoardCells.cells.indexOf(this);
        if(index>-1)BoardCells.cells.set(index,c);




    }
    @Override
    public void triggerShield(Player player)
    {
        player.setShielded(true);//player.takeDamage(damage);
        player.drawShielded();
        collectShield(player.getGc());
    }
}
