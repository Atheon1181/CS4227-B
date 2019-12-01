package AbstractFactory.TimeChallenge;

import AbstractFactory.MazeMenuBar;
import Command.*;
import Maze.Navigation;
import Maze.Player;
import Maze.TestMaze;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TimeChallengeMenuBar extends MazeMenuBar {
    Button time;
    //Button solutionBtn;
    public TimeChallengeMenuBar(ArrayList<Player>players){
        super(players);
        VBox vbox=new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        Label lbl=new Label("Time Challenge Maze");
        lbl.setStyle("-fx-text-fill:white");
        vbox.getChildren().addAll(lbl,optionsBar());
        getChildren().addAll(vbox);
        setAlignment(Pos.CENTER);
    }
    public Pane optionsBar(){
        HBox bar=new HBox();
        bar.setAlignment(Pos.CENTER);

        bar.setSpacing(20);
        INavigationControl nControl= NavigationController.getNavigation();
        PreviousScene previousScene=new PreviousScene(nControl);
        previousScene.setText("Exit");
        ChangeScene changeScene=new ChangeScene(previousScene);
        previousScene.setOnAction(e->{changeScene.press();});
        previousScene.getStyleClass().add("nav-btn");

          this.time=new Button(new SimpleDateFormat("mm:ss").format(timer));
         time.setAlignment(Pos.CENTER);
         time.getStyleClass().add("timerButton");
         solutionBtn=new Button("Solution");
         solutionBtn.getStyleClass().add("trappedButtons");

        solutionBtn.setAlignment(Pos.CENTER_RIGHT);
        bar.getChildren().addAll(time,solutionBtn);






        return bar;

    }

    @Override
    public void updateUI() {
        Platform.runLater(()->{
            time.setText(new SimpleDateFormat("mm:ss").format(timer));
            if(timer<10000){
                time.setTextFill(Color.RED);
            }
        });

        super.updateUI();

    }
    @Override
    public void updateHealth(Player p){
        int pi=players.indexOf(p);
        if(pi>-1)
          //  heartBox.get(pi).updateHearts(p.getHealth());

        super.updateHealth(p);
    }
}
