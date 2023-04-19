package commands.collManaging;

import collection.CollectionManager;
import commands.Command;
import dragon.*;
import exceptions.IncorrectFieldException;
import exceptions.KeyNotFoundException;
import exceptions.NoSuchOptionException;
import terminal.Terminal;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>replace_if_greater</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class ReplaceGreater extends Command {
    /**
     * Collection manager this command works with
     */
    CollectionManager collection;

    /**
     * Default constructor
     *
     * @param collectionManager Collection to work with
     */
    public ReplaceGreater(CollectionManager collectionManager) {
        super("replace_if_greater null {element}", "заменить значение по ключу, если новое значение больше старого");
        this.collection = collectionManager;
    }

    /**
     * Manual
     */
    public void manual() {
        System.out.println(
                """
                        Syntax: replace_if_greater <int key> <int var> <String arg>\s
                        Vars:
                        1 - name
                        2 - Coordinates. <int x>/<int y>
                        3 - age
                        4 - color:
                            1)Green
                            2)Red
                            3)Blue
                            4)Yellow
                            5)Brown
                        5 - type:
                            1)Underground
                            2)Fire
                            3)Air
                        6 - character:
                            1)Wise
                            2)Good
                            3)Chaotic_Evil
                        7 - cave. <int Num. of treasure>
                        """
        );
    }

    /**
     * Replace objects' value if new value is greater
     *
     * @param lever Special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) throws NoSuchOptionException {
        Dragon dragon;
        try {
            dragon = collection.getByKey(Integer.parseInt(getArgs()[0]));
        } catch (KeyNotFoundException k) {
            System.out.println("Элемента с таким ключом не существует");
            return;
        }
        System.out.println(dragon);
        if (getArgs().length > 1) {
            miniExecute1(lever, dragon);
            return;
        }
        manual();
        miniExecute2(lever, dragon);
    }


    /**
     * 2nd part of executing
     *
     * @param lever Boolean to stop the program
     * @throws NoSuchOptionException Will be thrown if there are any problems with enums
     */
    private void miniExecute1(AtomicBoolean lever, Dragon dragon) throws NoSuchOptionException {
        String[] args = this.getArgs();
        String message = "Если вы видите это сообщение замена выполнена.";
        System.out.println(Arrays.toString(args));
        switch (args[1]) {
            case "1" -> {
                if (dragon.getName().compareTo(args[2]) > 0) return;
                dragon.setName(args[2]);
                System.out.println(message);
            }
            case "2" -> {
                if (dragon.getCoordinates().compareTo(Coordinates.toCoordinates(args[2])) > 0) return;
                dragon.setCoordinates(Coordinates.toCoordinates(args[2]));
                System.out.println(message);
            }
            case "3" -> {
                if (dragon.getAge() > Integer.parseInt(args[2])) return;
                dragon.setAge(Integer.parseInt(args[2]));
                System.out.println(message);
            }
            case "4" -> {
                if (dragon.getColor().compareTo(Color.toColor(args[2])) > 0) return;
                dragon.setColor(Color.toColor(args[2]));
                System.out.println(message);
            }
            case "5" -> {
                if (dragon.getType().compareTo(DragonType.toDragonType(args[2])) > 0) return;
                dragon.setType(DragonType.toDragonType(args[2]));
                System.out.println(message);
            }
            case "6" -> {
                if (dragon.getCharacter().compareTo(DragonCharacter.parse(args[2])) > 0) return;
                dragon.setCharacter(DragonCharacter.parse(args[2]));
                System.out.println(message);
            }
            case "7" -> {
                try {
                    if (dragon.getCave().compareTo(new DragonCave(Integer.parseInt(args[2]))) > 0) return;
                    dragon.setCave(new DragonCave(Integer.parseInt(args[2])));
                    System.out.println(message);
                } catch (IncorrectFieldException io) {
                    System.out.println("Это значение для DragonCave неверное");
                }
            }
        }
    }

    private void miniExecute2(AtomicBoolean lever, Dragon dragon) {
        Scanner in = new Scanner(System.in);
        String buffer;
        while (true) {
            System.out.print("Выберите поле которое хотите изменить: ");
            buffer = in.next();
            if (Integer.parseInt(buffer) > 7 || Integer.parseInt(buffer) < 1) {
                Terminal.logger.write(new NoSuchOptionException());
                System.out.println("Такой выбор отсутствует!");
                continue;
            }
            break;
        }
        System.out.print("Введите/Выберите значение: ");
        String valBuff = in.next();
        System.out.println(buffer);
        setArgs(new String[]{getArgs()[0], buffer, valBuff.trim()});
        try {
            miniExecute1(lever, dragon);
        } catch (NoSuchOptionException e) {
            System.out.println("Ошибка");
            Terminal.logger.write(e);
        }
    }
}

