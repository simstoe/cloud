package at.simstoe.cloud.launcher.configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 23.12.2024 - 21:36
 */

public interface ConfigurationSource {
    Properties loadConfiguration() throws IOException;
}
