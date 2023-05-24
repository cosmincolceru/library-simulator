package util;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogToFile {
    private static LogToFile instance;
    public static LogToFile getInstance() {
        if (instance == null) {
            instance = new LogToFile();
        }
        return instance;
    }

    private LogToFile() { }
    public boolean logToFile(String action) {
        String filename = "logs.csv";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        FileWriter writer;

        try {
            writer = new FileWriter(filename, true);

            writer.write(action + '\t' + formattedDateTime + '\n');

            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
