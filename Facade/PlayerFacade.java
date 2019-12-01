package Facade;

import Maze.Player;

public class PlayerFacade {
    private Player player;

    public PlayerFacade(Player player) {
        this.player = player;
    }

    public Player getPlayer(){return  this.player;}
    public Player addHealth(int h){this.player.addHealth(h);return getPlayer();}
    public Player takeDamage(int d){this.player.takeDamage(d);return  getPlayer();}
    public void updatePlayer(Player newPlayer){
        this.player=newPlayer;
    }


}
