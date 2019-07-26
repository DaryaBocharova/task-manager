package ru.bocharova.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bocharova.tm.endpoint.*;
import ru.bocharova.tm.api.service.ISessionService;
import ru.bocharova.tm.api.service.ITerminalService;
import ru.bocharova.tm.api.command.AbstractCommand;
import ru.bocharova.tm.util.DateFormatter;

import javax.inject.Inject;
import java.util.Date;

@NoArgsConstructor
public final class TaskEditCommand implements AbstractCommand {

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
        return "task-edit";
    }

    @Override
    public String getDescription() {
        return "Edit selected task.";
    }

    @Override
    public void execute(
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception {
        @Nullable final SessionDTO currentSession = sessionService.getCurrentSession();
        sessionEndpoint.validateAdminSession(currentSession);
        System.out.println("Input task id for edit: ");
        @Nullable final String id = terminalService.nextLine();
        @Nullable final TaskDTO task = taskEndpoint.findOneTask(currentSession, id);
        System.out.println("Input new task's name: ");
        @Nullable final String name = terminalService.nextLine();
        System.out.println("Input new task's description: ");
        @Nullable final String description = terminalService.nextLine();
        System.out.println("Input task's status(planned, inprocess, done: ");
        @Nullable final String status = terminalService.nextLine();
        task.setName(name);
        task.setDescription(description);
        try {
            task.setStatus(Status.valueOf(status.toUpperCase()));
            task.setDateEnd(null);
            if (task.getStatus().equals(Status.DONE)) {
                task.setDateEnd(DateFormatter.dateToXMLGregorianCalendar(new Date()));
            }
            taskEndpoint.editTask(task, currentSession);
            System.out.println("Task id: " + id + " edit is update!");
        } catch (Exception e) {
            throw new DataValidateException_Exception(e.getMessage());
        }
    }
}
