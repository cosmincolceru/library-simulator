package service;

import models.Item;
import models.Magazine;
import repository.ItemRepository;
import models.Book;
import models.DVD;

public class ItemService {
    private ItemRepository itemRepository;

    public ItemService() { this.itemRepository = new ItemRepository(); }

    public boolean addBook(String name, int numberOfCopies, String author, int publicationYear, String publisher) {
        Item.numberOfItems += 1;
        int itemId = Item.numberOfItems;
        Book book = new Book(itemId, name, numberOfCopies, author, publicationYear, publisher);
        return this.itemRepository.addItem(book);
    }

    public boolean addDVD(String name, int numberOfCopies, String director, int releaseYear, int duration, String genre) {
        Item.numberOfItems += 1;
        int itemId = Item.numberOfItems;
        DVD dvd = new DVD(itemId, name, numberOfCopies, director, releaseYear, duration, genre);
        return this.itemRepository.addItem(dvd);
    }

    public boolean addMagazine(String name, int numberOfCopies, String publicationDate) {
        Item.numberOfItems += 1;
        int itemId = Item.numberOfItems;
        Magazine magazine = new Magazine(itemId, name, numberOfCopies, publicationDate);
        return this.itemRepository.addItem(magazine);
    }

    public Item[] getAllItems() { return this.itemRepository.getAll(); }

    public boolean removeItem(int itemId) {
        Item[] items = itemRepository.getAll();
        for (Item item : items) {
            if (item.getItemId() == itemId) {
                return itemRepository.remove(item);
            }
        }
        return false;
    }

    public Item[] getAllAvailableItems() {
        return this.itemRepository.getAvailable();
    }

    public boolean updateNumberOfCopies(int itemId, int numberOfCopies) {
        return itemRepository.updateNumberOfCopies(itemId, numberOfCopies);
    }
}
