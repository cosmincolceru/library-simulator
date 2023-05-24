package models;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Member extends Person {
    int memberId;
    List<Item> itemsRented;
    public static int numberOfMembers;

    public Member() { }

    public Member(String firstName, String lastName, String CNP, String birthdate, int memberId) {
        super(firstName, lastName, CNP, birthdate);
        this.memberId = memberId;
        this.itemsRented = new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public List<Item> getItemsRented() {
        return itemsRented;
    }

    public void setItemsRented(List<Item> itemsRented) {
        this.itemsRented = itemsRented;
    }

    static public int getNumberOfMembers() { return numberOfMembers; }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", itemsRented=" + itemsRented +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", CNP='" + CNP + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Member member = (Member) o;
        return memberId == member.memberId && Objects.equals(itemsRented, member.itemsRented);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), memberId, itemsRented);
    }
}
