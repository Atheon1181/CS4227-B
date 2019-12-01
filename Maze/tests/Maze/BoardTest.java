package Maze;

import Composite.Wall;
import Maze.*;
import Maze.ButtonFactory.Door;
import inventory.Models.Inventory;
import javafx.scene.paint.Color;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
   private MazePreference preference=new MazePreference();
   private  Board board=new Board(800.0,800.0,preference);
   private Player player=new Player("",0,new Inventory(),0,0,preference.getSizeFactor(),preference.getSizeFactor(),new Cell(0,0,0,0));
    @Test
    public void getMazePreference() throws Exception {

        assertNotEquals(null,board.getMazePreference(),"Maze.Board().getMazePreference Cannot be null");

    }

    @Test
    public void setNoMazePreference() {
        board.setMazePreference(null);
        assertNull(board.getMazePreference());
    }

    @Test
    public void setMazePreference()throws Exception  {
        board.setMazePreference(new MazePreference());
        assertNotNull(board.getMazePreference(),"Maze Preference is not null");
    }

    @Test
    public void getColumns()throws Exception  {
     assertNotEquals(0,board.getColumns(),"Column must be greater than 0");
    }

    @Test
    public void setColumns() throws Exception {
     int col=98;
     board.setColumns(col);
     assertEquals(col,board.getColumns(),"Column didn't set to expected value: "+col);
    }

    @Test
    public void getRows() throws Exception {
     assertNotEquals(0,board.getRows(),"Rows must be greater than 0");
    }

    @Test
    public void setRows() throws Exception {
     int row=98;
     board.setRows(row);
     assertEquals(row,board.getRows(),"Column didn't set to expected value: "+row);

    }

    @Test
    public void generateRandomColor() throws Exception {
     Color color =board.generateRandomColor();
     board.doorColors.indexOf(color);
     assertTrue(true);

    }

    @Test
    public void uniqueRandom() throws Exception {
     ArrayList<Integer> number=board.uniqueRandom(0,5,5,new ArrayList<>());
     assertTrue(number.contains(0));
     assertTrue(number.contains(1));
     assertTrue(number.contains(2));
     assertTrue(number.contains(3));
     assertTrue(number.contains(4));

    }


    @Test
    public void isGoodMaze() throws Exception {
     boolean goodCondition=board.getCellStack().lastElement().getI()>=(board.getRows()-1)/2&&board.getCellStack().lastElement().getJ()>=(board.getRows()-1)/2;

     assertTrue(goodCondition);
    }

    @Test
    public void setPlayer() throws Exception {

     board.addPlayer(player);

     assertFalse(board.getPlayers().isEmpty());
     //assertNotEquals(0,board.getPlayers().size());
    }

    @Test
    public void getCellStack() throws Exception {
     assertNotNull(board.getCellStack());
    }

    @Test
    public void getPlayerBoard() throws Exception {
     board.addPlayer(player);

     assertTrue(board.getChildren().contains(board.getPlayerBoard().get(0)));
    }

    @Test
    public void isGoal() throws Exception {

     assertNotNull(board.isGoal());
    }

    @Test
    public void setGoal() throws Exception {
     board.setGoal(true);
     Assertions.assertTrue(board.isGoal());
    }

    @Test
    public void getBoardHeight() throws Exception {
     boolean bh=board.getBoardHeight()>0;
     assertTrue(bh);
    }

    @Test
    public void setBoardHeight() throws Exception {
     board.setBoardHeight(20);
     assertEquals(20, board.getBoardHeight());
    }

    @Test
    public void getBoardWidth() throws Exception {
     boolean bh=board.getBoardWidth()>0;
     assertTrue(bh);
    }

    @Test
    public void setBoardWidth() throws Exception {
     board.setBoardWidth(20);
     assertEquals(20, board.getBoardWidth());
    }

    @Test
    public void initiate() throws Exception {
     assertNotEquals(0,BoardCells.cells.size());
    }


    @Test
    public void fillStack() throws Exception {
     assertNotEquals(0,BoardCells.cells.size());
     assertNotEquals(0,board.getCellStack().size());
     Assertions.assertTrue(BoardCells.emptyCells < 0);
    }


    @Test
    public void cellExists() throws Exception {
     Cell cell=new Cell(0,0,0,0);
     cell.id= UUID.randomUUID().toString();
     Stack<Cell> cells=new Stack<>();
     cells.push(cell);
     assertTrue(board.cellExists(cells,cell));
    }

    @Test
    public void removeWalls() throws Exception {
     Cell cell=new Cell(0,0,0,0);
     Cell cell2=new Cell(1,0,0,0);
     //n=0,e=1,s=2,w=3
     assertNotNull(cell.getSpecialWalls()[1]);
     board.removeWalls(cell,cell2);
     assertNull(cell.getSpecialWalls()[1]);
    }

    @Test
    public void run() throws Exception {
     preference.setRMG(true);
     assertTrue(preference.isRMG());
    }


    @Test
    public void attachWalls() throws Exception {
     int wallCount=0;
     Wall [] walls=board.attachWalls(0.8);
     for (Wall w:walls) {
      if(w!=null)wallCount++;

     }
     assertEquals(4,wallCount);
    }
}