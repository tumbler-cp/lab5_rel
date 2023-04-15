package commands.collManaging;

import collection.CollectionManager;
import commands.Command;
import dragon.*;
import exceptions.IncorrectFieldException;
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
                    try {
                        if (dragon.getCave().compareTo(new DragonCave(Integer.parseInt(args[2]))) > 0) return;
                        dragon.setCave(new DragonCave(Integer.parseInt(args[2])));
                    } catch (IncorrectFieldException io){
                        System.out.println("Это значение для DragonCave неверное");
                    }
                }
            }
        }
    }
}

