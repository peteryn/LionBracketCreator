package com.example.LionBracketCreator.domain.BracketTeams;

import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.domain.TeamEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString(exclude = {"bracket", "team"})
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BracketTeams implements Comparable<BracketTeams> {

    public BracketTeams(BracketEntity bracket, TeamEntity team, int seed) {
        this.bracket = bracket;
        this.team = team;
        this.seed = seed;
        this.id = new BracketTeamsKey(bracket.getId(), team.getId());
    }

    @EmbeddedId
    @EqualsAndHashCode.Include
    BracketTeamsKey id;

    @ManyToOne
    @MapsId("bracketId")
    @JoinColumn(name = "bracket_id")
    BracketEntity bracket;

    @ManyToOne
    @MapsId("userTeamKey")
    @JoinColumn(name = "team_id")
    @JoinColumn(name = "user_Id")
    TeamEntity team;

    int seed;

    @Override
    public int compareTo(BracketTeams o) {
        return this.getSeed() - o.getSeed();
    }
}
