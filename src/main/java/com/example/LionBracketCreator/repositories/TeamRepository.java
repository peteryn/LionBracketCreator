package com.example.LionBracketCreator.repositories;

import com.example.LionBracketCreator.domain.TeamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<TeamEntity, String> {
}
