package com.manuelberganza.todo_app.servicies;

import com.manuelberganza.todo_app.entities.User;

public interface IUserService {
    User save(User user);
    User findByUsername(String username);
}
