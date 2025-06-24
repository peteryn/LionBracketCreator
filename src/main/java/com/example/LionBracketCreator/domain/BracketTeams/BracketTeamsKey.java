package com.example.LionBracketCreator.domain.BracketTeams;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BracketTeamsKey implements Serializable {

    @Column(name = "bracket_id")
    String bracketId;

    @Column(name = "team_name")
    String teamName;
}
