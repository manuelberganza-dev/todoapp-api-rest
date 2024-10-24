package com.manuelberganza.todo_app.servicies;

import java.util.List;

import com.manuelberganza.todo_app.entities.Task;
import com.manuelberganza.todo_app.entities.User;

public interface ITaskService {
    Task save(Task task);
    List<Task> findTasks();
    Task findByIdAndUser(Integer id, User user);
    void deleteTask(Task task);
    Task activateTask(Integer id);
    Task deactivateTask(Integer id);
}
