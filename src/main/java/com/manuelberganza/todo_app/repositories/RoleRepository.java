package com.manuelberganza.todo_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manuelberganza.todo_app.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
