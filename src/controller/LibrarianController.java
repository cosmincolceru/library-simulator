package controller;

import models.Librarian;
import service.LibrarianService;

import java.util.ArrayList;
import java.util.Set;

public class LibrarianController {
    private LibrarianService librarianService;

    public LibrarianController() {
        librarianService = LibrarianService.getInstance();
    }

    public boolean addLibrarian(String firstName, String lastName, String CNP, String birthdate, double salary) {
        firstName = capitalizeString(firstName);
        lastName = capitalizeString(lastName);

        return librarianService.addLibrarian(firstName, lastName, CNP, birthdate, salary);
    }

    public ArrayList<Librarian> getAllLibrarians() { return librarianService.getAllLibrarians(); }

    private String capitalizeString (String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public boolean removeLibrarian(int librarianId) {
        return librarianService.removeLibrarian(librarianId);
    }

    public Set<Librarian> getLibrarianBiggestSalary() {
        return librarianService.getLibrarianBiggestSalary();
    }
}
