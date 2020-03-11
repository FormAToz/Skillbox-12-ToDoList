package main.controller;

import main.model.ToDoItem;
import main.storage.Storage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class DefaultController {

    @GetMapping("/todoitems/")
    public List<ToDoItem> list() {
        return Storage.list();
    }

    @PostMapping("/todoitems/")
    public ResponseEntity addItem(@NotNull @RequestBody ToDoItem item) {
        Storage.addItem(item);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @PutMapping("/todoitems/")
    public ResponseEntity putItemById(@NotNull @RequestBody ToDoItem item) {
        ToDoItem puttedItem = Storage.putItemById(item);
        if (puttedItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(puttedItem);
    }

    @DeleteMapping("/todoitems/{id}")
    public ResponseEntity deleteItemById(@PathVariable long id) {
        var todoItem = Storage.getItemById(id);
        if (todoItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ToDoItem deletedToDo = Storage.deleteItemById(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedToDo);
    }

    @GetMapping("/todoitems/{id}")
    public ResponseEntity getItemById(@PathVariable long id) {

        var todoItem = Storage.getItemById(id);
        if (todoItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(todoItem, HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<ToDoItem> searchByToDoText(@RequestParam(value = "query") String text) {
        return Storage.searchByToDoText(text);
    }
}
