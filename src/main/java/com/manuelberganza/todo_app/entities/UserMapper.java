package com.manuelberganza.todo_app.entities;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getUsername());
    }

    public static User toUser(UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getUsername());
    }
}
