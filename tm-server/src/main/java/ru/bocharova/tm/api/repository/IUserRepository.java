package ru.bocharova.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.bocharova.tm.model.entity.User;

import java.util.Collection;

public interface IUserRepository {

    User findOne(@NotNull final String id);

    Collection<User> findAll();

    void removeAll();

    void remove(@NotNull final User user);

    void persist(@NotNull final User user);

    User merge(@NotNull final User user);

    User findByLogin(@NotNull final String login);
}


