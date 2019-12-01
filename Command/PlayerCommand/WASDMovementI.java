package Command.PlayerCommand;

import Maze.Actor;


public class WASDMovementI implements IPlayerMovement {
    Actor actor;

    public WASDMovementI(Actor actor) {
        this.actor = actor;
    }

    @Override
    public void moveUp() {
        System.out.println("W = Move UP");
        actor.moveUp();
    }

    @Override
    public void moveDown() {
        System.out.println("S = Move DOWN");
        actor.moveDown();
    }

    @Override
    public void moveRight() {
        System.out.println("D = Move RIGHT");
        actor.moveRight();
    }

    @Override
    public void moveLeft() {
        System.out.println("A = Move LEFT");
        actor.moveLeft();
    }
}
