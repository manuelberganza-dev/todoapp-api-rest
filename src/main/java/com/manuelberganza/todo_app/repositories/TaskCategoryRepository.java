package com.manuelberganza.todo_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manuelberganza.todo_app.entities.TaskCategory;

public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Integer> {

}
