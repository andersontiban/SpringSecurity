package com.anderson.security.todos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodosRepository extends JpaRepository<TodosEntity, Long> {
//
//    List<TodosEntity>

}
