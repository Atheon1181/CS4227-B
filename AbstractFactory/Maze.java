package AbstractFactory;

import AbstractFactory.Survival.SurvivalMenuBar;
import AbstractFactory.TimeChallenge.TimeChallengeMenuBar;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import Maze.Board;
import Maze.Navigation;
import Maze.Scenes;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import Maze.MazeParts;
import  Maze.MazePreference;
import Maze.*;
import javax.swing.border.Border;

public class Maze extends ScrollPane implements  Runnable{
    private MazeFactory factory;
    private MazeMenuBar topMenu;
    private Board gameBoard;
    private MazeWinScene winScene;
    private MazeLoseScene loseScene;
    private MazeScene mainScene;
    private Stage parent;
    private long timeLength=30000;
    public int controllers=0;
    int deadPlayers=0;
    MazePreference preference;

    protected boolean gameOver=false;

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Maze(MazeFactory f, Stage parent, int controllers){
        this.controllers=controllers;
        this.factory=f;
        this.parent=parent;
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
        setStyle("-fx-background-color:red");

    }
    public Maze(MazeFactory f, Stage parent, int controllers, MazePreference preference){
        this.controllers=controllers;
        this.factory=f;
        this.preference=preference;
        this.parent=parent;
        timeLength=preference.getLength();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
        setStyle("-fx-background-color:red");

    }
    public void createMaze(){



        this.gameBoard=factory.createBoard(800,800,controllers,preference);
       // this.gameBoard.setTraps(preference.getTrapAmnt());
      //  System.out.println(preference.getTrapAmnt());
       // this.gameBoard.setHPs(2);
        this.topMenu= factory.createMenuBar(gameBoard.getPlayers());
        this.winScene=factory.createWinScene(parent,new BorderPane(),timeLength,gameBoard.getPlayers());
        this.loseScene=factory.createLoseScene(parent,new BorderPane(),timeLength);
        this.mainScene=factory.createMazeScene(parent,new BorderPane());

        if(this.topMenu instanceof TimeChallengeMenuBar){
            timeLength=preference.getLength();
        }
        else timeLength=0;


        MazeParts.currentMenubar=topMenu;

        this.mainScene.getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());
        this.winScene.getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());
        this.loseScene.getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());

        this.topMenu.getSolutionBtn().setOnAction(e->{
            Board board=new Board(this.gameBoard);
            new TestMaze(board);

        });



        setContent(setupGUI());
        pannableProperty().set(true);
        fitToWidthProperty().set(true);
        fitToHeightProperty().set(true);
        hbarPolicyProperty().setValue(ScrollBarPolicy.AS_NEEDED);
        vbarPolicyProperty().setValue(ScrollBarPolicy.AS_NEEDED);
        System.out.println("MAZE CREATED..");

    }
    public BorderPane setupGUI(){
        BorderPane border=new BorderPane();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        border.setPrefWidth(bounds.getWidth());
        border.setPrefHeight(bounds.getHeight());
        border.setTop(this.topMenu);
        BorderPane boardContainer=new BorderPane();
        double bh=600;
        double bw=600;


        boardContainer.setPrefHeight(600);
        boardContainer.setPrefWidth(600);
        System.out.println("board height: "+bh+"\nboard width: "+bw);

       gameBoard.setBoardHeight(bh);
       gameBoard.setBoardWidth(bw);
        boardContainer.setCenter(gameBoard);
        StackPane stackPane=new StackPane();
       stackPane.getChildren().addAll(boardContainer,bottom());
        border.setCenter(stackPane);
        //border.setBottom(bottom());
        border.setStyle("-fx-background-color:black;-fx-padding:30px");
        return border;
    }
    public void setPlayerControls(){
        System.out.println(gameBoard.getPlayerBoard());
        gameBoard.getPlayerBoard().get(0).setupControllers();
      /*  switch(controllers){
        case 1:gameBoard.getPlayerBoard().get(0).setupP1Controls();break;
            case 2:{
                gameBoard.getPlayerBoard().get(0).setupP1Controls();
                gameBoard.getPlayerBoard().get(1).setupP2Controls();
            }break;
            default:break;
        }*/


    }
    public void initGame(){


        Thread t=new Thread(gameBoard);

        t.start();


    }
    public Pane bottom(){
        HBox options=new HBox();
        options.setAlignment(Pos.CENTER);
        Button backBtbn=new Button("◀");
        Button homeButton=new Button("\u2302");
        Button nextButton=new Button("▶");
        options.setSpacing(10);
        options.setAlignment(Pos.TOP_LEFT);

        backBtbn.setOnAction(e-> {
            Navigation.previous();
            parent.setScene(Navigation.ACTIVESCENE);

        });
        nextButton.setOnAction(e->{
            Navigation.forward();
            parent.setScene(Navigation.ACTIVESCENE);
        });
        homeButton.setOnAction(e->{
            parent.setScene( Navigation.HOME);

        });
        homeButton.getStyleClass().add("nav-btn");
        backBtbn.getStyleClass().add("nav-btn");
        nextButton.getStyleClass().add("nav-btn");

        options.getChildren().addAll(backBtbn,homeButton);
        return options;
    }
    @Override
    public void run() {
        while (!gameOver&& MazeGameSettings.MAINTHREADON){
            try {
                //if Time is Up
                System.out.println(this.gameBoard.isGoal());

                   if(this.mainScene.isTimed()&&!this.mainScene.isFoward())
                   {
                       if(timeLength>0){
                           timeLength=timeLength-1000;
                           topMenu.setTimer(timeLength);
                           topMenu.updateUI();


                       }else{
                           gameOver=true;
                           if(this.gameBoard.isGoal()){
                               this.winScene.setWinner(gameBoard.getPlayers().get(0));
                               this.winScene.setTimer(timeLength);
                               this.winScene.updateUI();
                               Platform.runLater(()->parent.setScene(this.winScene));
                           }
                           else{
                               this.loseScene.setTimer(timeLength);
                               this.loseScene.updateUI();
                               Platform.runLater(()->parent.setScene(this.loseScene));
                           }
                       }
                   }
                   else if(this.mainScene.isTimed()&&this.mainScene.isFoward())
                   {

                        timeLength=(timeLength+1000);
                           topMenu.setTimer(timeLength);
                           topMenu.updateUI();

                   }

                   for(Player p:gameBoard.getPlayers())
                   {
                       if (p.getHealth()<=0)deadPlayers++;
                   }
                   if(deadPlayers>=gameBoard.getPlayers().size())
                   {
                       gameOver=true;
                       this.loseScene.updateUI();
                       Platform.runLater(()->parent.setScene(this.loseScene));
                   }

                    //if Goal is reached
                if(this.gameBoard.isGoal()){
                    gameOver=true;
                    this.winScene.setTimer(timeLength);
                    this.winScene.setWinner(this.winScene.getWinner());
                    this.winScene.updateUI();
                    Platform.runLater(()->parent.setScene(this.winScene));
                }




                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
