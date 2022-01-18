package by.epam.finalproject.model.mapper;

import by.epam.finalproject.exception.DaoException;
import by.epam.finalproject.model.entity.CustomEntity;

import java.sql.ResultSet;
import java.util.Optional;

/**
 * The interface Custom row mapper.
 *
 * @param <T> the type parameter
 */
@FunctionalInterface
public interface CustomRowMapper<T extends CustomEntity> {
    /**
     * Map row optional.
     *
     * @param resultSet the result set
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<T> mapRow(ResultSet resultSet) throws DaoException;
}
