package commands.collManaging;

import collection.CollectionManager;
import commands.Command;
import exceptions.EmptyCollectionException;
import terminal.Terminal;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>remove_key</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class RemoveKey extends Command {
    /**
     * Collection manager this command works with
     */
    CollectionManager collection;

    /**
     * Default constructor
     *
     * @param collectionManager collection this command works with
     */
    public RemoveKey(CollectionManager collectionManager) {
        super("remove_key null", "удалить элемент из коллекции по его ключу");
        collection = collectionManager;
    }

    /**
     * Delete object from collection by key
     *
     * @param lever - special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) {
        int key = Integer.parseInt(this.getArgs()[0]);
        try {
            collection.get_collection().remove(key);
        } catch (EmptyCollectionException e) {
            System.out.println("Коллекция пуста!");
            Terminal.logger.write(e);
        }
    }
}
