package Leaderboard.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class LeaderBoardOptions extends Stage {
    public LeaderBoardOptions() {
        setScene(leaderboardScene());
        initModality(Modality.APPLICATION_MODAL);
        setTitle("LeaderBoards");


    }

    public Scene leaderboardScene() {

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color:#111");
        VBox box = new VBox();
        box.setPadding(new Insets(20));
        box.setSpacing(15);
        Label title = new Label("TOP PLAYERS");
        Button ihl = new Button("Item Hunters (LB)");
        Button clb = new Button("\"Classic\" Leaderboard");
        Button tclb = new Button("Time Challengers (LB)");
        box.getChildren().addAll(title, ihl, clb, tclb);
        box.setAlignment(Pos.CENTER);
        borderPane.setCenter(box);

        ihl.setOnAction(e -> {
            LeaderboardGUI gui = new LeaderboardGUI("ITEM HUNTERS LEADERBOARD", "treasureHunt.txt", 0);

            gui.showAndWait();
        });
        clb.setOnAction(e -> {
            LeaderboardGUI gui = new LeaderboardGUI("SURVIVE LEADERBOARD", "survival.txt");

            gui.showAndWait();
        });
        tclb.setOnAction(e -> {
            LeaderboardGUI gui = new LeaderboardGUI("TIME CHALLENGE LEADERBOARD", "timeChallenge.txt");

            gui.showAndWait();
        });

        Scene sc = new Scene(borderPane, 800, 800);
        sc.getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());
        ihl.getStyleClass().add("timerButton");
        clb.getStyleClass().add("timerButton");
        tclb.getStyleClass().add("timerButton");
        title.getStyleClass().add("timerButton");
        box.getStyleClass().add("background");
        return sc;

    }


}
