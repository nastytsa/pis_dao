package dao.interfaces;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Dao<T, I> {
    
    T get(Long id);
    
    List getAll();
    
    void save(T t);
    
    void update(T t);
    
    void delete(T t);
}
