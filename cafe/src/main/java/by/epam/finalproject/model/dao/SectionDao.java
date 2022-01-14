package by.epam.finalproject.model.dao;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.Section;

import java.util.Optional;

public interface SectionDao {
    Optional<Section> findSectionByName(String sectionName) throws DaoException;
}
