package com.example.LionBracketCreator.repositories;

import com.example.LionBracketCreator.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

}
