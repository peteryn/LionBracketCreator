package com.example.LionBracketCreator.services;

import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.domain.UserTeamKey;
import com.example.LionBracketCreator.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final UserService userService;

    @Value("${SUPER_USER_UUID}")
    private UUID superUserId;

    public TeamService(TeamRepository teamRepository, UserService userService) {
        this.teamRepository = teamRepository;
        this.userService = userService;
    }

    public Optional<TeamEntity> findOne(UUID uuid, String teamName) {
        return this.teamRepository.findById(new UserTeamKey(uuid, teamName));
    }

    public List<TeamEntity> findAll(UUID uuid) {
        return this.teamRepository.findById_UserId(uuid);
    }

    public void createTeam(TeamEntity teamEntity) {
        var userEntity = this.userService.getLoggedInUser();
        teamEntity.setUserEntity(userEntity);

        this.teamRepository.save(teamEntity);
    }

    public List<TeamEntity> findAllSuperUserTeams() {
        return this.findAll(superUserId);
    }
}
