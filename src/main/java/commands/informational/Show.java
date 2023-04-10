package commands.informational;

import collection.CollectionManager;
import commands.Command;
import exceptions.EmptyCollectionException;
import tableMaker.Table;
import terminal.Terminal;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>show</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class Show extends Command {
    /**
     * Collection manager this command works with
     */
    CollectionManager collection;

    /**
     * Default constructor
     *
     * @param collection Collection to work with
     */
    public Show(CollectionManager collection) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collection = collection;
    }

    /**
     * Print all objects of collection
     *
     * @param lever - special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) {
        try {
            System.out.print(new Table(collection.get_collection()));
        } catch (EmptyCollectionException e) {
            System.out.println("Коллекция пуста!");
            Terminal.logger.write(e);
        }
    }
}
