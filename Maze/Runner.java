package Maze;

import javafx.application.Application;
import javafx.stage.Stage;

public class Runner extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Board board=new Board(800,800);
      new TestMaze(board);

    }
}
