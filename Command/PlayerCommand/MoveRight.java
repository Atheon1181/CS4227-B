package Command.PlayerCommand;

public class MoveRight implements ICommand {
    IPlayerMovement IPlayerMovement;

    public MoveRight(IPlayerMovement IPlayerMovement) {
        this.IPlayerMovement = IPlayerMovement;
    }

    @Override
    public void execute() {
        IPlayerMovement.moveRight();
    }
}
