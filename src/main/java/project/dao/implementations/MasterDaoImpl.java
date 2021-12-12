package project.dao.implementations;

import org.springframework.stereotype.Repository;
import project.dao.interfaces.Dao;
import project.entities.Master;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MasterDaoImpl implements Dao<Master, Long> {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Master get(Long id) {

        return entityManager.find(Master.class, id);
    }

    @Override
    public Master getByName(String name) {
        return null;
    }

    @Override
    public List getAll() {

        return entityManager.createQuery("select m from Master m").getResultList();
    }

    @Override
    public void save(Master master) {

        entityManager.persist(master);
    }

    @Override
    public void update(Master master) {

        entityManager.merge(master);

    }

    @Override
    public void delete(Master master) {

        if(entityManager.contains(master))
            entityManager.remove(master);
        else
            entityManager.remove(entityManager.merge(master));


    }
}
