package at.simstoe.cloud.launcher.console;

import at.simstoe.cloud.api.logger.Logger;
import at.simstoe.cloud.launcher.CloudLauncher;
import org.jline.reader.LineReader;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 30.12.2024 - 18:58
 */

public class ConsoleReadingThread extends Thread {
    private final String consolePrompt;
    private final SimpleConsoleManager consoleManager;
    private final LineReader lineReader;

    public ConsoleReadingThread(final Logger logger, final SimpleConsoleManager consoleManager, final boolean isWindows) {
        super("ConsoleReadingThread");

        this.consolePrompt = ">";
        this.consoleManager = consoleManager;
        this.lineReader = consoleManager.lineReader();
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            var line = this.lineReader.readLine(this.consolePrompt);

            if (line != null && !line.isEmpty()) {
                var input = this.consoleManager.inputs().poll();

                if (input != null) {
                    input.input().accept(line);
                } else {
                    CloudLauncher.instance().commandManager().execute(line);
                }
            }
        }
    }
}
