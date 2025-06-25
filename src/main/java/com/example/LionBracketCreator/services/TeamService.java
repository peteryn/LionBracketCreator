package com.example.LionBracketCreator.services;

import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Optional<TeamEntity> findOne(String teamName) {
        return this.teamRepository.findById(teamName.toLowerCase());
    }
}
