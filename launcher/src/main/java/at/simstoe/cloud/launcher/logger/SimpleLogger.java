package at.simstoe.cloud.launcher.logger;

import at.simstoe.cloud.api.logger.LogType;
import at.simstoe.cloud.api.logger.Logger;
import at.simstoe.cloud.api.logger.console.AnsiColor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Simon Stögerer
 * copyright - all rights reserved
 * created: 23.12.2024 - 13:16
 */

public final class SimpleLogger implements Logger {
    private final File logFile;

    public SimpleLogger(String logDirectory) {
        var directory = new File(logDirectory);

        if (!directory.exists()) directory.mkdirs();

        logFile = new File(directory, "application.log");

        try {
            if (!logFile.exists() && !logFile.createNewFile()) {
                throw new IOException("Log-Datei konnte nicht erstellt werden: " + logFile.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Erstellen der Log-Datei", e);
        }
    }

    @Override
    public void log(String text) {
        System.out.println(text);
    }

    @Override
    public void log(String text, LogType logType) {
        var timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        var logTemplate = "§8[§r" + timestamp + "§8] " + mapLogTypeToColor(logType).ansiCode() + logType + "§r - " + text;

        System.out.println(AnsiColor.replaceColorCodes(logTemplate));

        try (var writer = new BufferedWriter(new FileWriter(logFile, true))) {
            //TODO: remove § and colorcode
            writer.write(logTemplate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AnsiColor mapLogTypeToColor(LogType logType) {
        return switch (logType) {
            case INFO -> AnsiColor.CYAN;
            case SUCCESS -> AnsiColor.GREEN;
            case WARNING -> AnsiColor.YELLOW;
            case ERROR -> AnsiColor.RED;
            default -> AnsiColor.RESET;
        };
    }
}
