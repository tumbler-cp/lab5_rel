import collection.CollectionManager;
import commands.*;
import commands.collManaging.*;
import commands.informational.*;
import file.FileManager;
import logger.Logger;
import terminal.Terminal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CollectionManager collection = new CollectionManager();
        FileManager file;
        try {
            file = new FileManager(args[0], collection);
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("Укажите файл в аргументах программы!");
            Terminal.logger.write(a);
            return;
        }

        CommandManager command_list = new CommandManager() {{
            add("help", new Help(this));
            add("info", new Info(collection));
            add("show", new Show(collection));
            add("exit", new Exit());
            add("clear", new Clear(collection));
            add("save", new Save(file));
            add("insert", new Insert(collection));
            add("print_ascending", new PrintAscending(collection));
            add("remove_key", new RemoveKey(collection));
            add("update", new UpdateId(collection));
            add("remove_lower_key", new RemoveLowerKey(collection));
            add("filter_contains_name", new FilterName(collection));
            add("print_field_ascending_color", new AscendingColor(collection));
            add("replace_if_greater", new ReplaceGreater(collection));
        }};

        History history = new History();
        Terminal terminal = new Terminal(command_list, in, history);
        ExecuteScript ex = new ExecuteScript(terminal, command_list);
        ex.setCycleDepth(50);
        command_list.add("history", history);
        command_list.add("execute_script", ex);
        terminal.run();
    }
}
