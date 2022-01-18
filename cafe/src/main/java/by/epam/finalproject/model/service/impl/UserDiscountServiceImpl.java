package by.epam.finalproject.model.service.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.dao.AbstractDao;
import by.epam.finalproject.model.dao.EntityTransaction;
import by.epam.finalproject.model.dao.impl.UserDiscountDaoImpl;
import by.epam.finalproject.model.entity.UserDiscount;
import by.epam.finalproject.model.service.UserDiscountService;

import java.util.Optional;


/**
 * The type User discount service.
 */
public class UserDiscountServiceImpl implements UserDiscountService {
    private static final UserDiscountServiceImpl instance = new UserDiscountServiceImpl();

    private UserDiscountServiceImpl(){}

    /**
     * Get instance user discount service.
     *
     * @return the user discount service
     */
    public static UserDiscountServiceImpl getInstance(){
        return instance;
    }
    @Override
    public Optional<UserDiscount> findDiscountById(long id) throws ServiceException {
        AbstractDao<UserDiscount> abstractDao = new UserDiscountDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(abstractDao);
        try {
            return abstractDao.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findDiscountById method", e);
        } finally {
            transaction.end();
        }
    }
}
