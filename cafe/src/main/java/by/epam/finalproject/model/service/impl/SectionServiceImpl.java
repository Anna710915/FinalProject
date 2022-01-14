package by.epam.finalproject.model.service.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.dao.AbstractDao;
import by.epam.finalproject.model.dao.EntityTransaction;
import by.epam.finalproject.model.dao.impl.SectionDaoImpl;
import by.epam.finalproject.model.entity.Section;
import by.epam.finalproject.model.service.SectionService;

import java.util.List;
import java.util.Optional;

public class SectionServiceImpl implements SectionService {
    private static SectionServiceImpl instance;

    private SectionServiceImpl(){}

    public static SectionServiceImpl getInstance(){
        if(instance == null){
            instance = new SectionServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Section> findAllSections() throws ServiceException {
        AbstractDao<Section> abstractDao = new SectionDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(abstractDao);
        try {
            return abstractDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findAllSections method. ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean addNewSection(String sectionName) throws ServiceException {
        AbstractDao<Section> abstractDao = new SectionDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(abstractDao);
        try {
            return abstractDao.create(new Section(sectionName));
        } catch (DaoException e) {
            throw new ServiceException("Exception in a addNewSection method. ", e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public Optional<Section> findSectionByName(String sectionName) throws ServiceException {
        SectionDaoImpl sectionDao = new SectionDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(sectionDao);
        try {
            return sectionDao.findSectionByName(sectionName);
        } catch (DaoException e) {
            throw new ServiceException("Exception in a findSectionByName method. ", e);
        } finally {
            transaction.end();
        }
    }
}
