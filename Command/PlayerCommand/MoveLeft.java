package Command.PlayerCommand;

public class MoveLeft implements ICommand {
    IPlayerMovement IPlayerMovement;

    public MoveLeft(IPlayerMovement IPlayerMovement) {
        this.IPlayerMovement = IPlayerMovement;
    }

    @Override
    public void execute() {
        IPlayerMovement.moveLeft();
    }
}
