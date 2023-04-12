package commands;

import exceptions.EmptyCollectionException;
import terminal.Terminal;
import commands.informational.History;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of execute_script command
 *
 * @author Abdujalol Khodjaev
 * @see file.FileManager
 */
public class ExecuteScript extends Command {
    /**
     * Name of script file
     */
    private String scriptFile = "";
    private final HashSet buffer = new HashSet();
    private int buff = 1;
    public int cycleDepth = 0;
    CommandManager CM;
    /**
     * Terminal for executing commands
     */
    Terminal console;

    /**
     * Default constructor
     *
     * @param terminal - console origin
     */
    public ExecuteScript(Terminal terminal, CommandManager collectionManager) {
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        console = terminal;
        this.CM = collectionManager;
    }

    /**
     * Reads lines from file and execute commands written in the file
     *
     * @param lever - special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) {
        scriptFile = this.getArgs()[0];
        buffer.add(scriptFile);
        if (buffer.contains(scriptFile)) {
            buff++;
        }
        Scanner in;
        try {
            in = new Scanner(new FileReader(scriptFile));
        } catch (FileNotFoundException fnf) {
            System.out.println("Файл не найден: " + scriptFile);
            Terminal.logger.write(fnf);
            return;
        }
        try {
            miniEx(in, lever);
        } catch (EmptyCollectionException e) {
            System.out.println("Чё-то с прогой не так.");
        }
    }

    /**
     * Second part of executing
     *
     * @param in        Scanner of File
     * @param specLever That special boolean for stopping program
     * @throws EmptyCollectionException I don't think this exception will be thrown
     */
    private void miniEx(Scanner in, AtomicBoolean specLever) throws EmptyCollectionException {
        StringBuilder s;
        while (in.hasNextLine()) {
            if (buff > cycleDepth + 1) {
                buff = 1;
                return;
            }
            s = new StringBuilder(in.nextLine());
            String b = "";
            while (in.hasNextLine()) {
                b = in.nextLine();
                if (CM.get_map().containsKey(b)) break;
                s.append(" ").append(b);
            }
            System.out.println(s);
            console.executor(s.toString().split(" "), specLever);
        }
    }

    /**
     * Sets depth of recursive cycle of execute_scripts
     *
     * @param cycleDepth depth
     */
    public void setCycleDepth(int cycleDepth) {
        this.cycleDepth = cycleDepth;
    }
}
