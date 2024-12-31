package at.simstoe.cloud.launcher.console;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 30.12.2024 - 19:01
 */

public record ConsoleInput(
        Consumer<String> input,
        List<String> tabCompletions) {
}
