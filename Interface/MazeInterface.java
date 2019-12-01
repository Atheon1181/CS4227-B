package Interface;

import Maze.Player;
import inventory.Models.Inventory;
import inventory.Models.Item;
import inventory.UI.InventoryUI;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public interface MazeInterface {
    //void setupMenu();

    String getTitle();

    void setTitle(String title);

    BorderPane initMaze(Scene scene, InventoryUI inventoryMenu, Label invLabel, Item item, Player player, ArrayList<Item> items, Inventory inventory);

    void startMaze(Scene scene);

    void resetMaze();

    Item itemGenerator();

    boolean hasStarted();

    void setStop(boolean thread);


}
