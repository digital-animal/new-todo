package com.zahid.todo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zahid.todo.models.Todo;
import com.zahid.todo.repositories.TodoRepository;

@Component
public class TodoSeeder implements CommandLineRunner {
    
    private final Logger logger = LoggerFactory.getLogger(TodoSeeder.class);

    @Autowired
    TodoRepository todoItemRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    public void loadSeedData() {
        if(todoItemRepository.count() == 0) {
            Todo todoItem1 = new Todo("Get the Books");
            Todo todoItem2 = new Todo("Buy the milk");
            Todo todoItem3 = new Todo("Going to Market");
            Todo todoItem4 = new Todo("Making dinner");
            Todo todoItem5 = new Todo("Cooking soup");

            todoItemRepository.save(todoItem1);
            todoItemRepository.save(todoItem2);
            todoItemRepository.save(todoItem3);
            todoItemRepository.save(todoItem4);
            todoItemRepository.save(todoItem5);
        }

        logger.info("Number of todo items: {}", todoItemRepository.count());
    }
    
}
