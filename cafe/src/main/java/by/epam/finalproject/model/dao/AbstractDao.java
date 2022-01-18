package by.epam.finalproject.model.dao;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.CustomEntity;
import by.epam.finalproject.model.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * The type Abstract dao.
 *
 * @param <T> the type parameter
 */
public abstract class AbstractDao <T extends CustomEntity> {
    private static final Logger logger = LogManager.getLogger();
    /**
     * The Proxy connection.
     */
    protected ProxyConnection proxyConnection;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    public abstract List<T> findAll() throws DaoException;

    /**
     * Find entity by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    public abstract Optional<T> findEntityById(long id) throws DaoException;

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean delete(long id) throws DaoException;

    /**
     * Create boolean.
     *
     * @param entity the entity
     * @return the boolean
     * @throws DaoException the dao exception
     */
    public abstract boolean create(T entity) throws DaoException;

    /**
     * Update optional.
     *
     * @param entity the entity
     * @return the optional
     * @throws DaoException the dao exception
     */
    public abstract Optional<T> update(T entity) throws DaoException;

    /**
     * Close.
     *
     * @param statement the statement
     */
    public void close(Statement statement){
        try{
            if(statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,"Statement was not released. " + e.getMessage());
        }
    }

    /**
     * Set connection.
     *
     * @param connection the connection
     */
    protected void setConnection(Connection connection){
        this.proxyConnection = (ProxyConnection) connection;
    }
}
