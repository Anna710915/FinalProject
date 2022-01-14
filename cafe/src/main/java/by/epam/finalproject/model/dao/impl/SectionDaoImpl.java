package by.epam.finalproject.model.dao.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.dao.AbstractDao;
import by.epam.finalproject.model.dao.SectionDao;
import by.epam.finalproject.model.entity.Section;
import by.epam.finalproject.model.mapper.impl.SectionMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SectionDaoImpl extends AbstractDao<Section> implements SectionDao {
    private static final String SQL_SELECT_ALL_SECTIONS = """
            SELECT section_id, section_name FROM sections""";
    private static final String SQL_INSERT_NEW_SECTION = """
            INSERT INTO sections(section_name) VALUES (?)""";
    private static final String SQL_SELECT_SECTION_BY_NAME = """
            SELECT section_id, section_name FROM sections
            WHERE section_name = (?)""";

    @Override
    public List<Section> findAll() throws DaoException {
        PreparedStatement statement = null;
        List<Section> sectionList = new ArrayList<>();
        try {
            statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_SECTIONS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Optional<Section> optionalSection = new SectionMapper().mapRow(resultSet);
                optionalSection.ifPresent(sectionList::add);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception in a findAll method. ", e);
        } finally {
            close(statement);
        }
        return sectionList;
    }

    @Override
    public Section findEntityById(long id) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Section entity) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Section entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = this.proxyConnection.prepareStatement(SQL_INSERT_NEW_SECTION);
            statement.setString(1, entity.getSectionName());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException("Exception in a create section method. ", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public Section update(Section entity) throws DaoException {
        return null;
    }

    @Override
    public Optional<Section> findSectionByName(String sectionName) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = this.proxyConnection.prepareStatement(SQL_SELECT_SECTION_BY_NAME);
            statement.setString(1, sectionName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return new SectionMapper().mapRow(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception in a findSectionByName method. ", e);
        } finally {
            close(statement);
        }
        return Optional.empty();
    }
}
