package com.example.LionBracketCreator.services;

import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.repositories.BracketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BracketService {

    private BracketRepository bracketRepository;

    public BracketService(BracketRepository bracketRepository) {
        this.bracketRepository = bracketRepository;
    }

    public Optional<BracketEntity> findOne(String bracketId) {
        return bracketRepository.findById(bracketId);
    }
}
