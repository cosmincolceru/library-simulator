package service;

import models.Librarian;
import repository.LibrarianRepository;
import util.ID;

import java.util.ArrayList;
import java.util.Set;

public class LibrarianService {
    private LibrarianRepository librarianRepository;

    private static LibrarianService instance;
    private LibrarianService() {
        librarianRepository = new LibrarianRepository();
    }

    public static LibrarianService getInstance() {
        if (instance == null) {
            instance = new LibrarianService();
        }
        return instance;
    }

    ID<Librarian> id = ID.getInstance();

    public boolean addLibrarian(String firstName, String lastName, String CNP, String birthdate, double salary) {
        int librarianId = id.getNextId(new Librarian());

        Librarian librarian = new Librarian(firstName, lastName, CNP, birthdate, salary, librarianId);
        return this.librarianRepository.add(librarian);
    }

    public ArrayList<Librarian> getAllLibrarians() { return this.librarianRepository.getAll(); }

    public boolean removeLibrarian(int librarianId) {
        ArrayList<Librarian> librarians = librarianRepository.getAll();
        for (Librarian librarian : librarians) {
            if (librarian.getLibrarianId() == librarianId) {
                return librarianRepository.remove(librarian);
            }
        }
        return false;
    }

    public Set<Librarian> getLibrarianBiggestSalary() {
        return librarianRepository.getLibrarianBiggestSalary();
    }
}
