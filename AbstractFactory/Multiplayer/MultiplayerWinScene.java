package AbstractFactory.Multiplayer;

import AbstractFactory.MazeWinScene;
import Maze.Navigation;
import Maze.Player;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MultiplayerWinScene extends MazeWinScene {

    private Label time;
    private Stage stageParent;
    private final String TITLE="Multiplayer Maze";
    private final String GAMEOVER="Winner is";
    private  Label winnerLbl;

    public MultiplayerWinScene(Stage stageParent, Parent parent, long timeleft, ArrayList<Player> players) {
        super(parent,players);
        this.stageParent=stageParent;
        this.timer=timeleft;
        setRoot(mainScene());

    }
    public BorderPane mainScene(){
        BorderPane border=new BorderPane();
        border.prefHeightProperty().bind(this.heightProperty());
        border.prefWidthProperty().bind(this.widthProperty());
        border.setStyle("-fx-background-color:#111");
        border.setCenter(center());
        border.setBottom(bottom());
        return border;
    }
    public VBox center(){
        VBox vbox=new VBox();
        vbox.setAlignment(Pos.CENTER);
        Label title=new Label(TITLE);
        title.setAlignment(Pos.CENTER);
        title.setStyle("-fx-font-size:26px;-fx-text-fill:white;");
        Label gameover=new Label(GAMEOVER);
        gameover.setAlignment(Pos.CENTER);
        gameover.setStyle("-fx-font-size:38px;-fx-text-fill:green;");
       winnerLbl=new Label("");
        winnerLbl.setAlignment(Pos.CENTER);
        winnerLbl.setStyle("-fx-font-size:74px;-fx-text-fill:white");

        time=new Label();
        time.setStyle("-fx-font-size:72px;-fx-text-fill:white;");
        time.setAlignment(Pos.CENTER);
        time.setText(new SimpleDateFormat("mm:ss").format(timer));
        HBox actions=new HBox();
        actions.setAlignment(Pos.CENTER);
        actions.setSpacing(10);
        Button saveScore=new Button("Play Again");
        Button leaderboard=new Button("Leaderboard");
        actions.getChildren().addAll(saveScore);
        vbox.setSpacing(20);
        vbox.getChildren().addAll(title,gameover,winnerLbl);
        return vbox;
    }
    public Pane bottom(){
        HBox options=new HBox();
        Button backBtbn=new Button("◀");
        Button homeButton=new Button("\u2302");
        Button nextButton=new Button("▶");
        options.setSpacing(10);
        homeButton.getStyleClass().add("nav-btn");
        backBtbn.getStyleClass().add("nav-btn");

        backBtbn.setOnAction(e-> {
            Navigation.previous();
            stageParent.setScene(Navigation.ACTIVESCENE);

        });
        nextButton.setOnAction(e->{
            Navigation.forward();
            stageParent.setScene(Navigation.ACTIVESCENE);
        });
        homeButton.setOnAction(e->{
            stageParent.setScene( Navigation.HOME);

        });
        options.getChildren().addAll(backBtbn,homeButton);
        return options;
    }
    @Override
    public Player getWinner(){

        for (Player p:players) {
          //  System.out.println("Player: "+p.getName()+" Done: "+p.isDone());
            if(p.isDone()){
                super.Winner=p;
            break;}
        }
        return Winner;

    }
    @Override
    public void updateUI(){
        Platform.runLater(()->{
          //  time.setText(new SimpleDateFormat("mm:ss").format(timer));
            winnerLbl.setText(Winner.getName());
        });

    }
}
