package commands.collManaging;

import collection.CollectionManager;
import commands.Command;
import dragon.*;
import exceptions.IdNotFoundException;
import exceptions.IncorrectFieldException;
import exceptions.NoSuchOptionException;
import terminal.Terminal;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>update</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class UpdateId extends Command {
    /**
     * Collection manager this command works with
     */
    CollectionManager collection;

    /**
     * Default constructor
     *
     * @param collectionManager collection to work with
     */
    public UpdateId(CollectionManager collectionManager) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        collection = collectionManager;
    }

    /**
     * Manual
     */
    public void manual() {
        System.out.println("""
                       Syntax: update <int id> <int var> <value>\s
                       Vars:
                               1 - name                           | 1 - GREEN       | 1 - \u001B[33mUNDERGROUND\u001B[0m  | 1 - WISE
                               2 - Coordinates. <int x>/<int y>   | 2 - RED         | 2 - \u001B[31mFIRE\u001B[0m         | 2 - GOOD
                               3 - age                            | 3 - BLUE        | 3 - \u001B[32mAIR\u001B[0m          | 3 - CHAOTIC_EVIL
                               4 - color: column 1                | 4 - YELLOW      |                  |
                               5 - type: column 2                 | 5 - BROWN       |                  |
                               6 - character: column 3            |                 |                  |
                               7 - cave. <int Num. of treasure>   |                 |                  |
                       Чтобы обновить поля сразу в нескольких элементах необходимо вводить элементы/аргументы/значения через запятую без пробелов.
                """);
    }

    /**
     * Second part of execute
     * @param argLine Command params
     */
    private void miniExecute(String[] argLine) {
        int id = Integer.parseInt(argLine[0]);
        Dragon dragon;
        try {
            dragon = collection.getById(id);
        } catch (IdNotFoundException i) {
            System.out.println("Дракона с id " + id + " не существует.");
            return;
        }

        try {
            switch (argLine[1]) {
                case "1" -> dragon.setName(argLine[2]);
                case "2" -> dragon.setCoordinates(Coordinates.toCoordinates(argLine[2]));
                case "3" -> dragon.setAge(Integer.parseInt(argLine[2]));
                case "4" -> dragon.setColor(Color.toColor(argLine[2]));
                case "5" -> dragon.setType(DragonType.toDragonType(argLine[2]));
                case "6" -> dragon.setCharacter(DragonCharacter.parse(argLine[2]));
                case "7" -> dragon.setCave(new DragonCave(Integer.parseInt(argLine[2])));
            }
        } catch (NoSuchOptionException n) {
            System.out.println("Такого выбора нет!");
            Terminal.logger.write(n);
        } catch (IncorrectFieldException n){
            System.out.println("Неправильное значение DragonCave");
            Terminal.logger.write(n);
        }
    }

    /**
     * Update object element value by object id
     *
     * @param lever - special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) {
        String[] argLine = this.getArgs();
        if (argLine[0].equals("man")) manual();
        else {
            String[] arg1 = argLine[0].split(",");
            String[] arg2 = argLine[1].split(",");
            String[] arg3 = argLine[2].split(",");
            if (arg1.length > 0) {
                if (arg1.length != arg2.length) {
                    System.out.println("Количество полей не соответствует количеству элементов");
                    return;
                } else if (arg2.length != arg3.length) {
                    System.out.println("Количество значений не соответствует количеству полей");
                    return;
                }
                for (int i = 0; i < argLine[0].split(",").length; i++) {
                    String[] buff = {arg1[i], arg2[i], arg3[i]};
                    miniExecute(buff);
                }
            } else {
                miniExecute(argLine);
            }
        }
    }
}
