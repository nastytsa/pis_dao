package dao.implementations;

import dao.interfaces.Dao;
import entities.Manager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@Transactional
public class ManagerDaoImpl implements Dao<Manager, Long> {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public ManagerDaoImpl() {
    }
    
    @Override
    public Manager get(Long id) {
        
        return entityManager.find(Manager.class, id);
    }
    
    @Override
    public List getAll() {
        
        return entityManager.createQuery("from manager").getResultList();
    }
    
    @Override
    public void save(Manager manager) {
        
        entityManager.persist(manager);
    }
    
    @Override
    public void update(Manager manager) {
        
        entityManager.merge(manager);
        
    }
    
    @Override
    public void delete(Manager manager) {
        
        if(entityManager.contains(manager))
            entityManager.remove(manager);
        
    }
}
