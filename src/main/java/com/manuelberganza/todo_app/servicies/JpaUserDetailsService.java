package com.manuelberganza.todo_app.servicies;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manuelberganza.todo_app.entities.User;
import com.manuelberganza.todo_app.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = userRepository.findByUsername(username);

        if (!opt.isPresent()) {
            throw new UsernameNotFoundException("Usuario no registrado");
        }

        User user = opt.get();
        List<GrantedAuthority> authorities = user.getRoles().stream()
                                                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                                                            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), 
            user.getPassword(), 
            user.getStatus(), 
            true, 
            true,
            true, 
            authorities);
    }

}
