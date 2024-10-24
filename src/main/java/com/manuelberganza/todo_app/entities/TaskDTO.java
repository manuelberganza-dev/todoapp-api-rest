package com.manuelberganza.todo_app.entities;

import java.util.Date;

public class TaskDTO {
    private Integer id;
    private String title;
    private String description;
    private Date createdAt;
    private Boolean status;
    private TaskCategory category;
    private UserDTO userDTO;

    public TaskDTO() {
    }

    public TaskDTO(Integer id, String title, String description, Date createdAt, Boolean status, TaskCategory category, UserDTO userDTO) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.status = status;
        this.category = category;
        this.userDTO = userDTO;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public TaskCategory getCategory() {
        return category;
    }
    public void setCategory(TaskCategory category) {
        this.category = category;
    }
    public UserDTO getUserDTO() {
        return userDTO;
    }
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    
}
