package repository;

import models.*;

import java.sql.*;
import java.util.*;

public class MemberRepository implements CRUDRepository<Member> {
    DatabaseConnection db = new DatabaseConnection();


    public boolean add(Member member) {
        Connection connection = db.getConnection();
        String create = "CREATE TABLE IF NOT EXISTS MEMBERS (" +
                "firstName     varchar," +
                "lastName   varchar," +
                "CNP   varchar," +
                "birthdate     varchar," +
                "memberId      number" +
                ")";

        String create2 = "CREATE TABLE IF NOT EXISTS MEMBERSITEMS (" +
                "memberId   number," +
                "itemId     number)";

        try {
            Statement statement = connection.createStatement();
            statement.execute(create);
            statement.execute(create2);

            String insert = "INSERT INTO MEMBERS(firstName, lastName, CNP, birthdate, memberId) VALUES('" +
                    member.getFirstName() + "','" + member.getLastName() + "', '" + member.getCNP() + "', '"+ member.getBirthdate() + "',"
                    + member.getMemberId() + ")";

            statement.execute(insert);
            db.closeConnection(connection);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);

        return false;
    }

    public ArrayList<Member> getAll() {
        Connection connection = db.getConnection();
        ArrayList<Member> members = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String get = "Select * FROM MEMBERS";
            ResultSet result = statement.executeQuery(get);



            ArrayList<Member> members1 = new ArrayList<>();
            while (result.next()) {
                Member member = new Member(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5));
                members1.add(member);
            }

            for (Member member : members1) {
                String itemIds = "SELECT * FROM MEMBERSITEMS WHERE memberId = " + member.getMemberId();
                ResultSet resultItems = statement.executeQuery(itemIds);

                ArrayList<Item> items = new ArrayList<>();

                ArrayList<Integer> ids = new ArrayList<>();

                while (resultItems.next()) {
                    ids.add(resultItems.getInt("itemId"));
                }

                for (int itemId : ids) {

                    String getItem = "SELECT * FROM BOOKS WHERE itemID = " + itemId;
                    ResultSet itemRes = statement.executeQuery(getItem);

                    if (itemRes.next()) {
                        // item is a book
                        Book book = new Book(itemRes.getInt(1), itemRes.getString(2), itemRes.getInt(3), itemRes.getInt(4), itemRes.getString(5), itemRes.getInt(6), itemRes.getString(7));

                        items.add(book);
                    } else {
                        getItem = "SELECT * FROM DVDS WHERE itemID = " + itemId;
                        ResultSet dvdRes = statement.executeQuery(getItem);
                        if (dvdRes.next()) {
                            // item is a dvd
                            DVD dvd = new DVD(dvdRes.getInt(1), dvdRes.getString(2), dvdRes.getInt(3), dvdRes.getInt(4), dvdRes.getString(5), dvdRes.getInt(6), dvdRes.getInt(7), dvdRes.getString(8));

                            items.add(dvd);
                        } else {
                            getItem = "SELECT * FROM MAGAZINES WHERE itemID = " + itemId;
                            ResultSet magazineRes = statement.executeQuery(getItem);
                            if (magazineRes.next()) {
                                // item is a magazine
                                Magazine magazine = new Magazine(magazineRes.getInt(1), magazineRes.getString(2), magazineRes.getInt(3), magazineRes.getInt(4), magazineRes.getString(5));

                                items.add(magazine);
                            }
                        }
                    }
                }

                member.setItemsRented(items);

                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);

        return members;
    }

    public boolean remove(Member member) {
        Connection connection = db.getConnection();
        String cnp = member.getCNP();

        try {
            Statement statement = connection.createStatement();
            String delete = "DELETE FROM MEMBERS WHERE CNP='" + cnp + "'";
            statement.execute(delete);
            db.closeConnection(connection);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.closeConnection(connection);

        return false;
    }

    public Set<Member> getMembersWithMostRented() {
        int mostRented = 0;
        ArrayList<Member> allMembers = this.getAll();

        for (Member member : allMembers) {
            if (member.getItemsRented().size() > mostRented) {
                mostRented = member.getItemsRented().size();
            }
        }
        Set<Member> members = new HashSet<>();
        for (Member member : allMembers) {
            if (member.getItemsRented().size() == mostRented) {
                members.add(member);
            }
        }
        return members;
    }
}
