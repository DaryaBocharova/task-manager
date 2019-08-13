package ru.bocharova.tm.util;


import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bocharova.tm.enumerate.Role;
import ru.bocharova.tm.enumerate.Status;
import ru.bocharova.tm.exception.DataValidateException;
import ru.bocharova.tm.model.entity.Project;
import ru.bocharova.tm.model.entity.Task;
import ru.bocharova.tm.model.entity.User;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class DataValidator {
    public static boolean validateString(
            @Nullable final String... strings)
            throws DataValidateException {
        for (String string : strings) {
            if (string == null || string.isEmpty())
                throw new DataValidateException("Input parameter not must be null or empty!");
        }
        return false;
    }

    public static void validateRole(
            @Nullable final Role role)
            throws DataValidateException {
        if (role == null) throw new DataValidateException("Incorrect input role!");
    }

    public static void validateStatus(
            @Nullable final Status status)
            throws DataValidateException {
        if (status == null) throw new DataValidateException("Incorrect input status!");
    }

    public static void validateParameter(
            @Nullable final String parameter)
            throws DataValidateException {
        Map<String, String> parameters = new HashMap<String, String>(4);
        parameters.put("order", "order");
        parameters.put("dateBegin", "dateBegin");
        parameters.put("dateEnd", "dateEnd");
        parameters.put("status", "status");
        if (parameters.get(parameter) == null) throw new DataValidateException("Incorrect input parameter!");
    }

    public static void validateProject(
            @Nullable final Project project)
            throws DataValidateException {
        if (project == null)
            throw new DataValidateException("Incorrect project dto!");
        if (project.getId() == null || project.getId().isEmpty())
            throw new DataValidateException("Id project not must be empty!");
        if (project.getUserId() == null || project.getUserId().isEmpty())
            throw new DataValidateException("User id not must be empty!");
        if (project.getName() == null || project.getName().isEmpty())
            throw new DataValidateException("Project name not must be empty!");
        if (project.getDescription() == null || project.getDescription().isEmpty())
            throw new DataValidateException("Project description not must be empty!");
        if (project.getDateBegin() == null)
            throw new DataValidateException("Project date begin not must be empty!");
        validateStatus(project.getStatus());
    }

    public static void validateTask(
            @Nullable final Task task)
            throws DataValidateException {
        if (task == null)
            throw new DataValidateException("Incorrect task dto!");
        if (task.getId() == null || task.getId().isEmpty())
            throw new DataValidateException("Id task not must be empty!");
        if (task.getUserId() == null || task.getUserId().isEmpty())
            throw new DataValidateException("User id not must be empty!");
        if (task.getProjectId() == null || task.getProjectId().isEmpty())
            throw new DataValidateException("Project id not must be empty!");
        if (task.getName() == null || task.getName().isEmpty())
            throw new DataValidateException("Task name not must be empty!");
        if (task.getDescription() == null || task.getDescription().isEmpty())
            throw new DataValidateException("Task description not must be empty!");
        if (task.getDateBegin() == null)
            throw new DataValidateException("Task date begin not must be empty!");
        validateStatus(task.getStatus());
    }

    public static void validateUser(
            @Nullable final User user,
            @NotNull final boolean validatePassword)
            throws DataValidateException {
        if (user == null)
            throw new DataValidateException("Incorrect user dto!");
        if (user.getId() == null || user.getId().isEmpty())
            throw new DataValidateException("Id user not must be empty!");
        if (user.getLogin() == null || user.getLogin().isEmpty())
            throw new DataValidateException("User login not must be empty!");
        if (validatePassword)
            if (user.getPassword() == null || user.getPassword().isEmpty())
                throw new DataValidateException("User password not must be empty!");
        validateRole(user.getRole());
    }
}