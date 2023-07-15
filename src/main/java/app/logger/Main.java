package app.logger;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileLogger fileLogger = new FileLogger(new FileLoggerConfiguration()
                .setPath("log.txt")
                .setLevel(Level.DEBUG)
                .setMaxFileSize(80)
                .setLogFormat("[current time][%s] Message: %s"));


        try {
            fileLogger.log(Level.DEBUG, "Debug message");
            fileLogger.log(Level.INFO, "Info message");
        } catch (FileMaxSizeReachedException s) {
            System.out.println("Exception: " + s.getMessage());
            System.out.println("Max File Size: " + fileLogger.getConfig().getMaxFileSize());
            System.out.println("Current File Size: " + new File(fileLogger.getPath()).length());
            System.out.println("File Path: " + fileLogger.getConfig().getPath());

        }
    }
}
