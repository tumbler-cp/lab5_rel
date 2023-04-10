package commands.informational;

import collection.CollectionManager;
import commands.Command;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>info</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class Info extends Command {
    /**
     * Collection manager this command works with
     */
    CollectionManager collectionManager;

    /**
     * Default constructor
     *
     * @param collectionManager Collection to work with
     */
    public Info(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
    }

    /**
     * Prints collection manager. CollectionManagers' toString() already returns information about collection.
     *
     * @param lever - special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) {
        System.out.println(this.collectionManager);
    }
}
