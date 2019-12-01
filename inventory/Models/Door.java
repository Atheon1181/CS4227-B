package inventory.Models;

import inventory.controls.Effects;


import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Door extends Obstacle {
    private double width;
    private double height;

    public Door() {
        super();
        this.width = 20;
        this.height = width + 30.0;
    }

    public Door(double width, double height) {
        super();
        this.width = width;
        this.height = height;
        initDoor();
    }

    public Door(String ID, String name, String description) {
        super(ID, name, description);
        this.width = 20;
        this.height = width + 30.0;
        initDoor();
    }

    public Door(String ID, String name, String description, String url) {
        super(ID, name, description, url);
    }

    public Door(String ID, String name, String description, String url, double width, double height) {
        super(ID, name, description, url);
        this.width = width;
        this.height = height;
        initDoor();
    }

    public Door(String ID, String name, String description, double width, double height) {
        super(ID, name, description);
        this.width = width;
        this.height = height;
        initDoor();
    }

    public boolean openDoor(final KeyItem key) {
        System.out.println(key);
        return (key.getID().equalsIgnoreCase(ID));
    }

    public void initDoor() {
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
