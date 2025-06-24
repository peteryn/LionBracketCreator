package com.example.LionBracketCreator.domain.BracketTeams;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class BracketTeamsKey implements Serializable {

    @Column(name = "bracket_id")
    String bracketId;

    @Column(name = "team_name")
    String teamName;
}
