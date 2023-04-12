package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for logging exceptions
 */
public class Logger {
    File logfile = new File("log.txt");

    /**
     * Write exception to log.txt
     *
     * @param e Exception to write
     */

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

    /**
     * Overload of write to write errors
     *
     * @param e Error to write
     */
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
