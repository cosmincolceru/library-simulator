package repository;

import models.Item;
import models.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemRepository {
    private Item[] storedItems = new Item[0];

    public boolean addItem(Item item) {
        try {
            List<Item> arrList = new ArrayList<>(Arrays.asList(storedItems));
            arrList.add(item);
            storedItems = arrList.toArray(storedItems);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Item[] getAll() { return this.storedItems; }

    public boolean remove(Item item) {
        try {
            List<Item> arrList = new ArrayList<>(Arrays.asList(storedItems));
            arrList.remove(item);
            storedItems = arrList.toArray(new Item[arrList.size()]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Item[] getAvailable() {
        List<Item> availableItems = new ArrayList<>();
        for (Item item : storedItems) {
            if (item.getNumberOfAvailableCopies() > 0) {
                availableItems.add(item);
            }
        }
        Item[] availableItemsArray = availableItems.toArray(new Item[availableItems.size()]);
        return availableItemsArray;
    }

    public boolean updateNumberOfCopies(int itemId, int numberOfCopies) {
        for (Item item : storedItems) {
            if (item.getItemId() == itemId) {
                int nr = item.getNumberOfCopies();
                int diff = numberOfCopies - nr;

                // update the number of copies
                item.setNumberOfCopies(numberOfCopies);

                // update the number of available copies
                item.setNumberOfAvailableCopies(item.getNumberOfAvailableCopies() + diff);
                return true;
            }
        }
        return false;
    }
}
