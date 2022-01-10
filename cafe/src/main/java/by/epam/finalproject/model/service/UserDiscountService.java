package by.epam.finalproject.model.service;

import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.UserDiscount;

import java.math.BigDecimal;

public interface UserDiscountService {
    UserDiscount findDiscountById(long id) throws ServiceException;
}
