package Maze;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.paint.Color;

import java.util.*;

public class Enemy extends Cell {
    public boolean caughtPlayer;
    private int damage = 1;
    private long speed = 2000;
    private String name="Enemy 1";
    private Color color=Color.RED;

    public GraphicsContext gc;
    public  Cell current;
    public Board board;
    public Player player;
    private List<Cell> openList=new ArrayList<>();

    private List<Cell> closedList=new ArrayList<>();
    public Stack<Cell> path=new Stack<>();

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public Enemy(int i, int j, double h, double w) {
        super(i, j, h, w);
        current=this;
        super.id= UUID.randomUUID().toString();
        setRows(BoardCells.rows);
        setColumns(BoardCells.cols);

    }
    //find Player
    public void setHeuristics(){
        for (Cell cell:board.getCellStack()) {
            cell.sethCosts(calcHeuristic(cell));
        }
    }
    public int calcHeuristic(Cell current){
        System.out.println("calculating hur..."+player);

        double h=Math.sqrt(Math.pow((player.getI()-current.getI()),2)+Math.pow((player.getJ()-current.getJ()),2));
        return (int)h;

    }

    public ArrayList<ArrayList<Cell>> checkEnemyNeighbours(Cell thisCell){
        //Hold All neighbours both bad and Good Neighbours get added to open bad added to close
        System.out.println("THIS: "+thisCell);
        ArrayList<ArrayList<Cell>> neighbours=new ArrayList<>();
        //Good Neighbours
        neighbours.add(new ArrayList<>());
        //Bad Neighbours
        neighbours.add(new ArrayList<>());
        System.out.println(getI()+", "+getJ());


        int e=index(thisCell.getI()+1,thisCell.getJ());
        int w=index(thisCell.getI()-1,thisCell.getJ());
        int n=index(thisCell.getI(),thisCell.getJ()-1);
        int s=index(thisCell.getI(),thisCell.getJ()+1);
         System.out.println(n+","+e+","+s+","+w);
        Cell east=e!=-1? BoardCells.cells.get(e):null;
        Cell west=w!=-1?BoardCells.cells.get(w):null;
        Cell north=n!=-1?BoardCells.cells.get(n):null;
        Cell south=s!=-1?BoardCells.cells.get(s):null;


        if(north!=null)
        {
            if(!isWallNorth(thisCell,north))
                neighbours.get(0).add(north);
            else neighbours.get(1).add(north);
        }
        if(south!=null) {
            if(!isWallSouth(thisCell,south))
                neighbours.get(0).add(south);
            else neighbours.get(1).add(south);
        }
        if(east!=null) {
            if(!isWallEast(thisCell,east))
                neighbours.get(0).add(east);
            else neighbours.get(1).add(east);
        }
        if(west!=null){
            if(!isWallWest(thisCell,west))
                neighbours.get(0).add(west);
            else neighbours.get(1).add(east);
        }
        System.out.println("Neighbours: "+neighbours);
        return neighbours;
    }

    public void addNeigborsToOpenList(){
        System.out.println("ADDING NEIGHBOURS");
        ArrayList<Cell>goodNeighbours=checkEnemyNeighbours(this).get(0);
        System.out.println("GOOD NEIGHBOURS: "+goodNeighbours);
        for(Cell c:goodNeighbours){
            if(true){//!isBlocked(c)
                System.out.println("Not blocked");
                c.setParent(this.current);
                c.setgCosts(this.current.getgCosts());
                c.sethCosts(calcHeuristic(c));
                if(!findNeighborInList(this.openList,c)&&!findNeighborInList(this.closedList,c)){
                    c.setgCosts(c.parent.getgCosts()+1);
                    openList.add(c);
                    Collections.sort(this.openList);
                }
            }
        }

       /* ArrayList<Cell>badNeighbours=checkPlayerNeighbours(current).get(1);
        for(Cell c:badNeighbours){
            if(isBlocked(c)){
                closedList.add(c);
            }
        }*/
        System.out.println("OPEN LIST: "+this.openList);
        System.out.println("Done ");
    }
    public void resetCurrentPath()
    {
        path.clear();
        closedList.clear();
       current=this;
       openList.clear();

    }
    public Stack<Cell> trackPlayer() {
        System.out.println("Setting AI Path");
        //resetCurrentPath();
        System.out.println("Setting AI Path");
        System.out.println("FIND PATH TO GOAL");
        this.closedList.add(current);
        System.out.println("CLOSED LIST: "+this.closedList);

        addNeigborsToOpenList();

    int o=0;
        while (current.getI()!=this.player.getI()&&current.getJ() != this.player.getJ()) {
            if (this.openList.isEmpty()) { // Nothing to examine
                break;
            }
            current = this.openList.get(0); // get first node (lowest f score)
            this.openList.remove(0); // remove it
            this.closedList.add(current); // and add to the closed
            System.out.println("CLOSED LIST: "+this.closedList);
            addNeigborsToOpenList();
            System.out.println(o);
        }
        System.out.println("All Cells");
        for (Cell c :closedList)
        {
            System.out.println(c);
            this.path.push(c);
        }

        System.out.println("Path determined: \n"+this.path);


        return this.path;
    }
    private boolean findNeighborInList(List<Cell> array, Cell node) {
        return array.stream().anyMatch((n) -> (n.id == node.id));
    }
    public boolean isWallEast(Cell current,Cell next){
        System.out.println("CheckWallAt East: "+current.checkWallAt("E")+" - "+"CheckWallAt West: "+current.checkWallAt("W"));
        if(current.checkWallAt("E")!=null&&next.checkWallAt("W")!=null)
        return true;
        else return false;
    }
    public boolean isWallNorth(Cell current,Cell next){
          if(current.checkWallAt("N")!=null&&next.checkWallAt("S")!=null)
            return true;
        else return false;
    }
    public boolean isWallWest(Cell current,Cell next){
        if(current.checkWallAt("W")!=null&&next.checkWallAt("E")!=null)
            return true;
        else return false;

    }public boolean isWallSouth(Cell current,Cell next){
        if(current.checkWallAt("S")!=null&&next.checkWallAt("N")!=null)
            return true;
        else return false;

    }
    public void drawEnemy(){
        System.out.println("Enemy-> "+current);
        double x=super.getI()*super.w;
        double y=super.getJ()*super.w;
        //x=40.0;
        setX(x);
        setY(y);
        gc.setFill(this.color);
        gc.setStroke(Color.TRANSPARENT);
        gc.fillRect(getX(),
                getY(),
                getWidth(),
                getHeight());

    }
    public void drawEnemy(int i,int j){
        System.out.println("Enemy-> "+current);
        double x=i*super.w;
        double y=j*super.w;
        //x=40.0;
        setX(x);
        setY(y);
        gc.setFill(this.color);
        gc.setStroke(Color.TRANSPARENT);
        gc.fillRect(i,
               j,
                getWidth(),
                getHeight());

    }
    @Override
    public void clear(){
        gc.clearRect(getX(),getY(),getWidth(),getHeight());
    }

    public long getSpeed() {
        return this.speed;
    }
    //Attack player

}
