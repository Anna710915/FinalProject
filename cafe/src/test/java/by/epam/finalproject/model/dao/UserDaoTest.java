package by.epam.finalproject.model.dao;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.dao.impl.UserDaoImpl;
import by.epam.finalproject.model.entity.User;
import by.epam.finalproject.model.pool.ConnectionPool;
import by.epam.finalproject.util.PasswordEncryption;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class UserDaoTest {
    UserDaoImpl userDao;
    EntityTransaction entityTransaction;
    @BeforeClass
    public void init(){
        userDao = new UserDaoImpl();
        entityTransaction = new EntityTransaction();
        entityTransaction.init(userDao);
    }

    @Test
    public void findUserByLoginAndPasswordTest() throws DaoException {
        String encrypt = PasswordEncryption.md5Apache("16fduecn2002");
        Optional<User> actual = userDao.findUserByLoginAndPassword("2Merkul8",encrypt);
        assertTrue(actual.isPresent());
    }
    @Test
    public void findUserByIdTest() throws DaoException {
        User actual = userDao.findEntityById(1);
        assertNotNull(actual);
    }
    @Test
    public void findAllUsersTest() throws DaoException {
        List<User> actual = userDao.findAll();
        assertFalse(actual.isEmpty());
    }
    @AfterClass
    public void close(){
        entityTransaction.end();
        ConnectionPool.getInstance().destroyPool();
        userDao = null;
        entityTransaction = null;
    }
}
