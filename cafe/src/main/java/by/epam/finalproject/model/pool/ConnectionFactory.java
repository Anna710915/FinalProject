package by.epam.finalproject.model.pool;

import by.epam.finalproject.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Connection factory. Connection parameters keep
 * in a property file named database.properties.
 * The ConnectionFactory class uses this property file
 * which contains, as usual, such parameters as a login,
 * password access and others.
 */
class ConnectionFactory {
    private static final Logger logger = LogManager.getLogger();
    private static final String FILE_NAME = "sqldata/database.properties";
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;
    private static String fileProperties;

    static {
        try {
            ClassLoader loader = ConnectionFactory.class.getClassLoader();
            URL resource = loader.getResource(FILE_NAME);
            if(resource == null) {
                logger.log(Level.ERROR,"Resource is null! " + FILE_NAME);
                throw new IllegalArgumentException();
            }
            fileProperties = resource.getFile();
            properties.load(new FileReader(fileProperties));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        }catch (ClassNotFoundException | IOException e){
            logger.log(Level.FATAL,"File properties exception: " + fileProperties);
            throw new RuntimeException("File properties exception." + e.getMessage());
        }
        DATABASE_URL = (String) properties.get("db.url");
    }
    private  ConnectionFactory(){}

    /**
     * Create connection method Get connection using DriverManager class.
     *
     * @return the connection
     * @throws ConnectionPoolException the connection pool exception
     */
    static Connection createConnection() throws ConnectionPoolException {
        try {
            return DriverManager.getConnection(DATABASE_URL, properties);
        }catch (SQLException e){
            throw new ConnectionPoolException("Connection is not received: " + e.getMessage());
        }
    }
}
