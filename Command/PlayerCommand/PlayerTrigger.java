package Command.PlayerCommand;

public class PlayerTrigger {
    ICommand ICommand;

    public PlayerTrigger(ICommand ICommand) {
        this.ICommand = ICommand;
    }
    public void move(){
        this.ICommand.execute();
    }
}
