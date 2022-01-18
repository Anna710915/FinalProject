package by.epam.finalproject.model.service;

import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.UserDiscount;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * The interface User discount service.
 */
public interface UserDiscountService {
    /**
     * Find discount by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<UserDiscount> findDiscountById(long id) throws ServiceException;
}
