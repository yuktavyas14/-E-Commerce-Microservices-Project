package com.yukta.userservice.Service;


import Dto.LoginRequest;
import com.yukta.userservice.Entity.User;
import com.yukta.userservice.Repository.UserRepositiory;
import com.yukta.userservice.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepositiory userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepositiory userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public List<User> getUser(){
        return userRepo.findAll();
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String login(LoginRequest loginRequest) {
        User user = userRepo.findByUsername(loginRequest.getUsername());

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getUsername(),user.getRole());

    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());

        return userRepo.save(existingUser);

    }

    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepo.deleteById(id);
    }

}
