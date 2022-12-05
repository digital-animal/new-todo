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
    private TodoRepository todoRepository;

    public List<Todo> getAllTodoItems() {
        List<Todo> todoItemList = new ArrayList<>();
        todoRepository.findAll().forEach(todoItemList::add);
        return todoItemList;
    }

    public Todo getTodo(Long id) {
        return todoRepository.findById(id).get();
    }

    public void addTodo(Todo todoItem) {
        todoRepository.save(todoItem);
    }

    public void updateTodo(Todo todoItem) {
        Todo t = todoRepository.findById(todoItem.getId()).get();
        t = todoItem;
        todoRepository.save(t);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
