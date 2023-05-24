package models;

import java.util.Objects;

public class Person {
    String firstName;
    String lastName;
    String CNP;
    String birthdate;

    public Person() { }

    public Person(String firstName, String lastName, String CNP, String birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", CNP='" + CNP + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) && lastName.equals(person.lastName) && CNP.equals(person.CNP) && birthdate.equals(person.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, CNP, birthdate);
    }
}
