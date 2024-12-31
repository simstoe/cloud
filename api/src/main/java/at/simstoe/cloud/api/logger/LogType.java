package at.simstoe.cloud.api.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 22.12.2024 - 23:53
 */

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum LogType {
    SUCCESS("§aSUCCESS§r"),
    INFO("§bINFO§r"),
    ERROR("§cERROR§r"),
    WARNING("§6WARNING§r"),
    EMPTY("");

    private final String textField;
}
