//package dao.implementations;
//
//import dao.interfaces.Dao;
//import dao.interfaces.OrderDao;
//import entities.Order;
//import sql.ConnectionPool;
//
//import java.math.BigDecimal;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Optional;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class OrderDaoImpl implements OrderDao, Dao<Order, Long> {
//
//    private static final Logger LOGGER =
//            Logger.getLogger(ManagerDaoImpl.class.getName());
//    private final Optional<Connection> connection;
//
//    public OrderDaoImpl() {
//
//        this.connection = ConnectionPool.getConnection();
//    }
//
//    @Override
//    public Collection<Order> getByClientId(Long client_id) {
//
//        Collection<Order> orders = new ArrayList<>();
//        String sql = "SELECT * FROM orders WHERE client_id=" + client_id;
//        connection.ifPresent(conn -> {
//            try (Statement statement = conn.createStatement();
//                 ResultSet resultSet = statement.executeQuery(sql)) {
//
//                while (resultSet.next()) {
//                    Long order_id = resultSet.getLong("order_id");
//                    String comment = resultSet.getString("_comment");
//                    Long master_id = resultSet.getLong("master_id");
//                    BigDecimal price = resultSet.getBigDecimal("price");
//                    String status = resultSet.getString("status");
//                    String content = resultSet.getString("content");
//
//                    Order order = new Order(order_id, comment, client_id, master_id, price, status, content);
//                    orders.add(order);
//                }
//
//            } catch (SQLException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            }
//        });
//        return orders;
//    }
//
//    @Override
//    public Collection<Order> getByMasterId(Long id) {
//
//        return null;
//    }
//
//    @Override
//    public Optional<Order> get(Long id) {
//
//        return Optional.empty();
//    }
//
//    @Override
//    public Collection<Order> getAll() {
//        Collection<Order> orders = new ArrayList<>();
//        String sql = "SELECT * FROM orders";
//        connection.ifPresent(conn -> {
//            try (Statement statement = conn.createStatement();
//                 ResultSet resultSet = statement.executeQuery(sql)) {
//
//                while (resultSet.next()) {
//                    Long order_id = resultSet.getLong("order_id");
//                    Long client_id = resultSet.getLong("client_id");
//                    Long master_id = resultSet.getLong("master_id");
//                    BigDecimal price = resultSet.getBigDecimal("price");
//                    String comment = resultSet.getString("_comment");
//                    String status = resultSet.getString("status");
//                    String content = resultSet.getString("content");
//
//                    Order order = new Order(order_id, comment, client_id, master_id, price, status, content);
//                    orders.add(order);
//                }
//
//            } catch (SQLException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            }
//        });
//        return orders;
//    }
//
//    @Override
//    public Optional<Long> save(Order order) {
//        String sql = "INSERT INTO "
//                + "orders(_comment, client_id, master_id, price, status, content) "
//                + "VALUES(?, ?, ?, ?, ?, ?)";
//
//        return connection.flatMap(conn -> {
//            Optional<Long> generatedId = Optional.empty();
//
//            try (PreparedStatement statement =
//                         conn.prepareStatement(
//                                 sql,
//                                 Statement.RETURN_GENERATED_KEYS)) {
//
//                statement.setString(1, order.getComment());
//                statement.setLong(2, order.getClient_id());
//                statement.setLong(3, order.getMaster_id());
//                statement.setBigDecimal(4, order.getPrice());
//                statement.setString(5, order.getStatus());
//                statement.setString(6, order.getContent());
//
//                int numberOfInsertedRows = statement.executeUpdate();
//
//                // Retrieve the auto-generated id
//                if (numberOfInsertedRows > 0) {
//                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
//                        if (resultSet.next()) {
//                            generatedId = Optional.of(resultSet.getLong("order_id"));
//                        }
//                    }
//                }
//            } catch (SQLException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            }
//            return generatedId;
//        });
//    }
//
//    @Override
//    public void update(Order order) {
//        Long id = order.getOrder_id();
//        String sql = "UPDATE orders SET "
//                + "_comment=?, "
//                + "master_id=?, "
//                + "price=?, "
//                + "status=?, "
//                + "content=? "
//                + "WHERE order_id=" + id;
//        connection.ifPresent(conn -> {
//            try (PreparedStatement statement = conn.prepareStatement(sql)) {
//                statement.setString(1, order.getComment());
//                statement.setLong(2, order.getMaster_id());
//                statement.setBigDecimal(3, order.getPrice());
//                statement.setString(4, order.getStatus());
//                statement.setString(5, order.getContent());
//                statement.executeUpdate();
//
//            } catch (SQLException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            }
//        });
//    }
//
//    @Override
//    public void delete(Order order) {
//
//    }
//}
