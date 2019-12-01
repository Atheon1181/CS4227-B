package Command.PlayerCommand;

public class MoveUP implements ICommand {
    IPlayerMovement iPlayerMovement;

    public MoveUP(IPlayerMovement iPlayerMovement) {
        this.iPlayerMovement = iPlayerMovement;
    }

    @Override
    public void execute() {
        iPlayerMovement.moveUp();
    }
}
