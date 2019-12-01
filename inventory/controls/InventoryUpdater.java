package inventory.controls;

import inventory.Interface.InventoryInterface;
import inventory.Interface.Observer;
import inventory.Models.Item;

import java.util.ArrayList;

public class InventoryUpdater implements InventoryInterface {
    private ArrayList<Observer> observers;
    private Item item;


    public InventoryUpdater() {
        observers = new ArrayList<Observer>();
    }

    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);

    }

    @Override
    public void unregister(Observer deleteObserver) {
        observers.remove(observers.indexOf(deleteObserver));

    }

    @Override
    public void notifyObserver() {
        for (Observer o : observers) {
            o.update(item);
        }

    }

    public void setItem(Item item) {
        this.item = item;
        notifyObserver();
    }
}