package com.example.LionBracketCreator.mappers.impl;

import com.example.LionBracketCreator.domain.BracketTeams.BracketTeams;
import com.example.LionBracketCreator.domain.BracketTeamsDTO;
import com.example.LionBracketCreator.mappers.OneWayMapper;
import org.springframework.stereotype.Component;

@Component
public class BracketTeamsMapperImpl implements OneWayMapper<BracketTeams, BracketTeamsDTO> {

    @Override
    public BracketTeamsDTO mapTo(BracketTeams bracketTeams) {
        return new BracketTeamsDTO(
                bracketTeams.getId().getUserTeamKey().getUserId(),
                bracketTeams.getTeam().getDisplayName(),
                bracketTeams.getSeed()
        );
    }
}
