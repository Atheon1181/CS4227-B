package Maze.MazeSolver;

import Maze.Board;
import Maze.BoardCells;
import Maze.Cell;
import Composite.NormalWall;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;

/*Implementation of A* Algorithm*/
public class MazeSolver extends Canvas implements Runnable {
    public Board board;
    public Cell[][] cell;
    private List<Cell> openList=new ArrayList<>();
    private int columns,rows;
    private List<Cell> closedList=new ArrayList<>();
    private Stack<Cell>mazeGrid=new Stack<>();
    private  Stack<Cell> path=new Stack<>();
    private  Stack<Cell> bestPath=new Stack<>();

    private Cell now;
    private Cell goal,start;
    static boolean additionalPath=false;
    private GraphicsContext gc;

    public MazeSolver(Board board,int columns,int rows){
        this.columns=columns;
        this.rows=rows;
        this.board=board;
        //setTranslateX(10);
       // setTranslateY(10);
        gc=this.getGraphicsContext2D();
        this.setHeight(board.getBoardHeight());
        this.setWidth(board.getBoardWidth());

        mazeGrid.addAll(board.getCellStack());
        goal=mazeGrid.lastElement();
        start=mazeGrid.firstElement();
        Thread t=new Thread(board);
        t.start();
        start.setgCosts(0);

        now=start;


       setHeuristics();
        System.out.println("Heustics Aplied");
        findPathTo();
       // findOptimalPath();
        System.out.println(this.path);
       ;



    }


    public void setHeuristics(){
        for (Cell cell:mazeGrid) {
            cell.sethCosts(calcHeuristic(cell));
        }
    }
    public int calcHeuristic(Cell current){
       // double h=Math.sqrt(Math.pow((goal.getI()-current.getI()),2)+Math.pow((goal.getJ()-current.getJ()),2));
      //  return (int)h;
        return manHanDistance( current);

    }
    private int manHanDistance(Cell current) {

            return Math.abs(current.getI() - goal.getI()) + Math.abs(current.getJ() - goal.getJ()); // else return "Manhattan distance"

    }

    public ArrayList<ArrayList<Cell>> checkPlayerNeighbours(Cell thisCell){
        //Hold All neighbours both bad and Good Neighbours get added to open bad added to close
        ArrayList<ArrayList<Cell>> neighbours=new ArrayList<>();
        //Good Neighbours
        neighbours.add(new ArrayList<>());
        //Bad Neighbours
        neighbours.add(new ArrayList<>());
        //System.out.println(i+", "+j);

        int e=index(thisCell.getI()+1,thisCell.getJ());
        int w=index(thisCell.getI()-1,thisCell.getJ());
        int n=index(thisCell.getI(),thisCell.getJ()-1);
        int s=index(thisCell.getI(),thisCell.getJ()+1);
        // System.out.println(n+","+e+","+s+","+w);
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
        ArrayList<Cell>goodNeighbours=checkPlayerNeighbours(this.now).get(0);
        System.out.println("GOOD NEIGHBOURS: "+goodNeighbours);
        for(Cell c:goodNeighbours){
            if(true){//!isBlocked(c)
                System.out.println("Not blocked");
                c.setParent(this.now);
                c.setgCosts(this.now.getgCosts());
                c.sethCosts(calcHeuristic(c));
                if(!findNeighborInList(this.openList,c)&&!findNeighborInList(this.closedList,c)){
                    c.setgCosts(c.parent.getgCosts()+1);
                    openList.add(c);
                    Collections.sort(this.openList);
                }
            }
        }

       /* ArrayList<Cell>badNeighbours=checkPlayerNeighbours(this.now).get(1);
        for(Cell c:badNeighbours){
            if(isBlocked(c)){
                closedList.add(c);
            }
        }*/
        System.out.println("OPEN LIST: "+this.openList);
        System.out.println("Done ");
    }
    public List<Cell> findPathTo() {
        System.out.println("FIND PATH TO GOAL");
        this.closedList.add(this.now);
        System.out.println("CLOSED LIST: "+this.closedList);

        addNeigborsToOpenList();


        while (!this.now.id.equals(this.goal.id)) {
            if (this.openList.isEmpty()) { // Nothing to examine
                return null;
            }
            this.now = this.openList.get(0); // get first node (lowest f score)
            this.openList.remove(0); // remove it
            this.closedList.add(this.now); // and add to the closed
            System.out.println("CLOSED LIST: "+this.closedList);
            addNeigborsToOpenList();
        }
       // this.path.add(0, this.now);

        for (Cell c :
                closedList) {
           // c.setColor(Color.GOLD);
            this.path.push(c);
            System.out.println("Paent -> "+c.parent.id+" Child: "+c.id);

        }
        this.bestPath.add(0, this.now);
       // while (!this.now.id.equals(this.start.id)) {
       //     System.out.println(">>"+this.now.id+" v "+this.start.id +" Parent: "+this.now.parent.id);
          //  this.now = this.now.parent;
       //     this.bestPath.add(0, this.now);
       // }
        return this.path;
    }
    private void removeDuplicates(){
        List<Cell>newList=new ArrayList<>();
        for(Cell c:closedList){
            for(Cell cell:closedList)
            {

            }
        }
    }
    private void findOptimalPath(){

        this.bestPath=new Stack<>();
        Cell temp=this.now;
        Cell st=temp;
        this.bestPath.push(temp);

        while(temp.parent.id!=st.id){
            this.bestPath.push(temp.parent);
            temp=temp.parent;
            System.out.println(start.id+"-- vs --"+temp.parent.id+"Match: "+temp.parent.id==start.id);
        }
    }
    private boolean findNeighborInList(List<Cell> array, Cell node) {
        return array.stream().anyMatch((n) -> (n.id == node.id));
    }
    public void addNeighboursToOpenList(){
        Cell node;
    }

    public boolean isValid(Cell current){
        return false;
    }

    public boolean isDestination(Cell current){
        return current==goal;
    }
    public boolean isBlocked(Cell next){
        boolean blocked=false;
        if(next.visited){
            blocked=true;
        }
        //else if(next.)
        //return next.
        return  blocked;
    }
    public int index(int i, int j){
        // System.out.println("i: "+i+" j: " + j+"\nCol: "+columns+", Row: "+rows);
        if(i<0||j<0||i>columns-1||j>rows-1)
            return -1;
        return i+j*columns;
    }
    //[NESW]
    public void checkWallType(Cell current){
         if(current.getSpecialWalls()[1] instanceof NormalWall){
            //do nothing
        }else if(current.getSpecialWalls()[1] instanceof NormalWall){
             //bombed
        }
    }
    public boolean isWallEast(Cell current,Cell next){
        //checkWallType
        System.out.println("CheckWallAt East: "+current.checkWallAt("E")+" - "+"CheckWallAt West: "+current.checkWallAt("W"));
        if(current.checkWallAt("E")!=null&&next.checkWallAt("W")!=null)
        {

            return true;
        }
        else return false;
    }
    public boolean isWallNorth(Cell current,Cell next){
        if(current.checkWallAt("N")!=null&&next.checkWallAt("S")!=null)
        {
            return true;
        }
        else return false;
    }
    public boolean isWallWest(Cell current,Cell next){
        if(current.checkWallAt("W")!=null&&next.checkWallAt("E")!=null)
        {
            return true;
        }
        else return false;

    }public boolean isWallSouth(Cell current,Cell next){
        if(current.checkWallAt("S")!=null&&next.checkWallAt("N")!=null)
        {
            return true;
        }
        else return false;

    }
    public void drawPath(int i){
        System.out.println(i+") Drawing "+path.size()+" Cells:  "+path.get(i));

        //cellStack.lastElement().hightlight(gc);
        //cellStack.firstElement().isFirst=true;
        //cellStack.lastElement().isEnd=true;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(i==path.size()-1)
                    path.get(i).setColor(Color.GOLD);
                else
                path.get(i).setColor(Color.RED);
                path.get(i).hightlight(gc,Color.GOLD);

                System.out.println(path);
            }
        });
    }
    private void drawBestPath(int i) {
        System.out.println(i+") Drawing "+bestPath.size()+" Cells:  "+bestPath.get(i));

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                bestPath.get(i).setColor(Color.PURPLE);
                bestPath.get(i).hightlight(gc,Color.PURPLE);
                System.out.println(bestPath);
            }
        });
    }
    boolean part1=false;
    public int DRAWCOUNTER=0;
    @Override
    public void run() {

        //i<cellStack.size()
        while(DRAWCOUNTER<this.path.size()){

            try {
                drawPath(DRAWCOUNTER);
                System.out.println("Parent:: "+path.get(0).parent);
                DRAWCOUNTER++;

                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        for(Cell c:path){
            System.out.println("Parent:: "+c.parent);
        }
       int j=0;
        System.out.println(this.bestPath.size());
  while(j<this.bestPath.size()){
            try {
                drawBestPath(j);
                j++;

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void clear() {
        for(Cell c:this.path){
            c.clear();
            c.drawRectangle(gc);
        }
    }

    public void show() {
        for(Cell c:this.path){
            c.clear();
            c.hightlight(gc,Color.GOLD);
        }
    }
}
