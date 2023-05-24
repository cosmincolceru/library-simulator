package models;

import java.util.Objects;

public class Item {
    int itemId;
    String name;
    int numberOfCopies;
    int numberOfAvailableCopies;

    public static int numberOfItems;

    public Item() {}
    public Item(int itemId, String name, int numberOfCopies) {
        this.itemId = itemId;
        this.name = name;
        this.numberOfCopies = numberOfCopies;
        this.numberOfAvailableCopies = numberOfCopies;
    }

    public Item(int itemId, String name, int numberOfCopies, int numberOfAvailableCopies) {
        this.itemId = itemId;
        this.name = name;
        this.numberOfCopies = numberOfCopies;
        this.numberOfAvailableCopies = numberOfAvailableCopies;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public int getNumberOfAvailableCopies() {
        return numberOfAvailableCopies;
    }

    public void setNumberOfAvailableCopies(int numberOfAvailableCopies) {
        this.numberOfAvailableCopies = numberOfAvailableCopies;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + itemId +
                "name='" + name + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", numberOfAvailableCopies=" + numberOfAvailableCopies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return numberOfCopies == item.numberOfCopies && numberOfAvailableCopies == item.numberOfAvailableCopies && name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfCopies, numberOfAvailableCopies);
    }
}
