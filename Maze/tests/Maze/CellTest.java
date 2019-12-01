package Maze;

import Adapter.Trap;
import Maze.ButtonFactory.Door;
import inventory.Models.Inventory;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    private MazePreference preference=new MazePreference();
    private  Board board=new Board(800.0,800.0,preference);
    private  Cell c=new Cell(0,0,0,0);
    private Player player=new Player("",0,new Inventory(),0,0,preference.getSizeFactor(),preference.getSizeFactor(),new Cell(0,0,0,0));

    @Test
    void isLife() {
        Cell h=new Health(0,0,0,0);
        ArrayList<Cell>cells=new ArrayList<>();
        cells.add(h);

        boolean isHealth=false;
        for (Cell cell:cells) {
          if(cell.isLife())
              isHealth= true;
        }
        assertTrue(isHealth);
    }

    @Test
    void getGc() {
        assertNotNull(board.getCellStack().get(0).gc);
    }

    @Test
    void isTrap() {
        Cell c=new Trap(0,0,0,0);
        ArrayList<Cell>cells=new ArrayList<>();
        cells.add(c);

        boolean isTrap=false;
        for (Cell cell:cells) {
            if(cell.isTrap())
                isTrap= true;
        }
        assertTrue(isTrap);
    }

    @Test
    void isItem() {
        Cell c=new Item(0,0,0,0);
        ArrayList<Cell>cells=new ArrayList<>();
        cells.add(c);

        boolean isItem=false;
        for (Cell cell:cells) {
            if(cell.isItem())
                isItem= true;
        }
        assertTrue(isItem);
    }

    @Test
    void isDoor() {
        Cell c=new Door(0,0,0,0,new Cell(1,1,0,0), Color.BLACK);
        ArrayList<Cell>cells=new ArrayList<>();
        cells.add(c);

        boolean isDoor=false;
        for (Cell cell:cells) {
            if(cell.isDoor())
                isDoor= true;
        }
        assertTrue(isDoor);
    }

    @Test
    void setColumns() {

        int col=9;
        c.setColumns(col);
        assertEquals(col,c.getColumns());

    }

    @Test
    void setRows() {

        int row=9;
        c.setRows(row);
        assertEquals(row,c.getRows());
    }

    @Test
    void changeColor() {
        c.setColor(Color.GOLD);
        c.setGc(board.gc);
        Color old=c.getColor();
        c.changeColor(Color.WHITE);
        assertNotSame(c.getColor(), old);
    }

    @Test
    void drawTrap() {
        String file="/Images/trap.png";
        assertEquals(file,c.getTrapImg(),"trap.png missing");

    }

    @Test
    void drawHp() {
        String file="/Images/heart.png";
        assertEquals(file,c.getHeartImg(),"heart.png missing");

    }

    @Test
    void drawItem() {
        String file="/Images/iphone.png";
        String file1="/Images/pile.png";
        String file2="/Images/coin.png";
        String file3="/Images/crown.png";
        String file4="/Images/diamond.png";
        assertEquals(file,c.getItem1(),"iphone.png missing");
        assertEquals(file1,c.getItem2(),"pile.png missing");
        assertEquals(file2,c.getItem3(),"coin.png missing");
        assertEquals(file3,c.getItem4(),"crown.png missing");
        assertEquals(file4,c.getItem5(),"diamond.png missing");
    }

    @Test
    void drawDoor() {
        String file="/Images/gate01.png";
        assertEquals(file,c.getGate01Img(),"gate01.png missing");
        String file2="/Images/exit.png";
        assertEquals(file2,c.getExitImg(),"exit.png missing");
    }

    @Test
    void drawRectangle() {
        String file="/Images/grass.jpg";
        assertEquals(file,c.getGrassImg(),"grass.png missing");
    }


    @Test
    void index() {
        assertTrue(BoardCells.cells.get(0).getColumns() > 0);
        assertTrue(BoardCells.cells.get(0).getRows() > 0);
    }

    @Test
    void checkNeighbours() {
        assertTrue(BoardCells.cells.get(0).getColumns() > 0);
        assertTrue(BoardCells.cells.get(0).getRows() > 0);
    }


    @Test
    void setParent() {
        assertEquals(BoardCells.cells.get(BoardCells.cells.size() - 1).parent.id, c.id);
    }
}