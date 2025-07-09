package com.example.LionBracketCreator.repositories;

import com.example.LionBracketCreator.domain.PlayerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, String> {
}
