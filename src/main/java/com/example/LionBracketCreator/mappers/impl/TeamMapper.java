package com.example.LionBracketCreator.mappers.impl;

import com.example.LionBracketCreator.domain.TeamDTO;
import com.example.LionBracketCreator.domain.TeamEntity;
import com.example.LionBracketCreator.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper implements Mapper<TeamEntity, TeamDTO> {

    private final ModelMapper modelMapper;

    public TeamMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TeamDTO mapTo(TeamEntity teamEntity) {
        return modelMapper.map(teamEntity, TeamDTO.class);
    }

    @Override
    public TeamEntity mapFrom(TeamDTO teamDTO) {
        return modelMapper.map(teamDTO, TeamEntity.class);
    }
}
