package at.simstoe.cloud.api.logger;

import org.jetbrains.annotations.NotNull;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 22.12.2024 - 23:52
 */

public interface Logger {
    void log(final @NotNull String text, final @NotNull LogType logType);
    void log(final @NotNull String[] text, final @NotNull LogType logType);
    void log(final @NotNull String... text);

    //TODO: format method

    default void log(final @NotNull String text) {
        this.log(text, LogType.INFO);
    }
}
