package by.epam.finalproject.model.service;

import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Section;

import java.util.List;
import java.util.Optional;

/**
 * The interface Section service.
 */
public interface SectionService {
    /**
     * Find all sections list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Section> findAllSections() throws ServiceException;

    /**
     * Add new section boolean.
     *
     * @param sectionName the section name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addNewSection(String sectionName) throws ServiceException;

    /**
     * Find section by name optional.
     *
     * @param sectionName the section name
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Section> findSectionByName(String sectionName) throws ServiceException;

    /**
     * Update section name optional.
     *
     * @param newSection the new section
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Section> updateSectionName(Section newSection) throws ServiceException;

    /**
     * Delete section by id boolean.
     *
     * @param sectionId the section id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteSectionById(long sectionId) throws ServiceException;

    /**
     * Find all removing sections list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Section> findAllRemovingSections() throws ServiceException;

    /**
     * Restore section by id boolean.
     *
     * @param sectionId the section id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean restoreSectionById(long sectionId) throws ServiceException;
}
