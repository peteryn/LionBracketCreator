package com.example.LionBracketCreator.services;

import com.example.LionBracketCreator.repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
