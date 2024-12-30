package at.simstoe.cloud.launcher.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 23.12.2024 - 21:40
 */

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class ConfigurationManager {
    private ConfigurationSource configurationSource;

    public Properties configuration() throws IOException {
        return this.configurationSource.loadConfiguration();
    }
}
