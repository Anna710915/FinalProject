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

public class MenuServiceImpl implements MenuService {
    private static final Logger logger = LogManager.getLogger();
    private static MenuServiceImpl instance;

    private MenuServiceImpl(){}

    public static MenuServiceImpl getInstance(){
        if(instance == null){
            instance = new MenuServiceImpl();
        }
        return instance;
    }
    @Override
    public List<Menu> findAllMenu() throws ServiceException {
        AbstractDao<Menu> menuAbstractDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(menuAbstractDao);
        try {
            List<Menu> menuList = menuAbstractDao.findAll();
            return menuList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }finally {
            transaction.end();
        }
    }

    @Override
    public boolean addNewProduct(Map<String,String> map, String defaultImage) throws ServiceException {
        Validator validator = ValidatorImpl.getInstance();
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
            LocalTime time = LocalTime.parse(map.get(PRODUCT_TIME), DateTimeFormatter.ofPattern("HH:MM")); // TODO
            BigDecimal discount = BigDecimal.valueOf(Double.parseDouble(map.get(PRODUCT_DISCOUNT)));
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(map.get(PRODUCT_PRICE)));
            long sectionId = Long.parseLong(map.get(PRODUCT_SECTION));
            Menu menu = new Menu(name, defaultImage, composition, weight, calories, time, discount, price, sectionId);
            boolean result = menuDao.create(menu);
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
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
            throw new ServiceException(e);
        } finally {
            entityTransaction.end();
        }
    }

    @Override
    public Menu findProductById(long id) throws ServiceException{
        AbstractDao<Menu> abstractDao = new MenuDaoImpl();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.init(abstractDao);
        try {
            return abstractDao.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findProductById method", e);
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
            throw new ServiceException("Exception in a deleteProductById ", e);
        } finally {
            entityTransaction.end();
        }
    }

    @Override
    public Optional<Menu> updateProduct(long id, Map<String, String> updateData, String defaultImage) throws ServiceException {
        Validator validator = ValidatorImpl.getInstance();
        if(!validator.checkProductData(updateData)){
            return Optional.empty();
        }
        MenuDaoImpl menuDao = new MenuDaoImpl();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.init(menuDao);
        try {
            String name = updateData.get(PRODUCT_NAME);
            if(menuDao.findFoodByName(name).isPresent()){
                Menu findMenu = menuDao.findFoodByName(name).get();
                if(!findMenu.getNameFood().equals(menuDao.findEntityById(id).getNameFood())){
                    updateData.put(PRODUCT_NAME, NOT_UNIQ_PRODUCT_NAME);
                    return Optional.empty();
                }
            }
            String composition = updateData.get(PRODUCT_COMPOSITION);
            double weight = Double.parseDouble(updateData.get(PRODUCT_WEIGHT));
            double calories = Double.parseDouble(updateData.get(PRODUCT_CALORIES));
            LocalTime time = LocalTime.parse(updateData.get(PRODUCT_TIME), DateTimeFormatter.ofPattern("HH:MM"));
            BigDecimal discount = BigDecimal.valueOf(Double.parseDouble(updateData.get(PRODUCT_DISCOUNT)));
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(updateData.get(PRODUCT_PRICE)));
            long sectionId = Long.parseLong(updateData.get(PRODUCT_SECTION));
            Menu newMenu = new Menu(id, name, defaultImage, composition, weight, calories, time, discount, price, sectionId);
            Optional<Menu> optionalMenu = Optional.of(menuDao.update(newMenu));
            return optionalMenu;
        } catch (DaoException e) {
            throw new ServiceException(e);
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
            Menu menu = abstractDao.findEntityById(id);
            return map.remove(menu) != null;
        } catch (DaoException e) {
            throw new ServiceException("Exception in a deleteProductFromBasket method ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean addProductToBasket(Map<Menu, Integer> map, long id, int numberProduct) throws ServiceException{
        AbstractDao<Menu> abstractDao = new MenuDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(abstractDao);
        try {
            Menu menu = abstractDao.findEntityById(id);
            if(menu != null && map.containsKey(menu)){
                int value = map.get(menu) + numberProduct;
                map.put(menu, value);
                return true;
            }
            if(menu != null){
                map.put(menu, numberProduct);
                return true;
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception in a addProductToBasket method ", e);
        } finally {
            transaction.end();
        }
        return false;
    }
}
