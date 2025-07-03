package com.example.LionBracketCreator.domain.BracketTeams;

import com.example.LionBracketCreator.domain.UserTeamKey;
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

//    @Column(name = "bracket_id")
    private String bracketId;

    private UserTeamKey userTeamKey;
}
