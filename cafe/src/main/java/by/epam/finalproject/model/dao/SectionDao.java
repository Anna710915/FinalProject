package by.epam.finalproject.model.dao;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.Section;

import java.util.Optional;

/**
 * The interface Section dao.
 */
public interface SectionDao {
    /**
     * Find section by name optional.
     *
     * @param sectionName the section name
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Section> findSectionByName(String sectionName) throws DaoException;
}
