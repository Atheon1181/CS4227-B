package inventory.Models;

import Maze.Player;
import inventory.controls.Effects;


import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Trap extends Obstacle {
    private double width;
    private double height;

    public Trap() {
        super();
        this.width = 20;
        this.height = width + 30.0;
    }

    public Trap(double width, double height) {
        super();
        this.width = width;
        this.height = height;
        initTrap();
    }

    public Trap(String ID, String name, String description) {
        super(ID, name, description);
        this.width = 20;
        this.height = width + 30.0;
        initTrap();
    }

    public Trap(String ID, String name, String description, String url) {
        super(ID, name, description, url);
    }

    public Trap(String ID, String name, String description, String url, double width, double height) {
        super(ID, name, description, url);
        this.width = width;
        this.height = height;
        initTrap();
    }

    public Trap(String ID, String name, String description, double width, double height) {
        super(ID, name, description);
        this.width = width;
        this.height = height;
        initTrap();
    }

    public Player setOfTrap(Player player) {
        player.setHealth(player.getHealth() - 1);
        return player;
    }

    public boolean destroyTrap(final Item tb) {

        return (tb.getID().equalsIgnoreCase("tbr"));
    }

    public void initTrap() {
        Image image = new Image(getClass().getResourceAsStream("/Images/" + url));

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        setGraphic(imageView);




        setStyle("-fx-background-color:transparent;");

        setPrefSize(width, height);
        setEffect(Effects.LIGHTING());
    }

    public Animation open(long ms) {
        FadeTransition ft = new FadeTransition(Duration.millis(ms), this);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setAutoReverse(false);
        return ft;

    }

}
