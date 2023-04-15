package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class for logging exceptions
 */
public class Logger {
    private final File logfile = new File("log.txt");
    private FileWriter writer;


    public Logger(){
        try {
            writer = new FileWriter(logfile);
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом log.txt");
        }
    }

    /**
     * Write exception to log.txt
     *
     * @param e Exception to write
     */
    public void write(Exception e) {
        try {
            writer.write(Arrays.toString(e.getStackTrace()));
            writer.write("\n");
        } catch (IOException ex) {
            System.out.println("Ошибка при записи ошибки в log.txt");
        }
    }

    public void close(){
        try {
            writer.flush();
            writer.close();
        } catch (IOException io){
            System.out.println("Ошибка при записи ошибки в log.txt");
        }
    }
    /**
     * Overload of write to write errors
     *
     * @param e Error to write
     */
    public void write(Error e) {
        try {
            writer.write(Arrays.toString(e.getStackTrace()));
            writer.write("\n");
        } catch (IOException io) {
            System.out.println("Проблемы с записью в log.txt");
        }
    }
}
