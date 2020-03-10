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
    public long addItem(@NotNull @RequestBody ToDoItem item) {
        return Storage.addItem(item);
    }

    @DeleteMapping("/todoitems/{id}")
    public ResponseEntity deleteItemById(@PathVariable long id) {
        var todoItem = Storage.getItemById(id);
        if (todoItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Storage.deleteItemById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/todoitems/{id}")
    public ResponseEntity getItemById(@PathVariable long id) {

        var todoItem = Storage.getItemById(id);
        if (todoItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(todoItem, HttpStatus.OK);
    }
}
