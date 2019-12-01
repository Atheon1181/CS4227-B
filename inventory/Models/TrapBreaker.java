package inventory.Models;

import inventory.controls.Effects;

import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class TrapBreaker extends Item {

    private double width;
    private double height;
    private boolean playAnimation = false;
    private Animation animation;


    public TrapBreaker() {
        super("TB", "TrapBreaker", "Time Freeze 20 sec", "Freezes Time", ItemStatus.NEW);
        this.width = 20;
        this.height = width + 30.0;

    }

    public TrapBreaker(double width, double height, boolean playAnimation) {
        super("TB", "TrapBreaker", "Time Freeze 20 sec", "Freezes Time", ItemStatus.NEW);
        this.playAnimation = playAnimation;
        this.width = width;
        this.height = height;

        initSP();
    }

    public TrapBreaker(String id, String name, String description, boolean playAnimation) {
        super("TB", id, name, description, ItemStatus.NEW);
        this.playAnimation = playAnimation;
        this.width = 20;
        this.height = width + 30.0;

        initSP();
    }

    public TrapBreaker(String id, String name, String description, double width, double height, boolean playAnimation, int freezeLength) {
        super("TB", id, name, description, ItemStatus.NEW);
        this.playAnimation = playAnimation;
        this.width = width;
        this.height = height;

        initSP();
    }

    public void initSP() {
        Image image = new Image(getClass().getResourceAsStream("/Images/" + ID.toLowerCase() + ".png"));

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width / 2);
        imageView.setFitHeight(height / 2);
        setGraphic(imageView);

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
        long usage = 0;
        return usage;
    }

    @Override
    public String getImage() {
        return "/Images/" + ID.toLowerCase() + ".png";

    }


}
