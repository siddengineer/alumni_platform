package com.college.platform.alumni_platform.service;

import com.college.platform.alumni_platform.entity.User;
import com.college.platform.alumni_platform.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        System.out.println("Fetching user from DB...");
        return userRepository.findById(id).orElse(null);
    }
}