package com.example.LionBracketCreator.services;

import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.repositories.BracketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BracketService {

    private final BracketRepository bracketRepository;

    private final UserService userService;

    public BracketService(BracketRepository bracketRepository, UserService userService) {
        this.bracketRepository = bracketRepository;
        this.userService = userService;
    }

    public Optional<BracketEntity> findOne(String bracketId) {
        return bracketRepository.findById(bracketId);
    }

    public void createBracket(BracketEntity bracketEntity) {
        // TODO: handle duplicates
        var user = userService.getLoggedInUser();
        bracketEntity.setUserEntity(user);
        this.bracketRepository.save(bracketEntity);
    }
}
