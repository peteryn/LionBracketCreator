package com.example.LionBracketCreator.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.UUID;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class UserEntity {

    public UserEntity(String name, String provider, String providerId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.provider = provider;
        this.providerId = providerId;
        this.teams = new HashSet<>();
        this.brackets = new HashSet<>();
    }

    @Id
    private UUID id;

    private String name;

    private String provider;

    private String providerId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity")
    private Set<TeamEntity> teams;

    public void addTeam(TeamEntity teamEntity) {
        this.teams.add(teamEntity);
        teamEntity.setUserEntity(this);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity")
    private Set<BracketEntity> brackets;

    public void addBracket(BracketEntity bracketEntity) {
        this.brackets.add(bracketEntity);
        bracketEntity.setUserEntity(this);
    }
}
