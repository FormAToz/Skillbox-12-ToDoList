package main.controller;

import main.model.ToDoItem;
import main.model.ToDoItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class IndexController {

    @Autowired
    private ToDoItemsRepository toDoItemsRepository;

    @Value("${author}")
    private String author;

    @RequestMapping("/")
    public String index(Model model) {
        var items = new ArrayList<ToDoItem>();
        toDoItemsRepository.findAll().forEach(items::add);
        model.addAttribute("todoitems", items);
        model.addAttribute("author", author);

        return "index";
    }
}
