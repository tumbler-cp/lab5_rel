package commands;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>exit</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class Exit extends Command {
    /**
     * Default constructor
     */
    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    /**
     * Sets <i>l</i> (lever) to <b>false</b>
     *
     * @param l - special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean l) {
        l.set(false);
    }
}
