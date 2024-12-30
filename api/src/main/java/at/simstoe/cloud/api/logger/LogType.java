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
    SUCCESS("§aSUCCESS§7"),
    INFO("§bINFO§7"),
    ERROR("§cERROR§7"),
    WARNING("§6WARNING§7"),
    EMPTY("");

    private final String textField;
}
