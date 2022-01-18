package by.epam.finalproject.model.dao;

import by.epam.finalproject.model.dao.impl.UserDiscountDaoImpl;
import by.epam.finalproject.model.entity.UserDiscount;
import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.pool.ConnectionPool;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.testng.Assert.*;
public class UserDiscountDaoImplTest {
    AbstractDao<UserDiscount> abstractDao;
    EntityTransaction entityTransaction;
    @BeforeClass
    public void init(){
        abstractDao = new UserDiscountDaoImpl();
        entityTransaction = new EntityTransaction();
        entityTransaction.init(abstractDao);
    }

    @Test
    public void findAllTest() throws DaoException {
        List<UserDiscount> actual = abstractDao.findAll();
        assertNotNull(actual);
    }

    @Test
    public void createTest() throws DaoException {
        boolean actual = abstractDao.create(new UserDiscount(3, new BigDecimal(0.05), 6));
        assertTrue(actual);
    }


    @Test
    public void deleteByIdTest() throws DaoException {
        boolean actual = abstractDao.delete(3);
        assertTrue(actual);
    }

    @AfterClass
    public void close(){
        entityTransaction.end();
        ConnectionPool.getInstance().destroyPool();
        abstractDao = null;
        entityTransaction = null;
    }
}
