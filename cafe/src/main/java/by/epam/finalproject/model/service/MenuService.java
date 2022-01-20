package by.epam.finalproject.model.service;

import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Menu;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Menu service.
 */
public interface MenuService {
    /**
     * Find menu sublist list.
     *
     * @param pageSize the page size
     * @param offset   the offset
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Menu> findMenuSublist(int pageSize, int offset) throws ServiceException;

    /**
     * Find menu sublist by section id list.
     *
     * @param pageSize  the page size
     * @param offset    the offset
     * @param sectionId the section id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Menu> findMenuSublistBySectionId(int pageSize, int offset, long sectionId) throws ServiceException;

    /**
     * Add new product boolean.
     *
     * @param map          the map
     * @param defaultImage the default image
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addNewProduct(Map<String, String> map, String defaultImage) throws ServiceException;

    /**
     * Update product photo boolean.
     *
     * @param image the image
     * @param name  the name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateProductPhoto(String image, String name) throws ServiceException;

    /**
     * Find product by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Menu> findProductById(long id) throws ServiceException;

    /**
     * Delete product by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteProductById(long id) throws ServiceException;

    /**
     * Update product optional.
     *
     * @param id         the id
     * @param updateData the update data
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Menu> updateProduct(long id, Map<String, String> updateData) throws ServiceException;

    /**
     * Delete product from basket boolean.
     *
     * @param map the map
     * @param id  the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteProductFromBasket(Map<Menu, Integer> map, long id) throws ServiceException;

    /**
     * Add product to basket boolean.
     *
     * @param map           the map
     * @param id            the id
     * @param numberProduct the number product
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addProductToBasket(Map<Menu, Integer> map, long id, int numberProduct) throws ServiceException;

    /**
     * Read row count int.
     *
     * @return the int
     * @throws ServiceException the service exception
     */
    int readRowCount() throws ServiceException;

    /**
     * Sort all menu by price list.
     *
     * @param pageSize the page size
     * @param offset   the offset
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Menu> sortAllMenuByPrice(int pageSize, int offset) throws ServiceException;

    /**
     * Sort section menu by price list.
     *
     * @param pageSize  the page size
     * @param offset    the offset
     * @param sectionId the section id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Menu> sortSectionMenuByPrice(int pageSize, int offset, long sectionId) throws ServiceException;


    /**
     * Read row count by section int.
     *
     * @param sectionId the section id
     * @return the int
     * @throws ServiceException the service exception
     */
    int readRowCountBySection(long sectionId) throws ServiceException;

    /**
     * Find all removing menu list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Menu> findAllRemovingMenu() throws ServiceException;

    /**
     * Restore menu product by id boolean.
     *
     * @param menuId the menu id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean restoreMenuProductById(long menuId) throws ServiceException;
}
