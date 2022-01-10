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

public abstract class AbstractDao <T extends CustomEntity> {
    private static final Logger logger = LogManager.getLogger();
    protected ProxyConnection proxyConnection;

    public abstract List<T> findAll() throws DaoException;
    public abstract T findEntityById(long id) throws DaoException;
    public abstract boolean delete(long id) throws DaoException;
    public abstract boolean delete(T entity) throws DaoException;
    public abstract boolean create(T entity) throws DaoException;
    public abstract T update(T entity) throws DaoException;

    public void close(Statement statement){
        try{
            if(statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,"Statement was not released. " + e.getMessage());
        }
    }
    protected void setConnection(Connection connection){
        this.proxyConnection = (ProxyConnection) connection;
    }
}
