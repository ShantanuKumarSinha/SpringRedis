package com.shann.springredis.service;

import com.shann.springredis.exceptions.UserNotFoundException;
import com.shann.springredis.model.User;
import com.shann.springredis.repository.UserRepository;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class UserService {

    private UserRepository userRepository;

    private ObjectMapper objectMapper;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public User getUser(Long userId) throws UserNotFoundException {
        return objectMapper.convertValue(userRepository.findById(userId).orElseThrow(UserNotFoundException::new), User.class);
    }
}
