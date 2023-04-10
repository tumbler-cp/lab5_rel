package collection;

import dragon.Dragon;
import exceptions.EmptyCollectionException;
import exceptions.IdNotFoundException;
import exceptions.KeyNotFoundException;
import terminal.Terminal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class for managing collection
 *
 * @author Abdujalol Khodjaev
 * @see commands.CommandManager
 */

public class CollectionManager {
    /**
     * Field dragons. Contains Dragon objects
     */
    private HashMap<Integer, Dragon> dragons = new HashMap<>();
    private static int lastKey = 1;
    /**
     * Field initDate. Date of initialization of class
     */
    private final LocalDate initDate;

    /**
     * Default constructor
     */
    public CollectionManager() {
        initDate = LocalDate.now();
    }

    /**
     * Method insert. Put objects to dragons.
     *
     * @param dragon - object
     */
    public boolean insert(Dragon dragon) {
        if (dragon.check()) {
            this.dragons.put(lastKey, dragon);
            lastKey++;
            return true;
        }
        System.out.println("Этот дракон не подходит под условия.");
        return false;
    }

    public boolean key_check(int key) {
        for (int k : this.dragons.keySet()) {
            if (key == k) {
                System.out.println("Дракон с таким ключом уже существует");
                return false;
            }
        }
        return true;
    }

    public boolean insertWithKey(int key, Dragon dragon) {
        if (dragon.check()) {
            this.dragons.put(key, dragon);
            return true;
        }
        System.out.println("Этот дракон не подходит под условия.");
        return false;
    }

    /**
     * Method for getting dragons
     *
     * @return HashMap dragons
     */
    public HashMap<Integer, Dragon> get_collection() throws EmptyCollectionException {
        if (this.dragons.isEmpty()) throw new EmptyCollectionException();
        return this.dragons;
    }

    /**
     * Method for getting dragons values in List
     *
     * @return List<Dragon></>
     */
    public List<Dragon> get_list() {
        return new ArrayList<>(this.dragons.values());
    }

    public Dragon getByKey(int k) throws KeyNotFoundException {
        if (!dragons.containsKey(k)) throw new KeyNotFoundException();
        return this.dragons.get(k);
    }

    public Dragon getById(int id) throws IdNotFoundException {
        for (Dragon d : dragons.values()) {
            if (id == d.getId()) return d;
        }
        throw new IdNotFoundException();
    }

    /**
     * toString Override
     *
     * @return Information about collection
     */
    @Override
    public String toString() {
        return Terminal.YELLOW + "Collection type: " + Terminal.RESET + this.dragons.getClass().toString().split("class java.util.")[1] + "\n" +
                Terminal.YELLOW + "Init. Date: " + Terminal.RESET + this.initDate.toString() + "\n" +
                Terminal.YELLOW + "Object count: " + Terminal.RESET + this.dragons.size() + "\n";
    }
}
