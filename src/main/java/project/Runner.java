package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.dao.implementations.ClientDaoImpl;
import project.dao.implementations.ManagerDaoImpl;
import project.dao.interfaces.Dao;
import project.entities.Client;
import project.entities.Manager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLOutput;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private ClientDaoImpl dao;


    @Override
    public void run(String...args) throws Exception {
        System.out.println("\n----- Get all clients -----\n");
        dao.getAll().forEach(System.out::println);
        Client client1 = new Client("Adam", "abc5", "adam@mail.com");
        dao.save(client1);
        Client client2 = new Client("Eva", "old", "eva@mail.com");
        dao.save(client2);
        System.out.println("\n----- Add 2 clients -----\n");
        dao.getAll().forEach(System.out::println);
        client2.setPassword("new");
        dao.update(client2);
        dao.delete(client1);
        System.out.println("\n----- Delete Adam and change Eva's password -----\n");
        dao.getAll().forEach(System.out::println);
        System.out.println();


    }
}