package Maze;

import AbstractFactory.Maze;
import AbstractFactory.Multiplayer.MultiplayerMazeFactory;
import AbstractFactory.Survival.SurvivalMazeFactory;
import AbstractFactory.TimeChallenge.TimeChallengeMazeFactory;
import AbstractFactory.TreasureHunt.TreasureHuntMazeFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class GameScene  {
    private Scene scene;
    private final static String sceneID="GameScene01";
    private Stage parent;
    private Maze maze;
    private int controlers;
    public GameScene(Stage parent, GameModes modes,MazePreference preference){

        this.parent=parent;
        switch(modes){
            case MULTIPLAYER:
            {
                maze=new Maze(new MultiplayerMazeFactory(),parent,2,preference);

            }break;
            case SURVIVAL:
            {
                maze=new Maze(new SurvivalMazeFactory(),parent,1,preference);

            }break;
            case TREASUREHUNT:
            {
                maze=new Maze(new TreasureHuntMazeFactory(),parent,1,preference);

            }break;
            default:  maze=new Maze(new TimeChallengeMazeFactory(),parent,1,preference);

        }

        Thread t=new Thread(maze);
        MazeGameSettings.MAINTHREADON=true;
        maze.createMaze();
    /*  Board board=new Board(maze.getGameBoard().getBoardWidth(),maze.getGameBoard().getBoardHeight(),maze.getGameBoard().getSizeFactor());
  //  board.setCellStack(maze.getGameBoard().getCellStack());

*/

        maze.initGame();
        t.start();//new TestMaze(board);
        //getScene(h,w);
    }
    public GameScene(Stage parent, GameModes modes){

    this.parent=parent;
    switch(modes){
        case MULTIPLAYER:
        {
            maze=new Maze(new MultiplayerMazeFactory(),parent,2);

        }break;
        case SURVIVAL:
        {
            maze=new Maze(new SurvivalMazeFactory(),parent,1);

        }break;
        case TREASUREHUNT:
        {
            maze=new Maze(new TreasureHuntMazeFactory(),parent,1);

        }break;
        default:  maze=new Maze(new TimeChallengeMazeFactory(),parent,1);

    }

        Thread t=new Thread(maze);
        MazeGameSettings.MAINTHREADON=true;
    maze.createMaze();
    /*  Board board=new Board(maze.getGameBoard().getBoardWidth(),maze.getGameBoard().getBoardHeight(),maze.getGameBoard().getSizeFactor());
  //  board.setCellStack(maze.getGameBoard().getCellStack());

*/

        maze.initGame();
    t.start();//new TestMaze(board);
    //getScene(h,w);
    }
   /* public HBox navigation(){
        HBox vbox=new HBox();
        javafx.scene.control.Button backBtbn=new Button("◀");
         Button nextButton=new Button("▶");
        vbox.setSpacing(10);
         vbox.getChildren().addAll(backBtbn,nextButton);
        backBtbn.setOnAction(e-> {
                    Navigation.previous();
                    parent.setScene(Navigation.ACTIVESCENE);

                });
            nextButton.setOnAction(e->{
                Navigation.forward();
                parent.setScene(Navigation.ACTIVESCENE);
            });
            return vbox;

    }*/
    public Scene getScene(double h,double w){


        double bh=700;
        double bw=700;


        Scenes.GAMESCENE=new Scene(maze,h,w);
        Scenes.GAMESCENE.getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());


    maze.setPlayerControls();
       return Scenes.GAMESCENE;

    }
}
