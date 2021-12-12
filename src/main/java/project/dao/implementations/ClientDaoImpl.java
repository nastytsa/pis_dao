package project.dao.implementations;

import org.springframework.stereotype.Repository;
import project.dao.interfaces.Dao;
import project.entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ClientDaoImpl implements Dao<Client, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Client get(Long id) {

        return entityManager.find(Client.class, id);
    }

    @Override
    public Client getByName(String name) {
        return null;
    }

    @Override
    public List getAll() {

        return entityManager.createQuery("select c from Client c").getResultList();
    }

    @Override
    public void save(Client client) {

        entityManager.persist(client);
    }

    @Override
    public void update(Client client) {

        entityManager.merge(client);

    }

    @Override
    public void delete(Client client) {

        if(entityManager.contains(client))
            entityManager.remove(client);
        else
            entityManager.remove(entityManager.merge(client));

    }
}
