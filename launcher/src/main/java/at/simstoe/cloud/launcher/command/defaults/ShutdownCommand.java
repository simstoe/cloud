package at.simstoe.cloud.launcher.command.defaults;

import at.simstoe.cloud.launcher.CloudLauncher;
import at.simstoe.cloud.launcher.command.CloudCommand;
import at.simstoe.cloud.launcher.command.annotations.Command;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 22.12.2024 - 23:47
 */

@Command(name = "shutdown", description = "Shuts down the cloud system", aliases = {"stop", "exit"})
public final class ShutdownCommand extends CloudCommand {
    @Override
    public void execute(CloudLauncher cloudLauncher, String[] args) {
        cloudLauncher.shutdown();
    }
}
