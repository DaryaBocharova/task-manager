package ru.bocharova.tm.command.project;

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
public final class ProjectEditCommand implements AbstractCommand {

    @Inject
    @NotNull
    ProjectEndpoint projectEndpoint;

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
        return "project-edit";
    }

    @Override
    public String getDescription() {
        return "Edit selected project.";
    }

    @Override
    public void execute(
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception {
        @Nullable final SessionDTO currentSession = sessionService.getCurrentSession();
        sessionEndpoint.validateAdminSession(currentSession);
        System.out.println("Please input project ID for edit: ");
        @Nullable final String id = terminalService.nextLine();
        @Nullable final ProjectDTO project = projectEndpoint.findOneProject(currentSession, id);
        System.out.println("Input new project's name: ");
        @Nullable final String name = terminalService.nextLine();
        System.out.println("Input new project's description: ");
        @Nullable final String description = terminalService.nextLine();
        System.out.println("Input project's status(planned, inprocess, done): ");
        @Nullable final String status = terminalService.nextLine();
        project.setName(name);
        project.setDescription(description);
        try {
            project.setStatus(Status.valueOf(status.toUpperCase()));
            project.setDateEnd(null);
            if (project.getStatus().equals(Status.DONE)) {
                project.setDateEnd(DateFormatter.dateToXMLGregorianCalendar(new Date()));
            }
            projectEndpoint.editProject(currentSession, project);
            System.out.println("Project " + id + " is update!");
        } catch (Exception e) {
            throw new DataValidateException_Exception(e.getMessage());
        }

    }
}
