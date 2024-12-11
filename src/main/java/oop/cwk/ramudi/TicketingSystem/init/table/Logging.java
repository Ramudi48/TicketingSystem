package oop.cwk.ramudi.TicketingSystem.init.table;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private static FileHandler fileHandler = null;

    public Logging() {
        try {
            // Ensure the logs directory exists
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs(); // Create the directory
            }
            fileHandler = new FileHandler("logs/log.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logMethod(String process, Logger logger) {
        if (fileHandler != null) {
            logger.info("response code: " + HttpURLConnection.HTTP_OK);
            logger.info(Thread.currentThread().getName() + " " + process + " ");
        } else {
            logger.warning("FileHandler is null.");
        }
    }
}
