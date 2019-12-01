package AbstractFactory.TreasureHunt;

import AbstractFactory.MazeLoseScene;
import Maze.Navigation;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;

public class TreasureHuntLoseScene extends MazeLoseScene {
    private Stage stageParent;
    private Label time;
    private final String TITLE="Item Hunt Maze";
    private final String GAMEOVER="GAME OVER";
    private long timeleft;
    public TreasureHuntLoseScene(Stage stageParent, Parent parent, long timeleft) {

        super(parent);
        this.stageParent=stageParent;
        this.timeleft=timeleft;
        setRoot(mainScene());

    }
    public BorderPane mainScene(){
        BorderPane border=new BorderPane();
        border.prefHeightProperty().bind(this.heightProperty());
        border.prefWidthProperty().bind(this.widthProperty());
        border.setStyle("-fx-background-color:linear-gradient(#a80800,#111)");
        border.setCenter(center());
        border.setBottom(bottom());
        return border;
    }
    public VBox center(){
        VBox vbox=new VBox();
        Label title=new Label(TITLE);
        title.setStyle("-fx-font-size:26px;-fx-text-fill:white;");
        Label gameover=new Label(GAMEOVER);
        gameover.setStyle("-fx-font-size:68px;-fx-text-fill:white;");
        Label timeleft=new Label("Time Left");
        timeleft.setStyle("-fx-font-size:14px;-fx-text-fill:whitesmoke;");
        time=new Label();
        time.setStyle("-fx-font-size:50px;-fx-text-fill:white;");
        time.setText("Time UP!");//(new SimpleDateFormat("hh:MM:ss").format(timeleft));
        HBox actions=new HBox();
        Button saveScore=new Button("Save Score");
        Button leaderboard=new Button("Leaderboard");
        actions.getChildren().addAll(saveScore,leaderboard);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(title,gameover);
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

}
