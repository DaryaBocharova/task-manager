package ru.bocharova.tm.api.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.Nullable;
import ru.bocharova.tm.model.dto.SessionDTO;
import ru.bocharova.tm.exception.AuthenticationSecurityException;
import ru.bocharova.tm.exception.DataValidateException;
import ru.bocharova.tm.model.dto.UserDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@ApplicationScoped
public interface ISessionService {

    @Transactional
    void clear(

    ) throws DataValidateException;

    @Transactional(readOnly = true)
    SessionDTO findOne(
            @Nullable final String id
    ) throws DataValidateException;

    @Transactional
    void remove(
            @Nullable final String id
    ) throws DataValidateException;

    @Transactional(readOnly = true)
    Collection<SessionDTO> findAll(
    ) throws DataValidateException;

    @Transactional
    SessionDTO create(
            @Nullable final UserDTO userDTO
    ) throws DataValidateException;

    @Transactional(readOnly = true)
    void validate(
            @Nullable final SessionDTO sessionDTO
    ) throws AuthenticationSecurityException, DataValidateException;

    @Transactional(readOnly = true)
    void validateAdmin(
            @Nullable final SessionDTO sessionDTO
    ) throws AuthenticationSecurityException, DataValidateException;
}
