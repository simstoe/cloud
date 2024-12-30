package at.simstoe.cloud.launcher.command.defaults;

import at.simstoe.cloud.api.logger.LogType;
import at.simstoe.cloud.launcher.CloudLauncher;
import at.simstoe.cloud.launcher.command.CloudCommand;
import at.simstoe.cloud.launcher.command.annotations.Command;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 23.12.2024 - 14:07
 */

@Command(name = "help", description = "All commands and help description")
public final class HelpCommand extends CloudCommand {
    @Override
    public void execute(CloudLauncher cloudLauncher, String[] args) {
        var commandManager = cloudLauncher.commandManager();

        cloudLauncher.logger().log("");
        cloudLauncher.logger().log("All possible commands(§b" + commandManager.cloudCommands().size() + "§r):", LogType.INFO);
        cloudLauncher.logger().log("");

        commandManager.cloudCommands().values().stream().distinct().forEach(command ->
                cloudLauncher.logger().log("§b" + command.name() + aliases(command) + " §r- " + command.description(), LogType.INFO));

        cloudLauncher.logger().log("");
    }

    private String aliases(CloudCommand cloudCommand) {
        return cloudCommand.aliases().length == 0 ? "" : "§r(§b" + String.join(", ", cloudCommand.aliases()) + "§r)";
    }
}
