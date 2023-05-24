package repository;

import models.DatabaseConnection;
import models.Librarian;

import java.util.ArrayList;


import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class LibrarianRepository implements CRUDRepository<Librarian> {
    DatabaseConnection db = new DatabaseConnection();


    public boolean add(Librarian librarian) {
        Connection connection = db.getConnection();
        String create = "CREATE TABLE IF NOT EXISTS LIBRARIANS (" +
                "firstName     varchar," +
                "lastName   varchar," +
                "CNP   varchar," +
                "birthdate     varchar," +
                "salary    double," +
                "librarianId      number" +
                ")";
        try {
            Statement statement = connection.createStatement();
            statement.execute(create);

            String insert = "INSERT INTO LIBRARIANS(firstName, lastName, CNP, birthdate, salary, librarianId) VALUES('" +
                    librarian.getFirstName() + "','" + librarian.getLastName() + "', '" + librarian.getCNP() + "', '"+ librarian.getBirthdate() + "'," + librarian.getSalary() +
                    "," + librarian.getLibrarianId() + ")";

            statement.execute(insert);
            db.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return false;
    }

    public ArrayList<Librarian> getAll() {
        Connection connection = db.getConnection();
        ArrayList<Librarian> librarians = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String get = "Select * FROM LIBRARIANS";
            ResultSet result = statement.executeQuery(get);

            while (result.next()) {
                Librarian librarian = new Librarian(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getDouble(5), result.getInt(6));
                librarians.add(librarian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return librarians;
    }

    public boolean remove(Librarian librarian) {
        Connection connection = db.getConnection();
        String cnp = librarian.getCNP();

        try {
            Statement statement = connection.createStatement();
            String delete = "DELETE FROM LIBRARIANS WHERE CNP='" + cnp + "'";
            statement.execute(delete);
            db.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);
        return false;
    }

    public Set<Librarian> getLibrarianBiggestSalary() {
        double maxSalary = -1;
        Librarian librarianMax = null;
        ArrayList<Librarian> allLibrarians = this.getAll();
        for (Librarian librarian : allLibrarians) {
            if (librarian.getSalary() > maxSalary) {
                maxSalary = librarian.getSalary();
                librarianMax = librarian;
            }
        }

        Set<Librarian> librarians = new HashSet<>();
        for (Librarian librarian : allLibrarians) {
            if (librarian.getSalary() == maxSalary) {
                librarians.add(librarian);
            }
        }

        return librarians;
    }
}
