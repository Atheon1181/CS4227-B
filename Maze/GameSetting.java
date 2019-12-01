package Maze;

import Command.*;
import inventory.Models.Inventory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameSetting{
    private Scene scene;
    private Stage parent;
    private GameModes mode;


    public GameSetting(Stage parent, GameModes mode){

        this.parent=parent;
        this.mode=mode;






    }

    public HBox navigation(){
        INavigationControl nControl= NavigationController.getNavigation();
        PreviousScene previousScene=new PreviousScene(nControl);
        previousScene.setText("◀");
        NextScene nextScene=new NextScene(nControl);
        nextScene.setText("▶");
        ChangeScene changeScene=new ChangeScene(previousScene);
        previousScene.setOnAction(e->{changeScene.press();parent.setScene(Navigation.ACTIVESCENE);});
        nextScene.setOnAction(e->{changeScene.press();parent.setScene(Navigation.ACTIVESCENE);});

        previousScene.getStyleClass().add("nav-btn2");
        nextScene.getStyleClass().add("nav-btn2");

        HBox vbox=new HBox();
        Button backBtbn=new Button("◀");
        Button nextButton=new Button("▶");
        backBtbn.getStyleClass().add("nav-btn2");
        nextButton.getStyleClass().add("nav-btn2");
        vbox.setSpacing(10);
        vbox.getChildren().addAll(previousScene,nextScene);
        backBtbn.setOnAction(e->{
            Navigation.previous();
            parent.setScene(Navigation.ACTIVESCENE);

        });
        nextButton.setOnAction(e->{
            Navigation.forward();
            parent.setScene(Navigation.ACTIVESCENE);
        });
        return vbox;

    }
    private Rectangle selectedCharacter,selectedCharacter2;
    private Color c1=Color.WHITE,c2=Color.PURPLE;
    private int turn=1;
    MazePreference preference=new MazePreference();
    public VBox settingsDialog(){

        VBox box=new VBox();
        final Label heading=new Label();
        box.setAlignment(Pos.CENTER);
        heading.setStyle("-fx-font-size:32px");
        box.getChildren().add(heading);
        heading.setText("Player 1: Select a Color");

        System.out.println("settings");
        HBox characterType=new HBox();
        final VBox player2=new VBox();
        final VBox player1=new VBox();

        characterType.setAlignment(Pos.CENTER);
        Rectangle rec=new Rectangle(100,100,100,100);
        rec.setFill(Color.WHITE);
        Rectangle _rec=new Rectangle(100,100,100,100);
        _rec.setFill(Color.WHITE);
        selectedCharacter=_rec;
        player1.getChildren().add(selectedCharacter);

        rec.setOnMouseClicked((e)->{
            if(turn==1){
                c1=Color.WHITE;

                player1.getChildren().remove(selectedCharacter);
                selectedCharacter=_rec;
                player1.getChildren().add(0,selectedCharacter);
                heading.setText(mode==GameModes.MULTIPLAYER?"Player 2: Select a Color":"WHITE");

            }

            else if(turn==2)
            {
                c2=Color.WHITE;
                player2.getChildren().remove(selectedCharacter2);
                selectedCharacter2=_rec;
                player2.getChildren().add(0,selectedCharacter2);
                heading.setText("Player 1: Select a Color");
            }
            turn=mode==GameModes.MULTIPLAYER && turn!=2?(turn+1):1;
          //  rec.setDisable(true);

        });
        Rectangle rec1=new Rectangle(100,100,100,100);
        rec1.setFill(Color.PURPLE);
        Rectangle _rec1=new Rectangle(100,100,100,100);
        _rec1.setFill(Color.PURPLE);
        selectedCharacter2=_rec1;
        player2.getChildren().add(selectedCharacter2);
        rec1.setOnMouseClicked((e)->{
            System.out.println("PURP");
            if(turn==1){
                c1=Color.PURPLE;
                player1.getChildren().remove(selectedCharacter);
                selectedCharacter=_rec1;
                player1.getChildren().add(0,selectedCharacter);
                heading.setText(mode==GameModes.MULTIPLAYER?"Player 2: Select a Color":"ROYAL");

            }

            else if(turn==2)
            {
                c2=Color.PURPLE;
                player2.getChildren().remove(selectedCharacter2);
                selectedCharacter2=_rec1;
                player2.getChildren().add(0,selectedCharacter2);
                heading.setText("Player 1: Select a Color");

            }
            turn=mode==GameModes.MULTIPLAYER && turn!=2?(turn+1):1;
           // rec1.setDisable(true);
        });
        Rectangle rec2=new Rectangle(100,100,100,100);
        rec2.setFill(Color.LIGHTBLUE);
        Rectangle _rec2=new Rectangle(100,100,100,100);
        _rec2.setFill(Color.LIGHTBLUE);
        rec2.setOnMouseClicked((e)->{

            if(turn==1){
                c1=Color.LIGHTBLUE;
                player1.getChildren().remove(selectedCharacter);
                selectedCharacter=_rec2;
                player1.getChildren().add(0,selectedCharacter);
                heading.setText(mode==GameModes.MULTIPLAYER?"Player 2: Select a Color":"SKY");

            }

            else if(turn==2)
            {
                c2=Color.LIGHTBLUE;
                player2.getChildren().remove(selectedCharacter2);
                selectedCharacter2=_rec2;
                player2.getChildren().add(0,selectedCharacter2);
                heading.setText("Player 1: Select a Color");
            }
            turn=mode==GameModes.MULTIPLAYER && turn!=2?(turn+1):1;
           // rec2.setDisable(true);
        });
        Rectangle rec3=new Rectangle(100,100,100,100);
        rec3.setFill(Color.BEIGE);
        Rectangle _rec3=new Rectangle(100,100,100,100);
        _rec3.setFill(Color.BEIGE);
        rec3.setOnMouseClicked((e)->{
            if(turn==1){
                c1=Color.BEIGE;
                player1.getChildren().remove(selectedCharacter);
                selectedCharacter=_rec3;
                player1.getChildren().add(0,selectedCharacter);
                heading.setText(mode==GameModes.MULTIPLAYER?"Player 2: Select a Color":"BEIGE");

            }

            else if(turn==2)
            {
                c2=Color.BEIGE;
                player2.getChildren().remove(selectedCharacter2);
                selectedCharacter2=_rec3;
                player2.getChildren().add(0,selectedCharacter2);
                heading.setText("Player 1: Select a Color");
            }
            turn=mode==GameModes.MULTIPLAYER && turn!=2?(turn+1):1;
           // rec3.setDisable(true);
        });

        characterType.getChildren().addAll(rec,rec1,rec2,rec3);
        HBox slectedCharacter=new HBox();

        slectedCharacter.setAlignment(Pos.CENTER);
        TextField textField=new TextField();
        textField.setPromptText("Player 1 Enter a name");
        Label lbl=new Label("Player 1 controls: W = Up, S = Down, A = Right and D = Left");
        TextField textField2=new TextField();
        textField2.setPromptText("Player 2 Enter a name");
        Label lbl2=new Label("Player 2 controls: I = Up, K = Down, J = Right and L = Left");


        player1.setAlignment(Pos.CENTER);
        player1.getChildren().addAll(textField,lbl);

        player2.setAlignment(Pos.CENTER);
        player2.getChildren().addAll(textField2,lbl2);
        slectedCharacter.setSpacing(30);
        if(mode==GameModes.MULTIPLAYER){
            slectedCharacter.getChildren().addAll(player1,player2);
        }
        else{
            slectedCharacter.getChildren().add(player1);
        }

        HBox mazeDificulty = new HBox();
        mazeDificulty.setAlignment(Pos.CENTER);

        ToggleGroup group=new ToggleGroup();

        RadioButton eazyMaze=new RadioButton("Easy");
        eazyMaze.setToggleGroup(group);
        eazyMaze.setSelected(true);
        RadioButton challenging=new RadioButton("Challenging");
        challenging.setToggleGroup(group);
        RadioButton impossible=new RadioButton("Impossible");
        impossible.setToggleGroup(group);
        eazyMaze.setUserData(60);
        challenging.setUserData(40);
        impossible.setUserData(25);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                        preference.setSizeFactor((int)group.getSelectedToggle().getUserData());

                }
            }
        });

        VBox d1 =new VBox();
        d1.setSpacing(10);
        d1.getChildren().addAll(new Rectangle(40.0,40.0,40.0,40.0),eazyMaze);
        VBox d2 =new VBox();
        d2.setSpacing(10);
        d2.getChildren().addAll(new Rectangle(80.0,80.0,80.0,80.0),challenging);
        VBox d3 =new VBox();
        d3.setSpacing(10);
        d3.getChildren().addAll(new Rectangle(120.0,120.0,120.0,120.0),impossible);
        mazeDificulty.getChildren().addAll(d1,d2,d3);
        mazeDificulty.setSpacing(12);
        CheckBox doors=new CheckBox("Portals? ");
        CheckBox TrapWalls=new CheckBox("Trap Walls? ");
        CheckBox traps=new CheckBox("Traps? ");
        CheckBox enemies=new CheckBox("Enemies? ");
        CheckBox shields=new CheckBox("Shields? ");
        CheckBox mazeGeneration=new CheckBox("Real Time Maze Generation? ");
        doors.setSelected(true);
        TrapWalls.setSelected(true);
        traps.setSelected(preference.isTraps());
        enemies.setSelected(preference.isEnemy());
        shields.setSelected(preference.isShield());
        mazeGeneration.setSelected(true);
        doors.selectedProperty().addListener((ov,ot,nt)->{
            preference.setDoors(doors.isSelected());
        });
        TrapWalls.selectedProperty().addListener((ov,ot,nt)->{
            preference.setDoors(TrapWalls.isSelected());
        });

        HBox timeBox=new HBox();
        timeBox.setAlignment(Pos.CENTER);
        TextField tf=new TextField("00");
        Label l=new Label(":");
        TextField tf2=new TextField("30");
        timeBox.getChildren().addAll(tf,l,tf2);

        VBox p=preferenceVBox(doors,"Doors");
        VBox p1=preferenceVBox(TrapWalls,"TrapWalls");
        p1.getChildren().remove(1);
        Slider slider=new Slider();
        slider.setValue(preference.getWallDifficulty());
        p1.getChildren().add(new Label("Trap Wall Safety"));
        p1.getChildren().add(slider);

        VBox p2=preferenceVBox(traps,"Traps");
        TextField _p2=new TextField();
        _p2.setPromptText("Trap Damage");
        p2.getChildren().add(_p2);

        VBox p3=preferenceVBox(enemies,"Enemies");
        TextField _p3=new TextField();
        _p3.setPromptText("Enemy Damage");
        TextField __p3=new TextField();
        __p3.setPromptText("Enemy Speed ( > 1000)");
        p3.getChildren().add(_p3);
        p3.getChildren().add(__p3);
        VBox p4=preferenceVBox(shields,"Shield");




        HBox preferenceBox=new HBox();
        preferenceBox.setSpacing(12);
        preferenceBox.setAlignment(Pos.CENTER);
        preferenceBox.getChildren().addAll(p,p1,p2,p3,p4);

        TextField pointText=new TextField();
        pointText.setText(3000+"");
        pointText.setPromptText("Enter Target points 50+");

        CheckBox Rmg=new CheckBox("Show Maze Generation");
        Rmg.setSelected(preference.isRMG());
        switch(this.mode){

            case SURVIVAL:
                box.getChildren().addAll(characterType,slectedCharacter,mazeDificulty,preferenceBox,Rmg);break;
            case MULTIPLAYER:{
                preferenceBox.getChildren().remove(2);
                preferenceBox.getChildren().remove(3);
                preferenceBox.getChildren().remove(p3);

                box.getChildren().addAll(characterType,slectedCharacter,mazeDificulty,TrapWalls,preferenceBox,Rmg);
            }break;
            case TREASUREHUNT:{

                    preferenceBox.getChildren().remove(2);
                    preferenceBox.getChildren().remove(3);
                    p4.getChildren().clear();
                    p4.getChildren().addAll(new Label("Treasure points"),pointText);
                    preferenceBox.getChildren().add(p4);



                    box.getChildren().addAll(characterType,slectedCharacter,mazeDificulty,TrapWalls,preferenceBox,Rmg);

            }break;

            case TIMECHALLENGE:{
                preferenceBox.getChildren().remove(2);
                preferenceBox.getChildren().remove(3);
                preferenceBox.getChildren().remove(p3);

                box.getChildren().addAll(characterType,slectedCharacter,mazeDificulty,TrapWalls,preferenceBox,new Label("Duration"),timeBox,Rmg);
            }break;
        }

        box.setAlignment(Pos.CENTER);
        box.setSpacing(30);






        Button save=new Button("Let's Play");
        save.setOnAction(e->{
            Player play1=new Player(!textField.getText().equals("") ?textField.getText():"Player 1",3,new Inventory(),0,0,preference.getSizeFactor(),preference.getSizeFactor(),new Cell(0,0,preference.getSizeFactor(),preference.getSizeFactor()));
            play1.setColor(c1);
            Player play2=new Player(!textField2.getText().equals("") ?textField2.getText():"Player 2",3,new Inventory(),0,0,preference.getSizeFactor(),preference.getSizeFactor(),new Cell(0,0,preference.getSizeFactor(),preference.getSizeFactor()));
            play2.setColor(c2);
            preference.getPlayerInGame().clear();
            preference.getPlayerInGame().add(play1);
            if(mode==GameModes.MULTIPLAYER)
                preference.getPlayerInGame().add(play2);
            TextField doorText=(TextField) p.getChildren().get(1);
            TextField trapsText=(TextField) p2.getChildren().get(1);
            TextField enemiesText=(TextField) p3.getChildren().get(1);
            TextField shieldText=(TextField) p4.getChildren().get(1);


            preference.setDoorAmnt(doorText.getText().matches("[0-9]+")?Integer.parseInt(doorText.getText()):1);
            preference.setTrapAmnt(trapsText.getText().matches("[0-9]+")?Integer.parseInt(trapsText.getText()):1);
            preference.setEnemyAmnt(enemiesText.getText().matches("[0-9]+")?Integer.parseInt(enemiesText.getText()):1);
            preference.setShieldAmnt(shieldText.getText().matches("[0-9]+")?Integer.parseInt(shieldText.getText()):1);

            preference.setRMG(Rmg.isSelected());
            preference.setWallDifficulty(slider.getValue());
            preference.setTrapDamage(_p2.getText().matches("[0-9]+")?Integer.parseInt(_p2.getText()):1);
            preference.setEnemyDamage(_p3.getText().matches("[0-9]+")?Integer.parseInt(_p3.getText()):1);
            preference.setEnemySpeed(__p3.getText().matches("[0-9]+")?Integer.parseInt(__p3.getText()):1);
            preference.setTreasureGoal(pointText.getText().matches("[0-9]+")? Math.max(Integer.parseInt(pointText.getText()), 50) :3000);

            long totaltime=30000;
            if(tf.getText().matches("[0-9]+")&&tf2.getText().matches("[0-9]+")){
                String ss=tf2.getText().trim();
                String ms=tf.getText().trim();
                long s=Long.parseLong(ss)*1000;
                long m=Long.parseLong(ms)*1000*60;
                totaltime=s+m;
            }
            preference.setLength(totaltime);



            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            MazeGameSettings.preference=preference;

            GameScene game=new GameScene(parent,mode,preference);
            Scene gameScene=game.getScene(bounds.getHeight(),bounds.getWidth());
            Navigation.update(gameScene);
            parent.setScene(gameScene);
        });
       box.getChildren().add(save);




        return box;
    }
    public VBox preferenceVBox(CheckBox cb, String attribute){

        VBox p1=new VBox();
        p1.setSpacing(10);
        p1.setAlignment(Pos.CENTER);
        TextField tf=new TextField();
        tf.setPromptText("How many "+attribute);
        p1.getChildren().addAll(cb,tf);
        setPreferenceAntributes(cb,tf,attribute);
        cb.selectedProperty().addListener((a,b,c)->{
            setPreferenceAntributes(cb,tf,attribute);
        });


        return p1;

    }
    public void setPreferenceAntributes(CheckBox cb,TextField tf,String attribute){
        String regex="[0-9]+";
        switch(attribute){
            case "Doors":{
                preference.setDoorAmnt(cb.isSelected()&&tf.getText().matches(regex)?Integer.parseInt(tf.getText()):0);
                preference.setDoors(cb.isSelected());
            }break;
            case "Traps":{
                preference.setTrapAmnt(cb.isSelected()&&tf.getText().matches(regex)?Integer.parseInt(tf.getText()):0);
                preference.setTraps(cb.isSelected());
            }break;
            case "Enemies":{
                preference.setEnemyAmnt(cb.isSelected()&&tf.getText().matches(regex)?Integer.parseInt(tf.getText()):0);
                preference.setEnemy(cb.isSelected());
            }break;
            case "Shield":{
                preference.setShieldAmnt(cb.isSelected()&&tf.getText().matches(regex)?Integer.parseInt(tf.getText()):0);
                preference.setShield(cb.isSelected());
            }break;


            default:break;
        }
    }
    public Scene getScene(double h, double w){
        GridPane gridPane = new GridPane();


        VBox vbox =new VBox();
        vbox.setAlignment(Pos.CENTER);
        Button timeChallenge=new Button("Time Challenge");
        timeChallenge.setOnAction(e->{
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            GameScene game=new GameScene(parent,GameModes.TIMECHALLENGE);
            Scene gameScene=game.getScene(bounds.getHeight(),bounds.getWidth());
            Navigation.update(gameScene);
            parent.setScene(gameScene);
        });
        Button treasureHunt=new Button("Treasure Hunt");
        Button multiplayer=new Button("Multiplayer");
        multiplayer.setOnAction(e->{
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            GameScene game=new GameScene(parent,GameModes.MULTIPLAYER);
            Scene gameScene=game.getScene(bounds.getHeight(),bounds.getWidth());
            Navigation.update(gameScene);
            parent.setScene(gameScene);
        });
        Button survival=new Button("Survival");
        survival.setOnAction(e->{
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            GameScene game=new GameScene(parent,GameModes.SURVIVAL);
            Scene gameScene=game.getScene(bounds.getHeight(),bounds.getWidth());
            Navigation.update(gameScene);
            parent.setScene(gameScene);
        });
        vbox.setSpacing(10.0);
        vbox.setAlignment(Pos.CENTER);

        BorderPane borderPane=new BorderPane();


        gridPane.add(timeChallenge, 0, 0, 1, 1);
        gridPane.add(treasureHunt, 1, 0, 1, 1);
        gridPane.add(multiplayer,0,1,1,1);
        gridPane.add(survival,1,1,1,1);
        vbox.getChildren().addAll(gridPane);
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setTop(navigation());
        borderPane.setCenter(vbox);

         BorderPane root=new BorderPane();
            root.setTop(navigation());

        ((BorderPane) root).setCenter(settingsDialog());
        scene=new Scene(root,h,w, Color.WHITE); root.prefHeightProperty().bind(scene.heightProperty());
        root.prefWidthProperty().bind(scene.widthProperty());
        scene.getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());

        return scene;
    }
}
