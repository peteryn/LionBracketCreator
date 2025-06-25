package com.example.LionBracketCreator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BracketDTO {

    private String id;

    private String name;

    private List<BracketTeamsDTO> teams;
}
