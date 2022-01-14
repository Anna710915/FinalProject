package by.epam.finalproject.model.service;

import by.epam.finalproject.exception.ServiceException;
import by.epam.finalproject.model.entity.Section;

import java.util.List;
import java.util.Optional;

public interface SectionService {
    List<Section> findAllSections() throws ServiceException;
    boolean addNewSection(String sectionName) throws ServiceException;
    Optional<Section> findSectionByName(String sectionName) throws ServiceException;
}
