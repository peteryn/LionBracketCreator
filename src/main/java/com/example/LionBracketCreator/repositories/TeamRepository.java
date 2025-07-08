package com.example.LionBracketCreator.repositories;

import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.domain.UserTeamKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamRepository extends CrudRepository<TeamEntity, UserTeamKey> {

    List<TeamEntity> findById_UserId(UUID userId);
}
