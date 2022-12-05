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
import com.zahid.todo.services.TodoService;

@Controller
public class TodoController {
    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService; // better idea

    @GetMapping("/todo/all")
    public ModelAndView getAllTodo() {
        logger.debug("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("todoItems", todoService.getAllTodoItems());

        return modelAndView;
    }

    @GetMapping("/todo/{id}")
    public ModelAndView getOneTodo(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit-form");

        modelAndView.addObject("todo", todoService.getTodo(id));

        return modelAndView;
    }

    @PostMapping("/todo/{id}")
    public String updateTodo(@ModelAttribute Todo todoItem, @PathVariable("id") Long id, Model model) {

        logger.info("New Description = {}", todoItem.getDescription());

        Todo t = todoService.getTodo(id);

        t.setDescription(todoItem.getDescription());
        t.setCompleted(todoItem.getCompleted());
        t.setLastModifiedDate(Instant.now());

        todoService.updateTodo(t);

        return "edit-done";
    }

    @GetMapping("/todo/add")
    public ModelAndView createTodoForm(Model model) {
        ModelAndView modelAndView = new ModelAndView("create-form");
        Todo todo = new Todo();
        modelAndView.addObject("todo", todo);

        return modelAndView;
    }

    @PostMapping("/todo/add")
    public String createTodo(@ModelAttribute Todo todoItem, Model model) {
        logger.info("Todo = {}", todoItem);

        Todo t = new Todo(todoItem.getDescription());
        todoService.addTodo(t);

        return "create-done";
    }

    @GetMapping("/todo/delete/{id}")
    public ModelAndView deletePrompt(@PathVariable("id") Long id) {
        logger.info("Todo Id = {}", id);
        ModelAndView modelAndView = new ModelAndView("delete-confirm");
        modelAndView.addObject("todo", todoService.getTodo(id));

        return modelAndView;
    }

    @PostMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable("id") Long id) {
        logger.info("Todo Id = {}", id);

        todoService.deleteTodo(id);

        return "redirect:/todo/all";
    }

}