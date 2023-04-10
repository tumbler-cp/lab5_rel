package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    File logfile = new File("log.txt");

    public void write(Exception e) {
        try {
            FileWriter writer = new FileWriter(logfile);
            writer.write(e.toString());
            writer.flush();
            writer.close();
        } catch (IOException io) {
            System.out.println("Проблемы с записью в log.txt");
        }
    }

    public void write(Error e) {
        try {
            FileWriter writer = new FileWriter(logfile);
            writer.write(e.toString());
            writer.flush();
            writer.close();
        } catch (IOException io) {
            System.out.println("Проблемы с записью в log.txt");
        }
    }
}
