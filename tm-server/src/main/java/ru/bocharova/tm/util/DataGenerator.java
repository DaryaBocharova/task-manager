package ru.bocharova.tm.util;

import org.jetbrains.annotations.NotNull;
import ru.bocharova.tm.enumerate.Role;
import ru.bocharova.tm.exception.DataValidateException;
import ru.bocharova.tm.model.dto.ProjectDTO;
import ru.bocharova.tm.model.dto.TaskDTO;
import ru.bocharova.tm.model.dto.UserDTO;
import ru.bocharova.tm.api.service.IProjectService;
import ru.bocharova.tm.api.service.ISessionService;
import ru.bocharova.tm.api.service.ITaskService;
import ru.bocharova.tm.api.service.IUserService;

import javax.inject.Inject;

public class DataGenerator {

    @NotNull
    private final IProjectService projectService;

    @NotNull
    private final ITaskService taskService;

    @NotNull
    private final IUserService userService;

    @NotNull
    private final ISessionService sessionService;

    @Inject
    public DataGenerator(
            @NotNull final IProjectService projectService,
            @NotNull final ITaskService taskService,
            @NotNull final IUserService userService,
            @NotNull final ISessionService sessionService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    public void generate(){
        generateUsers();
        generateData();
    }
    public void generateUsers() {
        try {
            UserDTO admin = new UserDTO();
            admin.setLogin("admin");
            admin.setPassword(HashUtil.md5("admin"));
            admin.setRole(Role.ADMIN);

            UserDTO user = new UserDTO();
            user.setLogin("user");
            user.setPassword(HashUtil.md5("user"));
            user.setRole(Role.USER);

            UserDTO testAdmin = new UserDTO();
            testAdmin.setLogin("testAdmin");
            testAdmin.setPassword(HashUtil.md5("testAdmin"));
            testAdmin.setRole(Role.ADMIN);

            UserDTO testUser = new UserDTO();
            testUser.setLogin("testUser");
            testUser.setPassword(HashUtil.md5("testUser"));
            testUser.setRole(Role.USER);

            userService.clear();
            userService.create(admin);
            userService.create(user);
            userService.create(testAdmin);
            userService.create(testUser);
        } catch (DataValidateException e) {
            e.printStackTrace();
        }
    }

    public void generateData() {
        try {
            projectService.create(new ProjectDTO("My_project_1", "Description for my project 1", userService.findByLogin("admin").getId()));
            projectService.create(new ProjectDTO("My_project_2", "Description for my project 2", userService.findByLogin("admin").getId()));
            projectService.create(new ProjectDTO("My_project_3", "Description for my project 3", userService.findByLogin("user").getId()));
            projectService.create(new ProjectDTO("My_project_4", "Description for my project 4", userService.findByLogin("user").getId()));

            for (ProjectDTO project : projectService.findAllByUserId(userService.findByLogin("admin").getId())) {
                taskService.create(new TaskDTO("task_100", "Description for task 100", project.getId(), userService.findByLogin("admin").getId()));
                taskService.create(new TaskDTO("task_200", "Description for task 200", project.getId(), userService.findByLogin("admin").getId()));
                taskService.create(new TaskDTO("task_300", "Description for task 300", project.getId(), userService.findByLogin("admin").getId()));
                taskService.create(new TaskDTO("task_400", "Description for task 400", project.getId(), userService.findByLogin("admin").getId()));
            }

            for (ProjectDTO project : projectService.findAllByUserId(userService.findByLogin("user").getId())) {
                taskService.create(new TaskDTO("task_1", "Description for task 1", project.getId(), userService.findByLogin("user").getId()));
                taskService.create(new TaskDTO("task_2", "Description for task 2", project.getId(), userService.findByLogin("user").getId()));
                taskService.create(new TaskDTO("task_3", "Description for task 3", project.getId(), userService.findByLogin("user").getId()));
                taskService.create(new TaskDTO("task_4", "Description for task 4", project.getId(), userService.findByLogin("user").getId()));
            }

            sessionService.create(userService.findByLogin("admin"));
            sessionService.create(userService.findByLogin("admin"));
            sessionService.create(userService.findByLogin("admin"));
            sessionService.create(userService.findByLogin("user"));
            sessionService.create(userService.findByLogin("user"));
            sessionService.create(userService.findByLogin("user"));
        } catch (DataValidateException e) {
            e.printStackTrace();
        }
    }
}
