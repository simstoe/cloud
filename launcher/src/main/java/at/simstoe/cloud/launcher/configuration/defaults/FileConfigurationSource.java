package at.simstoe.cloud.launcher.configuration.defaults;

import at.simstoe.cloud.launcher.configuration.ConfigurationSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 23.12.2024 - 21:37
 */

@AllArgsConstructor
@Getter
@Setter
@Accessors(fluent = true)
public final class FileConfigurationSource implements ConfigurationSource {
    private String fileName;

    @Override
    public Properties loadConfiguration() throws IOException {
        var properties = new Properties();

        try (var input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("Configuration file not found!");
            }

            properties.load(input);
        }

        return properties;
    }
}
