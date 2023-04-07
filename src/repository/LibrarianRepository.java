package repository;

import models.Librarian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibrarianRepository {
    private Librarian[] storedLibrarians = new Librarian[0];

    public boolean add(Librarian librarian) {
        try {
            List<Librarian> arrList = new ArrayList<>(Arrays.asList(storedLibrarians));
            arrList.add(librarian);
            storedLibrarians = arrList.toArray(storedLibrarians);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Librarian[] getAll() { return this.storedLibrarians; }

    public boolean remove(Librarian librarian) {
        try {
            List<Librarian> arrList = new ArrayList<>(Arrays.asList(storedLibrarians));
            arrList.remove(librarian);
            storedLibrarians = arrList.toArray(new Librarian[arrList.size()]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Librarian getLibrarianBiggestSalary() {
        double maxSalary = -1;
        Librarian librarianMax = null;
        for (Librarian librarian : storedLibrarians) {
            if (librarian.getSalary() > maxSalary) {
                maxSalary = librarian.getSalary();
                librarianMax = librarian;
            }
        }
        return librarianMax;
    }
}
