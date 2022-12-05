package com.zahid.todo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zahid.todo.models.Todo;
import com.zahid.todo.repositories.TodoRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoItemRepository;

    public List<Todo> getAllTodoItems() {
        List<Todo> todoItemList = new ArrayList<>();
        todoItemRepository.findAll().forEach(todoItemList::add);
        return todoItemList;
    }

    public Todo getTodoItem(Long id) {
        return todoItemRepository.findById(id).get();
    }

    public void addTodoItem(Todo todoItem) {
        todoItemRepository.save(todoItem);
    }

    public void updateTodoItem(Todo todoItem) {
        Todo t = todoItemRepository.findById(todoItem.getId()).get();
        t = todoItem;
        todoItemRepository.save(t);
    }
}
