package com.zahid.todo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zahid.todo.models.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    // pass
}
