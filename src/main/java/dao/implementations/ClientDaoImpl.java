//package dao.implementations;
//
//import dao.interfaces.Dao;
//import entities.Client;
//import sql.ConnectionPool;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Optional;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class ClientDaoImpl implements Dao<Client, Long> {
//
//    private static final Logger LOGGER =
//            Logger.getLogger(ManagerDaoImpl.class.getName());
//    private final Optional<Connection> connection;
//
//    public ClientDaoImpl() {
//
//        this.connection = ConnectionPool.getConnection();
//    }
//
//    @Override
//    public Optional<Client> get(Long id) {
//
//        String sql = "SELECT * FROM client WHERE id=" + id;
//        return connection.flatMap(conn -> {
//            Optional<Client> client = Optional.empty();
//
//            try (Statement statement = conn.createStatement();
//                 ResultSet resultSet = statement.executeQuery(sql)) {
//
//                if (resultSet.next()) {
//                    String name = resultSet.getString("name");
//                    String email = resultSet.getString("email");
//                    String password = resultSet.getString("password");
//
//                    client = Optional.of(new Client(id, name, email, password));
//                }
//            } catch (SQLException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            }
//
//            return client;
//        });
//    }
//
//    @Override
//    public Collection<Client> getAll() {
//
//        Collection<Client> clients = new ArrayList<>();
//        String sql = "SELECT * FROM client";
//        connection.ifPresent(conn -> {
//            try (Statement statement = conn.createStatement();
//                 ResultSet resultSet = statement.executeQuery(sql)) {
//
//                while (resultSet.next()) {
//                    Long id = resultSet.getLong("id");
//                    String name = resultSet.getString("name");
//                    String email = resultSet.getString("email");
//                    String password = resultSet.getString("password");
//
//                    Client client = new Client(id, name, email, password);
//                    clients.add(client);
//                }
//
//            } catch (SQLException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            }
//        });
//        return clients;
//    }
//
//    @Override
//    public Optional<Long> save(Client client) {
//
//        String sql = "INSERT INTO "
//                + "client(name, email, password) "
//                + "VALUES(?, ?, ?)";
//
//        return connection.flatMap(conn -> {
//            Optional<Long> generatedId = Optional.empty();
//
//            try (PreparedStatement statement =
//                         conn.prepareStatement(
//                                 sql,
//                                 Statement.RETURN_GENERATED_KEYS)) {
//
//                statement.setString(1, client.getName());
//                statement.setString(2, client.getEmail());
//                statement.setString(3, client.getPassword());
//
//                int numberOfInsertedRows = statement.executeUpdate();
//
//                // Retrieve the auto-generated id
//                if (numberOfInsertedRows > 0) {
//                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
//                        if (resultSet.next()) {
//                            generatedId = Optional.of(resultSet.getLong("id"));
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
//    public void update(Client client) {
//
//        Long id = client.getId();
//        String sql = "UPDATE client SET "
//                + "name=?, "
//                + "email=?, "
//                + "password=? "
//                + "WHERE id=" + id;
//        connection.ifPresent(conn -> {
//            try (PreparedStatement statement = conn.prepareStatement(sql)) {
//                statement.setString(1, client.getName());
//                statement.setString(2, client.getEmail());
//                statement.setString(3, client.getPassword());
//                statement.executeUpdate();
//
//            } catch (SQLException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            }
//        });
//    }
//
//    @Override
//    public void delete(Client client) {
//
//        String sql = "DELETE FROM client WHERE id=?";
//        connection.ifPresent(conn -> {
//            try (PreparedStatement statement = conn.prepareStatement(sql)) {
//                statement.setLong(1, client.getId());
//                statement.executeUpdate();
//
//
//            } catch (SQLException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            }
//        });
//    }
//}
