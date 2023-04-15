package commands.informational;

import commands.Command;
import commands.CommandManager;
import exceptions.EmptyCollectionException;
import terminal.Terminal;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>help</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class Help extends Command {
    /**
     * Command collection manager
     */
    CommandManager commands;

    /**
     * Default constructor
     *
     * @param commandManager Manager where from help gets information about commands
     */
    public Help(CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commands = commandManager;
    }

    /**
     * Prints name, arguments and descriptions of add to command manager commands.
     *
     * @param lever - special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) {
        try {
            this.commands.get_map().values().forEach(command ->
            {
                System.out.println(Terminal.RED + command.getName() + " : " + Terminal.RESET + command.getDescription());
            });
        } catch (EmptyCollectionException e) {
            System.out.println("Команд нет!");
            Terminal.logger.write(e);
        }
    }
}
