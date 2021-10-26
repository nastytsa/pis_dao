package dao.implementations;

import dao.interfaces.Dao;
import entities.Manager;
import sql.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerDaoImpl implements Dao<Manager, Long> {
    
    private static final Logger LOGGER =
            Logger.getLogger(ManagerDaoImpl.class.getName());
    private final Optional<Connection> connection;
    
    public ManagerDaoImpl() {
        
        this.connection = ConnectionPool.getConnection();
    }
    
    @Override
    public Optional<Manager> get(Long id) {
        
        String sql = "SELECT * FROM manager WHERE id=" + id;
        return connection.flatMap(conn -> {
            Optional<Manager> manager = Optional.empty();
            
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    
                    manager = Optional.of(new Manager(id, name, password));
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            
            return manager;
        });
    }
    
    @Override
    public Collection<Manager> getAll() {
        
        Collection<Manager> managers = new ArrayList<>();
        String sql = "SELECT * FROM manager";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    
                    Manager manager = new Manager(id, name, password);
                    managers.add(manager);
                }
                
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return managers;
    }
    
    @Override
    public Optional<Long> save(Manager manager) {
        
        String sql = "INSERT INTO "
                + "manager(name, password) "
                + "VALUES(?, ?)";
        
        return connection.flatMap(conn -> {
            Optional<Long> generatedId = Optional.empty();
            
            try (PreparedStatement statement =
                         conn.prepareStatement(
                                 sql,
                                 Statement.RETURN_GENERATED_KEYS)) {
                
                statement.setString(1, manager.getName());
                statement.setString(2, manager.getPassword());
                
                int numberOfInsertedRows = statement.executeUpdate();
                
                // Retrieve the auto-generated id
                if (numberOfInsertedRows > 0) {
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            generatedId = Optional.of(resultSet.getLong("id"));
                        }
                    }
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            return generatedId;
        });
    }
    
    @Override
    public void update(Manager manager) {
        
        Long id = manager.getId();
        String sql = "UPDATE manager SET "
                + "name=?, "
                + "password=? "
                + "WHERE id=" + id;
        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, manager.getName());
                statement.setString(2, manager.getPassword());
                statement.executeUpdate();
                
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
    @Override
    public void delete(Manager manager) {
        
        String sql = "DELETE FROM manager WHERE id=?";
        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, manager.getId());
                statement.executeUpdate();
                
                
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        
    }
}
