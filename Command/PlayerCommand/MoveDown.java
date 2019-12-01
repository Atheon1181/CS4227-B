package Command.PlayerCommand;

public class MoveDown implements ICommand {
    IPlayerMovement IPlayerMovement;

    public MoveDown(IPlayerMovement IPlayerMovement) {
        this.IPlayerMovement = IPlayerMovement;
    }

    @Override
    public void execute() {
        IPlayerMovement.moveDown();
    }
}
