package inventory.Models;

import inventory.controls.Effects;

import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


public class SpeedPotion extends Item {


    private double width;
    private double height;
    private boolean playAnimation = false;
    private Animation animation;
    private int boost = 1;

    public SpeedPotion() {
        super("SP", "speedpotion", "", "Adds more boost", ItemStatus.NEW);
        this.width = 20;
        this.height = width + 30.0;
        this.boost = 1;
    }

    public SpeedPotion(double width, double height, boolean playAnimation) {
        super("SP", "speedpotion", "x2 Speed", "Adds more boost", ItemStatus.NEW);
        this.playAnimation = playAnimation;
        this.width = width;
        this.height = height;
        this.boost = 1;
        initSP();
    }

    public SpeedPotion(String id, String name, String description, boolean playAnimation) {
        super("SP", id, name, description, ItemStatus.NEW);
        this.playAnimation = playAnimation;
        this.width = 20;
        this.height = width + 30.0;
        this.boost = 1;
        initSP();
    }

    public SpeedPotion(String id, String name, String description, double width, double height, boolean playAnimation, int boost) {
        super("SP", id, name, description, ItemStatus.NEW);
        this.playAnimation = playAnimation;
        this.width = width;
        this.height = height;
        this.boost = boost;
        initSP();
    }

    public void initSP() {
        Image image = new Image(getClass().getResourceAsStream("/Images/" + ID.toLowerCase() + ".png"));

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width / 2);
        imageView.setFitHeight(height / 2);
        setGraphic(imageView);
        setText(boost + "");
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
        int usage = boost;
        return usage;
    }

    @Override
    public String getImage() {
        return "/Images/" + ID.toLowerCase() + ".png";

    }


    public void setBoost(int boost) {
        this.boost = boost;
    }
}
