package by.epam.finalproject.model.mapper.impl;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.Section;
import by.epam.finalproject.model.mapper.CustomRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * The type SectionMapper. Extract sections rows form ResultSet.
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

    /**
     * The constant IS_ACCESSIBLE_SECTION.
     */
    public static final String IS_ACCESSIBLE_SECTION = "is_accessible";

    @Override
    public Optional<Section> mapRow(ResultSet resultSet) throws DaoException {
        Section section = new Section();
        Optional<Section> optionalSection;
        try {
            section.setSectionId(resultSet.getLong(SECTION));
            section.setSectionName(resultSet.getString(SECTION_NAME));
            section.setAccessible(resultSet.getBoolean(IS_ACCESSIBLE_SECTION));
            optionalSection = Optional.of(section);
        } catch (SQLException e) {
            optionalSection = Optional.empty();
        }
        return optionalSection;
    }
}
