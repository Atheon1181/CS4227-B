package AbstractFactory.Survival;

import Adapter.IceTrap;
import Adapter.IceTrapAdapter;
import Adapter.Trap;
import Maze.*;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.Random;

public class SurvivalBoard extends Board {
Player player;
    public SurvivalBoard(double height, double width, int controllers, MazePreference preference) {
        super(height, width,preference);


        this.player=preference.getPlayerInGame().get(0);
        super.players=preference.getPlayerInGame();
        super.setPlayer(preference.getPlayerInGame());
        setTraps(preference.getTrapAmnt());
        setHPs(preference.getExtraLivesAmnt());

        setShields(preference.getShieldAmnt());

        setEnemy();

    }
    public void setTraps(int traps){
        traps=Math.min(traps,BoardCells.cells.size());
        while (traps>0){
            replaceForTrap();
            traps--;
        }
    }
    public void setShields(int shield){
        shield=Math.min(shield,BoardCells.cells.size());
        while (shield>0){
            replaceForShield();
            shield--;
        }
    }
    public void setHPs(int hp){
        while (hp>0){
            replaceForHealth();
            hp--;
        }
    }
    public void setEnemy(){
        System.out.println("Setting enemy");
      // Enemy enemy=new Enemy((int)(Math.random()*12),(int)(Math.random()*12),getSizeFactor(),getSizeFactor());
        Enemy enemy=new Enemy(3,3,getSizeFactor(),getSizeFactor());



        enemy.player=player;
        EnemyBoard eb=new EnemyBoard(enemy,this,player);
        enemy.board=this;
        enemy.setHeuristics();
        System.out.println("Setting enemy: "+enemy);
        getChildren().add(eb);
        Thread et=new Thread(eb);
        MazeGameSettings.ENEMYTHREADON=true;
        eb.startAi();
        et.start();
    }
    public void replaceForTrap(){
        Random r = new Random();
        boolean isIceTrap = r.nextBoolean();
        int entry=(int)(Math.random()*BoardCells.cells.size());
        System.out.println(entry+"--"+BoardCells.cells.size());
        Cell c= BoardCells.cells.get(entry);
        if(!c.isDoor()){
            c.setTrap(true);
            if (isIceTrap){
                BoardCells.cells.get(entry).changeColor(Color.LIGHTBLUE);
                IceTrap itrap = new IceTrap(c.getI(),c.getJ(),c.getWidth(),c.getHeight());
                itrap.setGc(c.getGc());
                System.out.println(">>>>>"+c);
                itrap.setSpecialWalls(c.getSpecialWalls());
                BoardCells.cells.set(entry,itrap);
                IceTrapAdapter trap = new IceTrapAdapter(itrap);
            }else {
                BoardCells.cells.get(entry).changeColor(Color.RED);
                Trap trap = new Trap(c.getI(), c.getJ(), c.getWidth(), c.getHeight());
                trap.setGc(c.getGc());
                System.out.println(">>>>>"+c);
                trap.setSpecialWalls(c.getSpecialWalls());
                BoardCells.cells.set(entry,trap);
            }
        }
        //else replaceForTrap();


    }
    public void replaceForShield(){
        int entry=(int)(Math.random()*BoardCells.cells.size());
        System.out.println(entry+"--"+BoardCells.cells.size());
        Cell c= BoardCells.cells.get(entry);
        if(!c.isShield()){
            c.setShield(true);
            //BoardCells.cells.get(entry).changeColor(Color.RED);
            Shield shield=new Shield(c.getI(),c.getJ(),c.getWidth(),c.getHeight());
            shield.setGc(c.getGc());
            System.out.println(">>>>>"+c);
            shield.setSpecialWalls(c.getSpecialWalls());
            BoardCells.cells.set(entry,shield);
        }
        //else replaceForTrap();


    }
    public void replaceForHealth(){
        int entry=(int)(Math.random()*BoardCells.cells.size());
        System.out.println(entry+"--"+BoardCells.cells.size());
        Cell c= BoardCells.cells.get(entry);
        if(!c.isDoor()){
            c.setLife(true);
            BoardCells.cells.get(entry).changeColor(Color.LIGHTGREEN);
            Health hp=new Health(c.getI(),c.getJ(),c.getWidth(),c.getHeight());
            hp.setGc(c.getGc());
            hp.setSpecialWalls(c.getSpecialWalls());
            BoardCells.cells.set(entry,hp);
        }
        else replaceForTrap();


    }

    @Override
    public void drawPath(int i){
        System.out.println(i+") Drawing "+cellStack.size()+" Cells: CellId - "+cellStack.get(i).id);
        cellStack.lastElement().hightlight(gc);
        cellStack.firstElement().isFirst=true;
        cellStack.lastElement().isEnd=true;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(cellStack.get(i).isDoor()){
                    cellStack.get(i).drawDoor(gc);
                }else if(cellStack.get(i).isTrap()){
                    cellStack.get(i).drawTrap(gc);
                }
                else if(cellStack.get(i).isShield()){
                    cellStack.get(i).drawShield(gc);
                }
                else if(cellStack.get(i).isLife()){
                    cellStack.get(i).drawHp(gc);
                }else
                    cellStack.get(i).drawRectangle(gc);
               // System.out.println(cellStack);
            }
        });
    }





}
