package Maze;

import AbstractFactory.TreasureHunt.TreasureHuntMenuBar;
import inventory.Models.ItemStatus;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class Item extends Cell {
    protected String type;
    protected int value;
    protected String url;



    public Item(int i, int j, double h, double w) {
        super(i, j, h, w);
        super.id= UUID.randomUUID().toString();
        setItem(true);

    }
    public void drawItem(){
        double x=getI()*w;
        double y=getJ()*w;
        //x=40.0;
        setX(x);
        setY(y);
        drawWalls(gc,x,y);

        Image img=new Image(getClass().getResourceAsStream(url));


        gc.drawImage(img,getX(),getY(),getWidth(),getHeight());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public void collectItem(GraphicsContext gc){
        clear();
        Cell c=new Cell(this.getI(),this.getJ(),this.getWidth(),this.getHeight());
        c.setSpecialWalls(this.getSpecialWalls());
        c.visited=true;
        c.drawRectangle(super.gc);
        int index=BoardCells.cells.indexOf(this);
        if(index>-1)BoardCells.cells.set(index,c);




    }
    @Override
    public void triggerItem(Player player)
    {
        TreasureHuntMenuBar.pointsBtn.setText(Integer.parseInt(TreasureHuntMenuBar.pointsBtn.getText())+value+"");
        collectItem(player.getGc());
    }



}
