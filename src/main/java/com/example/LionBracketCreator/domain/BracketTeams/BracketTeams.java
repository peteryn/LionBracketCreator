package com.example.LionBracketCreator.domain.BracketTeams;

import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.domain.TeamEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class BracketTeams {

    public BracketTeams() {}

    public BracketTeams(BracketEntity bracket, TeamEntity team, int seed) {
        this.bracket = bracket;
        this.team = team;
        this.seed = seed;
        this.id = new BracketTeamsKey(bracket.getId(), team.getName());
    }

    @EmbeddedId
    BracketTeamsKey id;

    @ManyToOne
    @MapsId("bracketId")
    @JoinColumn(name = "bracket_id")
    BracketEntity bracket;

    @ManyToOne
    @MapsId("teamName")
    @JoinColumn(name = "team_id")
    TeamEntity team;

    int seed;

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
