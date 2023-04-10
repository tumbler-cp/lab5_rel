package commands.collManaging;

import collection.CollectionManager;
import commands.Command;
import dragon.*;
import exceptions.KeyNotFoundException;
import exceptions.NoSuchOptionException;

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
                                1 - name                           | 1 - GREEN       | 1 - \u001B[33mUNDERGROUND\u001B[0m  | 1 - WISE
                                2 - Coordinates. <int x>/<int y>   | 2 - RED         | 2 - \u001B[31mFIRE\u001B[0m         | 2 - GOOD
                                3 - age                            | 3 - BLUE        | 3 - \u001B[32mAIR\u001B[0m          | 3 - CHAOTIC_EVIL
                                4 - color: column 1                | 4 - YELLOW      |                  |
                                5 - type: column 2                 | 5 - BROWN       |                  |
                                6 - character: column 3            |                 |                  |
                                7 - cave. <int Num. of treasure>   |                 |                  |
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
        String[] args = this.getArgs();
        if (this.getArgs()[0].equals("man")) manual();
        else {
            Dragon dragon;
            try {
                dragon = collection.getByKey(Integer.parseInt(args[0]));
            } catch (KeyNotFoundException k) {
                System.out.print("Элемента с таким ключом не существует");
                return;
            }
            switch (args[1]) {
                case "1" -> {
                    if (dragon.getName().compareTo(args[2]) > 0) return;
                    dragon.setName(args[2]);
                }
                case "2" -> {
                    if (dragon.getCoordinates().compareTo(Coordinates.toCoordinates(args[2])) > 0) return;
                    dragon.setCoordinates(Coordinates.toCoordinates(args[2]));
                }
                case "3" -> {
                    if (dragon.getAge() > Integer.parseInt(args[2])) return;
                    dragon.setAge(Integer.parseInt(args[2]));
                }
                case "4" -> {
                    if (dragon.getColor().compareTo(Color.toColor(args[2])) > 0) return;
                    dragon.setColor(Color.toColor(args[2]));
                }
                case "5" -> {
                    if (dragon.getType().compareTo(DragonType.toDragonType(args[2])) > 0) return;
                    dragon.setType(DragonType.toDragonType(args[2]));
                }
                case "6" -> {
                    if (dragon.getCharacter().compareTo(DragonCharacter.parse(args[2])) > 0) return;
                    dragon.setCharacter(DragonCharacter.parse(args[2]));
                }
                case "7" -> {
                    if (dragon.getCave().compareTo(new DragonCave(Integer.parseInt(args[2]))) > 0) return;
                    dragon.setCave(new DragonCave(Integer.parseInt(args[2])));
                }
            }
        }
    }
}

