package com.example.LionBracketCreator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BracketTeamsDTO {

    private String team_name;

    private int seed;
}
