package com.example.LionBracketCreator.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTeamKey implements Serializable {

    private String userId;

    private String teamId;
}
