package Maze;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TrappedPlus extends Stage {
    public TrappedPlus(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        setX(bounds.getMinX());
        setY(bounds.getMinY());
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
        setTitle("Trapped"); // Set the stage title
        Scene mainScene=new MainMenu(this).getScene(500.0,500.0);
        Navigation.initiate(mainScene);
        setScene(mainScene); // Place the scene in the stage
        setMaximized(true);
        show();
    }

}
