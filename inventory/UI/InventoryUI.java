package inventory.UI;

import inventory.controls.Effects;

import inventory.Models.*;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.image.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.time.Instant;

public class InventoryUI extends Stage {
    private final String name = "InventoryInterface";
    private Map<String, Item> stuff;
    private Inventory inventory = new Inventory();
    private ArrayList<Item> items = new ArrayList<>();
    private int invRow = 9;
    private boolean full = false;
    private Item useItem = null;
    private int doubleTap = 0;
    private Button useBtn, dropBtn;
    private VBox panels[];
    private Scene scene;

    public InventoryUI() {
        if (invRow > 6) invRow = 6;
        panels = new VBox[invRow * invRow];
    }

    public InventoryUI(Scene scene, int invRow) {
        this.scene = scene;
        this.invRow = invRow;
        useItem = null;
        if (invRow > 6) invRow = 6;
        panels = new VBox[invRow * invRow];
        if (inventory.getItems().size() >= (invRow * invRow)) full = true;


        load();
        initModality(Modality.APPLICATION_MODAL);
        setTitle(name.toUpperCase());

    }

    public void load() {

        loadItems();

        Button closeInventory = new Button("CLOSE");
        closeInventory.cancelButtonProperty();
        closeInventory.setStyle("{-fx-background-color:red;-fx-text-fill:black}");
        closeInventory.setEffect(Effects.GLOW());
        closeInventory.setOnAction(e -> close());
        closeInventory.setTranslateX(+200);
        closeInventory.setTranslateY(-230);
        StackPane stackPane = new StackPane();

        BorderPane bp = new BorderPane();
        stackPane.getChildren().add(bp);
        stackPane.getStyleClass().add("page");
        HBox head = new HBox();
        head.getChildren().addAll(title(), closeInventory);
        head.setAlignment(Pos.CENTER);
        bp.setTop(head);
        bp.setCenter(launch());
        bp.setBottom(actionButtons());

        Scene s = new Scene(stackPane, 500, 500);
        s.getStylesheets().add(getClass().getResource("/css/inventory.css").toExternalForm());

        bp.getStyleClass().add("Page");
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                if (useItem == null) {
                    useBtn.setDisable(true);
                    dropBtn.setDisable(true);
                } else {
                    useBtn.setDisable(false);
                    dropBtn.setDisable(false);

                }
            }
        });
        t.setDaemon(true);
        t.start();
        setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                useItem = null;
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        setScene(s);
    }

    public Item getItem() {
        return useItem;
    }


    public final VBox launch() {
        VBox mainView = new VBox();
        mainView.getChildren().add(body());
        mainView.setAlignment(Pos.CENTER);

        return mainView;
    }

    public int getItemCount() {
        return inventory.getSize();
    }

    public GridPane body() {
        //Create row x row Grid
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setGridLinesVisible(true);

        int r = 0, c = 0;
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new VBox();
            panels[i].setMaxSize(100.0, 100.0);
            panels[i].setMinSize(80.0, 80.0);
            panels[i].setAlignment(Pos.CENTER);
            panels[i].setPadding(new Insets(12.0f));
            panels[i].getStyleClass().add("unselectedGrid");
            panels[i].setEffect(Effects.Inner_Shadow());
            if (i % invRow == 0) {
                r = 0;
                c++;

            } else r++;

            gridpane.add(panels[i], r, c, 1, 1);
        }
        for (int i = 0; i < inventory.getItems().size(); i++) {

            addPanel(i);
        }


        gridpane.getStyleClass().add("grid");


        return gridpane;

    }

    public void addPanelOperation(ActionEvent e) {


        if (useItem == null) {
            useBtn.setDisable(true);
            dropBtn.setDisable(true);
        } else {
            useBtn.setDisable(false);
            dropBtn.setDisable(false);
            useBtn.setOnAction(ev -> {

                scene.setCursor(new ImageCursor(new Image(getClass().getResourceAsStream(useItem.getImage()))));
                close();
            });
        }

        Button b = (Button) e.getSource();

        for (int j = 0; j < panels.length; j++) {
            if (j == Integer.parseInt(b.getId())) {

                panels[j].getStyleClass().clear();
                panels[j].getStyleClass().add("selectedGrid");
            } else {
                panels[j].getStyleClass().clear();
                panels[j].getStyleClass().add("unselectedGrid");

            }
        }

    }

    public void addPanel(int i) {
        useItem = null;
        Label label = new Label();
        label.getStyleClass().add("itemName");
        if (i < panels.length)
            switch (inventory.getItems().get(i).getType().toUpperCase()) {
                case "KEY": {
                    KeyItem key = new KeyItem(inventory.getItems().get(i).getID(), inventory.getItems().get(i).getName(), inventory.getItems().get(i).getDescription(), 50, 60, false);
                    key.setTimePickedUp(inventory.getItems().get(i).getTimePickedUp());
                    label.setText(key.getID());
                    panels[i].getChildren().addAll(key, label);
                    key.setId(i + "");
                    key.setOnAction(e -> {
                        useItem = key;
                        addPanelOperation(e);
                    });
                    key.setOnMouseClicked(m -> {
                        if (m.getButton() == MouseButton.SECONDARY)
                            key.showInfo();
                    });
                }
                break;
                case "HP": {
                    HealthPotion hp = new HealthPotion(inventory.getItems().get(i).getID(), inventory.getItems().get(i).getName(), inventory.getItems().get(i).getDescription(), 50, 60, false, 1);
                    hp.setTimePickedUp(inventory.getItems().get(i).getTimePickedUp());
                    label.setText(hp.getID());
                    panels[i].getChildren().addAll(hp, label);
                    hp.setId(i + "");
                    hp.setOnAction(e -> {
                        useItem = hp;

                        addPanelOperation(e);
                    });
                    hp.setOnMouseClicked(m -> {
                        if (m.getButton() == MouseButton.SECONDARY)
                            hp.showInfo();
                    });

                }
                break;
                case "SP": {
                    SpeedPotion sp = new SpeedPotion(inventory.getItems().get(i).getID(), inventory.getItems().get(i).getName(), inventory.getItems().get(i).getDescription(), 50, 60, false, 1);
                    sp.setTimePickedUp(inventory.getItems().get(i).getTimePickedUp());
                    label.setText(sp.getID());
                    panels[i].getChildren().addAll(sp, label);
                    sp.setId(i + "");
                    sp.setOnAction(e -> {
                        useItem = sp;

                        addPanelOperation(e);
                    });
                    sp.setOnMouseClicked(m -> {
                        if (m.getButton() == MouseButton.SECONDARY)
                            sp.showInfo();
                    });

                }
                break;
                case "TIME": {
                    TimeFreeze time = new TimeFreeze(inventory.getItems().get(i).getID(), inventory.getItems().get(i).getName(), inventory.getItems().get(i).getDescription(), 50, 60, false, 20000);
                    time.setTimePickedUp(inventory.getItems().get(i).getTimePickedUp());
                    label.setText(time.getID());
                    panels[i].getChildren().addAll(time, label);
                    time.setId(i + "");
                    time.setOnAction(e -> {
                        useItem = time;

                        addPanelOperation(e);
                    });
                    time.setOnMouseClicked(m -> {
                        if (m.getButton() == MouseButton.SECONDARY)
                            time.showInfo();
                    });

                }
                break;
                case "TB": {
                    TrapBreaker tb = new TrapBreaker(inventory.getItems().get(i).getID(), inventory.getItems().get(i).getName(), inventory.getItems().get(i).getDescription(), 50, 60, false, 0);
                    tb.setTimePickedUp(inventory.getItems().get(i).getTimePickedUp());
                    label.setText(tb.getID());
                    panels[i].getChildren().addAll(tb, label);
                    tb.setId(i + "");
                    tb.setOnAction(e -> {
                        useItem = tb;

                        addPanelOperation(e);
                    });
                    tb.setOnMouseClicked(m -> {
                        if (m.getButton() == MouseButton.SECONDARY)
                            tb.showInfo();
                    });

                }
                break;
                default:
                    break;

            }
    }

    public void loadItems() {
        items.clear();

        try {
            File file = new File(getClass().getResource("/text/Inventory.txt").getFile());
            if (file.exists()) {
                BufferedReader fileReader = new BufferedReader(new FileReader(file));

                String st;
                while ((st = fileReader.readLine()) != null) {
                    String temp[];
                    temp = st.split(",");
                    for (String s : temp)

                    if (temp.length > 0) {
                        Item item = (new Item(temp[0], temp[1], temp[2], temp[3], ItemStatus.valueOf(temp[temp.length - 1])));

                        item.setTimePickedUp(Instant.parse(temp[4]));

                        items.add(item);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        inventory.setItems(items);
    }

    public void dropItem(Item item) {
        if (item.toString().equalsIgnoreCase(useItem.toString()))
            useItem = null;
        Platform.runLater(
                new Runnable() {

                    @Override
                    public void run() {
                        int count = -1;
                        for (Item it : inventory.getItems()) {

                            if (it.toString().equals(item.toString())) {

                                break;
                            } else count++;
                        }
                        if (inventory.getSize() >= 1) count++;

                        try {
                            if (count > -1)
                                inventory.getItems().remove(count);
                        } catch (ArrayIndexOutOfBoundsException arrE) {
                        }
                        updateInventoryFile();
                        resetPanels();
                        for (int i = 0; i < inventory.getItems().size(); i++) {
                            addPanel(i);
                        }
                        invCapacity.setText("Used Slots: " + items.size() + "  Max: " + (invRow * invRow));


                    }
                }
        );

    }

    public void updateInventoryFile() {
        try {
            FileWriter writer = new FileWriter(new File(getClass().getResource("/text/Inventory.txt").getFile()));
            for (Item item : inventory.getItems()) {
                writer.write(item.toString());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetPanels() {
        for (int i = 0; i < panels.length; i++) {
            panels[i].getChildren().clear();
        }
    }

    Label invCapacity;

    public HBox title() {
        HBox title = new HBox();
        Label heading = new Label(this.name.toUpperCase());
        heading.getStyleClass().add("header");
        invCapacity = new Label("Used Slots: " + inventory.getSize() + "  Max: " + (invRow * invRow));
        invCapacity.getStyleClass().add("slotsLabel");
         //set Properties

        //add children and set Properties
        title.getChildren().addAll(heading, invCapacity);
        title.setPadding(new Insets(20, 20, 20, 20));
        title.setAlignment(Pos.CENTER);
        title.setSpacing(10.0);
        title.setEffect(Effects.DROP_SHADOW());
        return title;
    }

    public HBox actionButtons() {
        HBox buttonsHolder = new HBox();
        useBtn = new Button("USE");
        useBtn.getStyleClass().add("use");
        useBtn.setEffect(Effects.GLOW());
        dropBtn = new Button("DROP");
        dropBtn.getStyleClass().add("drop");
        dropBtn.setOnAction(e -> {
            dropItem(useItem);
        });
        dropBtn.setEffect(Effects.GLOW());
        buttonsHolder.getChildren().addAll(useBtn, dropBtn);
        buttonsHolder.setAlignment(Pos.CENTER);
        buttonsHolder.setSpacing(10.0);
        buttonsHolder.setPadding(new Insets(20.0));

        return buttonsHolder;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }


    public void reload() {
        load();
    }
}