package service;

import models.*;
import repository.ItemRepository;
import util.ID;

import java.util.ArrayList;


public class ItemService {
    private ItemRepository itemRepository;

    private static ItemService instance;

    public static ItemService getInstance() {
        if (instance == null) {
            instance = new ItemService();
        }
        return instance;
    }

    ID<Item> id = ID.getInstance();
    private ItemService() { this.itemRepository = new ItemRepository(); }

    public boolean addBook(String name, int numberOfCopies, String author, int publicationYear, String publisher) {

        int itemId = id.getNextId(new Item());
        Book book = new Book(itemId, name, numberOfCopies, author, publicationYear, publisher);
        return this.itemRepository.addBook(book);
    }

    public boolean addDVD(String name, int numberOfCopies, String director, int releaseYear, int duration, String genre) {
        int itemId = id.getNextId(new Item());
        DVD dvd = new DVD(itemId, name, numberOfCopies, director, releaseYear, duration, genre);
        return this.itemRepository.addDVD(dvd);
    }

    public boolean addMagazine(String name, int numberOfCopies, String publicationDate) {
        int itemId = id.getNextId(new Item());
        Magazine magazine = new Magazine(itemId, name, numberOfCopies, publicationDate);
        return this.itemRepository.addMagazine(magazine);
    }

    public ArrayList<Book> getAllBooks() { return this.itemRepository.getAllBooks(); }
    public ArrayList<DVD> getAllDVDs() { return this.itemRepository.getAllDVDs(); }
    public ArrayList<Magazine> getAllMagazines() { return this.itemRepository.getAllMagazines(); }

    public boolean removeItem(int itemId) {
        ArrayList<Book> books = this.itemRepository.getAllBooks();
        for (Book book : books) {
            if (book.getItemId() == itemId) {
                return itemRepository.remove(book);
            }
        }

        ArrayList<DVD> dvds = this.itemRepository.getAllDVDs();
        for (DVD dvd : dvds) {
            if (dvd.getItemId() == itemId) {
                return itemRepository.remove(dvd);
            }
        }

        ArrayList<Magazine> magazines = this.itemRepository.getAllMagazines();
        for (Magazine magazine : magazines) {
            if (magazine.getItemId() == itemId) {
                return itemRepository.remove(magazine);
            }
        }

        return false;
    }

    public ArrayList<Book> getAvailableBooks() {
        return this.itemRepository.getAvailable(Book.class);
    }
    public ArrayList<DVD> getAvailableDVDs() {
        return  this.itemRepository.getAvailable(DVD.class);
    }
    public ArrayList<Magazine> getAvailableMagazines() {
        return this.itemRepository.getAvailable(Magazine.class);
    }
    public boolean updateNumberOfCopies(int itemId, int numberOfCopies) {
        return itemRepository.updateNumberOfCopies(itemId, numberOfCopies);
    }

    public <T extends Item> boolean decrementAvailableCopies(T item) {
        return this.itemRepository.decrementAvailableCopies(item);
    }

    public boolean rentItem(int memberId, int itemId) {
        return this.itemRepository.rentItem(memberId, itemId);
    }

    public <T extends Item> boolean incrementAvailableCopies(T item) {
        return this.itemRepository.incrementAvailableCopies(item);
    }

    public boolean returnItem(int itemId, int memberId) {
        return this.itemRepository.returnItem(itemId, memberId);
    }
}
