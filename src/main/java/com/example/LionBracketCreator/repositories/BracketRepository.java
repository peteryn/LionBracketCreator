package com.example.LionBracketCreator.repositories;

import com.example.LionBracketCreator.domain.BracketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BracketRepository extends CrudRepository<BracketEntity, String> {
}
