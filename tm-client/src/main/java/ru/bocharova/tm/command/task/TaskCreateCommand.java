package ru.bocharova.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bocharova.tm.endpoint.*;
import ru.bocharova.tm.api.service.ISessionService;
import ru.bocharova.tm.api.service.ITerminalService;
import ru.bocharova.tm.api.command.AbstractCommand;

import javax.inject.Inject;

@NoArgsConstructor
public final class TaskCreateCommand implements AbstractCommand {

    @Inject
    @NotNull
    ProjectEndpoint projectEndpoint;

    @Inject
    @NotNull
    TaskEndpoint taskEndpoint;

    @Inject
    @NotNull
    SessionEndpoint sessionEndpoint;

    @Inject
    @NotNull
    ISessionService sessionService;

    @Inject
    @NotNull
    ITerminalService terminalService;

    @Override
    public String getName() {
        return "task-create";
    }

    @Override
    public String getDescription() {
        return "Create new task.";
    }

    @Override
    public void execute(
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception {
        @Nullable final SessionDTO currentSession = sessionService.getCurrentSession();
        sessionEndpoint.validateAdminSession(currentSession);
        System.out.println("Please input project id: ");
        @Nullable final String id = terminalService.nextLine();
        projectEndpoint.findOneProject(currentSession, id);
        System.out.println("Please input task name: ");
        @Nullable final String name = terminalService.nextLine();
        System.out.println("Please input task description: ");
        @Nullable final String description = terminalService.nextLine();
        @Nullable final TaskDTO task = new TaskDTO();
        task.setName(name);
        task.setDescription(description);
        task.setProjectId(id);
        task.setUserId(currentSession.getUserId());
        taskEndpoint.createTask(task, currentSession);
        System.out.println("Task " + name + " is create!");
    }
}
