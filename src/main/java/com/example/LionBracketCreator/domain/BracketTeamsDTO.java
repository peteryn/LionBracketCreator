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
public class BracketTeamsDTO {

    private UUID userId;

    private String teamName;

    private int seed;
}
