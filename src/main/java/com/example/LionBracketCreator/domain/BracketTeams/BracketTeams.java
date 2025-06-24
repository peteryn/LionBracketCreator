package com.example.LionBracketCreator.domain.BracketTeams;

import com.example.LionBracketCreator.domain.BracketEntity;
import com.example.LionBracketCreator.domain.TeamEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BracketTeams {

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
}
