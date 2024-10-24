package com.manuelberganza.todo_app.servicies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manuelberganza.todo_app.entities.TaskCategory;
import com.manuelberganza.todo_app.repositories.TaskCategoryRepository;

@Service
public class TaskCategoryService implements ITaskCategoryService {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @Override
    public TaskCategory findById(Integer id) {
        Optional<TaskCategory> opt = taskCategoryRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }

        return null;
    }

}
