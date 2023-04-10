package commands;

import exceptions.NoSuchOptionException;
import terminal.Terminal;

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Abstract class for all commands
 *
 * @author Abdujalol Khodjaev
 */
public abstract class Command {
    /**
     * Name and arguments of command
     */
    private String descriptionLeft;
    /**
     * Command description
     */
    private String descriptionRight;
    /**
     * Arguments of command
     */
    private String[] args = {};

    /**
     * Default constructor.
     * Exists, because class heirs have their own constructor with different params
     *
     * @see Command#Command(String, String)
     */
    public Command() {
    }

    /**
     * Constructor for name and description assignment
     *
     * @param Description1 name and arguments
     * @param Description2 description
     * @see Command#Command()
     */
    public Command(String Description1, String Description2) {
        this.descriptionLeft = Description1;
        this.descriptionRight = Description2;
    }

    /**
     * Arguments assignment
     *
     * @param args arguments
     */
    public void setArgs(String[] args) {
        this.args = args;
    }

    /**
     * Arguments
     *
     * @return Command current arguments
     */
    public String[] getArgs() {
        return args;
    }

    public void manual() {
        System.out.println(this.descriptionRight);
    }

    /**
     * Method for executing command.
     *
     * @param lever special boolean. If you set it <b>false</b> program will stop working.
     * @see Terminal#run()
     */
    public void execute(AtomicBoolean lever) throws NoSuchOptionException {
    }

    /**
     * Command name and args getter
     *
     * @return name and args of command
     */
    public String getName() {
        return descriptionLeft;
    }

    /**
     * Command description getter
     *
     * @return description of command
     */
    public String getDescription() {
        return descriptionRight;
    }
}
