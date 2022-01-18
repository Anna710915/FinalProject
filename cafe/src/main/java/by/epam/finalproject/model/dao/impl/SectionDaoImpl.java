package by.epam.finalproject.model.dao.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.dao.AbstractDao;
import by.epam.finalproject.model.dao.SectionDao;
import by.epam.finalproject.model.entity.Section;
import by.epam.finalproject.model.mapper.impl.SectionMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Section dao.
 */
public class SectionDaoImpl extends AbstractDao<Section> implements SectionDao {
    private static final Logger logger = LogManager.getLogger();
    private static final int ONE_UPDATE = 1;
    private static final String SQL_SELECT_ALL_SECTIONS = """
            SELECT section_id, section_name FROM sections""";
    private static final String SQL_INSERT_NEW_SECTION = """
            INSERT INTO sections(section_name) VALUES (?)""";
    private static final String SQL_SELECT_SECTION_BY_NAME = """
            SELECT section_id, section_name FROM sections
            WHERE section_name = (?)""";
    private static final String SQL_SELECT_SECTION_BY_ID = """
            SELECT section_id, section_name FROM sections
            WHERE section_id = (?)""";
    private static final String SQL_UPDATE_SECTION_NAME = """
            UPDATE sections
            SET section_name = (?)
            WHERE section_id = (?)""";
    private static final String SQL_DELETE_SECTION_BY_ID = """
            DELETE FROM sections
            WHERE section_id = (?)""";

    @Override
    public List<Section> findAll() throws DaoException {
        List<Section> sectionList = new ArrayList<>();
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_ALL_SECTIONS)){
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Section> optionalSection = new SectionMapper().mapRow(resultSet);
                    optionalSection.ifPresent(sectionList::add);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find all sections ");
            throw new DaoException("Exception in a findAll method. ", e);
        }
        return sectionList;
    }

    @Override
    public Optional<Section> findEntityById(long id) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_SECTION_BY_ID)){
            statement.setLong(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new SectionMapper().mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find section by id method ");
            throw new DaoException("Exception in a findEntityById section method. ", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_DELETE_SECTION_BY_ID)){
            statement.setLong(1, id);
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while delete section method ");
            throw new DaoException("Exception in a delete section method. ", e);
        }
    }


    @Override
    public boolean create(Section entity) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_INSERT_NEW_SECTION)){
            statement.setString(1, entity.getSectionName());
            return statement.executeUpdate() == ONE_UPDATE;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while create new section  ");
            throw new DaoException("Exception in a create section method. ", e);
        }
    }

    @Override
    public Optional<Section> update(Section entity) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_UPDATE_SECTION_NAME)){
            Optional<Section> section = findEntityById(entity.getSectionId());
            statement.setString(1, entity.getSectionName());
            statement.setLong(2, entity.getSectionId());
            return statement.executeUpdate() == ONE_UPDATE ? section : Optional.empty();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while update section ");
            throw new DaoException("Exception in a update section method. ", e);
        }
    }

    @Override
    public Optional<Section> findSectionByName(String sectionName) throws DaoException {
        try(PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_SELECT_SECTION_BY_NAME)){
            statement.setString(1, sectionName);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new SectionMapper().mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Exception while find section by name ");
            throw new DaoException("Exception in a findSectionByName method. ", e);
        }
        return Optional.empty();
    }
}
