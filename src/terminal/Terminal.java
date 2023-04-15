package terminal;

import commands.CommandManager;
import commands.informational.History;
import exceptions.EmptyCollectionException;
import exceptions.NoSuchOptionException;
import logger.Logger;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Terminal class. Has main loop for command enter and in line filter
 *
 * @author Abdujalol Khodjaev
 */
public class Terminal {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    private final CommandManager commands;
    private final Scanner user_in;
    public static Logger logger = new Logger();
    History history;

    /**
     * Default constructor
     *
     * @param commandManager Command collection
     * @param scanner        System in scanner
     * @param history        History to save history
     */
    public Terminal(CommandManager commandManager, Scanner scanner, History history) {
        this.commands = commandManager;
        this.user_in = scanner;
        this.history = history;
    }

    /**
     * Sets arguments (if it's necessary) and executes command
     *
     * @param line System in next line
     * @param l    lever to stop program
     */
    public void executor(String[] line, AtomicBoolean l) {
        String command;
        try {
            command = line[0];
        } catch (NullPointerException n) {
            l.set(false);
            return;
        }
        String[] args = new String[line.length - 1];
        System.arraycopy(line, 1, args, 0, line.length - 1);
        try {
            for (String command_key : commands.get_map().keySet()) {
                if (command.equals(command_key)) {
                    var c = commands.get_map().get(command_key);
                    if (args.length > 0) c.setArgs(args);
                    c.execute(l);
                    history.add(command);
                    return;
                }
            }
        } catch (EmptyCollectionException e) {
            System.out.println("Коллекция пуста!");
        }
        catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("Вы не ввели все аргументы либо ввели лишние пробелы.");
            logger.write(a);
        } catch (NoSuchOptionException n) {
            System.out.println("Такой опции нет");
            logger.write(n);
        } catch (Exception e) {
            logger.write(e);
        }
        System.out.println("Команда '" + command + "' не найдена.");
    }

    /**
     * Main loop with command and args enter
     */
    public void run() {
        AtomicBoolean lever = new AtomicBoolean();
        lever.set(true);
        System.out.println("Введите help для получения списка команд.\nВведите команду:");
        while (lever.get()) {
            System.out.print(Terminal.GREEN);
            String[] line = null;
            try {
                line = user_in.nextLine().trim().split(" ");
            } catch (IOError r) {
                System.out.println("Программа завершена успешно");
                line = new String[]{"exit"};
                executor(line, lever);
                logger.write(r);
                return;
            }
            catch (NoSuchElementException e) {
                lever.set(false);
            }
            System.out.print(Terminal.RESET);
            executor(line, lever);
        }
        logger.close();
    }

}
