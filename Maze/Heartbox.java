package Maze;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Heartbox extends HBox {
    private int hearts;
    private String img="/Images/heart.png";
    public Heartbox(int hearts){
        this.hearts=hearts;
        setAlignment(Pos.CENTER);
        setupHeartbox();
    }
    public Heartbox(int hearts,String img){
        this.hearts=hearts;
        this.img=img;

        setupHeartbox();
    }
    public void setupHeartbox()
    {
        for(int i=0;i<hearts;i++) {
            ImageView imgv = new ImageView(new Image(getClass().getResourceAsStream(img)));
            imgv.setFitHeight(30);
            imgv.setFitWidth(30);

            getChildren().add(imgv);
        }
    }
    public void updateHearts(int hearts){
        setHearts(hearts);
        Platform.runLater(()->{
            getChildren().clear();
            setupHeartbox();
        });
    }

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
