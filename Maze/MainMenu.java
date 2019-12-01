package Maze;

import Leaderboard.UI.LeaderBoardOptions;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainMenu {
private Scene scene;
private Stage parent;
private final static String sceneID="MainMenu01";
 public MainMenu(Stage parent){
    this.parent=parent;
 }
 public Scene getScene(double h,double w){
     BorderPane borderPane=new BorderPane();

     VBox vbox =new VBox();
     Label label=new Label();
     label.setStyle("-fx-font-size:60px;-fx-text-fill:	#5a5860");
     label.setText("T R A P P E D II");
     Button playBtn=new Button("\u25B6");
     Button LeaderboardBtn=new Button("Leaderboard");
     LeaderboardBtn.setOnAction(e->{
        new  LeaderBoardOptions().show();
     });

     vbox.setSpacing(10.0);
     vbox.setAlignment(Pos.CENTER);

     playBtn.setOnAction(e->{
         Scene playMenuScene=new PlayMenu(this.parent).getScene(h,w);
         Navigation.update(playMenuScene);
         this.parent.setScene(playMenuScene);
     });

     vbox.getChildren().addAll(label,playBtn,LeaderboardBtn);
     borderPane.setCenter(vbox);



    scene=new Scene(borderPane,h,w, Color.WHITE);borderPane.prefHeightProperty().bind(scene.heightProperty());
    scene.getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());
    playBtn.getStyleClass().add("playButton");
    LeaderboardBtn.getStyleClass().add("leaderboard");

     borderPane.prefWidthProperty().bind(scene.widthProperty());
    Navigation.HOME=scene;
     return scene;
 }
}
