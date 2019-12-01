package inventory.Models;

import inventory.controls.Effects;

import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class HealthPotion extends Item {
    private double width;
    private double height;
    private boolean playAnimation = false;
    private Animation animation;
    private int health = 50;

    public HealthPotion() {
        super("HP", "healthpotion", "+" + 50 + " HP", "Add's 50 more Health points", ItemStatus.NEW);
        this.width = 20;
        this.height = width + 30.0;
        this.health = 50;
    }

    public HealthPotion(double width, double height, boolean playAnimation) {
        super("HP", "healthpotion", "+" + 50 + " HP", "Add's 50 more Health points", ItemStatus.NEW);
        this.playAnimation = playAnimation;
        this.width = width;
        this.height = height;
        this.health = 50;
        initHP();
    }

    public HealthPotion(String id, String name, String description, boolean playAnimation) {
        super("HP", id, name, description, ItemStatus.NEW);
        this.playAnimation = playAnimation;
        this.width = 20;
        this.height = width + 30.0;
        this.health = 50;
        initHP();
    }

    public HealthPotion(String id, String name, String description, double width, double height, boolean playAnimation, int health) {
        super("HP", id, name, description, ItemStatus.NEW);
        this.playAnimation = playAnimation;
        this.width = width;
        this.height = height;
        this.health = health;
        initHP();
    }

    public void initHP() {
        System.out.println("Images/" + ID.toLowerCase() + ".png");
        Image image = new Image(getClass().getResourceAsStream("/Images/" + ID.toLowerCase() + ".png"));

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width / 2);
        imageView.setFitHeight(height / 2);
        setGraphic(imageView);
        setText(health + "");
        setTextFill(Color.WHITE);




        setStyle("-fx-background-color:transparent;");

        setPrefSize(width, height);
        setEffect(Effects.GLOW());
        animation = pulseAnim(1000);
        if (playAnimation)
            animation.play();
        else animation.stop();
    }


    public boolean isPlayAnimation() {
        return playAnimation;
    }

    public void setPlayAnimation(boolean playAnimation) {
        this.playAnimation = playAnimation;
        if (!playAnimation)
            animation.stop();

    }

    @Override
    public Object use() {
        int usage = health;
        return usage;
    }

    @Override
    public String getImage() {
        return "/Images/" + ID.toLowerCase() + ".png";

    }


}