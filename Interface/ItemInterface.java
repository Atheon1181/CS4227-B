package Interface;

import inventory.Models.ItemStatus;
import inventory.UI.InfoDialog;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public interface ItemInterface {
    public String getID();

    public String getName();

    public String getDescription();

    public Duration getTimeElapsedSincePickedUp(Instant timeNow);

    public Instant getTimePickedUp();

    public ItemStatus getstatus();

    public void saveItem();

    public void showInfo();

    public Animation itemAnim(long ms);

    public Object use();

    public String getImage();


}
