package inventory.Interface;

import inventory.Models.Item;

public interface Observer {
    public void update(Item item);
}