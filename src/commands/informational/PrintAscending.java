package commands.informational;

import collection.CollectionManager;
import commands.Command;
import dragon.Dragon;


import java.util.List;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>print_ascending</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class PrintAscending extends Command {
    /**
     * Collection manager this command works with
     */
    CollectionManager collection;

    /**
     * Default constructor
     *
     * @param collectionManager collection to work with
     */
    public PrintAscending(CollectionManager collectionManager) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        collection = collectionManager;
    }

    /**
     * Prints objects of collection, sorted ascending
     *
     * @param lever special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) {
        List<Dragon> list = collection.get_list();
        Collections.sort(list);
        for (Dragon d : list) System.out.println(d);
    }
}
