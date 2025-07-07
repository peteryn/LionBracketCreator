package com.example.LionBracketCreator.repositories;

import com.example.LionBracketCreator.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    Optional<UserEntity> findByProviderAndProviderId(String provider, String ProviderId);

    boolean existsByProviderAndProviderId(String provider, String ProviderId);
}
