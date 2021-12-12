package project.dao.interfaces;

import java.util.List;

public interface Dao<T, I> {
    
    T get(Long id);

    T getByName(String name);
    
    List getAll();
    
    void save(T t);
    
    void update(T t);
    
    void delete(T t);
}
