package service;

import models.Librarian;
import repository.LibrarianRepository;

public class LibrarianService {
    private LibrarianRepository librarianRepository;

    public LibrarianService() { this.librarianRepository = new LibrarianRepository(); }

    public boolean addLibrarian(String firstName, String lastName, String CNP, String birthdate, double salary) {
        Librarian.numberOfLibrarians += 1;
        int librarianId = Librarian.numberOfLibrarians;

        Librarian librarian = new Librarian(firstName, lastName, CNP, birthdate, salary, librarianId);
        return this.librarianRepository.add(librarian);
    }

    public Librarian[] getAllLibrarians() { return this.librarianRepository.getAll(); }

    public boolean removeLibrarian(int librarianId) {
        Librarian[] librarians = librarianRepository.getAll();
        for (Librarian librarian : librarians) {
            if (librarian.getLibrarianId() == librarianId) {
                return librarianRepository.remove(librarian);
            }
        }
        return false;
    }

    public Librarian getLibrarianBiggestSalary() {
        return librarianRepository.getLibrarianBiggestSalary();
    }
}
