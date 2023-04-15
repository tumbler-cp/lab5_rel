package commands.collManaging;

import collection.CollectionManager;
import commands.Command;
import exceptions.EmptyCollectionException;
import terminal.Terminal;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>clear</b> command.
 *
 * @author Abdujalol Khodjaev
 * @see RemoveKey
 * @see RemoveLowerKey
 */
public class Clear extends Command {
    /**
     * Collection manager this command works with
     */
    CollectionManager collection;

    /**
     * Default constructor
     *
     * @param collectionManager - <b>collection</b> manager
     */
    public Clear(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collection = collectionManager;
    }

    /**
     * Overriding execute.
     * Clears collection.dragons
     *
     * @param lever Lever to stop program
     */
    @Override
    public void execute(AtomicBoolean lever) {
        try {
            collection.get_collection().clear();
        } catch (EmptyCollectionException e) {
            System.out.println("Коллекция пуста!");
            Terminal.logger.write(e);
        }
    }
}
