package at.simstoe.cloud.launcher.logger;

import at.simstoe.cloud.api.logger.LogType;
import at.simstoe.cloud.api.logger.Logger;
import at.simstoe.cloud.api.logger.console.AnsiColor;
import at.simstoe.cloud.launcher.console.SimpleConsoleManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jline.utils.InfoCmp;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 23.12.2024 - 13:16
 */

@Getter
@Accessors(fluent = true)
public final class SimpleLogger implements Logger {
    private SimpleConsoleManager consoleManager;

    public SimpleLogger() {
        try {
            this.consoleManager = new SimpleConsoleManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.setOut(new PrintStream(new LoggerOutputStream(this, LogType.INFO), true));
        System.setErr(new PrintStream(new LoggerOutputStream(this, LogType.ERROR), true));
    }


    @Override
    public void log(@NotNull String text, @NotNull LogType logType) {
        final var terminal = this.consoleManager.terminal();
        final var replacedText = AnsiColor.replaceColorCodes(logType.textField() + " " + text);

        terminal.puts(InfoCmp.Capability.carriage_return);
        terminal.writer().println(replacedText);
        terminal.flush();
        this.consoleManager.redraw();
    }

    @Override
    public void log(final @NotNull String[] text, final @NotNull LogType logType) {
        final var terminal = this.consoleManager.terminal();

        terminal.puts(InfoCmp.Capability.carriage_return);

        for (final var s : text) {
            terminal.writer().println(s);
        }
        terminal.flush();
        this.consoleManager.redraw();
    }

    @Override
    public void log(final @NotNull String... text) {
        this.log(text, LogType.INFO);
    }

    public boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
}
