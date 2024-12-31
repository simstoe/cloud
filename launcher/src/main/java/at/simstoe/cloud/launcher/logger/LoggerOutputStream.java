package at.simstoe.cloud.launcher.logger;

import at.simstoe.cloud.api.logger.LogType;
import at.simstoe.cloud.api.logger.Logger;
import lombok.AllArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 30.12.2024 - 22:41
 */

@AllArgsConstructor
public final class LoggerOutputStream extends ByteArrayOutputStream {
    private final Logger logger;
    private final LogType logType;

    @Override
    public void flush() throws IOException {
        final var input = this.toString(StandardCharsets.UTF_8);

        this.reset();

        if (input != null && !input.isEmpty()) {
            this.logger.log(input.split("\n"), this.logType);
        }
    }
}
