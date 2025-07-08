package com.example.LionBracketCreator.services;

import com.example.LionBracketCreator.domain.TeamDTO;
import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.domain.UserTeamKey;
import com.example.LionBracketCreator.repositories.TeamRepository;
import com.example.LionBracketCreator.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final UserService userService;

    public TeamService(TeamRepository teamRepository, UserService userService) {
        this.teamRepository = teamRepository;
        this.userService = userService;
    }

    public Optional<TeamEntity> findOne(UUID uuid, String teamName) {
        return this.teamRepository.findById(new UserTeamKey(uuid, teamName));
    }

    public void createTeam(TeamEntity teamEntity) {
        var userEntityOptional = this.userService.getLoggedInUser();
        if (userEntityOptional.isEmpty()) {
            return;
        }
        teamEntity.setUserEntity(userEntityOptional.get());

        this.teamRepository.save(teamEntity);
    }
}
