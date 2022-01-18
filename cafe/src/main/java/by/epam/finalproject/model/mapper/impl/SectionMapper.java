package by.epam.finalproject.model.mapper.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.Section;
import by.epam.finalproject.model.mapper.CustomRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * The type Section mapper.
 */
public class SectionMapper implements CustomRowMapper<Section> {
    /**
     * The constant SECTION.
     */
    public static final String SECTION = "section_id";
    /**
     * The constant SECTION_NAME.
     */
    public static final String SECTION_NAME = "section_name";

    @Override
    public Optional<Section> mapRow(ResultSet resultSet) throws DaoException {
        Section section = new Section();
        Optional<Section> optionalSection;
        try {
            section.setSectionId(resultSet.getLong(SECTION));
            section.setSectionName(resultSet.getString(SECTION_NAME));
            optionalSection = Optional.of(section);
        } catch (SQLException e) {
            optionalSection = Optional.empty();
        }
        return optionalSection;
    }
}
