package com.github.url.urlshortener.service;


import com.github.url.urlshortener.entity.User;
import com.github.url.urlshortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User save(User user) {
     return    userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public void deleteUser(int ... args) {
        for (int arg : args) {
            userRepository.deleteById(arg);
        }
    }
}
