package com.manuelberganza.todo_app.entities;

public class TaskMapper {
    public static TaskDTO toTaskDTO(Task task) {
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getCreatedAt(), task.getStatus(), task.getCategory(), UserMapper.toUserDTO(task.getUser()));
    }

    public static Task toTask(TaskDTO taskDTO) {
        return new Task(taskDTO.getId(), taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getCreatedAt(), taskDTO.getStatus(), taskDTO.getCategory(), UserMapper.toUser(taskDTO.getUserDTO()));
    }
}
