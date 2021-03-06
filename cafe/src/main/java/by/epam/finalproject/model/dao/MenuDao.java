package by.epam.finalproject.model.dao;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.Menu;

import java.util.List;
import java.util.Optional;

/**
 * The interface Menu dao.
 */
public interface MenuDao {
    /**
     * Update image path by name boolean.
     *
     * @param name     the name
     * @param filePath the file path
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateImagePathByName(String name, String filePath) throws DaoException;

    /**
     * Find food by name optional.
     *
     * @param name the name
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Menu> findFoodByName(String name) throws DaoException;

    /**
     * Find menu sublist by section id list.
     *
     * @param pageSize  the page size
     * @param offset    the offset
     * @param sectionId the section id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Menu> findMenuSublistBySectionId(int pageSize, int offset, long sectionId) throws DaoException;

    /**
     * Find menu sublist list.
     *
     * @param pageSize the page size
     * @param offset   the offset
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Menu> findMenuSublist(int pageSize, int offset) throws DaoException;

    /**
     * Read row count int.
     *
     * @return the int
     * @throws DaoException the dao exception
     */
    int readRowCount() throws DaoException;

    /**
     * Find all sorted menu list by price.
     *
     * @param pageSize the page size
     * @param offset   the offset
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Menu> findAllSortedMenuByPrice(int pageSize, int offset) throws DaoException;

    /**
     * Find sorted section menu list.
     *
     * @param pageSize  the page size
     * @param offset    the offset
     * @param sectionId the section id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Menu> findSortedSectionMenuByPrice(int pageSize, int offset, long sectionId) throws DaoException;

    /**
     * Find all sorted menu by popularity list.
     *
     * @param pageSize the page size
     * @param offset   the offset
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Menu> findAllSortedMenuByPopularity(int pageSize, int offset) throws DaoException;

    /**
     * Find all sorted section menu by popularity list.
     *
     * @param pageSize  the page size
     * @param offset    the offset
     * @param sectionId the section id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Menu> findAllSortedSectionMenuByPopularity(int pageSize, int offset, long sectionId) throws DaoException;
    /**
     * Read row count by section int.
     *
     * @param sectionId the section id
     * @return the int
     * @throws DaoException the dao exception
     */
    int readRowCountBySection(long sectionId) throws DaoException;

    /**
     * Delete menu by section id boolean.
     *
     * @param sectionId the section id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteMenuBySectionId(long sectionId) throws DaoException;

    /**
     * Restore menu by id boolean.
     *
     * @param menuId the menu id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean restoreMenuById(long menuId) throws DaoException;

    /**
     * Find all removing menu list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Menu> findAllRemovingMenu() throws DaoException;

    /**
     * Restore all menu by section id boolean.
     *
     * @param sectionId the section id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean restoreAllMenuBySectionId(long sectionId) throws DaoException;
}
