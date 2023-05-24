package util;

import models.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import models.*;

public class ID<T> {
    private static volatile ID instance;
    private DatabaseConnection db;

    private ID() {
        // Initialize the DatabaseConnection here
        db = new DatabaseConnection();
    }
    public static ID getInstance() {
        if (instance == null) {
            synchronized (ID.class) {
                if (instance == null) {
                    instance = new ID();
                }
            }
        }
        return instance;
    }
    public int getNextId(T x) {
        Connection connection = db.getConnection();
        try {
            String values;
            if (x instanceof Item) {
                values = "1, 0, 0";
            } else if (x instanceof Member) {
                values = "0, 0, 1";
            } else {
                values = "0, 1, 0";
            }

            String create = "CREATE TABLE ID(idItem number, idLibrarian number, idMember number)";
            Statement statement = connection.createStatement();
            statement.execute(create);

            statement.execute("INSERT INTO ID(idItem, idLibrarian, idMember) VALUES(" + values + ")");
            db.closeConnection(connection);
            return 1;
        } catch (SQLException e) {
            try {
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM ID");
                int idItem = result.getInt(1);
                int idLibrarian = result.getInt(2);
                int idMember = result.getInt(3);

                if (x instanceof Item) {
                    statement.execute("DELETE FROM ID WHERE idItem=" + idItem);
                    idItem += 1;
                    statement.execute("INSERT INTO ID(idItem, idLibrarian, idMember) VALUES(" + idItem + "," + idLibrarian + "," + idMember + ")");
                    db.closeConnection(connection);
                    return idItem;
                } else if (x instanceof Member) {
                    statement.execute("DELETE FROM ID WHERE idItem=" + idItem);
                    idMember += 1;
                    statement.execute("INSERT INTO ID(idItem, idLibrarian, idMember) VALUES(" + idItem + "," + idLibrarian + "," + idMember + ")");
                    db.closeConnection(connection);
                    return idMember;
                } else {
                    statement.execute("DELETE FROM ID WHERE idItem=" + idItem);
                    idLibrarian += 1;
                    statement.execute("INSERT INTO ID(idItem, idLibrarian, idMember) VALUES(" + idItem + "," + idLibrarian + "," + idMember + ")");

                    db.closeConnection(connection);
                    return idLibrarian;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                db.closeConnection(connection);
                return 0;
            } finally {
                 db.closeConnection(connection);
            }
        }
    }
}
