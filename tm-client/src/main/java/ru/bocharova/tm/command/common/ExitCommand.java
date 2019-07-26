package ru.bocharova.tm.command.common;

import lombok.NoArgsConstructor;
import ru.bocharova.tm.api.command.AbstractCommand;

@NoArgsConstructor
public final class ExitCommand implements AbstractCommand {

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Quit from application.";
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
