package controller;

import models.Book;
import models.DVD;
import models.Item;
import models.Magazine;
import service.ItemService;

import java.util.ArrayList;

public class ItemController {
    private ItemService itemService;

    public ItemController() { this.itemService = ItemService.getInstance(); }

    public boolean addBook(String name, int numberOfCopies, String author, int publicationYear, String publisher) {
        return itemService.addBook(name, numberOfCopies, author, publicationYear, publisher);
    }

    public boolean addDVD(String name, int numberOfCopies, String director, int releaseYear, int duration, String genre) {
        return itemService.addDVD(name, numberOfCopies, director, releaseYear, duration, genre);
    }

    public boolean addMagazine(String name, int numberOfCopies, String publicationDate) {
        return itemService.addMagazine(name, numberOfCopies, publicationDate);
    }

    public <T> int itemToRentIndex(int itemId, Class<T> c) {
//        Item[] items = getAllItems();
        ArrayList<? extends Item> items;

        if (c.equals(Book.class)) {
            items = this.getAllBooks();
        } else if (c.equals(DVD.class)) {
            items = this.getAllDVDs();
        } else {
            items = this.getAllMagazines();
        }

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemId() == itemId) {
                if (items.get(i).getNumberOfAvailableCopies() < 1) {
                    System.out.println("No copies of the item available.");
                    return -1;
                }
                return i;
            }
        }

        System.out.println("Item ID doesn't exist.");
        return -1;
    }

//    public Item[] getAllItems() { return itemService.getAllItems(); }

    public ArrayList<Book> getAllBooks() { return this.itemService.getAllBooks(); }
    public ArrayList<DVD> getAllDVDs() { return this.itemService.getAllDVDs(); }
    public ArrayList<Magazine> getAllMagazines() { return this.itemService.getAllMagazines(); }

    public boolean removeItem(int itemId) {
        return itemService.removeItem(itemId);
    }

    public ArrayList<Book> getAvailableBooks() {
        return this.itemService.getAvailableBooks();
    }
    public ArrayList<DVD> getAvailableDVDs() {
        return  this.itemService.getAvailableDVDs();
    }
    public ArrayList<Magazine> getAvailableMagazines() {
        return this.itemService.getAvailableMagazines();
    }

    public boolean updateNumberOfCopies(int itemId, int numberOfCopies) {
        return itemService.updateNumberOfCopies(itemId, numberOfCopies);
    }

    public <T extends Item> boolean decrementAvailableCopies(T item) {
        return itemService.decrementAvailableCopies(item);
    }

    public boolean rentItem(int memberId, int itemId) {
        return itemService.rentItem(memberId, itemId);
    }

    public <T extends Item>boolean incrementAvailableCopies(T item) {
        return itemService.incrementAvailableCopies(item);
    }

    public boolean returnItem(int itemId, int memberId) {
        return itemService.returnItem(itemId, memberId);
    }
}
