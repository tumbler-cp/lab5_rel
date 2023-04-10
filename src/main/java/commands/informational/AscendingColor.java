package commands.informational;

import collection.CollectionManager;
import commands.Command;
import dragon.Dragon;
import dragon.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>print_field_ascending_color</b> command
 *
 * @author Abdujalol Khodjaev
 * @see PrintAscending
 */
public class AscendingColor extends Command {
    /**
     * Collection manager command works with.
     */
    CollectionManager collection;

    public AscendingColor(CollectionManager collectionManager) {
        super("print_field_ascending_color", "вывести значения поля color всех элементов в порядке возрастания");
        this.collection = collectionManager;
    }

    /**
     * Overriding execute
     * Prints collection objects color sorted ascending.
     *
     * @param lever - see in Terminal
     */
    @Override
    public void execute(AtomicBoolean lever) {
        HashMap<Integer, Color> buffer = new HashMap<>();
        for (Dragon d : collection.get_list()) {
            buffer.put(d.getId(), d.getColor());
        }
        buffer.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
    }
}
