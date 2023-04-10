package commands.informational;

import collection.CollectionManager;
import commands.Command;
import dragon.Dragon;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class of <b>filter_contains_name</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class FilterName extends Command {
    /**
     * Collection manager this command works with
     */
    CollectionManager collection;

    /**
     * Default constructor
     *
     * @param collectionManager - collection object
     */
    public FilterName(CollectionManager collectionManager) {
        super("filter_contains_name name", "вывести элементы, значение поля name которых содержит заданную подстроку");
        this.collection = collectionManager;
    }

    /**
     * Overriding execute method. Finds matching dragons name and prints that dragons
     *
     * @param lever - special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) {
        String reg = ".*" + this.getArgs()[0] + ".*";
        Pattern pattern = Pattern.compile(reg);
        List<Dragon> list = collection.get_list();
        for (Dragon d : list) {
            Matcher matcher = pattern.matcher(d.getName());
            if (matcher.matches()) System.out.println(d);
        }
    }
}
