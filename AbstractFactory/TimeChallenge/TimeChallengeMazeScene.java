package AbstractFactory.TimeChallenge;

import AbstractFactory.MazeScene;
import Maze.Board;
import Maze.Navigation;
import Maze.Stages;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TimeChallengeMazeScene extends MazeScene {
    private Pane header;
    private Board center;
    private Stage parentStage;
    public TimeChallengeMazeScene(Stage parentStage, Parent parent) {
        super(parent);
        this.parentStage=parentStage;
        setRoot(mainScene());
        super.timed=true;


    }

    public Board getCenter() {
        return center;
    }

    public void setCenter(Board center) {
        this.center = center;
    }

    public Pane getHeader() {
        return header;
    }

    public void setHeader(Pane header) {
        this.header = header;
    }

    public BorderPane mainScene(){
        BorderPane border=new BorderPane();
        border.prefHeightProperty().bind(this.heightProperty());
        border.prefWidthProperty().bind(this.widthProperty());
        //TOP
        border.setTop(header);
        //CENTER
        border.setCenter(center);
        //BOTTOM
        border.setBottom(bottom());
        return border;
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
            parentStage.setScene(Navigation.ACTIVESCENE);

        });
        nextButton.setOnAction(e->{
            Navigation.forward();
            parentStage.setScene(Navigation.ACTIVESCENE);
        });
        homeButton.setOnAction(e->{
            parentStage.setScene( Navigation.HOME);

        });
        options.getChildren().addAll(backBtbn,homeButton);
        return options;
    }
}
