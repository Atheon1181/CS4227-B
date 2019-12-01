package Leaderboard.UI;

//import Leaderboard.Models.Leaderboard;

import Leaderboard.Models.Leaderboard;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LeaderboardGUI extends Stage {

    private String filename = "leaderboard.txt";
    private String title = "High Scores";
    private TableView<Leaders> table = new TableView<Leaders>();
    private int order = -1;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    private final ObservableList<Leaders> data =
            FXCollections.observableArrayList();

    public LeaderboardGUI() {
        setScene(leaderboardScene());
        initModality(Modality.APPLICATION_MODAL);
        setTitle(title);


    }

    public LeaderboardGUI(String title, String filename) {
        this.filename = filename;
        this.title = title;
        setScene(leaderboardScene());
        initModality(Modality.APPLICATION_MODAL);
        setTitle(title);


    }

    public LeaderboardGUI(String title, String filename, int order) {
        this.filename = filename;
        this.title = title;
        this.order = order;
        setScene(leaderboardScene());
        initModality(Modality.APPLICATION_MODAL);
        setTitle(title);


    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    public String gettitle() {
        return title;
    }


    public void settitle(String title) {
        this.title = title;
    }

    public Scene leaderboardScene() {
        Group root = new Group(getLeaderboard());
        Scene scene = new Scene(root, 700, 600, Color.DARKGRAY);
        Leaderboard a = new Leaderboard(filename);
        a.setOrder(order);


        ArrayList<ArrayList<String>> list = a.loadLeaderboard(filename);
        for (int i = 0; i < list.get(0).size(); i++) {
            data.add(new Leaders(list.get(0).get(i), filename.contains("time")? new SimpleDateFormat("mm:ss").format(Long.parseLong(list.get(1).get(i))):list.get(1).get(i), list.get(2).get(i), list.get(3).get(i)));

        }

        final Label label = new Label(title);
        label.setFont(new Font("Arial", 20));

        table.setEditable(false);


        TableColumn firstNameCol = new TableColumn("Username");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Leaders, String>("name"));

        TableColumn valCol = new TableColumn("Time/points");
        valCol.setMinWidth(100);
        valCol.setCellValueFactory(
                new PropertyValueFactory<Leaders, String>("val"));

        TableColumn daeCol = new TableColumn("Date");
        daeCol.setMinWidth(200);
        daeCol.setCellValueFactory(
                new PropertyValueFactory<Leaders, String>("date"));


        TableColumn lvl = new TableColumn("Maze Level");
        lvl.setMinWidth(200);
        lvl.setCellValueFactory(
                new PropertyValueFactory<Leaders, String>("mazeLevel"));

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, valCol, daeCol, lvl);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(table);
        vbox.getChildren().addAll(scrollPane);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);


        return scene;
    }

    public Text getLeaderboard() {


        Text text = new Text();

        text.setFont(Font.font("comic sans", FontWeight.BOLD, FontPosture.REGULAR, 35));

        text.setX(50);
        text.setY(50);

        text.setFill(Color.BLUE);

        text.setStrokeWidth(2);


        text.setStroke(Color.GREEN);




        return text;
    }

    public static class Leaders {
        private final SimpleStringProperty name;
        private final SimpleStringProperty val;
        private final SimpleStringProperty date;
        private final SimpleStringProperty mazeLevel;

        public Leaders(String fname, String value, String adate, String mazeLevel) {
            this.name = new SimpleStringProperty(fname);
            this.val = new SimpleStringProperty(value);
            this.date = new SimpleStringProperty(adate);
            this.mazeLevel = new SimpleStringProperty(mazeLevel);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String fName) {
            name.set(fName);
        }

        public String getVal() {
            return val.get();
        }

        public void setValue(String fName) {
            val.set(fName);
        }

        public String getDate() {
            return date.get();
        }

        public void setDate(String fName) {
            date.set(fName);
        }

        public String getMazeLevel() {
            return mazeLevel.get();
        }

        public void setMazeLevel(String lvl) {
            mazeLevel.set(lvl);
        }
    }

}
