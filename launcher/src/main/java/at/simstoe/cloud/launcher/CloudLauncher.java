package at.simstoe.cloud.launcher;

import at.simstoe.cloud.api.logger.LogType;
import at.simstoe.cloud.api.logger.Logger;
import at.simstoe.cloud.launcher.command.CommandManager;
import at.simstoe.cloud.launcher.command.CommandManagerImpl;
import at.simstoe.cloud.launcher.command.defaults.HelpCommand;
import at.simstoe.cloud.launcher.command.defaults.InfoCommand;
import at.simstoe.cloud.launcher.command.defaults.ShutdownCommand;
import at.simstoe.cloud.launcher.configuration.ConfigurationManager;
import at.simstoe.cloud.launcher.configuration.defaults.FileConfigurationSource;
import at.simstoe.cloud.launcher.logger.SimpleLogger;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 16.12.2024 - 16:11
 */

@Getter
@Setter
@Accessors(fluent = true)
public final class CloudLauncher {
    @Getter
    @Accessors(fluent = true)
    private static CloudLauncher instance;
    private String cloudVersion = "1.0.1-ALPHA";

    private final CommandManager commandManager;
    private final Logger logger;
    private final ConfigurationManager configurationManager;
    private final WorkerThread workerThread;

    private boolean isRunning = true;

    public CloudLauncher() {
        instance = this;

        this.commandManager = new CommandManagerImpl();
        this.logger = new SimpleLogger();
        this.configurationManager = new ConfigurationManager(new FileConfigurationSource("config.properties"));
        this.workerThread = new WorkerThread(this);

        System.out.println("      _               _             \n" +
                "     (_)             | |            \n" +
                "  ___ _ _ __ ___  ___| |_ ___   ___ \n" +
                " / __| | '_ ` _ \\/ __| __/ _ \\ / _ \\\n" +
                " \\__ \\ | | | | | \\__ \\ || (_) |  __/\n" +
                " |___/_|_| |_| |_|___/\\__\\___/ \\___|\n");

        System.out.println("CloudSystem - Version: " + this.cloudVersion);
        System.out.println("Author: Simon Stoegerer ( simstoe | https://simstoe.at )\n");
        this.logger.log("Starting the cloud system...", LogType.INFO);


        this.commandManager.registerCommand(new InfoCommand());
        this.commandManager.registerCommand(new HelpCommand());
        this.commandManager.registerCommand(new ShutdownCommand());

        this.workerThread.start();
    }

    public void shutdown() {
        this.isRunning = false;

        this.logger.log("Shutting down the cloud system...", LogType.INFO);

        this.logger.log("Cloud System shut down!", LogType.SUCCESS);
    }

    public static void main(String[] args) {
        new CloudLauncher();
    }
}
