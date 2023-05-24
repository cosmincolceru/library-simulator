package repository;

import models.*;

import java.sql.Connection;
import java.util.ArrayList;

import java.sql.*;

public class ItemRepository {
    DatabaseConnection db = new DatabaseConnection();


    public boolean addBook(Book book) {
        Connection connection = db.getConnection();
        String create = "CREATE TABLE IF NOT EXISTS BOOKS (" +
                            "itemID     number," +
                            "name   varchar," +
                            "numberOfCopies     number," +
                            "numberOfAvailableCopies    number," +
                            "author     varchar," +
                            "publicationYear      number," +
                            "publisher  varchar" +
                        ")";
        try {
            Statement statement = connection.createStatement();
            statement.execute(create);

            String insert = "INSERT INTO BOOKS(itemID, name, numberOfCopies, numberOfAvailableCopies, author, publicationYear, publisher) VALUES(" +
                    book.getItemId() + ",'" + book.getName() + "', " + book.getNumberOfCopies() + ", "+ book.getNumberOfAvailableCopies() + ",'" + book.getAuthor() +
                    "'," + book.getPublicationYear() + ",'" + book.getPublisher() + "')";

            statement.execute(insert);
            db.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return false;
    }

    public boolean addDVD(DVD dvd) {
        Connection connection = db.getConnection();
        String create = "CREATE TABLE IF NOT EXISTS DVDS (" +
                "itemID     number," +
                "name   varchar," +
                "numberOfCopies     number," +
                "numberOfAvailableCopies    number," +
                "director     varchar," +
                "releaseYear      number," +
                "duration   number," +
                "genre  varchar" +
                ")";
        try {

            Statement statement = connection.createStatement();
            statement.execute(create);

            String insert = "INSERT INTO DVDS(itemID, name, numberOfCopies, numberOfAvailableCopies, director, releaseYear, duration, genre) VALUES(" +
                    dvd.getItemId() + ",'" + dvd.getName() + "', " + dvd.getNumberOfCopies() + ", "+ dvd.getNumberOfAvailableCopies() + ",'" + dvd.getDirector() +
                    "'," + dvd.getReleaseYear() + "," + dvd.getDuration() +  ",'" + dvd.getGenres() + "')";

            statement.execute(insert);
            db.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return false;
    }
    public boolean addMagazine(Magazine magazine) {
        Connection connection = db.getConnection();
        String create = "CREATE TABLE IF NOT EXISTS MAGAZINES (" +
                "itemID     number," +
                "name   varchar," +
                "numberOfCopies     number," +
                "numberOfAvailableCopies    number," +
                "publicationDate      varchar" +
                ")";
        try {

            Statement statement = connection.createStatement();
            statement.execute(create);

            String insert = "INSERT INTO MAGAZINES(itemID, name, numberOfCopies, numberOfAvailableCopies, publicationDate) VALUES(" +
                    magazine.getItemId() + ",'" + magazine.getName() + "', " + magazine.getNumberOfCopies() + "," + magazine.getNumberOfAvailableCopies() + ",'" + magazine.getPublicationDate() + "')";

            statement.execute(insert);
            db.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return false;
    }


    public ArrayList<Book> getAllBooks() {
        Connection connection = db.getConnection();
        ArrayList<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String get = "Select * FROM BOOKS";
            ResultSet result = statement.executeQuery(get);

            while (result.next()) {
                Book book = new Book(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4), result.getString(5), result.getInt(6), result.getString(7));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return books;
    }

    public ArrayList<DVD> getAllDVDs() {
        Connection connection = db.getConnection();
        ArrayList<DVD> dvds = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String get = "Select * FROM DVDS";
            ResultSet result = statement.executeQuery(get);
            while (result.next()) {
                DVD dvd = new DVD(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4), result.getString(5), result.getInt(6), result.getInt(7), result.getString(8));
                dvds.add(dvd);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return dvds;
    }

    public ArrayList<Magazine> getAllMagazines() {
        Connection connection = db.getConnection();
        ArrayList<Magazine> magazines = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String get = "Select * FROM MAGAZINES";
            ResultSet result = statement.executeQuery(get);

            while (result.next()) {
                Magazine magazine = new Magazine(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4), result.getString(5));
                magazines.add(magazine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return magazines;
    }

    public <T extends Item> boolean remove(T item) {
        Connection connection = db.getConnection();
        int id = item.getItemId();

        String table;
        if (item instanceof Book) {
            table = "BOOKS";
        } else if (item instanceof DVD) {
            table = "DVDS";
        } else {
            table = "MAGAZINES";
        }
        try {
            Statement statement = connection.createStatement();
            String delete = "DELETE FROM " + table + " WHERE itemID='" + id + "'";
            statement.execute(delete);
            db.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return false;
    }

    public <T extends Item> ArrayList<T> getAvailable(Class<T> itemClass) {
        ArrayList<T> availableItems = new ArrayList<>();
        ArrayList<? extends Item> allItems;

        if (itemClass.equals(Book.class)) {
            allItems = this.getAllBooks();
        } else if (itemClass.equals(DVD.class)) {
            allItems = this.getAllDVDs();
        } else {
            allItems = this.getAllMagazines();
        }

        for (Item item : allItems) {
            if (item.getNumberOfAvailableCopies() > 0) {
                availableItems.add(itemClass.cast(item));
            }
        }

        return availableItems;
    }


    public boolean updateNumberOfCopies(int itemId, int numberOfCopies) {
        Connection connection = db.getConnection();
        ArrayList<Book> allBooks = this.getAllBooks();
        ArrayList<DVD> allDVDs = this.getAllDVDs();
        ArrayList<Magazine> allMagazines = this.getAllMagazines();

        String table = "";
        for (Book book : allBooks) {
            if (book.getItemId() == itemId) {
                table = "BOOKS";
                break;
            }
        }
        for (DVD dvd : allDVDs) {
            if (dvd.getItemId() == itemId) {
                table = "DVDS";
                break;
            }
        }
        for (Magazine magazine : allMagazines) {
            if (magazine.getItemId() == itemId) {
                table = "MAGAZINES";
                break;
            }
        }

        try {
            String get = "SELECT * FROM "+ table +" WHERE itemID = " + itemId;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(get);


            int numberOfCopiesOld = result.getInt("numberOfCopies");
            int numberOfAvailableCopiesOld = result.getInt("numberOfAvailableCopies");
            int nr = numberOfCopiesOld;
            int diff = numberOfCopies - nr;
            int numberOfAvailableCopiesNew = numberOfAvailableCopiesOld + diff;

            // update the number of copies and of available copies in the database
            String update = "UPDATE " + table + " " +
                    "SET numberOfCopies = " + numberOfCopies + ", " +
                    "numberOfAvailableCopies = " + numberOfAvailableCopiesNew + " " +
                    "WHERE itemID = '" + itemId + "'";

            statement.execute(update);
            db.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return false;
    }

    public <T extends Item> boolean decrementAvailableCopies(T item) {
        Connection connection = db.getConnection();
        int id = item.getItemId();

        String table;
        if (item instanceof Book) {
            table = "BOOKS";
        } else if (item instanceof DVD) {
            table = "DVDS";
        } else {
            table = "MAGAZINES";
        }

        try {
            String get = "SELECT * FROM "+ table +" WHERE itemID = " + id;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(get);
            int numberOfAvailableCopies = result.getInt("numberOfAvailableCopies");
            numberOfAvailableCopies -= 1;

            String update = "UPDATE " + table + " " +
                    "SET numberOfAvailableCopies = " + numberOfAvailableCopies + " " +
                    "WHERE itemID = '" + id + "'";

            statement.execute(update);
            db.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return false;
    }



    public boolean rentItem(int memberId, int itemId) {
        Connection connection = db.getConnection();
        String rent = "INSERT INTO MEMBERSITEMS(memberId, itemId) VALUES(" + memberId + "," + itemId + ")";
        try {
            Statement statement = connection.createStatement();
            statement.execute(rent);
            db.closeConnection(connection);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return false;
    }

    public <T extends Item> boolean incrementAvailableCopies(T item) {
        Connection connection = db.getConnection();
        int id = item.getItemId();

        String table;
        if (item instanceof Book) {
            table = "BOOKS";
        } else if (item instanceof DVD) {
            table = "DVDS";
        } else {
            table = "MAGAZINES";
        }

        try {
            String get = "SELECT * FROM "+ table +" WHERE itemID = " + id;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(get);
            int numberOfAvailableCopies = result.getInt("numberOfAvailableCopies");
            numberOfAvailableCopies += 1;

            String update = "UPDATE " + table + " " +
                    "SET numberOfAvailableCopies = " + numberOfAvailableCopies + " " +
                    "WHERE itemID = '" + id + "'";

            statement.execute(update);
            db.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return false;
    }

    public boolean returnItem(int itemId, int memberId) {
        Connection connection = db.getConnection();
        String ret = "DELETE FROM MEMBERSITEMS WHERE memberId = " + memberId + " AND itemId = " + itemId;
        try {
            Statement statement = connection.createStatement();
            statement.execute(ret);
            db.closeConnection(connection);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return false;

    }

}
