package by.epam.finalproject.model.dao;

import by.epam.finalproject.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    static final Logger logger = LogManager.getLogger();
    private Connection connection;

    public void initTransaction(AbstractDao ...daos){
        if(connection == null){
            connection = ConnectionPool.getInstance().getConnection();
        }
        try{
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
        for(AbstractDao daoElement: daos){
            daoElement.setConnection(connection);
        }
    }

    public void endTransaction(){
        if(connection != null){
            try{
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.log(Level.ERROR,e.getMessage());
            }
            ConnectionPool.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    public void commit(){
        try{
            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
    }

    public void rollback(){
        try{
            connection.rollback();
        } catch (SQLException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
    }

    public void init(AbstractDao dao){
        if(connection == null){
            connection = ConnectionPool.getInstance().getConnection();
        }
        dao.setConnection(connection);
    }

    public void end(){
        if(connection != null){
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        connection = null;
    }
}
