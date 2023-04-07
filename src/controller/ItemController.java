package controller;

import models.Item;
import service.ItemService;

public class ItemController {
    private ItemService itemService;

    public ItemController() { this.itemService = new ItemService(); }

    public boolean addBook(String name, int numberOfCopies, String author, int publicationYear, String publisher) {
        return itemService.addBook(name, numberOfCopies, author, publicationYear, publisher);
    }

    public boolean addDVD(String name, int numberOfCopies, String director, int releaseYear, int duration, String genre) {
        return itemService.addDVD(name, numberOfCopies, director, releaseYear, duration, genre);
    }

    public boolean addMagazine(String name, int numberOfCopies, String publicationDate) {
        return itemService.addMagazine(name, numberOfCopies, publicationDate);
    }

    public int itemToRentIndex(int itemId) {
        Item[] items = getAllItems();
        for (int i = 0; i < items.length; i++) {
            if (items[i].getItemId() == itemId) {
                if (items[i].getNumberOfAvailableCopies() < 1) {
                    System.out.println("No copies of the item available.");
                    return -1;
                }
                return i;
            }
        }
        System.out.println("Item ID doesn't exist.");
        return -1;
    }

    public Item[] getAllItems() { return itemService.getAllItems(); }

    public boolean removeItem(int itemId) {
        return itemService.removeItem(itemId);
    }

    public Item[] getAllAvailableItems() {
        return itemService.getAllAvailableItems();
    }

    public boolean updateNumberOfCopies(int itemId, int numberOfCopies) {
        return itemService.updateNumberOfCopies(itemId, numberOfCopies);
    }
}
