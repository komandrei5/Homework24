package app.logger;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {
    FileLoggerConfiguration config;

    public FileLogger(FileLoggerConfiguration config) {
        this.config = config;
    }

    public void log(Level level, String message) throws FileMaxSizeReachedException, IOException {
        if (!config.getLevel().includes(level)) {
            return;
        }

        File file = new File(config.getPath());
        if (!file.exists()) {
            file.createNewFile();
        }

        if (file.length() + message.length() > config.getMaxFileSize()) {
            throw new FileMaxSizeReachedException("File size limit reached",
                    config.getMaxFileSize(), file.length(), file.getAbsolutePath());
        }
        String logLine = formatLogline(level, message);

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(logLine + System.lineSeparator());
            System.out.println(logLine);
        }
    }
    public FileLoggerConfiguration getConfig() {
        return config;
    }

    public String getPath() {
        return config.getPath();
    }

    private String formatLogline(Level level, String message) {
        LocalDateTime currentTime = LocalDateTime.now();
        String formattedTime = currentTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return String.format("[%s][%s] Message: %s", formattedTime, level, message);
    }
}

