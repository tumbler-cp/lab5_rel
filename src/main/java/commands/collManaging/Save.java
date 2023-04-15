package commands.collManaging;

import commands.Command;
import file.FileManager;
import terminal.Terminal;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>save</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class Save extends Command {
    /**
     * File manager command works with
     */
    FileManager fm;

    /**
     * Default constructor
     *
     * @param fileManager File manager which write all data to file
     */
    public Save(FileManager fileManager) {
        super("save", "сохранить коллекцию в файл");
        this.fm = fileManager;
    }

    /**
     * Saves collection in file
     *
     * @param lever - special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) {
        try {
            this.fm.write();
        } catch (IOException io) {
            System.out.println("Ошибка записи в файл.");
            Terminal.logger.write(io);
        }
    }
}
