package com.manuelberganza.todo_app.entities;

public class UserDTO {
    private Integer id;
    private String name;
    private String username;
    
    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
