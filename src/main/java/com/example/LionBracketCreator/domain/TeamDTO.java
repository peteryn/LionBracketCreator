package com.example.LionBracketCreator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamDTO {

    private String teamId;

    private UUID ownerId;

    private String displayName;

    private String abbreviatedName;

    private String imagePath;

    private String accentColor;

    private String players;
}
