package Maze;

import javafx.scene.Scene;

import java.util.Stack;

public class Navigation {
    public static Stack<Scene> CURRENTSCENE=new Stack();
    public static Stack<Scene>  PREVIOUSSCENE=new Stack();
    public static Scene ACTIVESCENE=null;
    public static Scene RECENTSCENE=null;
    public static  Scene HOME=null;

    public static void initiate(Scene initScene){
        CURRENTSCENE.push(initScene);
        PREVIOUSSCENE=new Stack();
        ACTIVESCENE=initScene;
    }
    public static void update(Scene newScene){

        CURRENTSCENE.push(newScene);
        PREVIOUSSCENE.push(ACTIVESCENE);
        ACTIVESCENE=newScene;

    }
    public static void previous(){
        MazeGameSettings.MAINTHREADON=false;
        MazeGameSettings.ENEMYTHREADON=false;
        ACTIVESCENE=PREVIOUSSCENE.lastElement();
        RECENTSCENE=CURRENTSCENE.lastElement();
        CURRENTSCENE.push(ACTIVESCENE);
        PREVIOUSSCENE.pop();

    }
    public static void forward(){
        MazeGameSettings.MAINTHREADON=false;
        MazeGameSettings.ENEMYTHREADON=false;
       update(RECENTSCENE);
       RECENTSCENE=null;

    }
}
