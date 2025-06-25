package com.example.LionBracketCreator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamDTO {

    private String name;

    private String abbreviatedName;

    private String imagePath;

    private String accentColor;

    private String players;
}
