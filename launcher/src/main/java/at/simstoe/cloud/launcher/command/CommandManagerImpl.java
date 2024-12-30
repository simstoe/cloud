package at.simstoe.cloud.launcher.command;

import at.simstoe.cloud.launcher.CloudLauncher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 22.12.2024 - 23:43
 */

public final class CommandManagerImpl implements CommandManager {
    private final Map<String, CloudCommand> cloudCommands;

    public CommandManagerImpl() {
        this.cloudCommands = new HashMap<>();
    }

    @Override
    public void registerCommand(CloudCommand command) {
        this.cloudCommands.put(command.name(), command);


        /*
        for (var alias : command.aliases()) {
            this.cloudCommands.put(alias, command);
        }
        */
    }

    @Override
    public void execute(String command) {
        final var args = new ArrayList<>(Arrays.asList(command.split(" ")));
        var cloudCommand = this.cloudCommands.get(command);

        /* Maybe throw an exception */
        if (cloudCommand == null) return;

        args.removeFirst();

        cloudCommand.execute(CloudLauncher.instance(), args.toArray(new String[]{}));
    }

    @Override
    public Map<String, CloudCommand> cloudCommands() {
        return this.cloudCommands;
    }
}
