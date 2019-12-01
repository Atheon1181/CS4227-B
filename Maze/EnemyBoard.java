package Maze;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Stack;

public class EnemyBoard extends Canvas implements Runnable{
    private Board b;
    public Cell currentPosition;
    //public Player player;
   public Enemy enemy;


    private GraphicsContext game;

    public EnemyBoard(Enemy enemy,Board board,Player player){
        this.enemy=enemy;
        this.b=board;
        this.setHeight(b.getBoardHeight());
        this.setWidth(b.getBoardWidth());

        game=this.getGraphicsContext2D();
        enemy.board=b;
        enemy.gc=game;
        drawEnemy(enemy);
        enemy.setRows(b.getRows());
        enemy.setColumns(b.getColumns());




    }
    public void drawEnemy(Enemy enemy){
        game.clearRect(0,0,getWidth(),getHeight());
        setTranslateX(10);
        setTranslateY(10);
        enemy.drawEnemy();



    }
    synchronized public void startAi(){
        System.out.println("Starting AI");
        enemy.path=enemy.trackPlayer();
        System.out.println("AI init");
        System.out.println(enemy.path);

    }
    @Override
    public void run(){
        int i=0;

        while(!enemy.caughtPlayer&& MazeGameSettings.ENEMYTHREADON){
            i+=1000;
            try {

                System.out.println("Player Path? + -- "+enemy.path.size());
                System.out.println("Caught ? "+enemy.caughtPlayer);
                if(enemy.path.size()>0){
                    System.out.println("Before: "+enemy);
                    Cell temp=enemy.path.pop();
                    enemy.clear();
                    enemy.setI(temp.getI());
                    enemy.setJ(temp.getJ());
                    System.out.println("After: "+enemy);
                    enemy.drawEnemy();
                    enemy.trackPlayer();
                    if(i%5000==0){
                        System.out.println("Reload");
                        enemy.resetCurrentPath();
                        enemy.trackPlayer();
                    }
                }


                Thread.sleep(enemy.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
