package com.pbo.latres.service;

import com.pbo.latres.dto.InsertTodoDTO;
import com.pbo.latres.model.TodoRepository;
import com.pbo.latres.model.TodoTask;

import java.util.List;

public class TodoService {

    private final TodoRepository repository;

    public TodoService(
        TodoRepository repository
    ) {
        this.repository = repository;
    }

    public List<TodoTask> getAllTodos() {
        return repository.getAll();
    }

    public TodoTask getTodoById(int id) {
        return repository.getById(id);
    }

    public Boolean addTodo(
        InsertTodoDTO dto
    ) {
        return repository.insert(dto);
    }

    public Boolean updateTodo(
        TodoTask task
    ) {
        return repository.update(task);
    }

    public Boolean deleteTodo(int id) {
        return repository.deleteById(id);
    }
}
