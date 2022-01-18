package by.epam.finalproject.model.dao;

import by.epam.finalproject.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type Entity transaction.
 */
public class EntityTransaction {
    /**
     * The Logger.
     */
    static final Logger logger = LogManager.getLogger();
    private Connection connection;

    /**
     * Init transaction.
     *
     * @param daos the daos
     */
    public void initTransaction(AbstractDao...daos){
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

    /**
     * End transaction.
     */
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

    /**
     * Commit.
     */
    public void commit(){
        try{
            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
    }

    /**
     * Rollback.
     */
    public void rollback(){
        try{
            connection.rollback();
        } catch (SQLException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
    }

    /**
     * Init transaction. Set connection.
     *
     * @param dao the dao
     */
    public void init(AbstractDao dao){
        if(connection == null){
            connection = ConnectionPool.getInstance().getConnection();
        }
        dao.setConnection(connection);
    }

    /**
     * End transaction. Release connection.
     */
    public void end(){
        if(connection != null){
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        connection = null;
    }
}
