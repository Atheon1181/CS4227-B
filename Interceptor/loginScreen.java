package Interceptor;

import Maze.TrappedPlus;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class loginScreen {
    private Scene scene;
    private Stage parent;
    private final static String sceneID="LoginScreen";
    public loginScreen(Stage parent){
        this.parent=parent;
    }

    public static Stage login(){
        Stage stage=new Stage();
        Group root=new Group();
        Button loginButton=new Button("Login");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");
        loginButton.setOnAction(e->{
            boolean allowLogin = false;
            String username = usernameField.getText();
            String password = passwordField.getText();
            usernameFilter filter = new usernameFilter();
            try {
                allowLogin = filter.checkInput(username, password);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
                if (allowLogin) {
                    File file = new File("Resources/currentuser.txt");
                    FileWriter fr = null;
                    try {
                        fr = new FileWriter(file);
                        fr.write(username);
                    } catch (IOException f) {
                        f.printStackTrace();
                    }finally{
                        try {
                            fr.close();
                        } catch (IOException f) {
                            f.printStackTrace();
                        }
                        stage.close();
                        new TrappedPlus().show();
                    }
                }else{
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Cannot Log In");
                    errorAlert.setContentText("Username or Password is Incorrect");
                    errorAlert.showAndWait();
               // }
            }
        });
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.add(usernameField, 0, 0);
        grid.add(passwordField, 0, 1);
        grid.add(loginButton, 0, 2);
        Scene scene = new Scene(grid, 300, 275);
        stage.setScene(scene);
        return stage;
    }
}

