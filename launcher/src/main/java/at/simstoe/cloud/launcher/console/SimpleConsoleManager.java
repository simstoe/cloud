package at.simstoe.cloud.launcher.console;

import at.simstoe.cloud.api.logger.Logger;
import at.simstoe.cloud.launcher.logger.SimpleLogger;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 30.12.2024 - 18:49
 */

@Getter
@Accessors(fluent = true)
public final class SimpleConsoleManager {
    private final Logger logger;
    private Thread consoleReadingThread;
    private final Terminal terminal;
    private final LineReader lineReader;
    private final boolean isWindows;

    private final Queue<ConsoleInput> inputs;

    public SimpleConsoleManager(final Logger logger) throws IOException {
        this.isWindows = ((SimpleLogger) logger).isWindows();
        this.terminal = TerminalBuilder.builder()
                .system(true)
                .streams(System.in, System.out)
                .encoding(StandardCharsets.UTF_8)
                .dumb(true)
                .build();

        this.lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .option(LineReader.Option.DISABLE_EVENT_EXPANSION, true)
                .option(LineReader.Option.AUTO_REMOVE_SLASH, false)
                .option(LineReader.Option.INSERT_TAB, false)
                .build();

        this.logger = logger;
        this.clearConsole();

        this.inputs = new LinkedList<>();
    }

    public void start() {
        this.consoleReadingThread = new ConsoleReadingThread(this.logger, this, this.isWindows);
        this.consoleReadingThread.setUncaughtExceptionHandler((t, e) -> e.printStackTrace());
        this.consoleReadingThread.start();
    }

    public void clearConsole() {
        this.terminal.puts(InfoCmp.Capability.clear_screen);
        this.terminal.flush();
        this.redraw();
    }

    public void redraw() {
        if (this.lineReader.isReading()) {
            this.lineReader.callWidget(LineReader.REDRAW_LINE);
            this.lineReader.callWidget(LineReader.REDISPLAY);
        }
    }

    public void shutdown() throws IOException {
        this.terminal.close();
    }

    public void addInput(final Consumer<String> input, final List<String> tabCompletions) {
        this.inputs.add(new ConsoleInput(input, tabCompletions));
    }
}
