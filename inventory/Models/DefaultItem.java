package inventory.Models;

import Interface.ItemInterface;
import inventory.UI.InfoDialog;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;



public class DefaultItem extends Button implements ItemInterface {

    @Override
    public String getID() {
        return "item1";
    }

    @Override
    public String getName() {
        return "Item";
    }

    @Override
    public String getDescription() {
        return "unfunctional item";
    }

    @Override
    public Duration getTimeElapsedSincePickedUp(Instant timeNow) {
        return Duration.between(getTimePickedUp(), timeNow);
    }

    @Override
    public Instant getTimePickedUp() {
        return Instant.now();
    }

    @Override
    public ItemStatus getstatus() {
        return ItemStatus.NEW;
    }

    @Override
    public void saveItem() {

        try {

            FileWriter writer = new FileWriter(new File(getClass().getClassLoader().getResource("text/Inventory.txt").getFile()), true);
            writer.write(toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showInfo() {


    }

    @Override
    public Animation itemAnim(long ms) {
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(javafx.util.Duration.millis(ms));
        scaleTransition.setNode(this);
        scaleTransition.setByY(1.2);
        scaleTransition.setByX(1.2);
        scaleTransition.setCycleCount(Timeline.INDEFINITE);
        scaleTransition.setAutoReverse(true);
        return scaleTransition;
    }

    @Override
    public Object use() {
        return null;
    }

    @Override
    public String getImage() {
        return "";
    }
}
