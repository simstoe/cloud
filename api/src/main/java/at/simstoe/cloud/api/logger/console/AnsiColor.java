package at.simstoe.cloud.api.logger.console;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 23.12.2024 - 13:46
 */

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum AnsiColor {
    RESET("\u001B[0m", 'r'),
    BLACK("\u001B[30m", '0'),
    DARK_GRAY("\u001B[90m", '8'),
    RED("\u001B[31m", 'c'),
    GREEN("\u001B[32m", 'a'),
    YELLOW("\u001B[33m", 'e'),
    BLUE("\u001B[34m", '9'),
    PURPLE("\u001B[35m", 'd'),
    CYAN("\u001B[36m", 'b'),
    WHITE("\u001B[37m", 'f');

    private final String ansiCode;
    private final char legacyColorCode;

    public static String replaceColorCodes(String text) {
        if (text == null) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        int length = text.length();

        for (int i = 0; i < length; i++) {
            char currentChar = text.charAt(i);

            if (currentChar == '§' && i + 1 < length) {
                char code = text.charAt(i + 1);
                AnsiColor color = AnsiColor.byLegacyCode(code);

                if (color != null) {
                    result.append(color.ansiCode());
                    i++; // Überspringt das nächste Zeichen, da es der Farbcode ist
                } else {
                    result.append(currentChar);
                }
            } else {
                result.append(currentChar);
            }
        }

        result.append(AnsiColor.RESET.ansiCode());

        return result.toString();
    }

    private static AnsiColor byLegacyCode(char code) {
        for (var color: values()) {
            if (color.legacyColorCode() == code) {
                return color;
            }
        }

        return null;
    }
}
