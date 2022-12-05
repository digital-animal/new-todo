package com.zahid.todo.models;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Boolean completed;

    @Getter
    @Setter
    private Instant createdDate;

    @Getter
    @Setter
    private Instant lastModifiedDate;

    public Todo() {
    }

    public Todo(String description) {
        this.description = description;
        this.completed = false;
        this.createdDate = Instant.now();
        this.lastModifiedDate = Instant.now();
    }

    @Override
    public String toString() {
        return "TodoItem [id=" + id + ", description=" + description + ", createdDate=" + createdDate
                + ", lastModifiedDate=" + lastModifiedDate + ", completed=" + completed + "]";
    }

}
