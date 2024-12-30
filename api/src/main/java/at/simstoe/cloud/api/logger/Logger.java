package at.simstoe.cloud.api.logger;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 22.12.2024 - 23:52
 */

public interface Logger {
    void log(final String text);
    void log(final String text, final LogType logType);
}
