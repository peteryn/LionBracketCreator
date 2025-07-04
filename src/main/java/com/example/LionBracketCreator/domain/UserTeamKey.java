package com.example.LionBracketCreator.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTeamKey implements Serializable {

    private UUID userId;

    private String teamId;
}
