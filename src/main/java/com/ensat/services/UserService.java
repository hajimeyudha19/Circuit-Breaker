package com.ensat.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ensat.dto.UserList;
import com.ensat.entities.Users;
import com.ensat.repositories.UsersRepository;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users createUser(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Username and password must not be empty");
        }

        Users existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            return null; // User already exists
        }

        Users user = new Users();
        user.setName("user-" + username.toUpperCase());
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("user");
        return userRepository.save(user);
    }

      public List<UserList> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserList dto = new UserList();
                    dto.setId(user.getId());
                    dto.setUsername(user.getUsername());
                    // Set other fields you want to expose
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
