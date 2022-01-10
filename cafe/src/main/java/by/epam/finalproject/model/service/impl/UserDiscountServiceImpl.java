package by.epam.finalproject.model.service.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.dao.AbstractDao;
import by.epam.finalproject.model.dao.EntityTransaction;
import by.epam.finalproject.model.dao.impl.UserDiscountDao;
import by.epam.finalproject.model.entity.UserDiscount;
import by.epam.finalproject.model.service.UserDiscountService;


public class UserDiscountServiceImpl implements UserDiscountService {
    private static UserDiscountServiceImpl instance;

    private UserDiscountServiceImpl(){}

    public static UserDiscountServiceImpl getInstance(){
        if(instance == null){
            instance = new UserDiscountServiceImpl();
        }
        return instance;
    }
    @Override
    public UserDiscount findDiscountById(long id) throws ServiceException {
        AbstractDao<UserDiscount> abstractDao = new UserDiscountDao();
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
