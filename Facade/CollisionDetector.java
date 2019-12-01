package Facade;

import Adapter.Trap;
import Maze.*;

public class CollisionDetector {
    public Player player;
    public Cell trapCell;

    public CollisionDetector(Player player) {
        this.player = player;
    }

    public void detectTrapCollision( Trap cell){
        if(cell.isTrap()){
            cell.triggerTrap(player);
            MazeParts.currentMenubar.updateHealth(player);

        }

    }

    public void detectHPCollision(Health hp) {
        if(hp.isLife()){
            hp.triggerHealth(player);
            MazeParts.currentMenubar.updateHealth(player);

        }

    }
    public void detectShieldCollision(Shield s) {
        if(s.isShield()){
            s.triggerShield(player);
           // MazeParts.currentMenubar.updateHealth(player);

        }

    }
    public void detectItemCollision(Item item) {
        if(item.isItem()){
            item.triggerItem(player);
            // MazeParts.currentMenubar.updateHealth(player);

        }

    }
    public void detectWallHit(){
        System.out.println(player);
        MazeParts.currentMenubar.updateHealth(player);
    }
}
