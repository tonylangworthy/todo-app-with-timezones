package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {

    List<ToDoItem> findAllByUserId(Long userId);
}
