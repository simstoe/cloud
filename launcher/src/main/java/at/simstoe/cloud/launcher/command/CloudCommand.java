package at.simstoe.cloud.launcher.command;

import at.simstoe.cloud.launcher.CloudLauncher;
import at.simstoe.cloud.launcher.command.annotations.Command;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 22.12.2024 - 23:40
 */

@Getter
@Setter
@Accessors(fluent = true)
public abstract class CloudCommand {
    private final String name;
    private final String description;
    private final String[] aliases;

    public CloudCommand() {
        final var annotation = this.getClass().getAnnotation(Command.class);

        this.name = annotation.name();
        this.description = annotation.description();
        this.aliases = annotation.aliases();
    }

    public abstract void execute(CloudLauncher cloudLauncher, String[] args);
}
