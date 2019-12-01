package Command.PlayerCommand;

import Maze.Actor;

public class ControllerCommand
{
    private IPlayerMovement IPlayerMovement;
    private ICommand movements[];

    public ControllerCommand(Actor target) {
        IPlayerMovement = MovementType.getWASD(target);
        movements=new ICommand[]{
                new MoveUP(IPlayerMovement),
                new MoveLeft(IPlayerMovement),
                new MoveDown(IPlayerMovement),
                new MoveRight(IPlayerMovement)};
        this.target = target;
    }

    private Actor target;

    public IPlayerMovement getIPlayerMovement() {
        return IPlayerMovement;
    }

    public void setIPlayerMovement(IPlayerMovement IPlayerMovement) {
        this.IPlayerMovement = IPlayerMovement;
    }

    public ICommand[] getMovements() {
        return movements;
    }

    public void setMovements(ICommand[] movements) {
        this.movements = movements;
    }

    public Actor getTarget() {
        return target;
    }

    public void setTarget(Actor target) {
        this.target = target;
    }
}
