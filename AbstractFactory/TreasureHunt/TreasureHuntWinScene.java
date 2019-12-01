package AbstractFactory.TreasureHunt;

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

public class TreasureHuntWinScene extends MazeWinScene {

    private Label pointLbl;
    private Stage stageParent;
    private final String TITLE="Treasure Hunter";
    private final String GAMEOVER="You Made it";
    public TreasureHuntWinScene(Stage stageParent, Parent parent, long points, ArrayList<Player> players) {
        super(parent,players);
        this.stageParent=stageParent;
        this.points=points;
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
        gameover.setStyle("-fx-font-size:58px;-fx-text-fill:green;");
        Label points_earned=new Label("Points Earned");
        points_earned.setAlignment(Pos.CENTER);
        points_earned.setStyle("-fx-font-size:14px");
       pointLbl=new Label();
        pointLbl.setStyle("-fx-font-size:72px;-fx-text-fill:white;");
        pointLbl.setAlignment(Pos.CENTER);
        pointLbl.setText(points+" points");
        HBox actions=new HBox();
        actions.setAlignment(Pos.CENTER);
        actions.setSpacing(10);
        Button saveScore=new Button("Save Score");
        Button leaderboard=new Button("Leaderboard");
        actions.getChildren().addAll(saveScore,leaderboard);
        vbox.setSpacing(20);
        vbox.getChildren().addAll(title,gameover,points_earned,pointLbl,actions);
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
    public void updateUI(){
        Platform.runLater(()->{
            pointLbl.setText(TreasureHuntMenuBar.pointsBtn.getText()+" points");
        });

    }
}
