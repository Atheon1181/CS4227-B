package Maze;

import Facade.PlayerFacade;
import javafx.scene.canvas.GraphicsContext;

import java.util.UUID;

public class Health extends Cell {
    private int life=1;

    public Health(int i, int j, double h, double w) {
        super(i, j, h, w);
        super.id= UUID.randomUUID().toString();
        super.setLife(true);
    }

    public void addLife(GraphicsContext gc){
        clear();
        Cell c=new Cell(this.getI(),this.getJ(),this.getWidth(),this.getHeight());
        c.setSpecialWalls(this.getSpecialWalls());
        c.visited=true;
        c.drawRectangle(super.gc);
        int index=BoardCells.cells.indexOf(this);
        if(index>-1)BoardCells.cells.set(index,c);


    }
    @Override
    public void triggerHealth(Player player)
    {
       player= new PlayerFacade(player).addHealth(life);//player.addHealth(life);
        addLife(player.getGc());
    }
}

