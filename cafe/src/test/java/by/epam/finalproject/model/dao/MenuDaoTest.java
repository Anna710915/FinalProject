package by.epam.finalproject.model.dao;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.dao.impl.MenuDaoImpl;
import static org.testng.Assert.*;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.pool.ConnectionPool;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class MenuDaoTest {
    MenuDaoImpl menuDao;
    EntityTransaction entityTransaction;
    @BeforeClass
    public void init(){
        menuDao = new MenuDaoImpl();
        entityTransaction = new EntityTransaction();
        entityTransaction.init(menuDao);
    }
    @Test
    public void findAllMenuTest() throws DaoException {
        List<Menu> menuList = menuDao.findAll();
        assertNotNull(menuList);
    }
    @Test
    public void findAllMenuFirstTest() throws DaoException {
        List<Menu> menuList = menuDao.findAll();
        int actual = menuList.size();
        int expected = 1;
        assertEquals(actual, expected);
    }
    @AfterClass
    public void close(){
        entityTransaction.end();
        ConnectionPool.getInstance().destroyPool();
        menuDao = null;
        entityTransaction = null;
    }
}
