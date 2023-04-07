package models;

import java.util.Objects;

public class Magazine extends Item {
    String publicationDate;

    public Magazine(int itemId, String name, int numberOfCopies, String publicationDate) {
        super(itemId, name, numberOfCopies);
        this.publicationDate = publicationDate;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "id=" + itemId +
                ", publicationDate='" + publicationDate + '\'' +
                ", name='" + name + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", numberOfAvailableCopies=" + numberOfAvailableCopies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return publicationDate.equals(magazine.publicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publicationDate);
    }
}
