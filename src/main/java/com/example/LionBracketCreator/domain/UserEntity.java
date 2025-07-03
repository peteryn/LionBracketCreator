package com.example.LionBracketCreator.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class UserEntity {

    @Id
    private String id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TeamEntity> teams;

    public void addTeam(TeamEntity teamEntity) {
        this.teams.add(teamEntity);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity")
    private Set<BracketEntity> brackets;

    public void addBracket(BracketEntity bracketEntity) {
        this.brackets.add(bracketEntity);
        bracketEntity.setUserEntity(this);
    }
}
