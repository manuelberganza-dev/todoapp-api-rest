package com.manuelberganza.todo_app.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manuelberganza.todo_app.entities.Role;
import com.manuelberganza.todo_app.entities.User;
import com.manuelberganza.todo_app.entities.UserMapper;
import com.manuelberganza.todo_app.repositories.RoleRepository;
import com.manuelberganza.todo_app.servicies.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        
        if (result.hasErrors()) {
            return validation(result);
        }
        
        Optional<Role> optRole = roleRepository.findById(1);
        if (optRole.isPresent()) {
            List<Role> roles = new LinkedList<>();
            roles.add(optRole.get());
            user.setRoles(roles);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(true);

        return ResponseEntity.ok().body(UserMapper.toUserDTO(userService.save(user)));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
