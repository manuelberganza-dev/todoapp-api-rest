package com.manuelberganza.todo_app.servicies;

import com.manuelberganza.todo_app.entities.TaskCategory;

public interface ITaskCategoryService {
    TaskCategory findById(Integer id);
}
