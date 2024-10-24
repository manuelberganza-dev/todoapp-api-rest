package com.manuelberganza.todo_app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manuelberganza.todo_app.entities.Task;
import com.manuelberganza.todo_app.entities.User;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findByIdAndUser(Integer id, User user);
}
