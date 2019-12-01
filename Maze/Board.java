package Maze;

import Composite.BombedWall;
import Composite.NormalWall;
import Composite.Wall;
import Maze.ButtonFactory.Door;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javafx.scene.canvas.*;

import java.util.*;

public class Board extends StackPane implements Runnable
{
    private Cell current;
    protected Stack<Cell> cellStack,virtualStack;
    private double height=500;
    private double width=500;
    private int sizeFactor=60;//reccomended 60
    private int columns,rows;
    private Canvas canvas;
    private boolean goal;
    //private ArrayList<Cell>cells=new ArrayList<>();
    protected GraphicsContext gc;
    protected ArrayList<PlayerBoard> playerBoard=new ArrayList<>();
    protected ArrayList<Player> players=new ArrayList<>();
    public ArrayList<Color> doorColors=new ArrayList<>();
    private MazePreference mazePreference=new MazePreference();

    public MazePreference getMazePreference() {
        return mazePreference;
    }

    public void setMazePreference(MazePreference mazePreference) {
        this.mazePreference = mazePreference;
    }


    public Board(Board b){
        this.height=b.height+300;
        this.width=b.width+300;
        this.sizeFactor=b.mazePreference.getSizeFactor();
        this.mazePreference=b.mazePreference;
        this.canvas=new Canvas(height,height);
        this.gc=canvas.getGraphicsContext2D();
        this.cellStack=b.getCellStack();
        this.columns=b.getColumns();
        this.rows=b.getRows();
        current=BoardCells.cells.get(0);
        getChildren().addAll(canvas);
        for(int i=0;i<BoardCells.cells.size();i++){
            draw(i);
        }
        for(int i=0;i<cellStack.size();i++){
            drawPath(i);
        }
       // fillStack();

    }
    public Board(double height, double width, MazePreference mazePreference){
        this.height=height;
        this.width=width;
        this.sizeFactor=mazePreference.getSizeFactor();
        this.mazePreference=mazePreference;
        cellStack=new Stack<>();
        virtualStack=new Stack<>();
        virtualStack.addAll(cellStack);
        canvas=new Canvas(height,height);

        this.width=width+20;
        this.height=height+20;
        this.setHeight(height);
        this.setWidth(height);
        gc= canvas.getGraphicsContext2D();
        MazeParts.graphicsContext=gc;

        this.columns=(int)Math.floor(width/sizeFactor);
        this.rows=(int)Math.floor(height/sizeFactor);
        BoardCells.cols=this.columns;
        BoardCells.rows=this.rows;
        System.out.println(getHeight()+"--"+getWidth());
        setStyle("-fx-background-color:#051806;-fx-margin:20px");
        initiate();
        current=BoardCells.cells.get(0);


        // playerBoard=new PlayerBoard(player,this);

        getChildren().addAll(canvas);



        current.visited=true;

        BoardCells.emptyCells=BoardCells.cells.size();
        BoardCells.emptyCells--;


       /* while(cellStack.size()<=BoardCells.cells.size()){
            fillStack();
        }*/
        for(int i=0;i<BoardCells.cells.size();i++){
            draw(i);
        }
        fillStack();


    }

    public Board(double height,double width){
        this.height=height;
        this.width=width;
        cellStack=new Stack<>();
        virtualStack=new Stack<>();
        virtualStack.addAll(cellStack);
        canvas=new Canvas(height,height);

        this.width=width+20;
        this.height=height+20;
        this.setHeight(height);
        this.setWidth(height);
        gc= canvas.getGraphicsContext2D();

        this.columns=(int)Math.floor(width/sizeFactor);
        this.rows=(int)Math.floor(height/sizeFactor);
        System.out.println(getHeight()+"--"+getWidth());
        setStyle("-fx-background-color:#111;-fx-margin:20px");
        initiate();
        current=BoardCells.cells.get(0);

        // playerBoard=new PlayerBoard(player,this);

        getChildren().addAll(canvas);



        current.visited=true;

        BoardCells.emptyCells=BoardCells.cells.size();
        BoardCells.emptyCells--;


       /* while(cellStack.size()<=BoardCells.cells.size()){
            fillStack();
        }*/
        for(int i=0;i<BoardCells.cells.size();i++){
            draw(i);
        }
        fillStack();


    }


    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public  Color generateRandomColor(){
        Random randomGenerator = new Random();
        int red = randomGenerator.nextInt(256);
        int green = randomGenerator.nextInt(256);
        int blue = randomGenerator.nextInt(256);

        Color randomColor = Color.rgb(red,green,blue);
        if(doorColors.indexOf(randomColor)==-1)doorColors.add(randomColor);
        return randomColor;

    }
public ArrayList<Integer> uniqueRandom(int min, int max, int size,ArrayList<Integer> temp){
        if(max<size)max=size;
       int num=0;
    System.out.println(size);
        if (size<=0)
            return temp;
        else {
            num=(int)(Math.random()*max);
            if(temp.contains(num))uniqueRandom(min,max,size,temp);
            else{
                temp.add(num);
                size--;
                uniqueRandom(min,max,size,temp);
            }
        }
        return temp;

}
    public  void setDoors(int numOfDoors,int max){

         max=(int)(sizeFactor/10)/2;
        // numOfDoors=(int)(Math.random()*max);
        numOfDoors=Math.min(numOfDoors, cellStack.size());//umOfDoors>=cellStack.size()-1&&numOfDoors/2>=cellStack.size()-1? (cellStack.size()-1)/2 :numOfDoors/2; //
       ArrayList<Integer>allIndexes=uniqueRandom(0,cellStack.size(),numOfDoors,new ArrayList<>());
        System.out.println(allIndexes);
       int i=0;
       while (i<allIndexes.size()-1) {
            System.out.println(numOfDoors);
            int entry=allIndexes.get(i++);
            int exit=allIndexes.get(i++);
            if(exit==entry){
                exit=(int)((Math.random()*cellStack.size()-2)+1)+2;
            }
            System.out.println("??? "+entry);
            Cell c=cellStack.get(entry);
            c.setGc(this.gc);
            Color color=generateRandomColor();
            BoardCells.cells.get(entry).changeColor(color);
            BoardCells.cells.get(entry).setDoor(true,true);
            if(cellStack.get(entry).id!=cellStack.lastElement().id)
            BoardCells.cells.set(entry,new Door(c.getI(),c.getJ(),c.getWidth(),c.getHeight(), BoardCells.cells.get(exit),color));
            numOfDoors--;
        }




    }

    public void isGoodMaze(){
        if(cellStack.lastElement().getI()>=(rows-1)/2&&cellStack.lastElement().getJ()>=(columns-1)/2){

        }
        else{
            cellStack.clear();
            virtualStack.clear();
            initiate();
            current=BoardCells.cells.get(0);
            BoardCells.emptyCells=BoardCells.cells.size();
            current.visited=true;
            BoardCells.emptyCells--;
            for(int i=0;i<BoardCells.cells.size();i++){
                draw(i);
            }
            fillStack();

        }
    }
    public void setPlayer(ArrayList<Player>players){
        this.players=players;
        for(Player p:players){
            p.setColumns(columns);
            p.setRows(rows);


        }
        PlayerBoard pb=new PlayerBoard(players,this);
        playerBoard.add(pb);
        getChildren().add(pb);

    }
    public void addPlayer(Player p){
        this.players.add(p);
        p.setColumns(columns);
        p.setRows(rows);
        PlayerBoard pb=new PlayerBoard(players,this);
        playerBoard.add(pb);
        getChildren().add(pb);
    }


    public Stack<Cell> getCellStack() {
        return cellStack;
    }

    public void setCellStack(Stack<Cell> cellStack) {
        this.cellStack = cellStack;
    }

    public ArrayList<PlayerBoard> getPlayerBoard() {
        return this.playerBoard;
    }



    public boolean isGoal() {
        return this.goal;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public double getBoardHeight() {
        return height;
    }


    public void setBoardHeight(double height) {
        this.height = height;
    }


    public double getBoardWidth() {
        return width;
    }


    public void setBoardWidth(double width) {
        this.width = width;
    }

    public void initiate(){
        BoardCells.cells=new ArrayList<>();
        String[][]ids={{"A","B","C"},{"D","E","F"},{"G","H","I"}};
        for(int j=0;j<rows;j++){
            for(int i=0;i<columns;i++){
               // System.out.println("i: "+i+" j: "+j);
                Cell cell=new Cell(i,j,sizeFactor,sizeFactor);
                if(i==0||j==0||j==rows-1||i==columns-1){
                    Wall[] walls=new Wall[]{new NormalWall(),new NormalWall(),new NormalWall(),new NormalWall()};
                    cell.setSpecialWalls(walls);
                }else
                cell.setSpecialWalls(attachWalls(mazePreference.getWallDifficulty()/100));

                cell.id=UUID.randomUUID().toString();//ids[i][j];//
                cell.setColumns(columns);
                cell.setRows(rows);
                BoardCells.cells.add(cell);

            }
        }

    }

    public void draw(int i){

        BoardCells.cells.get(i).show(gc);
        System.out.println("drawing..");




    }
    public void fillStack(){
        current.visited=true;
        System.out.println("cellStack: "+cellStack.size());
        System.out.println("board: "+(BoardCells.cells.size()-1));
        System.out.println(BoardCells.emptyCells+" Empty cells left");

        System.out.println("current: "+current);
        Cell next=current.checkNeighbours();
        System.out.println("Next: "+next);

        if(!cellExists(cellStack,current))
        {BoardCells.emptyCells--;}
        if(!cellExists(cellStack,current))
            cellStack.push(current);
        if(next!=null){
            next.visited=true;

            virtualStack.push(current);
            if(!cellExists(cellStack,current))
            cellStack.push(current);

            removeWalls( current, next);
            current=next;


        }else if(virtualStack.size()>0){
            System.out.println("Current: "+current);
            current=virtualStack.pop();



        }
       // if(cellStack.size()<BoardCells.cells.size()-1){

        if(BoardCells.emptyCells>=0) {
            fillStack();

        }else{
            System.out.println("No More empty cells");
            BoardCells.path.addAll(cellStack);
            setDoors(mazePreference.getDoorAmnt(),mazePreference.getDoorAmnt());

            isGoodMaze();
        }

    }
    public void setTraps(int amt){

    }
    public void setHPs(int amt){}
    public void drawPath(int i){
        System.out.println(i+") Drawing "+cellStack.size()+" Cells: CellId - "+cellStack.get(i).id);
        cellStack.lastElement().hightlight(gc);
        cellStack.firstElement().isFirst=true;
        cellStack.lastElement().isEnd=true;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
               if(cellStack.get(i).isDoor()){
                   System.out.println(cellStack.get(i).getEntry());
                   cellStack.get(i).drawDoor(gc);
                }else
                cellStack.get(i).drawRectangle(gc);
            //    System.out.println(cellStack);
            }
        });
    }

    public boolean cellExists(Stack<Cell> list,Cell cell ){
        boolean oldCell=false;
        for (Cell c:list) {
            if(c.id.equals(cell.id))
                oldCell=true;

        }
        return oldCell;
    }
    public void removeWalls(Cell a,Cell b){
        int x=a.getI()-b.getI();
       // System.out.println("Remove wall at: "+x);
        //eastern neighbours
        if(x==1){
            a.changeWallAt(3,null);
            b.changeWallAt(1,null);
        }
        //western neighbours
        else if(x==-1){
            a.changeWallAt(1,null);
            b.changeWallAt(3,null);
        }
        int y=a.getJ()-b.getJ();
        //northern
        if(y==1){
            a.changeWallAt(0,null);
            b.changeWallAt(2,null);
        }
        //southern
        else if(y==-1){
            a.changeWallAt(2,null);
            b.changeWallAt(0,null);
        }
    }
    public void setSizeFactor(int size){
        if(size>=40){
            this.sizeFactor=size;
        }

    }

    @Override
    public void run() {
        int i=0;
        //i<cellStack.size()
        if(mazePreference.isRMG())
        while(i<cellStack.size()){

            try {
                drawPath(i);
                i++;

                Thread.sleep(mazePreference.isRMG()?10:0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        else  for( i=0;i<cellStack.size();i++){
            drawPath(i);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getSizeFactor() {
        return sizeFactor;
    }
    public Wall[] attachWalls(double chance){
        Wall walls[]=new Wall[4];
        double rand=Math.random()*(Math.abs(1.0-chance));
        if(chance<=0.01){
            if(rand<=.25&&rand>=0){
                //add Bombed wall
                walls[0]=new BombedWall();

            }
            else   walls[0]=new NormalWall();


        double rand1=Math.random()*(1.0-(chance/2));
        if(rand1<=.25&&rand1>=0){
            //add Bombed wall
            walls[1]=new BombedWall();

        }
        else   walls[1]=new NormalWall();

        double rand2=Math.random()*(1.0-(chance/3));
        if(rand2<=.25&&rand2>=0){
            //add Bombed wall
            walls[2]=new BombedWall();

        }
        else   walls[2]=new NormalWall();

        double rand3=Math.random()*(1.0-(chance/4));
        if(rand3<=.25&&rand3>=0){
            //add Bombed wall
            walls[3]=new BombedWall();

        }
        else   walls[3]=new NormalWall();
    }
        else{
            walls[0]=new NormalWall();
            walls[1]=new NormalWall();
            walls[2]=new NormalWall();
            walls[3]=new NormalWall();
        }
        return walls;
    }
}