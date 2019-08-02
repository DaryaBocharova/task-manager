package ru.bocharova.tm.api.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.Nullable;
import ru.bocharova.tm.model.dto.ProjectDTO;
import ru.bocharova.tm.exception.DataValidateException;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@ApplicationScoped
public interface IProjectService {

    @Transactional
    void create(
            @Nullable final ProjectDTO projectDTO
    ) throws DataValidateException;

    @Transactional
    void edit(
            @Nullable final ProjectDTO projectDTO
    ) throws DataValidateException;

    @Transactional(readOnly = true)
    ProjectDTO findOne(
            @Nullable final String id,
            @Nullable final String userId
    ) throws DataValidateException;

    @Transactional
    void remove(
            @Nullable final String id,
            @Nullable final String userId
    ) throws DataValidateException;

    @Transactional
    void clear(
    ) throws DataValidateException;

    @Transactional(readOnly = true)
    ProjectDTO findOne(
            @Nullable final String id
    ) throws DataValidateException;

    @Transactional
    void remove(
            @Nullable final String id
    ) throws DataValidateException;

    @Transactional(readOnly = true)
    Collection<ProjectDTO> findAll(
    ) throws DataValidateException;

    @Transactional(readOnly = true)
    Collection<ProjectDTO> findAllByUserId(
            @Nullable final String id
    ) throws DataValidateException;

    @Transactional
    void removeAllByUserId(
            @Nullable final String id
    ) throws DataValidateException;

    @Transactional(readOnly = true)
    Collection<ProjectDTO> sortAllByUserId(
            @Nullable final String id,
            @Nullable final String parameter
    ) throws DataValidateException;

    @Transactional(readOnly = true)
    Collection<ProjectDTO> findAllByPartOfNameOrDescription(
            @Nullable final String name,
            @Nullable final String description,
            @Nullable final String userId
    ) throws DataValidateException;
}
