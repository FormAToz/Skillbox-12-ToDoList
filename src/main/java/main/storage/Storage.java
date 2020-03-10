package main.storage;

import main.model.ToDoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {

    private static long currentId = 1;
    private static ConcurrentHashMap<Long, ToDoItem> todoItems = new ConcurrentHashMap<>();

    //список всех дел
    public static List<ToDoItem> list() {

        var items = new ArrayList<ToDoItem>();
        items.addAll(todoItems.values());
        return items;
    }

    //добавление дела
    public static long addItem(ToDoItem toDoItem) {

        var id = currentId++;
        toDoItem.setId(id);
        todoItems.put(id, toDoItem);
        return id;
    }

    //получить дело по id
    public static ToDoItem getItemById(long id) {
        if (todoItems.containsKey(id)) {
            return todoItems.get(id);
        }
        return null;
    }

    //удаляем дело по id
    public static ToDoItem deleteItemById(long id) {
        if (todoItems.containsKey(id)) {
            currentId--;
            return todoItems.remove(id);
        }
        return null;
    }
}
