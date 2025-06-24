package com.example.LionBracketCreator.domain;

import com.example.LionBracketCreator.domain.BracketTeams.BracketTeams;
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
@Table(name="brackets")
public class BracketEntity {

    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "bracket", cascade = CascadeType.ALL)
    private Set<BracketTeams> teams;

    public void addTeam(TeamEntity teamEntity, int seed) {
        BracketTeams bt = new BracketTeams(this, teamEntity, seed);
        this.teams.add(bt);
    }
}
