package Maze.ButtonFactory;

import Maze.Cell;
import inventory.controls.Effects;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.UUID;

public class Door extends Cell {

    private Color doorColor=Color.BLACK;
    private Cell exit;
    public Door(int i, int j, double h, double w,Cell exit,Color doorColor) {

        super(i, j, h, w);
        super.setColor(doorColor);
        super.id= UUID.randomUUID().toString();
        this.setDoor(true,true);
        this.exit=exit;
        this.exit.setColor(doorColor);
        this.exit.setDoor(true,false);





    }

    @Override
    public void exitDoor(Cell player){
        System.out.println("Exitting Door: "+exit);
        player.clear();
        player.setI(exit.getI());
        player.setJ(exit.getJ());
        System.out.println("Player: "+player);

        player.repaint();

    }

}
