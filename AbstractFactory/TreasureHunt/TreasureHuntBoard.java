package AbstractFactory.TreasureHunt;

import Adapter.Trap;
import Maze.*;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TreasureHuntBoard extends Board {
Player player;
    public TreasureHuntBoard(double height, double width, int controllers, MazePreference preference) {
        super(height, width,preference);


        this.player=preference.getPlayerInGame().get(0);
        super.players=preference.getPlayerInGame();
        super.setPlayer(preference.getPlayerInGame());
       // setTraps(preference.getTrapAmnt());
        //setHPs(preference.getExtraLivesAmnt());
        setItems(preference.getTreasureGoal());
        //setShields(preference.getShieldAmnt());

        setEnemy();

    }
    public void setItems(int items){
        int maxItems=0;
        while (maxItems<=items){
            maxItems+=replaceForItems();

        }

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
        eb.startAi();
        et.start();
    }
    public void replaceForTrap(){
        int entry=(int)(Math.random()*BoardCells.cells.size());
        System.out.println(entry+"--"+BoardCells.cells.size());
        Cell c= BoardCells.cells.get(entry);
        if(!c.isDoor()){
            c.setTrap(true);
            BoardCells.cells.get(entry).changeColor(Color.RED);
            Trap trap=new Trap(c.getI(),c.getJ(),c.getWidth(),c.getHeight());
            trap.setGc(c.getGc());
            System.out.println(">>>>>"+c);
            trap.setSpecialWalls(c.getSpecialWalls());
            BoardCells.cells.set(entry,trap);
        }
        //else replaceForTrap();


    }
    public int replaceForItems(){

        int entry=(int)(Math.random()*BoardCells.cells.size());
        System.out.println(entry+"--"+BoardCells.cells.size());
        Cell c= BoardCells.cells.get(entry);
        if(!c.isDoor()){
            c.setItem(true);
            //BoardCells.cells.get(entry).changeColor(Color.RED);
            Item coin=new GoldCoin(c.getI(),c.getJ(),c.getWidth(),c.getHeight());
            Item ct=new CoinPile(c.getI(),c.getJ(),c.getWidth(),c.getHeight());
            Item crown=new Crown(c.getI(),c.getJ(),c.getWidth(),c.getHeight());
            Item dime=new Diamond(c.getI(),c.getJ(),c.getWidth(),c.getHeight());
            Item iphone=new IPhone(c.getI(),c.getJ(),c.getWidth(),c.getHeight());

            ArrayList<Item>items=new ArrayList<>();
            items.add(coin);
            double rand=Math.random()*(1.0);
            if(rand<=.15&&rand>=0){
                items.add(crown);
            }
             if(rand<=.35&&rand>=.15){
                items.add(dime);
            }
             if(rand<=.55&&rand>=.35){
                items.add(iphone);
            }
             if(rand<=.75&&rand>=.55){
                items.add(ct);
            }
            Collections.shuffle(items);
            items.get(0).setGc(c.getGc());
            System.out.println(">>>>>"+c);
            items.get(0).setSpecialWalls(c.getSpecialWalls());
            items.get(0).drawItem(gc,items.get(0));
            BoardCells.cells.set(entry,items.get(0));
            return items.get(0).getValue();
        }
        //else replaceForTrap();
return 0;

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
                }else if(cellStack.get(i).isItem()){
                    cellStack.get(i).drawItem(gc,(Item)cellStack.get(i));
                }
                else
                    cellStack.get(i).drawRectangle(gc);
               // System.out.println(cellStack);
            }
        });
    }





}
