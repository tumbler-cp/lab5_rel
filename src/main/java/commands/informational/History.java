package commands.informational;

import commands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>history</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class History extends Command {
    /**
     * History list
     */
    List<String> list = new ArrayList<>();

    /**
     * Default constructor
     */
    public History() {
        super("history", "вывести последние 14 команд (без их аргументов)");
    }

    /**
     * Adding command name to list. Deletes first added if list size is bigger than 14;
     *
     * @param c - command name
     */
    public void add(String c) {
        this.list.add(c);
        if (list.size() > 14) list.remove(0);
    }

    /**
     * Prints last 14 entered commands.
     *
     * @param lever - special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) {
        int i = 1;
        for (String s : list) {
            System.out.println(i + " " + s);
            i++;
        }
    }
}
