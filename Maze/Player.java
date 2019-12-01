package Maze;

import Adapter.Trap;
import Facade.CollisionDetector;
import inventory.Models.Inventory;
import inventory.controls.Effects;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class Player extends Cell implements Actor{
    private int health = 3;
    private long speed = 2000;
    private String name="Player 1";
    private Color color=Color.WHITE;
    private int i,j=0;
    public GraphicsContext gc;
    public  Cell current;
    private boolean done=false;
    private boolean shielded=false;


    private Inventory inventory;


    public Player(String name, int health, Inventory inventory,int i, int j, double h,double w,Cell initPos) {
    super(i,j,h,w);
        this.i=initPos.getI();
        this.j=initPos.getJ();
        this.name = name;
        this.health = health;
        this.inventory = inventory;
        this.current=initPos;
       // setHeight(h/2);
       // setWidth(w/2);

    }

    public boolean isShielded() {
        return shielded;
    }

    public void setShielded(boolean shielded) {
        this.shielded = shielded;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;

    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    @Override
    public void drawPlayer(){

        System.out.println("Current-> "+current);
        double x=super.getI()*super.w;
        double y=super.getJ()*super.w;
        //x=40.0;
        setX(x);
        setY(y);
        gc.setEffect(Effects.BLOOM(20.0));
        gc.setFill(this.color);
        gc.setStroke(Color.TRANSPARENT);
        gc.fillRect(getX(),
                getY(),
                getWidth(),
                getHeight());
        gc.setEffect(null);

    }
    @Override
    public void moveRight(){
        //Check Neighbour

        Cell next=checkPlayerNeighbours(current).get("E");
        System.out.println("Neighbour is at: "+next);

        movePlayer(Directions.EAST,next);

    }
    @Override
    public void moveLeft(){
        //Check Neighbour

        Cell next=checkPlayerNeighbours(current).get("W");
        System.out.println("Neighbour is at: "+next);
        movePlayer(Directions.WEST,next);
    }
    @Override
    public void moveUp(){
        //Check Neighbour

        Cell next=checkPlayerNeighbours(current).get("N");
        System.out.println("Neighbour is at: "+next);
        movePlayer(Directions.NORTH,next);
    }
    @Override
    public void moveDown(){
        //Check Neighbour

        Cell next=checkPlayerNeighbours(current).get("S");
        System.out.println("Neighbour is at: "+next);
       movePlayer(Directions.SOUTH,next);
    }
    @Override
    public void movePlayer(Directions dir,Cell next){
        boolean move=false;
        if(next!=null) {
            System.out.println("NExt is a door? " + next.isDoor());
            System.out.println("From: (" + this.i + ", " + this.j + ") to: (" + next.getI() + ", " + next.getJ() + ")");

            switch (dir) {
                case EAST: {
                    move = !isWallEast(next);
                }
                break;
                case WEST: {
                    move = !isWallWest(next);
                }
                break;
                case NORTH: {
                    move = !isWallNorth(next);
                }
                break;
                case SOUTH: {
                    move = !isWallSouth(next);
                }
                break;
            }
            if (move) {
                gc.clearRect(getX(), getY(), getWidth(), getHeight());

                setI(next.getI());
                setJ(next.getJ());
                current = next;
                if(!shielded)drawPlayer();
                else drawShielded();

            }
            if (next.isDoor()) {
                current = this;

                next.exitDoor(this);

            }
            if (next.isTrap()) {

                current = this;

                CollisionDetector collision = new CollisionDetector(this);
                if(next instanceof Trap)
                System.out.println(next+" instance of trap" );
                else
                    System.out.println("Instance of Cell");
                if(!shielded)
                {collision.detectTrapCollision((Trap)next);this.health = collision.player.health;}
                else {
                    shielded=false;
                    collision.detectTrapCollision((Trap)next);
                    drawPlayer();
                }


            }
            if (next.isItem()){
                current = this;
                CollisionDetector collision = new CollisionDetector(this);
                collision.detectItemCollision((Item)next);

            }
            if (next.isLife()) {

                current = this;
                CollisionDetector collision = new CollisionDetector(this);
                collision.detectHPCollision((Health)next);
                this.health = collision.player.health;

            }
            if(next.isShield()){
                current = this;
                //gc.clearRect(getX(), getY(), getWidth(), getHeight());
                CollisionDetector collision = new CollisionDetector(this);
                collision.detectShieldCollision((Shield)next);
              //  drawShielded();

            }
        }

    }


    @Override
    public void show(GraphicsContext gc) {

    }

    @Override
    public void drawRectangle(GraphicsContext gc) {

    }
    @Override
    public  Map<String,Cell> checkPlayerNeighbours(Cell thisCell){
        System.out.println("Current->>"+current);
        Map<String,Cell> neighbours=new HashMap<>();
        //System.out.println(i+", "+j);

        int e=index(thisCell.getI()+1,thisCell.getJ());
        int w=index(thisCell.getI()-1,thisCell.getJ());
        int n=index(thisCell.getI(),thisCell.getJ()-1);
        int s=index(thisCell.getI(),thisCell.getJ()+1);
       // System.out.println(n+","+e+","+s+","+w);
        Cell east=e!=-1?BoardCells.cells.get(e):null;
        Cell west=w!=-1?BoardCells.cells.get(w):null;
        Cell north=n!=-1?BoardCells.cells.get(n):null;
        Cell south=s!=-1?BoardCells.cells.get(s):null;


        /*
        Cell east=e!=-1?BoardCells.path.get(e):null;
        Cell west=w!=-1?BoardCells.path.get(w):null;
        Cell north=n!=-1?BoardCells.path.get(n):null;
        Cell south=s!=-1?BoardCells.path.get(s):null;
        */




        if(north!=null)
        {
            neighbours.put("N",north);
        }
        if(south!=null) {
            neighbours.put("S",south);
        }
        if(east!=null) {
            neighbours.put("E",east);
        }
        if(west!=null){
            neighbours.put("W",west);
        }
        System.out.println("Neighbours: "+neighbours);
        return neighbours;
    }
    public void hitWall(Cell current,char dir){


        switch (dir){
            case 'N':{
                current.getSpecialWalls()[0].hitWall(this);
                System.out.println(current.getSpecialWalls()[0]);
            }break;
            case 'E':{
                current.getSpecialWalls()[1].hitWall(this);
                System.out.println(current.getSpecialWalls()[1]);
            }break;
            case 'S':{
                current.getSpecialWalls()[2].hitWall(this);
                System.out.println(current.getSpecialWalls()[2]);
            }break;
            case 'W':{
                current.getSpecialWalls()[3].hitWall(this);
                System.out.println(current.getSpecialWalls()[3]);
            }break;
            default:break;
        }

    }
    public boolean isWallEast(Cell next){
        //checkWallType
        System.out.println("CheckWallAt East: "+current.checkWallAt("E")+" - "+"CheckWallAt West: "+current.checkWallAt("W"));
        if(current.checkWallAt("E")!=null&&next.checkWallAt("W")!=null)
        {
            hitWall(current,'E');
            return true;
        }
        else return false;
    }
    public boolean isWallNorth(Cell next){
        if(current.checkWallAt("N")!=null&&next.checkWallAt("S")!=null)
        {
            hitWall(current,'N');
            return true;
        }
        else return false;
    }
    public boolean isWallWest(Cell next){
        if(current.checkWallAt("W")!=null&&next.checkWallAt("E")!=null)
        {
            hitWall(current,'W');
            return true;
        }
        else return false;

    }public boolean isWallSouth(Cell next){
        if(current.checkWallAt("S")!=null&&next.checkWallAt("N")!=null)
        {
            hitWall(current,'S');
            return true;
        }
        else return false;

    }
    @Override
    public void repaint(){
        System.out.println("Player: 259: "+this);this.drawPlayer();
    }
    @Override
    public void clear(){
        gc.clearRect(getX(),getY(),getWidth(),getHeight());
    }

    public void takeDamage(int damage) {
        this.health=(Math.max(this.health - damage, 0));
        if(health<=0)clear();



    }

    public void addHealth(int life) {
        this.health=(Math.max(this.health + life, 0));

    }

    @Override
    public String toString() {
        return "Player{Shielded? "+shielded +
                "health=" + health +

                ", name='" + name + '\'' +

                '}';
    }

    public void drawShielded() {
        System.out.println("Current-> "+current);
        double x=super.getI()*super.w;
        double y=super.getJ()*super.w;
        //x=40.0;
        setX(x);
        setY(y);
        gc.setEffect(Effects.GLOW(20.0));
        gc.setFill(Color.LIGHTBLUE);
        gc.setStroke(Color.LIGHTBLUE);

        gc.setFill(this.color);
        gc.strokeOval(getX(),
                getY(),
                getWidth(),
                getHeight());
        gc.fillOval(getX(),
                getY(),
                getWidth(),
                getHeight());
        gc.setEffect(null);
    }
}
