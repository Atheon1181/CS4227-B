package Maze;

import Command.*;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PlayMenu {
    private Scene scene;
    private Stage parent;


    public PlayMenu(Stage parent){
        this.parent=parent;
    }

    public HBox navigation(){
        INavigationControl nControl= NavigationController.getNavigation();
        PreviousScene previousScene=new PreviousScene(nControl);
        previousScene.setText("◀");
        NextScene nextScene=new NextScene(nControl);
        nextScene.setText("▶");
        ChangeScene changeScene=new ChangeScene(previousScene);
        previousScene.setOnAction(e->{changeScene.press();parent.setScene(Navigation.ACTIVESCENE);});
        nextScene.setOnAction(e->{changeScene.press();parent.setScene(Navigation.ACTIVESCENE);});
        previousScene.getStyleClass().add("nav-btn");
        nextScene.getStyleClass().add("nav-btn");

        HBox vbox=new HBox();
        vbox.getStyleClass().add("nav-bar");
        Button backBtbn=new Button("◀");
        Button nextButton=new Button("▶");
        backBtbn.getStyleClass().add("nav-btn");
        nextButton.getStyleClass().add("nav-btn");
        vbox.setSpacing(10);
       vbox.getChildren().addAll(previousScene,nextScene);
        backBtbn.setOnAction(e->{
            Navigation.previous();
            parent.setScene(Navigation.ACTIVESCENE);

        });
        nextButton.setOnAction(e->{
            Navigation.forward();
            parent.setScene(Navigation.ACTIVESCENE);
        });
        return vbox;

    }
    public Scene getScene(double h, double w){
        GridPane gridPane = new GridPane();


        VBox vbox =new VBox();
        VBox b1=new VBox();
        b1.setAlignment(Pos.CENTER);
        b1.setSpacing(10);

        VBox b2=new VBox();
        b2.setAlignment(Pos.CENTER);
        b2.setSpacing(10);

        VBox b3=new VBox();
        b3.setAlignment(Pos.CENTER);
        b3.setSpacing(10);

        VBox b4=new VBox();
        b4.setAlignment(Pos.CENTER);
        b4.setSpacing(10);

        ImageView iv1=new ImageView(new Image(getClass().getResourceAsStream("/Images/clock.png")));
        iv1.setFitHeight(67);
        iv1.setFitWidth(69);
        Button timeChallenge=new Button("",iv1);

        timeChallenge.setOnAction(e->{
            playGame(GameModes.TIMECHALLENGE);
        });
        b1.setOnMouseClicked(e->{
            playGame(GameModes.TIMECHALLENGE);
        });
        Label l1=new Label("Time Challenge");

        ImageView iv2=new ImageView(new Image(getClass().getResourceAsStream("/Images/treasure.png")));
        iv2.setFitHeight(67);
        iv2.setFitWidth(69);
        Button treasureHunt=new Button("",iv2);
        treasureHunt.setOnAction(e->{
            playGame(GameModes.TREASUREHUNT);
        });
        b2.setOnMouseClicked(e->{
            playGame(GameModes.TREASUREHUNT);
        });
        Label l2=new Label("Treasure Hunt");

        ImageView iv3=new ImageView(new Image(getClass().getResourceAsStream("/Images/multiplayer.png")));
        iv3.setFitHeight(67);
        iv3.setFitWidth(69);
        Button multiplayer=new Button("",iv3);
        multiplayer.setOnAction(e->{
            playGame(GameModes.MULTIPLAYER);
        });
        b3.setOnMouseClicked(e->{
            playGame(GameModes.MULTIPLAYER);
        });
        Label l3=new Label("Multi-Player");

        ImageView iv4=new ImageView(new Image(getClass().getResourceAsStream("/Images/danger.png")));
        iv4.setFitHeight(67);
        iv4.setFitWidth(69);
        Button survival=new Button("",iv4);
        survival.setOnAction(e->{
            playGame(GameModes.SURVIVAL);
        });
        b4.setOnMouseClicked(e->{
            playGame(GameModes.SURVIVAL);
        });
        Label l4=new Label("Survival");

        b1.getChildren().addAll(timeChallenge,l1);
        b2.getChildren().addAll(treasureHunt,l2);
        b3.getChildren().addAll(multiplayer,l3);
        b4.getChildren().addAll(survival,l4);


        vbox.setSpacing(10.0);
        vbox.setAlignment(Pos.CENTER);

        BorderPane borderPane=new BorderPane();


        gridPane.add(b1, 0, 0, 1, 1);
        gridPane.add(b2, 1, 0, 1, 1);
        gridPane.add(b3,0,1,1,1);
        gridPane.add(b4,1,1,1,1);

        vbox.getChildren().addAll(gridPane);
        gridPane.getStyleClass().add("playBoxHolder");
        vbox.getStyleClass().add("playBoxHolder");
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setTop(navigation());
        borderPane.setCenter(vbox);

        scene=new Scene(borderPane,h,w, Color.WHITE); borderPane.prefHeightProperty().bind(scene.heightProperty());
        scene.getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());

        timeChallenge.getStyleClass().add("modeBtn");
        treasureHunt.getStyleClass().add("modeBtn");
        survival.getStyleClass().add("modeBtn");
        multiplayer.getStyleClass().add("modeBtn");
        l1.getStyleClass().add("modeTxt");
        l2.getStyleClass().add("modeTxt");
        l3.getStyleClass().add("modeTxt");
        l4.getStyleClass().add("modeTxt");

        double playBoxSize=400;

        b1.getStyleClass().add("playBox");
        b1.setPrefSize(playBoxSize,playBoxSize);
        b2.getStyleClass().add("playBox");
        b2.setPrefSize(playBoxSize,playBoxSize);
        b3.getStyleClass().add("playBox");
        b3.setPrefSize(playBoxSize,playBoxSize);
        b4.getStyleClass().add("playBox");
        b4.setPrefSize(playBoxSize,playBoxSize);


        borderPane.prefWidthProperty().bind(scene.widthProperty());
        return scene;
    }

    public void playGame(GameModes mode) {
        MazeGameSettings.currentMode=mode;
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        GameSetting game = new GameSetting(parent, mode);
        Navigation.update(game.getScene(bounds.getHeight(), bounds.getWidth()));
        parent.setScene(game.getScene(bounds.getHeight(), bounds.getWidth()));
    }
}
