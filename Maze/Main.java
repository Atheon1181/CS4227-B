package Maze;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Interceptor.loginScreen;



public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage= loginScreen.login();
        stage.setMaximized(true);
        stage.show();




    }
    /*public Stage login(){
        Stage stage=new Stage();
        Group root=new Group();
        Scene scene= new Scene(root,500,500);

        HBox box=new HBox();
        TextField tf=new TextField();
        tf.setPromptText("Name");
        Button button=new Button("Login");
        button.setOnAction(e->{
            stage.close();
            new TrappedPlus().show();
        });
        box.getChildren().addAll(tf,button);
        BorderPane bp=new BorderPane();
        bp.setCenter(box);
        root.getChildren().addAll(bp);
        stage.setScene(scene);
        return stage;
    }*/
}
