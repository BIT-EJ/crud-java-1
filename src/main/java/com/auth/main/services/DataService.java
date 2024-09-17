package com.auth.main.services;

import com.auth.main.entities.User;
import com.auth.main.entities.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataService {
    UserRepository userRepository;

    public DataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }
}
