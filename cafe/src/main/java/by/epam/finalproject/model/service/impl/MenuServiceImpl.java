package by.epam.finalproject.model.service.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.dao.AbstractDao;
import by.epam.finalproject.model.dao.EntityTransaction;
import by.epam.finalproject.model.dao.impl.MenuDaoImpl;
import by.epam.finalproject.model.entity.Menu;
import by.epam.finalproject.model.service.MenuService;
import by.epam.finalproject.validator.Validator;
import by.epam.finalproject.validator.impl.ValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.finalproject.controller.Parameter.*;
import static by.epam.finalproject.controller.Parameter.PRODUCT_PRICE;

/**
 * The type MenuService class. This class contains business logic
 * for menu products.
 */
public class MenuServiceImpl implements MenuService {
    private static final Logger logger = LogManager.getLogger();
    private static final String TIME_PATTERN = "HH:mm";
    private static final MenuServiceImpl instance = new MenuServiceImpl();
    private final Validator validator = ValidatorImpl.getInstance();

    private MenuServiceImpl(){}

    /**
     * Get instance menu service.
     *
     * @return the menu service
     */
    public static MenuServiceImpl getInstance(){
        return instance;
    }

    @Override
    public List<Menu> findMenuSublist(int pageSize, int offset) throws ServiceException {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(menuDao);
        logger.log(Level.INFO, "Parameter: " + pageSize + " " + offset);
        try {
            return menuDao.findMenuSublist(pageSize, offset);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findMenuSublist method. ", e);
        }finally {
            transaction.end();
        }
    }

    @Override
    public boolean addNewProduct(Map<String,String> map, String defaultImage) throws ServiceException {
        if(!validator.checkProductData(map)){
            return false;
        }
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.init(menuDao);
        try {
            String name = map.get(PRODUCT_NAME);
            if(menuDao.findFoodByName(name).isPresent()){
                map.put(PRODUCT_NAME, NOT_UNIQ_PRODUCT_NAME);
                return false;
            }
            String composition = map.get(PRODUCT_COMPOSITION);
            double weight = Double.parseDouble(map.get(PRODUCT_WEIGHT));
            double calories = Double.parseDouble(map.get(PRODUCT_CALORIES));
            LocalTime time = LocalTime.parse(map.get(PRODUCT_TIME), DateTimeFormatter.ofPattern(TIME_PATTERN));
            BigDecimal discount = BigDecimal.valueOf(Double.parseDouble(map.get(PRODUCT_DISCOUNT)));
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(map.get(PRODUCT_PRICE)));
            long sectionId = Long.parseLong(map.get(PRODUCT_SECTION));
            Menu menu = new Menu(name, defaultImage, composition, weight, calories, time, discount, price, sectionId, true);
            return menuDao.create(menu);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a addNewProduct service method ", e);
        }finally {
            entityTransaction.end();
        }
    }

    @Override
    public boolean updateProductPhoto(String image, String name) throws ServiceException{
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.init(menuDao);
        try {
            return menuDao.updateImagePathByName(name, image);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a updateProductPhoto service method ", e);
        } finally {
            entityTransaction.end();
        }
    }

    @Override
    public Optional<Menu> findProductById(long id) throws ServiceException{
        AbstractDao<Menu> abstractDao = new MenuDaoImpl();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.init(abstractDao);
        try {
            return abstractDao.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findProductById service method", e);
        } finally {
            entityTransaction.end();
        }
    }

    @Override
    public boolean deleteProductById(long id) throws ServiceException{
        AbstractDao<Menu> abstractDao = new MenuDaoImpl();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.init(abstractDao);
        try{
            return abstractDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a deleteProductById service method ", e);
        } finally {
            entityTransaction.end();
        }
    }

    @Override
    public Optional<Menu> updateProduct(long id, Map<String, String> updateData) throws ServiceException {
        if(!validator.checkProductData(updateData)){
            return Optional.empty();
        }
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.init(menuDao);
        try {
            String name = updateData.get(PRODUCT_NAME);
            logger.log(Level.INFO, "name = " + name);
            if(menuDao.findFoodByName(name).isPresent() && menuDao.findEntityById(id).isPresent()){
                Menu findMenu = menuDao.findFoodByName(name).get();
                if(!findMenu.getNameFood().equals(menuDao.findEntityById(id).get().getNameFood())){
                    updateData.put(PRODUCT_NAME, NOT_UNIQ_PRODUCT_NAME);
                    return Optional.empty();
                }
            }
            String composition = updateData.get(PRODUCT_COMPOSITION);
            double weight = Double.parseDouble(updateData.get(PRODUCT_WEIGHT));
            double calories = Double.parseDouble(updateData.get(PRODUCT_CALORIES));
            BigDecimal discount = BigDecimal.valueOf(Double.parseDouble(updateData.get(PRODUCT_DISCOUNT)));
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(updateData.get(PRODUCT_PRICE)));
            LocalTime time = LocalTime.parse(updateData.get(PRODUCT_TIME), DateTimeFormatter.ofPattern(TIME_PATTERN));
            long sectionId = Long.parseLong(updateData.get(PRODUCT_SECTION));
            Menu newMenu = new Menu(id, name, composition, weight, calories, time, discount, price, sectionId, true);
            return menuDao.update(newMenu);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a updateProduct service method ", e);
        }finally {
            entityTransaction.end();
        }
    }

    @Override
    public boolean deleteProductFromBasket(Map<Menu, Integer> map, long id) throws ServiceException{
        AbstractDao<Menu> abstractDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(abstractDao);
        try {
            Optional<Menu> menu = abstractDao.findEntityById(id);
            if(menu.isPresent()){
                return map.remove(menu.get()) != null;
            }

        } catch (DaoException e) {
            throw new ServiceException("Exception in a deleteProductFromBasket service method ", e);
        } finally {
            transaction.end();
        }
        return false;
    }

    @Override
    public boolean addProductToBasket(Map<Menu, Integer> map, long id, int numberProduct) throws ServiceException{
        AbstractDao<Menu> abstractDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(abstractDao);
        try {
            Optional<Menu> menu = abstractDao.findEntityById(id);
            if(menu.isPresent() && map.containsKey(menu.get())){
                int value = map.get(menu.get()) + numberProduct;
                map.put(menu.get(), value);
                return true;
            }
            if(menu.isPresent()){
                map.put(menu.get(), numberProduct);
                return true;
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception in a addProductToBasket service method ", e);
        } finally {
            transaction.end();
        }
        return false;
    }

    @Override
    public int readRowCount() throws ServiceException {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(menuDao);
        try {
            return menuDao.readRowCount();
        } catch (DaoException e) {
            throw new ServiceException("Exception in a readRowCount service method. ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Menu> findMenuSublistBySectionId(int pageSize, int offset, long sectionId) throws ServiceException {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(menuDao);
        logger.log(Level.INFO, "Parameter: " + pageSize + " " + offset);
        try {
            return menuDao.findMenuSublistBySectionId(pageSize, offset, sectionId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findMenuSublist service method. ", e);
        }finally {
            transaction.end();
        }
    }

    @Override
    public List<Menu> sortAllMenuByPrice(int pageSize, int offset) throws ServiceException {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(menuDao);
        try {
            logger.log(Level.INFO, "page size = " + pageSize + " offset = " + offset);
            return menuDao.findAllSortedMenuByPrice(pageSize, offset);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a sortAllMenuByPrice service method. ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Menu> sortSectionMenuByPrice(int pageSize, int offset, long sectionId) throws ServiceException {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(menuDao);
        try {
            return menuDao.findSortedSectionMenuByPrice(pageSize, offset, sectionId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a sortSectionMenuByPrice service method. ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public int readRowCountBySection(long sectionId) throws ServiceException {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(menuDao);
        try {
            return menuDao.readRowCountBySection(sectionId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a readRowCountBySection service method. ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Menu> findAllRemovingMenu() throws ServiceException {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(menuDao);
        try {
            return menuDao.findAllRemovingMenu();
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findAllRemovingMenu service method ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean restoreMenuProductById(long menuId) throws ServiceException {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(menuDao);
        try {
            return menuDao.restoreMenuById(menuId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a restoreMenuProductById method ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Menu> findSortedMenuSubListByPopularity(int pageSize, int offset) throws ServiceException {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(menuDao);
        try {
            return menuDao.findAllSortedMenuByPopularity(pageSize, offset);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findSortedMenuSubListByPopularity service method", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public List<Menu> findSortedMenuSectionSubListByPopularity(int pageSize, int offset, long sectionId) throws ServiceException {
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(menuDao);
        try {
            return menuDao.findAllSortedSectionMenuByPopularity(pageSize, offset, sectionId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findSortedMenuSectionSubListByPopularity service method", e);
        }finally {
            transaction.end();
        }
    }
}
