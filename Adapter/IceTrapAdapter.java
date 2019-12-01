package Adapter;

import Maze.Player;
import javafx.scene.canvas.GraphicsContext;

public class IceTrapAdapter implements ITrap {
    IceTrap icetrap;
    public IceTrapAdapter(IceTrap newIceTrap){
        icetrap = newIceTrap;
    }

    @Override
    public void destroyTrap(GraphicsContext gc) {
        icetrap.defrost(gc);
    }

    @Override
    public void triggerTrap(Player player) {
        icetrap.freezePlayer(player);
        icetrap.defrost(player.getGc());
    }
}
