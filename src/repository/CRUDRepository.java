package repository;

import java.util.ArrayList;

public interface CRUDRepository<T> {
    boolean add(T a);

    ArrayList<T> getAll();

    boolean remove(T a);

}
