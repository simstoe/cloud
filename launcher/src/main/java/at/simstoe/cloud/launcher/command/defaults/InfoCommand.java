package at.simstoe.cloud.launcher.command.defaults;

import at.simstoe.cloud.api.logger.LogType;
import at.simstoe.cloud.launcher.CloudLauncher;
import at.simstoe.cloud.launcher.command.CloudCommand;
import at.simstoe.cloud.launcher.command.annotations.Command;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 23.12.2024 - 13:43
 */

@Command(name = "info", description = "Shows information about the cloud system")
public final class InfoCommand extends CloudCommand {
    @Override
    public void execute(CloudLauncher cloudLauncher, String[] args) {
        var runtime = Runtime.getRuntime();

        cloudLauncher.logger().log("");
        cloudLauncher.logger().log("Cloud System Information", LogType.INFO);
        cloudLauncher.logger().log("");
        cloudLauncher.logger().log("Version: " + cloudLauncher.cloudVersion(), LogType.INFO);
        cloudLauncher.logger().log("Available Processors: " + runtime.availableProcessors(), LogType.INFO);
        cloudLauncher.logger().log("Max Memory: " + runtime.maxMemory() / 1024 / 1024 + "MB", LogType.INFO);
        cloudLauncher.logger().log("Total Memory: " + runtime.totalMemory() / 1024 / 1024 + "MB", LogType.INFO);
        cloudLauncher.logger().log("Free Memory: " + runtime.freeMemory() / 1024 / 1024 + "MB", LogType.INFO);
        cloudLauncher.logger().log("");
    }
}
