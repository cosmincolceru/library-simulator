package models;

import java.util.Objects;

public class Librarian extends Person {
    int librarianId;
    double salary;

    static public int numberOfLibrarians;

    public Librarian() { }

    public Librarian(String firstName, String lastName, String CNP, String birthdate, double salary, int librarianId) {
        super(firstName, lastName, CNP, birthdate);
        this.salary = salary;
        this.librarianId = librarianId;
    }
    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "salary=" + salary +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", CNP='" + CNP + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", librarianId=" + librarianId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Librarian librarian = (Librarian) o;
        return Double.compare(librarian.salary, salary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary);
    }
}
