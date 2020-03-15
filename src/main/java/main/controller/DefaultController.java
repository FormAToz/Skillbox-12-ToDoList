package main.controller;

import main.model.ToDoItem;
import main.model.ToDoItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DefaultController {

    @Autowired
    private ToDoItemsRepository toDoItemsRepository;

    @GetMapping("/todoitems/")
    public List<ToDoItem> list() {

        List<ToDoItem> items = new ArrayList<>();
        toDoItemsRepository.findAll().forEach(items::add);
        return items;
    }

    @PostMapping("/todoitems/")
    public ResponseEntity addItem(@NotNull @RequestBody ToDoItem item) {

        toDoItemsRepository.save(item);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @PutMapping("/todoitems/")
    public ResponseEntity updateItem(@NotNull @RequestBody ToDoItem item) {

        ToDoItem puttedItem = toDoItemsRepository.save(item);
        if (puttedItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(puttedItem);
    }

    @DeleteMapping("/todoitems/{id}")
    public ResponseEntity deleteItemById(@PathVariable long id) {

        Optional<ToDoItem> optionalToDoItem = toDoItemsRepository.findById(id);
        if (!optionalToDoItem.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        var deletedItem = optionalToDoItem.get();
        toDoItemsRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedItem);
    }

    @GetMapping("/todoitems/{id}")
    public ResponseEntity getItemById(@PathVariable long id) {

        Optional<ToDoItem> optionalToDoItem = toDoItemsRepository.findById(id);
        if (!optionalToDoItem.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalToDoItem.get());
    }

    @GetMapping("/search")
    public List<ToDoItem> searchByToDoText(@RequestParam(value = "query") String text) {

        List<ToDoItem> items = new ArrayList<>();
        toDoItemsRepository.findAll().forEach(el -> {
            if (el.getTitle().equals(text)) {
                items.add(el);
            }
        });
        return items;
    }
}
