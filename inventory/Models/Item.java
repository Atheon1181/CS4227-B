package inventory.Models;


import inventory.Interface.ItemInterface;
import inventory.UI.InfoDialog;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;

import java.io.*;

import java.time.Duration;
import java.time.Instant;

/**
 * This Class Has The Definition of An @InventoryInterface Item,
 * Thus, it contains the item's
 * id: The unique indentifier of the item,
 * name: The name or title of the Item,
 * description: Descibes the object's function
 * timePickedUP: The Time the Item is Picked Up
 * timePassedSincePickedUP: Gets the time Elapsed since the object has been picked up
 * status: Default item is new, once item is used status sets to used
 */
public class Item extends Button implements ItemInterface {

    protected String type;
    protected String name;//eg Gold Key
    protected String ID = "gold_key";
    protected String description;//eg key for opening gold door01
    protected Instant timePickedUp = Instant.now();//e.g this - current time gives the duration
    protected Duration timeElapsedSincePickedUp;
    protected ItemStatus status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.toUpperCase();
    }

    public String getID() {
        return ID;
    }

    public void setTimePickedUp(Instant timePickedUp) {
        this.timePickedUp = timePickedUp;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Item() {

        this.type = "KEY";
        this.name = "";
        this.ID = name + Math.random() * 1000;
        this.description = "";
        this.timeElapsedSincePickedUp = Duration.between(timePickedUp, Instant.now());
        this.status = ItemStatus.NEW;

    }

    public Item(String type, String ID, String name, String description, ItemStatus status) {
        this.type = type.toUpperCase();

        this.name = name;
        this.ID = ID;
        this.description = description;
        this.timeElapsedSincePickedUp = Duration.between(timePickedUp, Instant.now());
        this.status = status;

    }

    public Instant getTimePickedUp() {
        return timePickedUp;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Duration getTimeElapsedSincePickedUp(Instant timeNow) {
        return timeElapsedSincePickedUp = Duration.between(timePickedUp, timeNow);
    }

    public ItemStatus getstatus() {
        return status;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public String toString() {
        return type + "," + ID + "," + name + "," + description + "," + timePickedUp + "," + timeElapsedSincePickedUp + "," + status + "\n";
    }

    public void saveItem() {
        try {

            FileWriter writer = new FileWriter(new File(getClass().getClassLoader().getResource("text/Inventory.txt").getFile()), true);
            writer.write(toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInfo() {
        new InfoDialog(this).showAndWait();

    }

    protected Animation pulseAnim(long ms) {
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