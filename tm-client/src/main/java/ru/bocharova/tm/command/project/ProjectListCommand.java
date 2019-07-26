package ru.bocharova.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.bocharova.tm.endpoint.*;
import ru.bocharova.tm.api.service.ISessionService;
import ru.bocharova.tm.api.command.AbstractCommand;

import javax.inject.Inject;

@NoArgsConstructor
public final class ProjectListCommand implements AbstractCommand {

    @Inject
    @NotNull
    ProjectEndpoint projectEndpoint;

    @Inject
    @NotNull
    SessionEndpoint sessionEndpoint;

    @Inject
    @NotNull
    ISessionService sessionService;

    @Override
    public String getName() {
        return "project-list";
    }

    @Override
    public String getDescription() {
        return "Show all project or selected project.";
    }

    @Override
    public void execute(
    ) throws AuthenticationSecurityException_Exception, DataValidateException_Exception {
        @Nullable final SessionDTO currentSession = sessionService.getCurrentSession();
        sessionEndpoint.validateAdminSession(currentSession);
        System.out.println("List of projects:");
        projectEndpoint.findAllProjectByUserId(currentSession)
                .forEach(e -> System.out.println("id: " + e.getId() + " name: " + e.getName()));
    }
}
