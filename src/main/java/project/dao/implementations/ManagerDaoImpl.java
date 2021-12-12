package project.dao.implementations;

import org.springframework.stereotype.Repository;
import project.dao.interfaces.Dao;
import project.entities.Manager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ManagerDaoImpl implements Dao<Manager, Long> {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Manager get(Long id) {
        
        return entityManager.find(Manager.class, id);
    }

    @Override
    public Manager getByName(String name) {
        TypedQuery<Manager> query = entityManager.createQuery("SELECT m FROM Manager m WHERE m.name = :name", Manager.class).setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List getAll() {
        
        return entityManager.createQuery("select m from Manager m").getResultList();
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
        else
            entityManager.remove(entityManager.merge(manager));
        
    }
}
