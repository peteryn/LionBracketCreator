package com.example.LionBracketCreator.services;

import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.domain.UserEntity;
import com.example.LionBracketCreator.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addTeamToUser(String userId, TeamEntity teamEntity) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(userId);
        if (userEntityOptional.isEmpty()) {
            return;
        }
        var userEntity = userEntityOptional.get();
        userEntity.addTeam(teamEntity);
        this.userRepository.save(userEntity);
    }
}
