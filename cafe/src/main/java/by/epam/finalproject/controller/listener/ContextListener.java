package by.epam.finalproject.controller.listener;

import by.epam.finalproject.model.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * The type Context listener. It closes the connection pool.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
       ConnectionPool.getInstance().destroyPool();
    }
}
