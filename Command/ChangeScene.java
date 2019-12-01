package Command;

public class ChangeScene {
    ICommand ICommand;
    public  ChangeScene(ICommand newICommand){
        ICommand = newICommand;
    }
    public void press(){
        ICommand.execute();
    }
}
