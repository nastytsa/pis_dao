package dao.implementations;

import dao.interfaces.Dao;
import entities.Master;
import sql.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MasterDaoImpl implements Dao<Master, Long> {
    
    private static final Logger LOGGER =
            Logger.getLogger(ManagerDaoImpl.class.getName());
    private final Optional<Connection> connection;
    
    public MasterDaoImpl() {
        
        this.connection = ConnectionPool.getConnection();
    }
    
    @Override
    public Optional<Master> get(Long id) {
        
        String sql = "SELECT * FROM master WHERE id=" + id;
        return connection.flatMap(conn -> {
            Optional<Master> master = Optional.empty();
            
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    String rate = resultSet.getString("rate");
                    
                    master = Optional.of(new Master(id, name, password, rate));
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
            
            return master;
        });
    }
    
    @Override
    public Collection<Master> getAll() {
        
        Collection<Master> masters = new ArrayList<>();
        String sql = "SELECT * FROM master";
        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    String rate = resultSet.getString("rate");
                    
                    Master master = new Master(id, name, password, rate);
                    masters.add(master);
                }
                
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
        return masters;
    }
    
    @Override
    public Optional<Long> save(Master master) {
        
        String sql = "INSERT INTO "
                + "master(name, password, rate) "
                + "VALUES(?, ?, ?)";
        
        return connection.flatMap(conn -> {
            Optional<Long> generatedId = Optional.empty();
            
            try (PreparedStatement statement =
                         conn.prepareStatement(
                                 sql,
                                 Statement.RETURN_GENERATED_KEYS)) {
                
                statement.setString(1, master.getName());
                statement.setString(2, master.getPassword());
                statement.setString(3, master.getRate());
                
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
    public void update(Master master) {
        
        Long id = master.getId();
        String sql = "UPDATE master SET "
                + "name=?, "
                + "password=? "
                + "rate=? "
                + "WHERE id=" + id;
        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, master.getName());
                statement.setString(2, master.getPassword());
                statement.setString(3, master.getRate());
                statement.executeUpdate();
                
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
    
    @Override
    public void delete(Master master) {
        
        String sql = "DELETE FROM master WHERE id=?";
        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setLong(1, master.getId());
                statement.executeUpdate();
                
                
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        });
    }
}
