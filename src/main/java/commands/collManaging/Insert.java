package commands.collManaging;

import collection.CollectionManager;
import commands.Command;
import dragon.*;
import exceptions.NoSuchOptionException;
import terminal.Terminal;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class of <b>insert</b> command
 *
 * @author Abdujalol Khodjaev
 */
public class Insert extends Command {

    /**
     * Collection manager this command works with
     */
    CollectionManager collectionManager;

    /**
     * Default constructor
     *
     * @param collectionManager collection to work with
     */
    public Insert(CollectionManager collectionManager) {
        super("insert null {element}", "добавить новый элемент с заданным ключом. Введите insert man для просмотра инструкции");
        this.collectionManager = collectionManager;
    }

    private void inLoop() {
        int key = Integer.parseInt(getArgs()[0]);
        if (!collectionManager.key_check(key)) {
            return;
        }
        Scanner tempIn = new Scanner(System.in);
        String[] mans = new String[]
                {
                        "Введите имя: ",
                        "Введите координаты(x y): ",
                        "Введите возраст: ",
                        "1.Зеленый\n2.Красный\n3.Синий\n4.Жёлтый\n5.Коричневый\nВыберите цвет: ",
                        "1.Подземный\n2.Воздушный\n3.Огненный\nВыберите тип: ",
                        "1.Мудрый\n2.Хороший\n3.Злой хаотичный\nВыберите характер: ",
                        "Введите число сокровищ в пещере: "
                };
        Dragon d = new Dragon(null, null, 0, null, null, null, null);
        String buff;
        for (int i = 0; i < 7; ++i) {
            System.out.print(mans[i]);
            buff = tempIn.nextLine();
            try {
                switch (i) {
                    case 0 -> d.setName(buff);
                    case 1 -> d.setCoordinates(Coordinates.toCoordinates(buff));
                    case 2 -> d.setAge(Integer.parseInt(buff));
                    case 3 -> d.setColor(Color.toColor(buff));
                    case 4 -> d.setType(DragonType.toDragonType(buff));
                    case 5 -> d.setCharacter(DragonCharacter.parse(buff));
                    case 6 -> d.setCave(new DragonCave(Integer.parseInt(buff)));
                }
            } catch (NoSuchOptionException n) {
                System.out.println("Такой выбор отсутствует!");
                Terminal.logger.write(n);
                i--;
            } catch (NumberFormatException n) {
                System.out.println("Слишком большое число");
                Terminal.logger.write(n);
                i--;
            }
        }
        if (collectionManager.insertWithKey(key, d)) System.out.println("Дракон успешно добавлен!");
        else System.out.println("Ошибка при добавлении!");
    }

    /**
     * @param lever - special boolean. If you set it <b>false</b> program will stop working.
     */
    @Override
    public void execute(AtomicBoolean lever) {
        if (getArgs().length == 0) {
            System.out.println("Введите ключ нового дракона.");
            return;
        }
        inLoop();
    }
}
