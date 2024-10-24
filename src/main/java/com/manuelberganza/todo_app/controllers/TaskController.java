package com.manuelberganza.todo_app.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manuelberganza.todo_app.entities.Task;
import com.manuelberganza.todo_app.servicies.ITaskCategoryService;
import com.manuelberganza.todo_app.servicies.ITaskService;
import com.manuelberganza.todo_app.servicies.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ITaskCategoryService taskCategoryService;

    @GetMapping()
    public ResponseEntity<?> getAllTask() {
        return ResponseEntity.ok().body(taskService.findTasks());
    }
    
    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody Task task, BindingResult result, Authentication authentication) {
        if (result.hasErrors()) {
            return validation(result);
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeNow = now.format(formatter); 
        Timestamp timestamp = Timestamp.valueOf(dateTimeNow);

        task.setCreatedAt(timestamp);
        task.setStatus(true);
        task.setCategory(taskCategoryService.findById(task.getCategory().getId()));
        task.setUser(userService.findByUsername(authentication.getName()));

        return ResponseEntity.ok().body(taskService.save(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable("id") Integer id, Authentication authentication) {
        Task task = taskService.findByIdAndUser(id, userService.findByUsername(authentication.getName()));
        if (task == null) {
            return ResponseEntity.badRequest().body("Tarea no encontrada");
        }

        return ResponseEntity.ok().body(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable("id") Integer id, @Valid @RequestBody Task task, BindingResult result, Authentication authentication) {

        if (result.hasErrors()) {
            return validation(result);
        }

        Task taskDb = taskService.findByIdAndUser(id, userService.findByUsername(authentication.getName()));
        if (taskDb == null) {
            return ResponseEntity.badRequest().body("Tarea no encontrada");
        }

        return ResponseEntity.ok().body(taskService.save(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@Valid @RequestBody Task task, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }

        return ResponseEntity.ok().body("Tarea eliminada con exito!");
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<?> activateTask(@PathVariable("id") Integer id, Authentication authentication) {

        Task taskDb = taskService.findByIdAndUser(id, userService.findByUsername(authentication.getName()));
        if (taskDb == null) {
            return ResponseEntity.badRequest().body("Tarea no encontrada");
        }

        return ResponseEntity.ok().body(taskService.activateTask(id));
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateTask(@PathVariable("id") Integer id, Authentication authentication) {        
        Task taskDb = taskService.findByIdAndUser(id, userService.findByUsername(authentication.getName()));
        if (taskDb == null) {
            return ResponseEntity.badRequest().body("Tarea no encontrada");
        }

        return ResponseEntity.ok().body(taskService.activateTask(id));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
