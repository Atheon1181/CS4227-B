package inventory.UI;

import java.time.Instant;

import inventory.Models.Item;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.stage.*;

public class InfoDialog extends Stage {
    private Item item;

    public InfoDialog(Item item) {
        this.item = item;

        initModality(Modality.APPLICATION_MODAL);
        setTitle("About " + item.getName().toUpperCase());

        BorderPane bp = new BorderPane();

        bp.setTop(header());
        bp.setCenter(descriptionField());

        Scene s = new Scene(bp, 250, 300);
        s.getStylesheets().add("file:c:/Users/Ali/Documents/InventoryInterface/src/inventory/UI/css/inventory.css");
        setScene(s);
    }

    public Label header() {
        Label text = new Label(item.getName().toUpperCase());
        return text;
    }

    public VBox descriptionField() {
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        Label id = new Label("ID: " + item.getID());
        Label type = new Label("TYPE: " + item.getType());
        Label collectedOn = new Label("Collected " + parseDuration(item.getTimeElapsedSincePickedUp(Instant.now()).getSeconds()) + " Ago");
        Label description = new Label("Description: \n" + item.getDescription());
        hbox.getChildren().addAll(id, type, collectedOn);
        hbox.setSpacing(15.0);
        hbox.setPadding(new Insets(15.0f));
        hbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(hbox, description);
        vbox.setPadding(new Insets(20.0f));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(12.0);
        return vbox;
    }

    public String parseDuration(long millis) {
        int seconds = (int) millis % 60;
        int minutes = (int) (millis % 3600) / 60;
        int hours = (int) millis / 3600;
        if (millis >= 60) {
            if (millis >= 3600) {
                return hours + ":" + minutes + ":" + seconds + " Hours";
            } else return minutes + ":" + seconds + " Minutes";
        } else return seconds + " Seconds";

    }
}