package at.simstoe.cloud.launcher.command;

import java.util.Map;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 22.12.2024 - 23:40
 */

public interface CommandManager {
    void registerCommand(CloudCommand command);
    void execute(String command);

    Map<String, CloudCommand> cloudCommands();
}
