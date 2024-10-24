package com.manuelberganza.todo_app.servicies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manuelberganza.todo_app.entities.User;
import com.manuelberganza.todo_app.repositories.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> opt = userRepository.findByUsername(username);

        if (opt.isPresent()) {
            return opt.get();
        }

        return null;
    }

}
