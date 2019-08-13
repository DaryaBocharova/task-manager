package ru.bocharova.tm.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.bocharova.tm.enumerate.Role;

import java.io.Serializable;

@Getter
@Setter
public class User extends AbstractEntity implements Serializable {

    @Nullable
    private String login = "";

    @Nullable
    private String password = "";

    @Nullable
    private Role role = Role.USER;

    public User(
            @Nullable final String login,
            @Nullable final String password,
            String parameter, String requestParameter, @Nullable final Role role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.description = description;
        this.role = role;
    }
}
