package com.anderson.security.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodosService {

    private final TodosRepository todosRepository;

    @Autowired
    public TodosService(TodosRepository todosRepository) {
        this.todosRepository = todosRepository;
    }

    public List<TodosEntity> getAll() {
        return todosRepository.findAll();
    }

    public void addItem(TodosEntity todo) {
        try {
            todosRepository.save(todo);
        } catch (Exception e) {
            //TODO add a logger
            System.out.println(e);
        }
    }
}
