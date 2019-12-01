//*
// /**
// * The Maze.Board
// *
// * Need to generate a maze
// */
//import Maze.Cell;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.Random;
//
//import javax.swing.JPanel;
//
//public class Board extends JPanel{
//	private Cell[][] cells;
//	private HashMap<String, Cell> walls;
//	private LinkedList<String> mapKeys;
//
//	private int cellWidth;
//	private int cellHeight;
//
//
//	public Board(int cellWidth, int cellHeight) {
//	  setBackground(Color.BLACK);
//	  walls = new HashMap<String, Cell>();
//	  mapKeys = new LinkedList<String>();
//
//	  this.cellWidth = cellWidth;
//	  this.cellHeight = cellHeight;
//
//	}
//
//	public void paint(Graphics g) {
//		super.paint(g);
//		Graphics2D g2d = (Graphics2D) g;
//		drawMaze(g2d);
//	}
//
//	/**
//	 * Draws the maze onto the board
//	 */
//	public void drawMaze(Graphics2D g2d) {
//
//		//int cellx = cells.length-1;
//		//int celly = cells[0].length - 1;
//
//		for (int i=0; i < cells.length-1; i++) {
//			for (int j=0; j < cells[i].length - 1; j++) {
//				//if (cells[i][j] != null) {
//					/*if (cells[i][j].isPassable()) {
//						System.out.println("Row: " + i + " Column: " + j);
//						g2d.setColor(Color.RED);
//						g2d.drawRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
//					}
//				} else {*/
//					//g2d.setColor(Color.BLACK);
//					//g2d.drawRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
//
//
//				//}
//			}
//		}
//	}
//
//	/**
//	 * Creates cells and tries to generate a maze
//	 */
//	public void createMaze() {
//
//		Random seed = new Random();
//
//		int width = this.getWidth();
//		int height = this.getHeight();
//
//		int cellsPerRow = width/cellWidth;
//		int cellsPerColumn = height/cellHeight;
//
//		cells = new Cell[cellsPerRow][cellsPerColumn];
//
//		//Pick the first cell
//		Cell firstCell = new Cell(cellHeight, cellWidth, true);
//		int theRow = seed.nextInt(cellsPerRow);
//		int theColumn = seed.nextInt(cellsPerColumn);
//		System.out.println("First row: " + theRow + " First Column: " + theColumn);
//		cells[theRow][theColumn] = firstCell;
//
//		//Add the adjacent walls to the map
//		addAdjacentWalls(theRow, theColumn, cellWidth, cellHeight);
//
//		Cell newCell = new Cell(cellHeight, cellWidth, false);
//
//		/**
//		 * Loop through the walls
//		 */
//		while (!mapKeys.isEmpty()) {
//
//			//Get a random key from the Keymap
//			String posKey = mapKeys.remove(seed.nextInt(mapKeys.size()));
//
//			newCell = walls.get(posKey);
//
//			//Split the pos string
//			String parts[] = posKey.split(" ");
//			theRow = Integer.parseInt(parts[0]);
//			theColumn = Integer.parseInt(parts[1]);
//
//			System.out.println("The Row: " + theRow);
//			System.out.println("The Column: " + theColumn);
//
//			//Check that the opposite cells haven't been picked
//			if (!this.checkAdjacent(theRow, theColumn)) {
//
//				//Add the cell to the floor map
//				newCell.setPassable(true);
//				cells[theRow][theColumn] = newCell;
//
//				//Add the adjacent walls to the map
//				addAdjacentWalls(theRow, theColumn, cellWidth, cellHeight);
//			}
//
//		}
//
//	}
//
//	/**
//	 * Checks the surrounding cells of the cell at [row][column]
//	 *
//	 * @param theRow
//	 * @param theColumn
//	 * @param cellHeight
//	 * @param cellWidth
//	 */
//	public boolean checkAdjacent(int theRow, int theColumn) {
//
//		for (int i=-1; i<2; i++) {
//			for (int j=-1; j<2; j++) {
//				//Check for array out of bounds exceptions
//				if (!((theRow == 0 && i == -1 ) || (theColumn == 0 && j == -1))) {
//
//					//Make sure the corners aren't selected.
//					if (!((i+j)%2 == 0 || theRow + i > cells.length || theColumn + j > cells[theColumn].length -1)) {
//
//
//						if (cells[theRow+i][theColumn+j] == null ||!cells[theRow+i][theColumn+j].isPassable()) {
//							return false;
//						}
//					}
//				}
//
//			}
//		}
//
//		return true;
//	}
//
//	/**
//	 * Adds the cells above, below, left and right of the cell specified by cellColumn and cellRow
//	 * to the walls map
//	 *
//	 * @param cellColumn
//	 * @param cellRow
//	 * @param cellWidth
//	 * @param cellHeight
//	 */
//	public void addAdjacentWalls(int cellRow, int cellColumn, int cellWidth, int cellHeight) {
//
//		for (int i=-1; i<2; i++) {
//			for (int j=-1; j<2; j++) {
//				//Check for array out of bounds exceptions
//				if (!((cellRow == 0 && i == -1 ) || (cellColumn == 0 && j == -1))) {
//
//					//Make sure the corners aren't selected.
//					if (!((i+j)%2 == 0 || cellRow + i > cells.length || cellColumn + j > cells[cellColumn].length -1)) {
//
//						Cell aWall = new Cell(cellHeight, cellWidth, false);
//
//						cellRow += i;
//						cellColumn += j;
//
//						//Create the key
//						String pos = "" + cellRow +" "+ cellColumn;
//
//						//Check that the wall has not already been added.
//						if (!walls.containsKey(pos)) {
//							//Add the wall
//							mapKeys.add(pos);
//							walls.put(pos , aWall);
//						} else {
//							mapKeys.remove(pos);
//							walls.remove(pos);
//						}
//					}
//				}
//
//			}
//
//		}
//
//	}
//
//}*/