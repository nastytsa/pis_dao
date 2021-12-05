import dao.implementations.*;
import dao.interfaces.Dao;
import entities.*;


public class Main {
    
    private static Dao<Manager, Long> managerDao = new ManagerDaoImpl();
    
    
    public static void main(String[] args) {
        
//        Client client1 = new Client("Adam", "abc5", "adam@mail.com");
//        clientDao.save(client1).ifPresent(client1::setId);
//        Client client2 = new Client("Eva", "drty", "eva@mail.com");
//        clientDao.save(client2).ifPresent(client2::setId);
//
//        Master master1 = new Master("John", "134", "");
//        masterDao.save(master1).ifPresent(master1::setId);
//
//        System.out.println("-----adding 2 clients and a master-----");
//        clientDao.getAll().forEach(System.out::println);
//        masterDao.getAll().forEach(System.out::println);
//
//        Order order1 = new Order(null, client1.getId(), master1.getId(), null, null, "Repair shoes");
//        orderDao.save(order1).ifPresent(order1::setOrder_id);
//        Order order2 = new Order(null, client1.getId(), master1.getId(), null, null, "Fix bugs");
//        orderDao.save(order2).ifPresent(order2::setOrder_id);
//        Order order3 = new Order(null, client2.getId(), master1.getId(), null, null, "Repair jewelry");
//        orderDao.save(order3).ifPresent(order3::setOrder_id);
//
//        System.out.println("-----adding 3 orders-----");
//        orderDao.getAll().forEach(System.out::println);
//
//        order2.setStatus("DENIED");
//        order2.setComment("Not interesting");
//        orderDao.update(order2);
//        System.out.println("-----updated second order and get orders of client1-----");
//        orderDao.getByClientId(client1.getId()).forEach(System.out::println);
    
        managerDao.getAll().forEach(System.out::println);
        
//        Manager firstManager =
//                new Manager("ТЕФ", "forever");
//        Manager secondManager =
//                new Manager("ІПСА", "дякуюбоже");
//        managerDao.save(firstManager);
//        managerDao.save(secondManager);
//
//        System.out.println("-----adding 2 managers-----");
//        managerDao.getAll().forEach(System.out::println);
//
//        secondManager.setName("ФПСА");
//        managerDao.update(secondManager);
//
//        System.out.println("-----updating second manager-----");
//        managerDao.getAll().forEach(System.out::println);
//
//        managerDao.delete(secondManager);
//
//        System.out.println("-----deleting second manager-----");
//        managerDao.getAll().forEach(System.out::println);
    }
}
