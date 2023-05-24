package models;

import java.util.Objects;

public class Book extends Item {
    String author;
    int publicationYear;
    String publisher;

    public Book() { }
    public Book(int itemId, String name, int numberOfCopies, String author, int publicationYear, String publisher) {
        super(itemId, name, numberOfCopies);
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
    }

    public Book(int itemId, String name, int numberOfCopies, int numberOfAvailableCopies,String author, int publicationYear, String publisher) {
        super(itemId, name, numberOfCopies, numberOfAvailableCopies);
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + itemId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", publisher='" + publisher + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", numberOfAvailableCopies=" + numberOfAvailableCopies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return publicationYear == book.publicationYear && author.equals(book.author) && publisher.equals(book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, publicationYear, publisher);
    }
}
