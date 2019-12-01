package inventory.controls;

import inventory.Interface.InventoryInterface;
import inventory.Interface.Observer;
import inventory.Models.Item;

public class InventoryObserver implements Observer {
    private Item item;
    private static int ObserverIDTracker = 0;
    private int ObserverID;
    private InventoryInterface inventory;

    public InventoryObserver(InventoryInterface inventory) {
        this.inventory = inventory;
        this.ObserverID = ++ObserverIDTracker;
        System.out.println("New Observer" + ObserverID);
        inventory.register(this);
    }

    @Override
    public void update(Item item) {
        this.item = item;
        printItem("Item: " + item.getName());

    }

    public void printItem(Object msg) {
        System.out.println(msg);
    }

}
