package project.dao.interfaces;


import project.entities.Order;

import java.util.Collection;

public interface OrderDao {
    
    Collection<Order> getByClientId(Long id);
    
    Collection<Order> getByMasterId(Long id);
    
}
