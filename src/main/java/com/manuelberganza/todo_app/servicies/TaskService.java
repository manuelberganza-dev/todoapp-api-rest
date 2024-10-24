package com.manuelberganza.todo_app.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manuelberganza.todo_app.entities.Task;
import com.manuelberganza.todo_app.entities.User;
import com.manuelberganza.todo_app.repositories.TaskRepository;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task findByIdAndUser(Integer id, User user) {
        Optional<Task> opt = taskRepository.findByIdAndUser(id, user);
        if (opt.isPresent()) {
            return opt.get();
        }

        return null;
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public Task activateTask(Integer id) {
        Optional<Task> opt = taskRepository.findById(id);
        if (opt.isPresent()) {
            Task task = opt.get();
            task.setStatus(true);
            return taskRepository.save(task);
        }

        return null;
    }

    @Override
    public Task deactivateTask(Integer id) {
        Optional<Task> opt = taskRepository.findById(id);
        if (opt.isPresent()) {
            Task task = opt.get();
            task.setStatus(false);
            return taskRepository.save(task);
        }

        return null;
    }

}
