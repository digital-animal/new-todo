package com.zahid.todo.controllers;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zahid.todo.models.Todo;
import com.zahid.todo.repositories.TodoRepository;
import com.zahid.todo.services.TodoService;

@Controller
public class TodoController {
    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    // @Autowired
    // private TodoItemRepository todoItemRepository; // bad idea

    @Autowired
    private TodoService todoService; // better idea

    @GetMapping("/home") 
    public ModelAndView home() {
        logger.debug("request to GET home");
        ModelAndView modelAndView = new ModelAndView("home");

        modelAndView.addObject("todoItems", todoService.getAllTodoItems());
        
        return modelAndView;
    }


    @PostMapping("/todo/{id}")
    public String updateTodo(@ModelAttribute Todo todoItem, @PathVariable("id") Long id, Model model) {
        // logger.info("Todo Id = {}", id);
        // logger.info("New Description = {}", (String)model.getAttribute("description"));
        logger.info("New Description = {}", todoItem.getDescription());
       
        // TodoItem t = todoItemService.getTodoItem(id);
        Todo t = todoItem;
        // t.setDescription((String)model.getAttribute("description"));
        t.setDescription(todoItem.getDescription());
        // t.setCompleted((Boolean)model.getAttribute("completed"));
        t.setCompleted(todoItem.getCompleted());
        t.setLastModifiedDate(Instant.now());

        todoService.updateTodoItem(t);

        return "edit-done";
    }

}