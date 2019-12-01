package inventory.Models;

import javafx.scene.control.*;

public class Obstacle extends Button {
    protected String ID = "";
    protected String name = "gold_door";
    protected String description = "";
    protected String url = "";

    public Obstacle(String ID, String name, String description, String url) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Obstacle() {
    }


    public Obstacle(String ID, String name, String description) {
        this.ID = ID;
        this.name = name;
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ID + "," + name + "," + description;

    }
}