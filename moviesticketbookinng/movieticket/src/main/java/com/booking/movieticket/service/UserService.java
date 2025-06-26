package com.booking.movieticket.service;

import com.booking.movieticket.model.User;
import com.booking.movieticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User newUser) {
        User existing = userRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(newUser.getName());
            existing.setEmail(newUser.getEmail());
            existing.setPassword(newUser.getPassword());
            return userRepository.save(existing);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
