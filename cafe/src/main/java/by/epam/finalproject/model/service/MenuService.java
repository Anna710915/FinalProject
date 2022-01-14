package by.epam.finalproject.model.service;

import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MenuService {
    List<Menu> findMenuSublist(int pageSize, int offset) throws ServiceException;
    List<Menu> findMenuSublistBySectionId(int pageSize, int offset, long sectionId) throws ServiceException;
    boolean addNewProduct(Map<String, String> map, String defaultImage) throws ServiceException;
    boolean updateProductPhoto(String image, String name) throws ServiceException;
    Menu findProductById(long id) throws ServiceException;
    boolean deleteProductById(long id) throws ServiceException;
    Optional<Menu> updateProduct(long id, Map<String, String> updateData, String defaultImage) throws ServiceException;
    boolean deleteProductFromBasket(Map<Menu, Integer> map, long id) throws ServiceException;
    boolean addProductToBasket(Map<Menu, Integer> map, long id, int numberProduct) throws ServiceException;
    int readRowCount() throws ServiceException;
}
