package Command;

public class NavigationController {
    public static INavigationControl getNavigation(){
        return  new SceneINavigation();
    }
}
