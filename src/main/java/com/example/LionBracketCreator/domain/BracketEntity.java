package com.example.LionBracketCreator.domain;

import com.example.LionBracketCreator.domain.BracketTeams.BracketTeams;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString(exclude = "teams")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="brackets")
public class BracketEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String name;

    @OneToMany(mappedBy = "bracket", cascade = CascadeType.ALL)
    private Set<BracketTeams> teams;

    public void addTeam(TeamEntity teamEntity, int seed) {
        BracketTeams bt = new BracketTeams(this, teamEntity, seed);
        this.teams.add(bt);
        teamEntity.getBrackets().add(bt);
    }
}
