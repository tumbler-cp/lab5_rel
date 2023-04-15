package commands.collManaging;

import collection.CollectionManager;
import commands.Command;
import exceptions.EmptyCollectionException;
import terminal.Terminal;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class <b>remove_lower_key</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class RemoveLowerKey extends Command {
    /**
     * Collection manager this command works with
     */
    CollectionManager collection;

    /**
     * Default constructor
     *
     * @param collectionManager collection to work with
     */
    public RemoveLowerKey(CollectionManager collectionManager) {
        super("remove_lower_key null", "удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        collection = collectionManager;
    }

    /**
     * Delete all objects which key is smaller than given
     *
     * @param lever Special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) {
        int key = Integer.parseInt(this.getArgs()[0]);
        ArrayList<Integer> toDel = new ArrayList<>();
        try {
            for (Integer currentKey : collection.get_collection().keySet()) {
                //if (keyToRem!=0) collection.get_collection().remove(keyToRem);
                if (key > currentKey) toDel.add(currentKey);
            }
            for (Integer i : toDel) {
                collection.get_collection().remove(i);
            }
        } catch (EmptyCollectionException e) {
            System.out.println("Коллекция пуста!");
            Terminal.logger.write(e);
        }
    }
}
