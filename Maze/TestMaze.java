package Maze;

import Maze.MazeSolver.MazeSolver;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TestMaze extends Stage {
    Board board;
    MazeSolver ai;
    public TestMaze(Board board){
        this.board=board;
        ai=new MazeSolver(board,board.getRows(),board.getColumns());

        board.getChildren().add(ai);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        BorderPane border=new BorderPane();

        border.setPrefWidth(bounds.getWidth());
        border.setPrefHeight(bounds.getHeight());

        BorderPane boardContainer=new BorderPane();
        double bh=600;
        double bw=600;


        boardContainer.setPrefHeight(600);
        boardContainer.setPrefWidth(600);


        board.setBoardHeight(bh);
        board.setBoardWidth(bw);
        Button startAI=new Button("START AI");
        Button clearAI=new Button("CLEAR PATH");
        TextField ft=new TextField();
        ft.setPromptText("Speed(ms)");
        HBox box=new HBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(startAI,clearAI);
        boardContainer.setCenter(board);
        border.setCenter(boardContainer);
        border.setTop(box);

        setX(bounds.getMinX());
        setY(bounds.getMinY());
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
        setTitle("Trapped AI"); // Set the stage title
        Scene scene= new Scene(border);
        Thread t1=new Thread(ai);

        setScene(scene);
        show();
        //initModality(Modality.APPLICATION_MODAL);
       // ai.findPathTo();
       //
        startAI.setOnAction(e->{
          //  ai.findPathTo();
            ai.DRAWCOUNTER=0;
            try{
                t1.start();
            }catch(IllegalThreadStateException ex){
                ai.show();
            }

        });
        clearAI.setOnAction(e->{
            ai.clear();
        });
    }
}
